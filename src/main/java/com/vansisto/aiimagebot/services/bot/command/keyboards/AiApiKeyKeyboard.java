package com.vansisto.aiimagebot.services.bot.command.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static com.vansisto.aiimagebot.services.bot.command.Commands.*;

@Component
public class AiApiKeyKeyboard extends AbstractKeyboard {
    private static final String API_KEY_SHOW_PROPERTY_KEY = "commands.menu.settings.apiKey.show";
    private static final String API_KEY_SET_PROPERTY_KEY = "commands.menu.settings.apiKey.set";

    public InlineKeyboardMarkup create() {
        return makeHorizontalKeyboard(initButtonsData());
    }

    private Map<Commands, String> initButtonsData() {
        Map<Commands, String> commands = new LinkedHashMap<>();
        Locale locale = settingsService.getLocale(updateCache.getUpdate());

        commands.put(SHOW_API_KEY, messageSource.getMessage(API_KEY_SHOW_PROPERTY_KEY, null, locale));
        commands.put(SET_API_KEY, messageSource.getMessage(API_KEY_SET_PROPERTY_KEY, null, locale));

        return commands;
    }
}
