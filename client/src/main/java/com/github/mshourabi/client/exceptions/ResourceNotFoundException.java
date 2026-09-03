package com.github.mshourabi.client.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String propertyName;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String propertyName, String message) {
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
