package com.vansisto.aiimagebot.services;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.Voice;
import com.vansisto.aiimagebot.services.bot.command.Commands;

import java.util.Objects;

public class UpdateUtil {
    private UpdateUtil() {}

    public static Long getChatId(Update update) {
        return update != null && update.message() != null && update.message().chat() != null
                ? update.message().chat().id()
                : null;
    }

    public static String getMessageText(Update update) {
        return update != null && update.message() != null ? update.message().text() : null;
    }

    public static Voice getVoice(Update update) {
        return update != null && update.message() != null ? update.message().voice() : null;
    }

    public static Commands getCommand(Update update) {
        return update != null && update.callbackQuery() != null && update.callbackQuery().data() != null
                ? Commands.valueOf(update.callbackQuery().data().toUpperCase())
                : null;
    }

    public static String getUserId(Update update) {
        if (update != null) {
            Message message = update.message();
            if (message != null) {
                User user = message.from();
                if (user != null && user.id() != null) {
                    return user.id().toString();
                }
            }
        }

        Long callbackId = getCallbackId(update);
        return callbackId != null ? callbackId.toString() : null;
    }

    public static boolean isMessage(Update update) {
        return Objects.nonNull(update.message());
    }

    public static Long getCallbackId(Update update) {
        if (update != null && update.callbackQuery() != null && update.callbackQuery().id() != null) {
            return update.callbackQuery().from().id();
        }
        return null;
    }

    public static Long getMessageId(Update update) {
        return update != null && update.message() != null && update.message().chat() != null
                ? update.message().chat().id()
                : null;
    }
}
