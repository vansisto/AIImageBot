package com.vansisto.aiimagebot.services.bot.command.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class EnglishCommand extends AbstractCommand {
//    TODO:
//    @Autowired
//    private State state;

    @Override
    public void execute(TelegramBot bot, Update update) {
        long chatId = getChatId(update);
//        state.setLanguage(Language.EN);
        bot.execute(new SendMessage(chatId, "Selected english language"));
    }
}
