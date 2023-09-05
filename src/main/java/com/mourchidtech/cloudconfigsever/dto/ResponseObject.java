package com.mourchidtech.cloudconfigsever.dto;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    private String message;

    private String error;

    private int status;

    private String timestamp;

}
