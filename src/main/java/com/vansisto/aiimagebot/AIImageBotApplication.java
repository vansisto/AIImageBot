package com.vansisto.aiimagebot;

import com.vansisto.aiimagebot.services.bot.MyBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AIImageBotApplication implements CommandLineRunner {

    @Autowired
    private MyBot bot;

    public static void main(String[] args) {
        SpringApplication.run(AIImageBotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bot.run();
    }
}
