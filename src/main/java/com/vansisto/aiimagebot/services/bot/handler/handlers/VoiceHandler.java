package com.vansisto.aiimagebot.services.bot.handler.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Voice;
import com.pengrad.telegrambot.request.SendMessage;
import com.vansisto.aiimagebot.exceptions.AudioFilesProcessingException;
import com.vansisto.aiimagebot.services.bot.handler.AbstractHandler;
import com.vansisto.aiimagebot.services.bot.handler.UpdateHandler;
import com.vansisto.aiimagebot.services.converter.AudioConverter;
import com.vansisto.aiimagebot.services.converter.TelegramFileDownloader;
import com.vansisto.aiimagebot.services.openai.RequestsExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.vansisto.aiimagebot.services.UpdateUtil.getChatId;
import static com.vansisto.aiimagebot.services.UpdateUtil.getVoice;

@Slf4j
@Service
public class VoiceHandler extends AbstractHandler implements UpdateHandler {

    @Autowired
    private TelegramFileDownloader telegramFileDownloader;

    @Override
    public void handle(Update update) {
        Voice voice = getVoice(update);
        long chatId = getChatId(update);

        try {
            File sourceOggFile = telegramFileDownloader.downloadVoiceFile(voice);
            File targetMp3File = new java.io.File(update.updateId() + ".mp3");
            CompletableFuture<File> conversionFuture = AudioConverter.convertOggToMp3(sourceOggFile, targetMp3File);
            String token = settingsService.getOpenAiApiKey();

            conversionFuture.thenAccept(convertedFile -> {
                String response = RequestsExecutor.sendTranscriptionRequest(token, convertedFile);
                bot.execute(new SendMessage(chatId, response));

                try {
                    Files.delete(sourceOggFile.toPath());
                    log.info("Source deleted.");
                } catch (IOException e) {
                    log.error("Failed to delete source file: ", e);
                }

                try {
                    Files.delete(convertedFile.toPath());
                    log.info("Output file deleted.");
                } catch (IOException e) {
                    log.error("Failed to delete output file: ", e);
                }
            });

        } catch (IOException e) {
            throw new AudioFilesProcessingException();
        }
    }

    @Override
    public boolean canHandle(Update update) {
        return Objects.nonNull(update.message()) && Objects.nonNull(getVoice(update));
    }
}
