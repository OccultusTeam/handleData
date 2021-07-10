package com.occultus.handledata.file.imdbimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.occultus.handledata.file.ISaveFile;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.FileReader;

@Slf4j
public class ImdbPrincipalTitleFile implements ISaveFile {
    private final FileReader fileReader;
    private final RabbitTemplate rabbitTemplate;
    private final static ObjectMapper om = new ObjectMapper();
    private int i = 0;

    public static final String FOLDER = "imdb/";
    public static final String TITLE_PRINCIPAL_QUEUE_NAME = "imdb_title_principal";


    public ImdbPrincipalTitleFile(RabbitTemplate rabbitTemplate, FileReader fileReader) {
        this.rabbitTemplate = rabbitTemplate;
        this.fileReader = fileReader;
    }

    @Override
    public void saveFileRow() {
        TsvParserSettings settings = new TsvParserSettings();
        RowListProcessor rowListProcessor = new RowListProcessor() {
            @Override
            public void rowProcessed(String[] row, ParsingContext context) {
                try {
                    rabbitTemplate.convertAndSend(
                            DIRECT_EXCHANGE_NAME,
                            TITLE_PRINCIPAL_QUEUE_NAME,
                            om.writeValueAsString(row));
                    i++;
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        };
        settings.setProcessor(rowListProcessor);
        settings.setHeaderExtractionEnabled(true);
        //settings.setNumberOfRowsToSkip(30000000);
        //settings.setNumberOfRecordsToRead(6468817);
        TsvParser parser = new TsvParser(settings);
        parser.parse(fileReader);
        log.warn(TITLE_PRINCIPAL_QUEUE_NAME + "total row count :{}", i);
    }
}
