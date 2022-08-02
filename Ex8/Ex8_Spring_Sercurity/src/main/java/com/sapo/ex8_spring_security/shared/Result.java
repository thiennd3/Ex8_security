package com.sapo.ex8_spring_security.shared;

public class Result<T>{
    public String action;
    public String message;
    private T object;

    public Result(String message, T object) {
        this.message = message;
        this.object = object;
    }

    public Result(String action, String message, T object) {
        this.action = action;
        this.message = message;
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
