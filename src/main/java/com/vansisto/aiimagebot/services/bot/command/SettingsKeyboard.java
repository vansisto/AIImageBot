package com.vansisto.aiimagebot.services.bot.command;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.vansisto.aiimagebot.services.bot.command.Commands.*;

@Component
public class SettingsKeyboard {

    public InlineKeyboardMarkup create() {
        return makeVerticalKeyboard(initButtonsData());
    }

    @NotNull
    private static InlineKeyboardMarkup makeVerticalKeyboard(Map<Commands, String> commandsNames) {
        InlineKeyboardButton[][] keyboard = commandsNames.keySet().stream()
                .map(key -> new InlineKeyboardButton[]{new InlineKeyboardButton(commandsNames.get(key)).callbackData(key.getValue())})
                .toArray(InlineKeyboardButton[][]::new);

        return new InlineKeyboardMarkup(keyboard);
    }

    private Map<Commands, String> initButtonsData() {
        Map<Commands, String> commands = new LinkedHashMap<>();

        commands.put(PICTURES_COUNT, "Select number of generated pictures");
        commands.put(X_256, "Size of pictures = 256x256");
        commands.put(X_512, "Size of pictures = 512x512");
        commands.put(X_1024, "Size of pictures = 1024x1024");
        commands.put(ENGLISH, "Change language to english");
        commands.put(UKRAINIAN, "Вибрати Українську мову");
        commands.put(HELP, "help");

        return commands;
    }
}
