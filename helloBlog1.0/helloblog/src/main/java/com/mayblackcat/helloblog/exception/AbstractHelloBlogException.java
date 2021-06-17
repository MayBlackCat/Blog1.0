package com.mayblackcat.helloblog.exception;

import org.jetbrains.annotations.NotNull;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public abstract class AbstractHelloBlogException extends RuntimeException{
    //错误信息
    private Object errorMessage;

    public AbstractHelloBlogException(String message) {
        super(message);
    }

    public AbstractHelloBlogException(String message, Throwable cause, Object errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    //错误状态码
    @NonNull
    public abstract HttpStatus getStatus();

    @NotNull
    public Object getErrorMessage(){return errorMessage;}

    @NonNull
    public AbstractHelloBlogException setErrorData(@Nullable Object errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }




}
