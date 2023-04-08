package com.vansisto.aiimagebot.exceptions;

public class TranslationRequestException extends RuntimeException {
    public TranslationRequestException() {
        super("An error occurred during translation request");
    }
}
