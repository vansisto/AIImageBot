package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class EnglishCommand extends AbstractCommand {
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        getSetting(update).setLocale(Locale.ENGLISH);
        bot.execute(new SendMessage(chatId, "English selected"));
    }
}