package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Voice;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VoiceHandler implements UpdateHandler {
    @Autowired
    private TelegramBot bot;

    @Override
    public void handle(Update update) {
        Voice voice = update.message().voice();
        long chatId = update.message().chat().id();
        bot.execute(new SendMessage(chatId, "Це аудіофайл: " + voice.fileId()));
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message()) && Objects.nonNull(update.message().voice());
    }
}
