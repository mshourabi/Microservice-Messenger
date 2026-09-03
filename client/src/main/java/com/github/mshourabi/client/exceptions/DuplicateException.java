package com.github.mshourabi.client.exceptions;

public class DuplicateException extends RuntimeException {
    private String propertyName;

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String propertyName, String message) {
        super(message);
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
