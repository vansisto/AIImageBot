package com.vansisto.aiimagebot.services.openai.requests;

import com.vansisto.aiimagebot.exceptions.JsonRequestFormingException;
import com.vansisto.aiimagebot.services.openai.Message;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TranslationRequestUtil {

    private TranslationRequestUtil() {}

    public static Request createRequest(String token, String textToTranslate) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        List<Message> messages = createPromptMessages(textToTranslate);
        JSONObject jsonBody = makeJsonFromPrompts(messages);
        RequestBody requestBody = RequestBody.create(json, jsonBody.toString());

        return new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(requestBody)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .build();
    }

    private static JSONObject makeJsonFromPrompts(List<Message> messages) {
        JSONObject jsonBody = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Message message : messages) {
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("role", message.getRole());
                jsonObject.put("content", message.getContent());

                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                throw new JsonRequestFormingException();
            }
        }
        try {
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("messages", jsonArray);
        } catch (JSONException e) {
            throw new JsonRequestFormingException();
        }

        return jsonBody;
    }

    @NotNull
    private static List<Message> createPromptMessages(String textToTranslate) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", "You are a translator from Ukrainian to English"));
        messages.add(new Message("user", "Тарас Шевченко! Досить було однієї людини, щоб урятувати цілу націю"));
        messages.add(new Message("assistant", "Taras Shevchenko. One man was enough to save an entire nation"));
        messages.add(new Message("user", "Поганий той школяр який учителя не переважить"));
        messages.add(new Message("assistant", "A bad student is a student who cannot surpass his teacher"));
        messages.add(new Message("user", textToTranslate));
        return messages;
    }
}
