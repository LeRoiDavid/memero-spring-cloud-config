package com.mourchidtech.cloudconfigsever.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ConstraintViolationError extends ApiSubError {

    private String property;

    private Object value;

    private List<String> messages;
}
