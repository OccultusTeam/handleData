package com.occultus.handledata.service;

import com.occultus.handledata.file.ISaveFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReadFileService {

    public void readFile(ISaveFile iSaveFile) {
        try {
            iSaveFile.saveFileRow();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
