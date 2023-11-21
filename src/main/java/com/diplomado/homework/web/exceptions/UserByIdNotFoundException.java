package com.diplomado.homework.web.exceptions;

public class UserByIdNotFoundException extends RuntimeException {
    public UserByIdNotFoundException(Long userId) {

        super("There's no users with this id: " + userId);
    }
}
