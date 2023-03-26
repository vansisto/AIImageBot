package com.vansisto.aiimagebot.services.bot.command.commands.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import com.vansisto.aiimagebot.services.bot.command.keyboards.SettingsKeyboard;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettingsCommand extends AbstractCommand {
    private final SettingsKeyboard settingsKeyboard;

    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = getSetting(update);
        InlineKeyboardMarkup keyboard = settingsKeyboard.create();

        String currentSettings = String.format("""
                Кількість зображень - %d
                Розмір зображень - %s
                """, setting.getNumberOfPictures(), setting.getSize());

        bot.execute(new SendMessage(chatId, "Поточні налаштування:" + currentSettings)
                .replyMarkup(keyboard));
    }
}
