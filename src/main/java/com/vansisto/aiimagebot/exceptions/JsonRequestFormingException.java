package com.vansisto.aiimagebot.exceptions;

public class JsonRequestFormingException extends RuntimeException {
    public JsonRequestFormingException() {
        super("An error occurred during forming json for image generating");
    }
}
