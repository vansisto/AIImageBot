package com.vansisto.aiimagebot.services.openai.requests;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;

public class TranscriptionRequestUtil {

    private TranscriptionRequestUtil() {}

    private static final MediaType MEDIA_TYPE_MP3 = MediaType.parse("audio/mp3");

    public static Request createRequest(String token, File file) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(file, MEDIA_TYPE_MP3))
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("response_format", "text")
                .build();

        return new Request.Builder()
                .url("https://api.openai.com/v1/audio/translations")
                .post(requestBody)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "multipart/form-data")
                .build();

    }
}
