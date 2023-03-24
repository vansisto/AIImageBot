package com.vansisto.aiimagebot.services.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public interface CommandStrategy {
    void execute(TelegramBot bot, Update update);
}
