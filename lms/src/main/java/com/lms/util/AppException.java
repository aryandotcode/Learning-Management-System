package com.lms.util;

/**
 * Simple runtime exception wrapper for application errors.
 */
public class AppException extends RuntimeException {
    public AppException(String message) { super(message); }
    public AppException(String message, Throwable cause) { super(message, cause); }
}
