package ru.systems.demo.exception;

public class InvalidException extends RuntimeException {

    public InvalidException(String message) {
        super(message);
    }
}