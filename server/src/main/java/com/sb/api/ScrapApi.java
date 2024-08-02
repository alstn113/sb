package com.sb.api;

import com.sb.application.scrap.ScrapService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapApi {

    private final ScrapService scrapService;

    public ScrapApi(ScrapService scrapService) {
        this.scrapService = scrapService;
    }
}
