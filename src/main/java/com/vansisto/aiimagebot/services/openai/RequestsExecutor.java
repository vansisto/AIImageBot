package com.vansisto.aiimagebot.services.openai;

import com.vansisto.aiimagebot.services.openai.requests.transcript.Transcription;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Slf4j
public class RequestsExecutor {

    private RequestsExecutor(){}

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(50))
            .writeTimeout(Duration.ofSeconds(50))
            .readTimeout(Duration.ofSeconds(50))
            .build();

    public static String sendTranscriptionRequest(String token, File file) {
        Request request = Transcription.createRequest(token, file);
        log.info("Transcription request sent...");
        try(Response response = client.newCall(request).execute()) {
            return response.isSuccessful() ? response.body().string() : "Fail: " + response.message();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
