package com.gbemisola.countries.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private HttpStatus httpStatus;
    private String responseCode;
    private String responseMessage;
    private List responseContent;
    private Object responseObject;
}
