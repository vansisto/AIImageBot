package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings.apikey;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.menu.AbstractSettingsCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.vansisto.aiimagebot.services.settings.UpdateType.API_KEY_ANSWER;

@Component
@RequiredArgsConstructor
public class SetAiApiKeyCommand extends AbstractSettingsCommand {

    private static final String PASTE_MESSAGE_PROPERTY_KEY = "commands.menu.settings.apiKey.setRequest";
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = getSetting(update);
        setting.setUpdateType(API_KEY_ANSWER);

        String message = messageSource.getMessage(PASTE_MESSAGE_PROPERTY_KEY, null, setting.getLocale());
        bot.execute(new SendMessage(chatId, message));
    }
}
