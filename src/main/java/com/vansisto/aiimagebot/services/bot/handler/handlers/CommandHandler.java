package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.bot.command.CommandStrategyFactory;
import com.vansisto.aiimagebot.services.bot.command.Commands;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.vansisto.aiimagebot.services.UpdateUtil.getMessageText;

@Service
@RequiredArgsConstructor
public class CommandHandler extends AbstractHandler implements UpdateHandler {
    private final CommandStrategyFactory commandStrategyFactory;

    @Override
    public void handle(Update update) {
        String messageText = getMessageText(update);
        commandStrategyFactory
                .getCommandStrategy(Commands.valueOf(messageText.substring(1).toUpperCase()))
                .execute(bot, update);
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message())
                && Objects.nonNull(getMessageText(update))
                && update.message().text().startsWith("/");
    }
}
