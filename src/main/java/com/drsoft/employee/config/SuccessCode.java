package com.drsoft.employee.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessCode {
    SUCCESS(1000L, "Successful."),
    PENDING_REQUEST(1001L, "Pending request."),
	REQUEST_NEED_PASSWORD(1002L,"Request is pending and need to add password");

    private final Long code;
    private final String description;

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
