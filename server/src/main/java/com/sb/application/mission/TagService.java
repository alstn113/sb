package com.sb.application.mission;

import com.sb.domain.mission.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
}
