package com.vansisto.aiimagebot.services.bot.command.commands.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends AbstractCommand {
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        bot.execute(new SendMessage(chatId, "Вітаю! Це команда /start"));
    }
}