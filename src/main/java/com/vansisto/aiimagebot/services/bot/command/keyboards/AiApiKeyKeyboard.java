package com.vansisto.aiimagebot.services.bot.command.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.vansisto.aiimagebot.services.bot.command.Commands.*;

@Component
public class AiApiKeyKeyboard extends AbstractKeyboard {
    public InlineKeyboardMarkup create() {
        return makeHorizontalKeyboard(initButtonsData());
    }

    private Map<Commands, String> initButtonsData() {
        Map<Commands, String> commands = new LinkedHashMap<>();

        commands.put(SHOW_API_KEY, "Show");
        commands.put(SET_API_KEY, "Set");

        return commands;
    }
}
