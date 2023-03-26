package com.vansisto.aiimagebot.services.settings;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Locale;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSetting extends KeySetting {
    private int numberOfPictures;
    private String size;
    private Locale locale;
    private UpdateType updateType;

    @Builder
    public UserSetting(String userId, String openAiApiKey, int numberOfPictures, String size, Locale locale) {
        super(userId, openAiApiKey);
        this.numberOfPictures = numberOfPictures;
        this.size = size;
        this.locale = locale;
        this.updateType = UpdateType.MESSAGE;
    }

    public UserSetting() {
    }
}
