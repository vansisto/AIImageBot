package com.vansisto.aiimagebot.services.converter;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AudioConverter {

    private AudioConverter(){}

    public static CompletableFuture<File> convertOggToMp3(File sourceOggFile, File targetMp3File) {
        return CompletableFuture.supplyAsync(() -> {
            File outputDirectory = createOutputDirectory("voiceOutput");
            File outputFile = new File(outputDirectory, targetMp3File.getName());
            executeFFmpeg(sourceOggFile, outputFile);

            return outputFile;
        });
    }

    private static File createOutputDirectory(String directoryName) {
        File outputDirectory = new File(directoryName);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        return outputDirectory;
    }

    private static void executeFFmpeg(File sourceOggFile, File outputFile) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg",
                "-i", sourceOggFile.getAbsolutePath(),
                "-codec:a", "libmp3lame",
                "-qscale:a", "5",
                "-b:a", "96k",
                "-ar", "22050",
                "-ac", "1",
                "-filter:a", "loudnorm,atempo=1.5",
                outputFile.getAbsolutePath()
        );

        try {
            Process process = processBuilder.start();
            logProcessOutput(process);
            int exitCode = process.waitFor();
            log.info(exitCode == 0 ? "Convert done." : "An error occurred during conversion. Code: " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();        }
    }

    private static void logProcessOutput(Process process) throws IOException {
        try (BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = stdoutReader.readLine()) != null) {
                log.info("STDOUT: " + line);
            }
        }

        try (BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = stderrReader.readLine()) != null) {
                log.error("STDERR: " + line);
            }
        }
    }
}
