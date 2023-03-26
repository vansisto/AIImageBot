package com.vansisto.aiimagebot.services.bot.command;

public enum Commands {
    START("start", null),
    HELP("help", "commands.menu.settings.help"),
    ENGLISH("english", null),
    UKRAINIAN("ukrainian", null),
    PICTURES_COUNT("pictures_count", "commands.menu.settings.picturesCount"),
    X_256("x_256", "commands.menu.settings.x256"),
    X_512("x_512", "commands.menu.settings.x512"),
    X_1024("x_1024", "commands.menu.settings.x1024"),
    SETTINGS("settings", "commands.menu.settings"),
    SHOW_API_KEY("show_api_key", "commands.menu.settings.apiKey.show"),
    SET_API_KEY("set_api_key", "commands.menu.settings.apiKey.set");

    private final String value;
    private final String propertyKey;

    Commands(String value, String propertyKey) {
        this.value = value;
        this.propertyKey = propertyKey;
    }

    public String getValue() {
        return value;
    }
    public String getPropertyKey() {
        return propertyKey;
    }
}
