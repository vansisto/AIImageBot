package com.vansisto.aiimagebot.exceptions;

public class JsonContentResponseException extends RuntimeException {
    public JsonContentResponseException() {
        super("An error occurred during parsing response json");
    }
}
