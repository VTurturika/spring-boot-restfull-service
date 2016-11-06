package com.example.models;

public class Response {

    private String summary;
    private Object result;

    public Response(String summary, Object result) {
        this.summary = summary;
        this.result = result;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
