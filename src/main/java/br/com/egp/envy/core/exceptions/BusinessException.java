package br.com.egp.envy.core.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {
    private static final long serialVersionUID = -123124343493493L;
    final HttpStatus status;

    public BusinessException(String msg) {
        super(msg);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
