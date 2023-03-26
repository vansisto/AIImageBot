package com.vansisto.aiimagebot.services.bot.handler.handlers.answers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import static com.vansisto.aiimagebot.services.UpdateUtil.getChatId;
import static com.vansisto.aiimagebot.services.UpdateUtil.getMessageText;
import static com.vansisto.aiimagebot.services.settings.UpdateType.MESSAGE;

@Service
@RequiredArgsConstructor
public class PictureHandlerAnswerHandler extends AbstractHandler implements UpdateHandler {
    private final MessageSource messageSource;

    private static final String DONE_MESSAGE_PROPERTY_KEY = "commands.menu.settings.picturesCount.done";
    @Override
    public void handle(Update update) {
        UserSetting setting = settingsService.getOrInitSetting(update);
        setting.setUpdateType(MESSAGE);
        setting.setNumberOfPictures(Integer.valueOf(getMessageText(update)));
        settingsService.save(setting);

        String message = messageSource.getMessage(DONE_MESSAGE_PROPERTY_KEY, null, setting.getLocale());
        bot.execute(new SendMessage(getChatId(update), String.format(message, getMessageText(update))));
    }

    @Override
    public boolean canHandle(Update update) {
        return settingsService.isPicturesCountAnswer(update);
    }
}
