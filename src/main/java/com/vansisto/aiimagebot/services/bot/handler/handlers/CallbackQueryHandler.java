package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategy;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategyFactory;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CallbackQueryHandler implements UpdateHandler {
    private final TelegramBot bot;
    private final CommandStrategyFactory commandStrategyFactory;

    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        String commandName = callbackQuery.data();
        Commands command = Commands.valueOf(commandName.toUpperCase());
        CommandStrategy commandStrategy = commandStrategyFactory.getCommandStrategy(command);
        commandStrategy.execute(bot, update);
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.callbackQuery());
    }
}
