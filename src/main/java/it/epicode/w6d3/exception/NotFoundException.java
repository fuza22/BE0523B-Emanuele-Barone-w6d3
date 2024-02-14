package it.epicode.w6d3.exception;

public class NotFoundException extends Exception{

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
