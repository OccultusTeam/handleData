package com.occultus.handledata.file.imdbimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.occultus.handledata.file.ISaveFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.FileReader;

@Slf4j
public class ImdbRatingTitleFile implements ISaveFile {
    public static final String FOLDER = "imdb/";
    private FileReader fileReader;
    private final RabbitTemplate rabbitTemplate;
    private final static ObjectMapper om = new ObjectMapper();


    int i = 0;

    public ImdbRatingTitleFile(RabbitTemplate rabbitTemplate, FileReader fileReader) {
        this.rabbitTemplate = rabbitTemplate;
        this.fileReader = fileReader;
    }

    @Override
    public void saveFileRow() {
    }
}
