package com.example.Games.exception;


public class GenericRequestParameterErrorResponse {
    String paramName;

    String description;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
