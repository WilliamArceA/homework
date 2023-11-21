package com.diplomado.homework.web.exceptions;

public class DetailsByUserIdNotFoundException extends RuntimeException {
    public DetailsByUserIdNotFoundException(Long userId) {
        super("There's no details for a user with this id: " + userId);
    }
}
