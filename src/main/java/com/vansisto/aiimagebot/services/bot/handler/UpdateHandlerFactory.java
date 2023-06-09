package com.vansisto.aiimagebot.services.bot.handler;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.handler.handlers.*;
import com.vansisto.aiimagebot.services.bot.handler.handlers.answers.ApiKeyAnswerHandler;
import com.vansisto.aiimagebot.services.bot.handler.handlers.answers.PictureHandlerAnswerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateHandlerFactory {
    private final List<UpdateHandler> handlers;

    @Autowired
    public UpdateHandlerFactory(ApiKeyAnswerHandler apiKeyAnswerHandler,
                                PictureHandlerAnswerHandler pictureHandlerAnswerHandler,
                                CallbackQueryHandler callbackQueryHandler,
                                MessageHandler messageHandler,
                                CommandHandler commandHandler,
                                VoiceHandler voiceHandler) {
        this.handlers = Arrays.asList(
                voiceHandler,
                callbackQueryHandler,
                commandHandler,
                apiKeyAnswerHandler,
                pictureHandlerAnswerHandler,
                messageHandler
        );
    }

    public UpdateHandler getFromUpdate(Update update) {
        return handlers.stream()
                .filter(handler -> handler.canHandle(update))
                .findFirst()
                .orElse(null);
    }
}
