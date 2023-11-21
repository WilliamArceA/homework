package com.diplomado.homework.web.exceptions;

public class RoleByIdNotFoundException extends RuntimeException {
    public RoleByIdNotFoundException(Integer roleId) {

        super("There's no roles with this id: " + roleId);
    }
}
