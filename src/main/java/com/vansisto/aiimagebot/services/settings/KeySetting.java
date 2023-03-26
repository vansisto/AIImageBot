package com.vansisto.aiimagebot.services.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("KeySetting")
public class KeySetting {
    @Id
    private String userId;
    private String openAiApiKey;

    public KeySetting(KeySetting keySetting) {
        this.userId = keySetting.getUserId();
        this.openAiApiKey = keySetting.getOpenAiApiKey();
    }
}
