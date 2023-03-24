package com.vansisto.aiimagebot.services.bot.command.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.SettingsKeyboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettingsCommand extends AbstractCommand {
    private final SettingsKeyboard settingsKeyboard;

    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        InlineKeyboardMarkup keyboard = settingsKeyboard.create();

        bot.execute(new SendMessage(chatId, "Поточні налаштування:")
                .replyMarkup(keyboard));
    }
}
