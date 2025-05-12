package com.personal.smartcity.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(2001,"Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    PARSE_EXCEPTION(2002, "format error", HttpStatus.INTERNAL_SERVER_ERROR),
    HOTEL_EXISTS(1001, "Hotel already exists", HttpStatus.CONFLICT),
    HOTEL_NOTFOUND(1002, "Hotel not found", HttpStatus.NOT_FOUND),
    PHONE_NUMBER_EXISTS(1003, "Phone number already exists", HttpStatus.CONFLICT),
    EMAIL_EXISTS(1004, "Email already exists", HttpStatus.CONFLICT),
    INVALID_PHONE_NUMBER(1005, "Phone number must be number", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1006, "Email is wrong format", HttpStatus.BAD_REQUEST),
    INVALIDATION_KEY(1100, "Wrong validation key", HttpStatus.BAD_REQUEST),
    ;
    int code;
    String message;
    HttpStatus httpStatusCode;
}
