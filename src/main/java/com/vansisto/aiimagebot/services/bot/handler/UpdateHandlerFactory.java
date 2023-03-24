package com.vansisto.aiimagebot.services.bot.handler;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.handler.handlers.CallbackQueryHandler;
import com.vansisto.aiimagebot.services.bot.handler.handlers.CommandHandler;
import com.vansisto.aiimagebot.services.bot.handler.handlers.MessageHandler;
import com.vansisto.aiimagebot.services.bot.handler.handlers.VoiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateHandlerFactory {
    private final List<UpdateHandler> handlers;

    @Autowired
    public UpdateHandlerFactory(CallbackQueryHandler callbackQueryHandler,
                                MessageHandler messageHandler,
                                CommandHandler commandHandler,
                                VoiceHandler voiceHandler) {
        this.handlers = Arrays.asList(
                callbackQueryHandler,
                messageHandler,
                commandHandler,
                voiceHandler);
    }

    public UpdateHandler getFromUpdate(Update update) {
        return handlers.stream()
                .filter(handler -> handler.canHandle(update))
                .findFirst()
                .orElse(null);
    }
}
