package com.sb.domain.mission;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.sb.infra.persistence.IdentifiableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy = "mission", cascade = CascadeType.PERSIST)
    private Set<MissionTag> missionTags = new HashSet<>();

    protected Mission() {
    }

    public Mission(
            String title,
            Language language,
            String description,
            String thumbnail,
            String url,
            List<Tag> tags
    ) {
        this(null, title, language, description, thumbnail, url, tags);
    }

    public Mission(
            Long id,
            String title,
            Language language,
            String description,
            String thumbnail,
            String url,
            List<Tag> tags
    ) {
        super(id);
        this.title = title;
        this.language = language;
        this.description = description;
        this.thumbnail = thumbnail;
        this.url = url;
        mapTags(tags);
    }

    public void mapTags(List<Tag> tags) {
        tags.forEach(this::addTag);
    }


    public void addTag(Tag tag) {
        MissionTag missionTag = new MissionTag(this, tag);
        missionTags.add(missionTag);
    }

    public void removeTag(Tag tag) {
        MissionTag missionTag = new MissionTag(this, tag);
        missionTags.remove(missionTag);
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

    public Set<MissionTag> getMissionTags() {
        return missionTags;
    }
}
