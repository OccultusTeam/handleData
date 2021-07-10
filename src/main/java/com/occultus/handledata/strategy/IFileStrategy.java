package com.occultus.handledata.strategy;

import com.occultus.handledata.file.ISaveFile;

public interface IFileStrategy {
    String DATA_PATH = "/Users/occultus/workspace/data/";

    ISaveFile getSaveFile(String strategyName, String fileName);
}
