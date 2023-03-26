package com.vansisto.aiimagebot.services.settings.service;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.settings.KeySetting;
import com.vansisto.aiimagebot.services.settings.UserSetting;

import java.util.Locale;
import java.util.Set;

public interface SettingsService {
    void save(KeySetting keySetting);
    void save(UserSetting userSetting);
    boolean isApiKeyAnswer(Update update);
    boolean isPicturesCountAnswer(Update update);
    UserSetting getOrInitSetting(Update update);
    Locale getLocale(Update update);
    Set<KeySetting> getAll();
}
