package br.com.train.springkeycloack.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class LoginServiceException extends RuntimeException {

    public LoginServiceException(String s, Exception e) {
        super(s, e);
    }

    public LoginServiceException(String s) {
        super(s);
    }
}
