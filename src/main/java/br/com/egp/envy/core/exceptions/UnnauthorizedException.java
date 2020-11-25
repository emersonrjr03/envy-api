package br.com.egp.envy.core.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundEntityException extends BusinessException {
    public NotFoundEntityException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
