package com.drsoft.employee.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ERROR_VALIDATIONS(4000L, "Error in validation."),
    ERROR_AUTHENTICATION(4001L, "Error in authentication."),
    ERROR_REGISTERATION(4002L, "Error in registration."),
    ERROR_ACCOUNT_LOCKED(4003L, "Account is locked."),
    ERROR_ENTITY_NOT_FOUND(4004L, "Entity not found."),
    ERROR_INCORRECT_PASSWORD(4005L,"Incorrect Password."),
    ERROR_ALREADY_EXIST(4006L,"Request is alreay exist."),
    ERROR_DATEFORMAT(4007L,"DateFormat is not correct."),
    ERROR_ANSWER_NOT_TRUE(4008L,"Your answer does not true."),
    ERROR_PASSWORD_CANNOT_VALID(4009L,"Passwod cannot valid."),
    ERROR_BAD_REQUEST(4010L,"Bad Request."),
    ERROR_PENDING_ACC_REQUEST(4011L,"Account is pending now."),
    
    ERROR_SERVER_ERR(5000L,"Internal Server Error."),
    ERROR_DATA_EMPTY(5001L,"Data is empty now."),
    ERROR_AMOUNT_IMSUFFICENT(5002L,"Your balance is insufficient now."),
    ERROR_EXCEED_LIMIT(5003L,"Transaction amount is exceed limit."),
    ERROR_NO_TRANSACTION(5004L,"There is no transaction."),
    
    ERROR_SENDING_SMS(6000L, "Error in sending sms."),
    ERROR_SENDING_EMAIL(6001L, "Error in sending email."),
    ERROR_INVALID_DATE_FORMAT(6002L, "Invalid date time format."),
    ERROR_EXPIRED_TOKEN(6003L, "Token expired."),
    ERROR_INVALID_TOKEN(6004L, "Invalid token."),
    ERROR_ACCESS_DENIED(6005L, "Access denied."),
    ERROR_OTP_EXPIRED(6006L, "OTP Expired."),
    ERROR_INVALID_OTP(6007L, "Invalid OTP."),
    ERROR_INVALID_PAGE_NUMBER(6008L, "Page number must start from 1."),
    ERROR_INVALID_PAGE_LIMIT(6009L, "Page limit must be between 1 and 30."),
    ERROR_INVALID_ENUM(6010L, "Invalid enum code."),
	
	ERROR_IMAGE_FILE(7000L,"Image file is exceed 1MB"),
	
    ERROR_QR_TOKEN(8000L,"QR Token has some error."),
    ERROR_QR_TOKEN_NOT_MATCH(8001L,"QR Token and UserId does not match."),
    ERROR_QR_TOKEN_EXP(8002L,"QR Token Expired now");

    private final Long code;
    private final String description;

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
