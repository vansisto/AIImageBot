package com.vansisto.aiimagebot.services.bot.handler;

import com.pengrad.telegrambot.model.Update;

public interface UpdateHandler {
    void handle(Update update);
    boolean canHandle(Update update);
}
