package com.vansisto.aiimagebot.services.bot.command.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategy;
import com.vansisto.aiimagebot.services.bot.command.keyboards.SettingsKeyboard;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import com.vansisto.aiimagebot.services.settings.service.SettingsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.vansisto.aiimagebot.services.UpdateUtil.*;

@Component
@NoArgsConstructor
public abstract class AbstractCommand implements CommandStrategy {
    @Autowired
    protected SettingsService settingsService;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected SettingsKeyboard settingsKeyboard;

    private static final String CURRENT_SETTINGS_PROPERTY_KEY = "commands.menu.keyboards.currentSettings";

    protected long getChatId(Update update) {
        return isMessage(update) ? getMessageId(update) : getCallbackId(update);
    }

    protected UserSetting getSetting(Update update) {
        return settingsService.getOrInitSetting(update);
    }

    protected String getPropertyMessage(Update update, UserSetting setting, String propertyKey) {
        Locale locale = settingsService.getLocale(update);
        Object[] args = new Object[]{};
        String sourceMessage = messageSource.getMessage(propertyKey, args, locale);
        return String.format(sourceMessage, setting.getNumberOfPictures(), setting.getSize());
    }

    protected void showSettingsKeyboard(TelegramBot bot, Update update, long chatId, UserSetting setting) {
        InlineKeyboardMarkup keyboard = settingsKeyboard.create(update);
        String menuMessage = getPropertyMessage(update, setting, CURRENT_SETTINGS_PROPERTY_KEY);
        bot.execute(new SendMessage(chatId, menuMessage)
                .replyMarkup(keyboard));
    }
}