package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Voice;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.vansisto.aiimagebot.services.UpdateUtil.getChatId;
import static com.vansisto.aiimagebot.services.UpdateUtil.getVoice;

@Service
public class VoiceHandler extends AbstractHandler implements UpdateHandler {
    @Override
    public void handle(Update update) {
        Voice voice = getVoice(update);
        long chatId = getChatId(update);
        bot.execute(new SendMessage(chatId, "Це аудіофайл: " + voice.fileId()));
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message()) && Objects.nonNull(getVoice(update));
    }
}
