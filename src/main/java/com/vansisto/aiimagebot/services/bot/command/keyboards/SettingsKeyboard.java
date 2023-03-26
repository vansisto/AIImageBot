package com.vansisto.aiimagebot.services.bot.command.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.vansisto.aiimagebot.services.bot.command.Commands.*;

@Component
public class SettingsKeyboard extends AbstractKeyboard {

    public InlineKeyboardMarkup create() {
        return makeVerticalKeyboard(initButtonsData());
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
