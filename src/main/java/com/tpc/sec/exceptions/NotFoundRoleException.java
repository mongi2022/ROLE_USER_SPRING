package com.tpc.sec.exceptions;

public class NotFoundRoleException extends RuntimeException{
   public NotFoundRoleException(String message){
        super(message);
    }
}
