package com.vansisto.aiimagebot.services.settings.service;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.settings.KeySetting;
import com.vansisto.aiimagebot.services.settings.UserSetting;

import java.util.Set;

public interface SettingsService {
    void save(KeySetting keySetting);
    void save(UserSetting userSetting);
    Set<KeySetting> getAll();
    boolean isAnswer(Update update);
    UserSetting getOrInitSetting(Update update);
}
