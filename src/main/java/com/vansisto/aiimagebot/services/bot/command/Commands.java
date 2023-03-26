package com.vansisto.aiimagebot.services.bot.command;

public enum Commands {
    START("start"),
    HELP("help"),
    ENGLISH("english"),
    UKRAINIAN("ukrainian"),
    PICTURES_COUNT("pictures_count"),
    X_256("x_256"),
    X_512("x_512"),
    X_1024("x_1024"),
    SETTINGS("settings"),
    SHOW_API_KEY("show_api_key"),
    SET_API_KEY("set_api_key");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
