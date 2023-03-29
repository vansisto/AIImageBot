package com.vansisto.aiimagebot.config;

import com.pengrad.telegrambot.model.Update;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UpdateCache {
    private Update update;
}
