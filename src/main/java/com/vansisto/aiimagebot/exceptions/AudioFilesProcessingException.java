package com.vansisto.aiimagebot.exceptions;

public class AudioFilesProcessingException extends RuntimeException {
    public AudioFilesProcessingException() {
        super("An error occurred during audio processing");
    }
}
