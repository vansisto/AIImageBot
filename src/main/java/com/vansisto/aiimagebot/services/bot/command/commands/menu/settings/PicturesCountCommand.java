package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.menu.AbstractSettingsCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import org.springframework.stereotype.Component;

import static com.vansisto.aiimagebot.services.settings.UpdateType.PICTURES_COUNT_ANSWER;

@Component
public class PicturesCountCommand extends AbstractSettingsCommand {
    private static final String PICTURES_COUNT_PROPERTY_KEY = "commands.menu.settings.picturesCountRequest";

    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = getSetting(update);
        setting.setUpdateType(PICTURES_COUNT_ANSWER);

        String message = messageSource.getMessage(PICTURES_COUNT_PROPERTY_KEY, null, setting.getLocale());
        bot.execute(new SendMessage(chatId, message));
    }
}
