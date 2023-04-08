package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.exceptions.JsonContentResponseException;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.openai.RequestsExecutor;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.vansisto.aiimagebot.services.UpdateUtil.getChatId;
import static com.vansisto.aiimagebot.services.UpdateUtil.getMessageText;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageHandler extends AbstractHandler implements UpdateHandler {
    @Override
    public void handle(Update update) {
        String messageText = getMessageText(update);
        long chatId = getChatId(update);
        UserSetting setting = settingsService.getOrInitSetting(update);
        proceed(messageText, chatId, setting);
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message())
                && Objects.nonNull(update.message().text())
                && !update.message().text().startsWith("/");
    }

    private void proceed(String messageText, long chatId, UserSetting setting) {
        if (setting.getLocale().getLanguage().equals("uk")) {
            bot.execute(new SendMessage(chatId, "Translating..."));
            String json = RequestsExecutor.translate(messageText, setting);
            messageText = extractContentFromJson(json);
        }
        bot.execute(new SendMessage(chatId, "Image generating request..."));
        messageText = RequestsExecutor.generateImage(messageText, setting);
        bot.execute(new SendMessage(chatId, messageText));
    }

    private String extractContentFromJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject choice = choices.getJSONObject(0);
            JSONObject message = choice.getJSONObject("message");
            return message.getString("content");
        } catch (JSONException e) {
            throw new JsonContentResponseException();
        }
    }
}
