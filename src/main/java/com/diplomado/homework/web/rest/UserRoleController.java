package com.diplomado.homework.web.rest;

import com.diplomado.homework.dto.UserRoleDTO;
import com.diplomado.homework.services.UserRoleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/userRoles")
public final class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>>getUserRoles() {
        return ResponseEntity.ok().body(userRoleService.getUserRoles());
    }
    @GetMapping(path = "/role/{roleId}")
    public ResponseEntity<List<UserRoleDTO>>getUsersByRole(@PathVariable Integer roleId) {
        return ResponseEntity.ok().body(userRoleService.getUsersByRole(roleId));
    }
    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<UserRoleDTO>>getRolesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userRoleService.getRolesByUser(userId));
    }
    @PostMapping
    public ResponseEntity<List<UserRoleDTO>> createUserRole(@RequestBody UserRoleDTO... userRoles) throws URISyntaxException {
        for (UserRoleDTO userRole: userRoles) {
            userRole.setCreatedAt(LocalDateTime.now());
        }
        List<UserRoleDTO> newUserRoles = userRoleService.createUserRoles(userRoles);
        if (!newUserRoles.isEmpty()) {
            return ResponseEntity.created(new URI("/v1/userRoles")).body(newUserRoles);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Integer userRoleId) {
        if (userRoleService.getUserRole(userRoleId).isPresent()) {
            userRoleService.deleteUserRole(userRoleId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "{userRoleId}", method = RequestMethod.PATCH)
    public ResponseEntity<UserRoleDTO> setInactive(@PathVariable Integer userRoleId) {
        Optional<UserRoleDTO> optionalUserRole = userRoleService.getUserRole(userRoleId);

        if (optionalUserRole.isPresent()) {
            UserRoleDTO userRole = userRoleService.setInactive(userRoleId);
            return ResponseEntity.ok(userRole);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}