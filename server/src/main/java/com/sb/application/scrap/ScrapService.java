package com.sb.application.scrap;

import com.sb.domain.scrap.ScrapRepository;
import org.springframework.stereotype.Service;

@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;

    public ScrapService(ScrapRepository scrapRepository) {
        this.scrapRepository = scrapRepository;
    }
}
