package com.vansisto.aiimagebot.services;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Voice;
import com.vansisto.aiimagebot.services.bot.command.Commands;

import java.util.Objects;

public class UpdateUtil {
    private UpdateUtil() {}

    public static long getChatId(Update update) {
        return update.message().chat().id();
    }

    public static String getMessageText(Update update) {
        return update.message().text();
    }

    public static Voice getVoice(Update update) {
        return update.message().voice();
    }

    public static Commands getCommand(Update update) {
        return Commands.valueOf(update.callbackQuery().data().toUpperCase());
    }

    public static String getUserId(Update update) {
        if (
                Objects.nonNull(update.message())
                && Objects.nonNull(update.message().from())
                && Objects.nonNull(update.message().from().id())
        ) {
            return update.message().from().id().toString();
        } else return getCallbackId(update).toString();
    }

    public static boolean isMessage(Update update) {
        return Objects.nonNull(update.message());
    }

    public static Long getCallbackId(Update update) {
        if (
                Objects.nonNull(update.callbackQuery())
                && Objects.nonNull(update.callbackQuery().id())
        ) {
            return update.callbackQuery().from().id();
        }
        return null;
    }

    public static Long getMessageId(Update update) {
        return update.message().chat().id();
    }
}
