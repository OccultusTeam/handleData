package com.occultus.handledata.strategy;

import com.occultus.handledata.file.ISaveFile;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Slf4j
public class CsvFileStrategyImpl implements IFileStrategy {
    private static final String CSV_SUFFIX = ".csv";

    @Override
    public ISaveFile getSaveFile(String strategyName, String fileName) {
        ISaveFile iSaveFile = null;
        return iSaveFile;
    }

    public FileReader getFileReader(String folder, String fileName) throws FileNotFoundException {
        return new FileReader(DATA_PATH + folder + fileName + CSV_SUFFIX);
    }
}
