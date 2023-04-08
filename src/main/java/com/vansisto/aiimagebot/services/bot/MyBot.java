package com.vansisto.aiimagebot.services.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.config.UpdateCache;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyBot {
    private final TelegramBot telegramBot;
    private final UpdateHandlerFactory updateHandlerFactory;
    private final UpdateCache updateCache;

    public void run() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    updateCache.setUpdate(update);
                    UpdateHandler updateHandler = updateHandlerFactory.getFromUpdate(update);
                    updateHandler.handle(update);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
