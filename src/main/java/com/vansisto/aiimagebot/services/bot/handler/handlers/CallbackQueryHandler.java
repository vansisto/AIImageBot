package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategy;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategyFactory;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.vansisto.aiimagebot.services.UpdateUtil.getCommand;

@Service
@RequiredArgsConstructor
public class CallbackQueryHandler extends AbstractHandler implements UpdateHandler {
    private final CommandStrategyFactory commandStrategyFactory;

    @Override
    public void handle(Update update) {
        Commands command = getCommand(update);
        CommandStrategy commandStrategy = commandStrategyFactory.getCommandStrategy(command);
        commandStrategy.execute(bot, update);
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.callbackQuery());
    }
}
