package com.sb.domain.mission;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.Hibernate;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
        this.id = id;
        this.title = title;
        this.language = language;
        this.description = description;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Mission mission = (Mission) o;
        return Objects.equals(id, mission.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
