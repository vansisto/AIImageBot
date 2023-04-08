package com.vansisto.aiimagebot.services.openai;

import com.vansisto.aiimagebot.exceptions.ImageGenerationRequestException;
import com.vansisto.aiimagebot.exceptions.TranscriptionRequestException;
import com.vansisto.aiimagebot.services.openai.requests.ImageGenerationRequestUtil;
import com.vansisto.aiimagebot.services.openai.requests.TranscriptionRequestUtil;
import com.vansisto.aiimagebot.services.settings.UserSetting;
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
        Request request = TranscriptionRequestUtil.createRequest(token, file);
        log.info("TranscriptionRequestUtil request sent...");
        try(Response response = client.newCall(request).execute()) {
            return response.isSuccessful() ? response.body().string() : "Fail: " + response.message();
        } catch (IOException e) {
            throw new TranscriptionRequestException();
        }
    }

    public static String generateImage(String messageText, UserSetting setting) {
        Request request = ImageGenerationRequestUtil.createRequest(setting.getOpenAiApiKey(), messageText, setting.getNumberOfPictures(), setting.getSize());
        log.info("Image generating request sent...");
        try(Response response = client.newCall(request).execute()) {
            return response.isSuccessful() ? response.body().string() : "Fail: " + response.message();
        } catch (IOException e) {
            throw new ImageGenerationRequestException();
        }
    }
}
