package br.com.egp.envy.core.exceptions;

import org.springframework.http.HttpStatus;

public class UnnauthorizedException extends BusinessException {
    public UnnauthorizedException(String msg) {
        super(msg, HttpStatus.FORBIDDEN);
    }
}
