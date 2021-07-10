package com.occultus.handledata.controller;

import com.occultus.handledata.service.ReadFileService;
import com.occultus.handledata.strategy.CsvFileStrategyImpl;
import com.occultus.handledata.strategy.JsonFileStrategyImpl;
import com.occultus.handledata.strategy.TsvFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讀取檔案建立
 * ex:json, csv,..
 */
@RestController
@RequestMapping("/loadData")
@Slf4j
public class LoadDataController {
    private final ReadFileService readFileService;
    private final RabbitTemplate rabbitTemplate;

    public LoadDataController(ReadFileService readFileService, RabbitTemplate rabbitTemplate) {
        this.readFileService = readFileService;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * json檔案匯入
     */
    @GetMapping("/json")
    public void jsonRead(@RequestParam("strategyName") String strategyName,
                         @RequestParam(value = "fileName", required = false) String fileName) {
        readFileService.readFile(new JsonFileStrategyImpl().getSaveFile(strategyName, fileName));
    }

    /**
     * csv檔案匯入
     */
    @GetMapping("/csv")
    public void csvRead(@RequestParam("strategyName") String strategyName,
                        @RequestParam(value = "fileName", required = false) String fileName) {
        readFileService.readFile(new CsvFileStrategyImpl().getSaveFile(strategyName, fileName));
    }

    /**
     * tsv檔案匯入
     */
    @GetMapping("/tsv")
    public void tsvRead(@RequestParam("strategyName") String strategyName,
                        @RequestParam(value = "fileName", required = false) String fileName) {
        var tsvFileStrategyImpl = new TsvFileStrategyImpl();
        tsvFileStrategyImpl.setRabbitTemplate(rabbitTemplate);
        readFileService.readFile(tsvFileStrategyImpl.getSaveFile(strategyName, fileName));
    }
}
