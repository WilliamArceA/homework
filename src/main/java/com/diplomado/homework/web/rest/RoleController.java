package com.diplomado.homework.web.rest;

import com.diplomado.homework.dto.RoleDTO;
import com.diplomado.homework.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diplomado.homework.web.exceptions.RoleByIdNotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/roles")
public final class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping
    public ResponseEntity<List<RoleDTO>> readRoles() {
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO role) throws URISyntaxException {
        if (role.getId() != null) {
            throw new IllegalArgumentException("A new role can't have an id " + role.getId());
        }
        final RoleDTO newRole = roleService.createRole(role);
        return ResponseEntity.created(new URI("/v1/roles" + newRole.getId())).body(newRole);
    }
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> readRole(@PathVariable Integer roleId) {
        RoleDTO role = roleService.getRole(roleId).orElseThrow(() -> new RoleByIdNotFoundException(roleId));
        return ResponseEntity.ok().body(role);
    }
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer roleId){
        if (roleService.getRole(roleId).isPresent()) {
            roleService.deleteRole(roleId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{roleId}")
    public  ResponseEntity<RoleDTO> updateRole(@PathVariable Integer roleId, @RequestBody RoleDTO role){
        if (roleId == null || role.getId() == null || !roleId.equals(role.getId())) {
            throw new IllegalArgumentException("Path variable id and user id in the request body must match");
        }
        Optional<RoleDTO> existingRole = roleService.getRole(roleId);
        if (existingRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roleService.createRole(role));
    }
}