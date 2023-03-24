package com.vansisto.aiimagebot.services.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandlerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBot {
    private final TelegramBot telegramBot;
    private final UpdateHandlerFactory updateHandlerFactory;

    public void run() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                UpdateHandler updateHandler = updateHandlerFactory.getFromUpdate(update);
                updateHandler.handle(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
