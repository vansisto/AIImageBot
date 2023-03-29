package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings.apikey;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.menu.AbstractSettingsCommand;
import com.vansisto.aiimagebot.services.bot.command.keyboards.AiApiKeyKeyboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static com.vansisto.aiimagebot.services.bot.command.Commands.API_KEY_MENU;

@Component
@RequiredArgsConstructor
public class ApiKeyMenuCommand extends AbstractSettingsCommand {
    private final AiApiKeyKeyboard aiApiKeyKeyboard;

    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);

        InlineKeyboardMarkup keyboard = aiApiKeyKeyboard.create();
        Locale locale = settingsService.getLocale(update);
        String menuMessage = messageSource.getMessage(API_KEY_MENU.getPropertyKey(), null, locale);
        bot.execute(new SendMessage(chatId, menuMessage)
                .replyMarkup(keyboard));
    }
}
