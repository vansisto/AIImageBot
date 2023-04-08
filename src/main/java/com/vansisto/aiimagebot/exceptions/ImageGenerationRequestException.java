package com.vansisto.aiimagebot.exceptions;

public class ImageGenerationRequestException extends RuntimeException {
    public ImageGenerationRequestException() {
        super("An error occurred during image generating request");
    }
}
