package com.sb.domain.mission;

import com.sb.domain.IdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Mission extends IdentifiableEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String thumbnail;

    @Column(nullable = false)
    private String url;

    protected Mission() {
    }

    public Mission(String title, Language language, String description, String thumbnail, String url) {
        this(null, title, language, description, thumbnail, url);
    }

    public Mission(Long id, String title, Language language, String description, String thumbnail, String url) {
        super(id);
        this.title = title;
        this.language = language;
        this.description = description;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Language getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getUrl() {
        return url;
    }
}
