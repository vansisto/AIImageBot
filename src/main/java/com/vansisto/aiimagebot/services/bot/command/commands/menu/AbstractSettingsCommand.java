package com.vansisto.aiimagebot.services.bot.command.commands.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractSettingsCommand extends AbstractCommand {
    protected void showSettingsKeyboard(TelegramBot bot, Update update, long chatId, UserSetting setting) {
        InlineKeyboardMarkup keyboard = settingsKeyboard.create(update);
        String menuMessage = settingsKeyboard.getPropertyMessage(update, setting);
        bot.execute(new SendMessage(chatId, menuMessage)
                .replyMarkup(keyboard));
    }

    protected AbstractSettingsCommand() {}
}
