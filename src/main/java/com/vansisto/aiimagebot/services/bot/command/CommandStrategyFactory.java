package com.vansisto.aiimagebot.services.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CommandStrategyFactory {
    private final Map<Commands, CommandStrategy> commandStrategies = new EnumMap<>(Commands.class);

    @Autowired
    public CommandStrategyFactory(@Qualifier("startCommand") CommandStrategy startCommand,
                                  @Qualifier("helpCommand") CommandStrategy helpCommand,
                                  @Qualifier("englishCommand") CommandStrategy englishCommand,
                                  @Qualifier("ukrainianCommand") CommandStrategy ukrainianCommand,
                                  @Qualifier("picturesCountCommand") CommandStrategy picturesCountCommand,
                                  @Qualifier("x256Command") CommandStrategy x256Command,
                                  @Qualifier("x512Command") CommandStrategy x512Command,
                                  @Qualifier("x1024Command") CommandStrategy x1024Command,
                                  @Qualifier("settingsCommand") CommandStrategy settingsCommand,
                                  @Qualifier("setAiApiKeyCommand") CommandStrategy setAiApiKeyCommand
    ) {
        commandStrategies.put(Commands.START, startCommand);
        commandStrategies.put(Commands.HELP, helpCommand);
        commandStrategies.put(Commands.ENGLISH, englishCommand);
        commandStrategies.put(Commands.UKRAINIAN, ukrainianCommand);
        commandStrategies.put(Commands.PICTURES_COUNT, picturesCountCommand);
        commandStrategies.put(Commands.X_256, x256Command);
        commandStrategies.put(Commands.X_512, x512Command);
        commandStrategies.put(Commands.X_1024, x1024Command);
        commandStrategies.put(Commands.SETTINGS, settingsCommand);
        commandStrategies.put(Commands.SET_API_KEY, setAiApiKeyCommand);
    }

    public CommandStrategy getCommandStrategy(Commands command) {
        return commandStrategies.get(command);
    }
}
