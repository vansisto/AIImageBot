package com.vansisto.aiimagebot.services.bot.command.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class AbstractKeyboard {
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
