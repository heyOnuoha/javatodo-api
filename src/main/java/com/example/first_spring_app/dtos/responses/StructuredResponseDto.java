package com.example.first_spring_app.dtos.responses;

public class StructuredResponseDto <T> {
    private Boolean success;
    private String message;
    private T payload;

    public StructuredResponseDto(Boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public static <T> StructuredResponseDto<T> success(String message, T payload) {
        return new StructuredResponseDto<T>(true, message, payload);
    }

    public static <T> StructuredResponseDto<T> error(String message, T payload) {
        return new StructuredResponseDto<T>(false, message, payload);
    }
}
