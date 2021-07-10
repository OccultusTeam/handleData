package com.occultus.handledata.strategy;

import com.occultus.handledata.file.ISaveFile;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class JsonFileStrategyImpl implements IFileStrategy {
    private static final String JSON_SUFFIX = ".json";

    @Override
    public ISaveFile getSaveFile(String strategyName, String fileName) {
        ISaveFile iSaveFile = null;
        return iSaveFile;
    }

    public byte[] getFileToBytes(String folder, String fileName) throws IOException {
        String filePath = DATA_PATH + folder + fileName + JSON_SUFFIX;
        return Files.readAllBytes(Path.of(filePath));
    }
}
