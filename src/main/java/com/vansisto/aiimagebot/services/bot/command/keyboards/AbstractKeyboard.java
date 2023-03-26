package com.vansisto.aiimagebot.services.bot.command.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import com.vansisto.aiimagebot.services.settings.service.SettingsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class AbstractKeyboard {
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected SettingsService settingsService;

    protected AbstractKeyboard() {}

    @NotNull
    protected static InlineKeyboardMarkup makeVerticalKeyboard(Map<Commands, String> commandsNames) {
        InlineKeyboardButton[][] keyboard = commandsNames.keySet().stream()
                .map(key -> new InlineKeyboardButton[]{new InlineKeyboardButton(commandsNames.get(key)).callbackData(key.getValue())})
                .toArray(InlineKeyboardButton[][]::new);

        return new InlineKeyboardMarkup(keyboard);
    }

    @NotNull
    protected static InlineKeyboardMarkup makeHorizontalKeyboard(Map<Commands, String> commandsNames) {
        InlineKeyboardButton[] keyboard = commandsNames.keySet().stream()
                .map(key -> new InlineKeyboardButton(commandsNames.get(key)).callbackData(key.getValue()))
                .toArray(InlineKeyboardButton[]::new);

        return new InlineKeyboardMarkup(keyboard);
    }
}
