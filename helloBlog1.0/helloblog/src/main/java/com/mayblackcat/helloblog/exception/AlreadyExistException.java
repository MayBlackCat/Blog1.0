package com.mayblackcat.helloblog.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyExistException extends AbstractHelloBlogException{
    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause, Object errorMessage) {
        super(message, cause, errorMessage);
    }

    @Override
    public @NonNull HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
