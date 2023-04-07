package com.vansisto.aiimagebot.services.openai.requests.transcript;

import com.vansisto.aiimagebot.services.settings.UsersCache;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class Transcription {
    private static final MediaType MEDIA_TYPE_MP3 = MediaType.parse("audio/mp3");

    private final UsersCache usersCache;

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
