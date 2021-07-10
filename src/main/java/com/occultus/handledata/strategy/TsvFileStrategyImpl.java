package com.occultus.handledata.strategy;

import com.occultus.handledata.file.ISaveFile;
import com.occultus.handledata.file.imdbimpl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Slf4j
@Component
public class TsvFileStrategyImpl implements IFileStrategy {
    private RabbitTemplate rabbitTemplate;

    private static final String TSV_SUFFIX = ".tsv";
    private static final String IMDB_BASE_NAME = "imdb_basename";
    private static final String IMDB_BASIC_TITLE = "imdb_basic_title";
    private static final String IMDB_PRINCIPAL_TITLE = "imdb_principal_title";
    private static final String IMDB_RATING_TITLE = "imdb_rating_title";
    private static final String IMDB_AKA_TITLE = "imdb_aka_title";

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public ISaveFile getSaveFile(String strategyName, String fileName) {
        ISaveFile iSaveFile = null;
        try {
            switch (strategyName.toLowerCase()) {
                case IMDB_BASE_NAME:
                    iSaveFile = new ImdbBaseNameFile(rabbitTemplate, this.getFileReader(ImdbBaseNameFile.FOLDER, "name_basics"));
                    break;
                case IMDB_BASIC_TITLE:
                    iSaveFile = new ImdbBasicTitleFile(rabbitTemplate, this.getFileReader(ImdbBasicTitleFile.FOLDER, "title_basics"));
                    break;
                case IMDB_PRINCIPAL_TITLE:
                    iSaveFile = new ImdbPrincipalTitleFile(rabbitTemplate, this.getFileReader(ImdbPrincipalTitleFile.FOLDER, "title_principals"));
                    break;
                case IMDB_RATING_TITLE:
                    iSaveFile = new ImdbRatingTitleFile(rabbitTemplate, this.getFileReader(ImdbRatingTitleFile.FOLDER, "title_ratings"));
                    break;
                case IMDB_AKA_TITLE:
                    iSaveFile = new ImdbAkaTitleFile(rabbitTemplate, this.getFileReader(ImdbAkaTitleFile.FOLDER, "title_aka"));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return iSaveFile;
    }

    public FileReader getFileReader(String folder, String fileName) throws FileNotFoundException {
        return new FileReader(DATA_PATH + folder + fileName + TSV_SUFFIX);
    }
}
