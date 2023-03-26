package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.vansisto.aiimagebot.services.UpdateUtil.getChatId;
import static com.vansisto.aiimagebot.services.UpdateUtil.getMessageText;
import static com.vansisto.aiimagebot.services.settings.UpdateType.MESSAGE;

@Service
@RequiredArgsConstructor
public class ApiKeyAnswerHandler extends AbstractHandler implements UpdateHandler {
    @Override
    public void handle(Update update) {
        UserSetting setting = settingsService.getOrInitSetting(update);
        setting.setUpdateType(MESSAGE);
        setting.setOpenAiApiKey(getMessageText(update));
        settingsService.save(setting);
        bot.execute(new SendMessage(getChatId(update), "Api key has been set up"));
    }

    @Override
    public boolean canHandle(Update update) {
        return settingsService.isAnswer(update);
    }
}
