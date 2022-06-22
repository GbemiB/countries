package com.gbemisola.countries.domain;

public enum ResponseField {
    SUCCESS("000", "SUCCESS"),
    INTERNAL_SERVER_ERROR("101", "INTERNAL SERVER ERROR"),
    NOT_FOUND("102", "COUNTRY NOT FOUND"),
    DUPLICATE_RECORD("102", "DUPLICATE COUNTRY NAME");

    private final String code;
    private String message;

    ResponseField(String code) {
        this.code = code;
        this.message = "";
    }

    ResponseField(String code, String message) {
        this(code);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
