package com.vansisto.aiimagebot.exceptions;

public class TranscriptionRequestException extends RuntimeException {
    public TranscriptionRequestException() {
        super("Something went wrong during transcription request");
    }
}
