package com.vansisto.aiimagebot.services.settings;

import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vansisto.aiimagebot.services.UpdateUtil.getUserId;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class UsersCache {
    @Value("${app.openai.defaultSettings.numberOfPictures}")
    private int numberOfPictures;
    @Value("${app.openai.defaultSettings.locale}")
    private Locale locale;
    @Value("${app.openai.defaultSettings.size}")
    private String size;

    @Getter
    private Set<UserSetting> userSettings;

    public void set(Set<KeySetting> keySettings) {
        userSettings = toUserSettings(keySettings);
    }

    private Set<UserSetting> toUserSettings(Set<KeySetting> keySettings) {
        if (isEmpty(keySettings)) {
            return new HashSet<>();
        }

        return keySettings.stream()
                .map(keySetting -> UserSetting.builder()
                                        .userId(keySetting.getUserId())
                                        .openAiApiKey(keySetting.getOpenAiApiKey())
                                        .numberOfPictures(numberOfPictures)
                                        .locale(locale)
                                        .size(size)
                                        .build())
                .collect(Collectors.toSet());
    }

    public UserSetting getByUserId(String userId) {
        return userSettings.stream()
                .filter(userSetting -> userSetting.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public UpdateType getUpdateType(Update update) {
        UserSetting userSetting = getByUserId(getUserId(update));
        return Objects.nonNull(userSetting) ? userSetting.getUpdateType() : null;
    }

    public void add(UserSetting userSetting) {
        userSettings.add(userSetting);
    }

    public void save(UserSetting userSetting) {
        UserSetting existingUserSetting = getByUserId(userSetting.getUserId());

        if (existingUserSetting == null) {
            userSettings.add(userSetting);
        } else {
            existingUserSetting.setOpenAiApiKey(userSetting.getOpenAiApiKey());
            existingUserSetting.setNumberOfPictures(userSetting.getNumberOfPictures());
            existingUserSetting.setSize(userSetting.getSize());
            existingUserSetting.setLocale(userSetting.getLocale());
        }
    }
}