package com.drsoft.employee.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private Long code;
    private String message;
    private T payload;

    public Response(Long code, String message, T payload) {
        this.message = message;
        this.code = code;
        this.payload = payload;
    }

    public Response(Long code, String message) {
        this.message = message;
        this.code = code;
    }

    public static <T> Response<T> createSuccessResponse(Long code, String message, T payload) {
        return new Response(code, message, payload);
    }

    public static <T> Response<T> createSuccessResponse(Long code, String message) {
        return new Response(code, message);
    }

    public static <T> Response<T> createErrorResponse(Long code, String message) {
        return new Response(code, message);
    }
}

