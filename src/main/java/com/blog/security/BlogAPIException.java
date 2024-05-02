package com.blog.security;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends Exception {
    public BlogAPIException(HttpStatus httpStatus, String invalidJwtSignature) {
    }
}
