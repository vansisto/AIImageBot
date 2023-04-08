package com.vansisto.aiimagebot.services.openai.requests;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ImageGenerationRequestUtil {

    private ImageGenerationRequestUtil() {}

    public static Request createRequest(String token, String prompt, int numberOfImages, String size) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        String jsonBody = String.format("{\"prompt\": \"%s\", \"n\": %d, \"size\": \"%s\"}", prompt, numberOfImages, size);
        RequestBody requestBody = RequestBody.create(json, jsonBody);

        return new Request.Builder()
                .url("https://api.openai.com/v1/images/generations")
                .post(requestBody)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .build();
    }
}
