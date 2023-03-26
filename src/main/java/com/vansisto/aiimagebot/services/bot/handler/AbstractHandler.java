package com.vansisto.aiimagebot.services.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.vansisto.aiimagebot.services.settings.service.SettingsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public abstract class AbstractHandler {
    @Autowired
    protected TelegramBot bot;
    @Autowired
    protected SettingsService settingsService;

}
