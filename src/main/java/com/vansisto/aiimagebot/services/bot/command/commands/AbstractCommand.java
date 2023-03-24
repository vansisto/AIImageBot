package com.vansisto.aiimagebot.services.bot.command.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public abstract class AbstractCommand implements CommandStrategy {
    private Update update;

    protected long getChatId(Update update) {
        this.update = update;
        return isMessage() ? getMessageId() : getCallbackId();
    }

    private boolean isMessage() {
        return Objects.nonNull(update.message());
    }

    private Long getCallbackId() {
        return update.callbackQuery().from().id();
    }

    private Long getMessageId() {
        return update.message().chat().id();
    }

    @Override
    public abstract void execute(TelegramBot bot, Update update);
}