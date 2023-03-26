package com.vansisto.aiimagebot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.vansisto.aiimagebot.services.settings.KeySetting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CommonConfig {
    @Value("${app.telegram.bot.token}")
    private String botToken;

    @Bean
    public TelegramBot getTelegramBot() {
        return new TelegramBot(botToken);
    }

    @Bean
    public RedisTemplate<String, KeySetting> redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisSerializer serializer = new StringRedisSerializer();
        RedisTemplate<String, KeySetting> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        serializeTemplate(serializer, template);
        return template;
    }

    private static void serializeTemplate(StringRedisSerializer serializer, RedisTemplate<String, KeySetting> template) {
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
    }
}
