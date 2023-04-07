package com.vansisto.aiimagebot.services.converter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Voice;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetFileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class TelegramFileDownloader {
    private final TelegramBot bot;

    public java.io.File downloadVoiceFile(Voice voice) throws IOException {
        GetFile getFileRequest = new GetFile(voice.fileId());
        GetFileResponse getFileResponse = bot.execute(getFileRequest);
        File file = getFileResponse.file();

        byte[] fileContent = bot.getFileContent(file);
        java.io.File voiceFile = Paths.get(System.getProperty("user.dir"), "voices", voice.fileId() + ".ogg").toFile();
        voiceFile.getParentFile().mkdirs();
        try (FileOutputStream outputStream = new FileOutputStream(voiceFile)) {
            outputStream.write(fileContent);
        }
        return voiceFile;
    }
}
