package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.openai.RequestsExecutor;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.vansisto.aiimagebot.services.UpdateUtil.getChatId;
import static com.vansisto.aiimagebot.services.UpdateUtil.getMessageText;

@Service
@RequiredArgsConstructor
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
        String response = RequestsExecutor.generateImage(messageText, setting);
        bot.execute(new SendMessage(chatId, response));
    }
}
