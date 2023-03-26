package com.vansisto.aiimagebot.services.bot.command.commands;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategy;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import com.vansisto.aiimagebot.services.settings.service.SettingsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.vansisto.aiimagebot.services.UpdateUtil.*;

@Component
@NoArgsConstructor
public abstract class AbstractCommand implements CommandStrategy {
    @Autowired
    private SettingsService settingsService;


    protected long getChatId(Update update) {
        return isMessage(update) ? getMessageId(update) : getCallbackId(update);
    }

    protected UserSetting getSetting(Update update) {
        return settingsService.getOrInitSetting(update);
    }
}