package com.sb.support.data;

import java.util.List;
import com.sb.domain.mission.Language;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.Tag;

public class MissionTestData {

    public static MissionBuilder defaultMission() {
        return new MissionBuilder()
                .withTitle("title")
                .withLanguage(Language.JAVA)
                .withDescription("description")
                .withThumbnail("https://thumbnail.com")
                .withUrl("https://url.com")
                .withTags(List.of());
    }

    public static class MissionBuilder {

        private Long id;
        private String title;
        private Language language;
        private String description;
        private String thumbnail;
        private String url;
        private List<Tag> tags;

        public MissionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MissionBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MissionBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        public MissionBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public MissionBuilder withThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public MissionBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public MissionBuilder withTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }


        public Mission build() {
            return new Mission(id, title, language, description, thumbnail, url, tags);
        }
    }
}
