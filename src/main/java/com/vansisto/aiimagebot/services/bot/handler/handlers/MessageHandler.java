package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageHandler implements UpdateHandler {
    private final TelegramBot bot;

    @Override
    public void handle(Update update) {
        String messageText = update.message().text();
        long chatId = update.message().chat().id();
        proceed(messageText, chatId);
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message())
                && Objects.nonNull(update.message().text())
                && !update.message().text().startsWith("/");
    }

    private void proceed(String messageText, long chatId) {
        bot.execute(new SendMessage(chatId, messageText));
    }
}
