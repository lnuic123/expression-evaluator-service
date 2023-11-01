package com.exercise.expressionevaluator.domain;

public class ServiceResponseWrapper<T> {
    private ResponseStatus responseStatus;
    private String responseMessage;
    private T response;

    public ServiceResponseWrapper() {
        this.responseStatus = ResponseStatus.OK;
        this.responseMessage = "Success";
    }

    public ServiceResponseWrapper(T response) {
        this.responseStatus = ResponseStatus.OK;
        this.responseMessage = "Success";
        this.response = response;
    }

    public ServiceResponseWrapper(ResponseStatus responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

    public ServiceResponseWrapper(ResponseStatus responseStatus, String responseMessage, T response) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.response = response;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
