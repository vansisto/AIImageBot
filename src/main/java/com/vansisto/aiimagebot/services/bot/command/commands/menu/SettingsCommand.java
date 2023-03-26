package com.vansisto.aiimagebot.services.bot.command.commands.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettingsCommand extends AbstractCommand {
    private static final String CURRENT_SETTINGS_PROPERTY_KEY = "commands.menu.keyboards.currentSettings";

    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = getSetting(update);

        InlineKeyboardMarkup keyboard = settingsKeyboard.create(update);
        String menuMessage = getPropertyMessage(update, setting, CURRENT_SETTINGS_PROPERTY_KEY);
        bot.execute(new SendMessage(chatId, menuMessage)
                .replyMarkup(keyboard));
    }
}
