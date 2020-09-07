package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private int status = 200;
    private String message = "ok";
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result () {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void putData (String key, Object value) {
        this.data.put(key, value);
    }

    public Result ok () {
        this.status = 200;
        this.message = "ok";
        return this;
    }

    public Result fail () {
        this.status = -1;
        this.message = "fail";
        return this;
    }

    public Result fail (String failMessage) {
        this.status = -1;
        this.message = failMessage;
        return this;
    }
}
