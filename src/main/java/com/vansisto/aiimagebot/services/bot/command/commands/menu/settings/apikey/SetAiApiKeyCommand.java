package com.vansisto.aiimagebot.services.bot.command.commands.menu.settings.apikey;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.services.bot.command.commands.AbstractCommand;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.vansisto.aiimagebot.services.settings.UpdateType.ANSWER;

@Component
@RequiredArgsConstructor
public class SetAiApiKeyCommand extends AbstractCommand {
    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
        UserSetting setting = getSetting(update);
        setting.setUpdateType(ANSWER);

        String text = """
                Paste your OpenAI API Key:
                """;

        bot.execute(new SendMessage(chatId, text));
    }
}
