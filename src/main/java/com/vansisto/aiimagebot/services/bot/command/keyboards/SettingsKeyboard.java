package com.vansisto.aiimagebot.services.bot.command.keyboards;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static com.vansisto.aiimagebot.services.bot.command.Commands.*;

@Component
@RequiredArgsConstructor
public class SettingsKeyboard extends AbstractKeyboard {
    private static final String CURRENT_SETTINGS_PROPERTY_KEY = "commands.menu.keyboards.currentSettings";

    public InlineKeyboardMarkup create(Update update) {
        Locale locale = settingsService.getLocale(update);
        return makeVerticalKeyboard(initButtonsData(locale));
    }

    private Map<Commands, String> initButtonsData(Locale locale) {
        Map<Commands, String> commands = new LinkedHashMap<>();

        commands.put(PICTURES_COUNT, getMessage(PICTURES_COUNT, locale));
        commands.put(X_256, getMessage(X_256, locale));
        commands.put(X_512, getMessage(X_512, locale));
        commands.put(X_1024, getMessage(X_1024, locale));
        commands.put(ENGLISH, "Select English");
        commands.put(UKRAINIAN, "Обрати Українську");
        commands.put(API_KEY_MENU, "OpenAI API Key");
        commands.put(HELP, getMessage(HELP, locale));

        return commands;
    }

    @NotNull
    private String getMessage(Commands command, Locale locale) {
        return messageSource.getMessage(command.getPropertyKey(), null, locale);
    }

    public String getPropertyMessage(Update update, UserSetting setting) {
        Locale locale = settingsService.getLocale(update);
        Object[] args = new Object[]{};
        String sourceMessage = messageSource.getMessage(CURRENT_SETTINGS_PROPERTY_KEY, args, locale);
        return String.format(sourceMessage, setting.getNumberOfPictures(), setting.getSize());
    }
}
