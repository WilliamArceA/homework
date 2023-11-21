package com.diplomado.homework.web.rest;

import com.diplomado.homework.dto.UserDTO;
import com.diplomado.homework.dto.UserDetailDTO;
import com.diplomado.homework.services.UserDetailService;
import com.diplomado.homework.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/userDetail")
public final class UserDetailController {
    private final UserDetailService userDetailService;
    private final UserService userService;

    public UserDetailController(UserDetailService userDetailService, UserService userService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDetailDTO>> readUserDetails() {
        return ResponseEntity.ok().body(userDetailService.getUserDetails());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDTO> getDetailByUser(@PathVariable Long userId) {
        return userDetailService.getUserDetail(userId)
                .map(userDetail -> ResponseEntity.ok().body(userDetail))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDetailDTO> createUserDetail(@RequestParam(required = false, defaultValue = "false") boolean createUser, @RequestBody UserDetailDTO userDetail) throws URISyntaxException {
        final UserDTO user = userDetail.getUser();
        if (createUser) {
            user.setCreatedAt(LocalDateTime.now());
            UserDTO createdUser = userService.createUser(user);
            userDetail.getUser().setId(createdUser.getId());
        }
        if (userDetailService.getUserDetail(user.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        final UserDetailDTO newUserDetail = userDetailService.createUserDetail(userDetail);
        return ResponseEntity.created(new URI("v1/userDetail" + newUserDetail.getId())).body(newUserDetail);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserDetail(@PathVariable Long userId) {
        if (userDetailService.getUserDetail(userId).isPresent()) {
            userDetailService.deleteUserDetail(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailDTO> updateUserDetail(@PathVariable Long userId, @RequestBody UserDetailDTO userDetail) {
        validateUserIdAndUserDetail(userId, userDetail);

        Optional<UserDetailDTO> existingUserDetail = userDetailService.getUserDetail(userId);
        if (existingUserDetail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        validateUserDetailIdMatching(existingUserDetail.get(), userDetail);

        return ResponseEntity.ok().body(userDetailService.createUserDetail(userDetail));
    }

    private void validateUserIdAndUserDetail(Long userId, UserDetailDTO userDetail) {
        if (userId == null || userDetail.getId() == null ||
                userDetail.getUser().getId() == null ||
                !userId.equals(userDetail.getUser().getId())) {
            throw new IllegalArgumentException("Path variable id and user id in the request body must match");
        }
    }

    private void validateUserDetailIdMatching(UserDetailDTO existingUserDetail, UserDetailDTO userDetail) {
        if (!userDetail.getId().equals(existingUserDetail.getId())) {
            throw new IllegalArgumentException("Path variable id and user id in the request body must match");
        }
    }

}