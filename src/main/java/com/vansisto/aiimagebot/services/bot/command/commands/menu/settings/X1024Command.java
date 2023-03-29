package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.command.commands.menu.AbstractSettingsCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import org.springframework.stereotype.Component;

@Component
public class X1024Command extends AbstractSettingsCommand {
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = settingsService.getOrInitSetting(update);
        setting.setSize("1024x1024");
        settingsService.save(setting);

        showSettingsKeyboard(bot, update, chatId, setting);
    }
}
