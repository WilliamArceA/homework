package com.diplomado.homework.web.rest;

import com.diplomado.homework.dto.UserDTO;
import com.diplomado.homework.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diplomado.homework.web.exceptions.UserByIdNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public final class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> readUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) throws URISyntaxException {
        if (user.getId() != null) {
            throw new IllegalArgumentException("A new user can't have an id " + user.getId());
        }
        user.setCreatedAt(LocalDateTime.now());
        System.out.println("aaaaa");
        final UserDTO newUser = userService.createUser(user);
        System.out.println("bbbbb");
        return ResponseEntity.created(new URI("/v1/users" + newUser.getId())).body(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> readUser(@PathVariable Long userId) {
        UserDTO user = userService.getUser(userId).orElseThrow(() -> new UserByIdNotFoundException(userId));
        return ResponseEntity.ok().body(user);
    }


    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        if (userService.getUser(userId).isPresent()) {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public  ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO user){
        if (userId == null || user.getId() == null || !userId.equals(user.getId())) {
            throw new IllegalArgumentException("Path variable id and user id in the request body must match");
        }
        Optional<UserDTO> existingUser = userService.getUser(userId);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(userService.createUser(user));
    }
}
