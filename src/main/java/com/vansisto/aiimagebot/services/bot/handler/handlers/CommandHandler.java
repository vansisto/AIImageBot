package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommandHandler implements UpdateHandler {
    private final TelegramBot bot;
    private final CommandStrategyFactory commandStrategyFactory;

    @Override
    public void handle(Update update) {
        String messageText = update.message().text();
        commandStrategyFactory
                .getCommandStrategy(Commands.valueOf(messageText.substring(1).toUpperCase()))
                .execute(bot, update);
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message())
                && Objects.nonNull(update.message().text())
                && update.message().text().startsWith("/");
    }
}
