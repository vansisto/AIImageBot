package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UkrainianCommand extends AbstractCommand {
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = getSetting(update);
        setting.setLocale(new Locale("uk", "UA"));
        settingsService.save(setting);
        bot.execute(new SendMessage(chatId, "Мову змінено"));

        showSettingsKeyboard(bot, update, chatId, setting);
    }
}
