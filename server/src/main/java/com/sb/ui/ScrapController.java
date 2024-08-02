package com.sb.ui;

import com.sb.application.scrap.ScrapService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapController {

    private final ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }
}
