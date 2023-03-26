package com.vansisto.aiimagebot.services.settings.service.impl;

import com.pengrad.telegrambot.model.Update;
import com.vansisto.aiimagebot.services.settings.KeySetting;
import com.vansisto.aiimagebot.services.settings.UpdateType;
import com.vansisto.aiimagebot.services.settings.UserSetting;
import com.vansisto.aiimagebot.services.settings.UsersCache;
import com.vansisto.aiimagebot.services.settings.service.SettingsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.vansisto.aiimagebot.services.UpdateUtil.getUserId;
import static com.vansisto.aiimagebot.services.settings.UpdateType.API_KEY_ANSWER;
import static com.vansisto.aiimagebot.services.settings.UpdateType.PICTURES_COUNT_ANSWER;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class SettingsServiceImpl implements SettingsService {
    @Value("${app.openai.defaultSettings.numberOfPictures}")
    private int numberOfPictures;
    @Value("${app.openai.defaultSettings.locale}")
    private Locale locale;
    @Value("${app.openai.defaultSettings.size}")
    private String size;
    @Value("${app.openai.token:}")
    private String aiApiKey;

    private final HashOperations<String, String, String> hashOperations;
    private static final String KEY = "KeySetting";
    private final UsersCache usersCache;

    public SettingsServiceImpl(RedisTemplate<String, String> redisTemplate,
                               UsersCache usersCache) {
        hashOperations = redisTemplate.opsForHash();
        this.usersCache = usersCache;
    }

    @PostConstruct
    public void init() {
        usersCache.set(getAll());
    }

    @Override
    public void save(KeySetting keySetting) {
        hashOperations.put(KEY, keySetting.getUserId(), keySetting.getOpenAiApiKey());
    }

    @Override
    public void save(UserSetting userSetting) {
        KeySetting keySetting = new KeySetting(userSetting);
        save(keySetting);
        usersCache.save(userSetting);
    }

    @Override
    public Set<KeySetting> getAll() {
        Map<String, String> map = hashOperations.entries(KEY);
        return isEmpty(map)
                ? new HashSet<>()
                : map.entrySet().stream()
                    .map(entry -> new KeySetting(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toSet());
    }

    @Override
    public boolean isApiKeyAnswer(Update update) {
        UpdateType updateType = usersCache.getUpdateType(update);
        return Objects.nonNull(updateType) && updateType.equals(API_KEY_ANSWER);
    }

    @Override
    public boolean isPicturesCountAnswer(Update update) {
        UpdateType updateType = usersCache.getUpdateType(update);
        return Objects.nonNull(updateType) && updateType.equals(PICTURES_COUNT_ANSWER);
    }

    @Override
    public UserSetting getOrInitSetting(Update update) {
        UserSetting setting = usersCache.getByUserId(getUserId(update));
        initIfAbsent(update, setting);
        return setting;
    }

    @Override
    public Locale getLocale(Update update) {
        return getOrInitSetting(update).getLocale();
    }

    private void initIfAbsent(Update update, UserSetting setting) {
        if (Objects.isNull(setting)) {
            setting = UserSetting.builder()
                    .userId(getUserId(update))
                    .locale(locale)
                    .size(size)
                    .numberOfPictures(numberOfPictures)
                    .openAiApiKey(aiApiKey)
                    .build();

            usersCache.add(setting);
            save(setting);
        }
    }
}
