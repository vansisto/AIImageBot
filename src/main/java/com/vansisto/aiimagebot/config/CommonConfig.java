package com.vansisto.aiimagebot.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Value("${app.telegram.bot.token}")
    String botToken;

    @Bean
    public TelegramBot getTelegramBot() {
        return new TelegramBot(botToken);
    }
}
