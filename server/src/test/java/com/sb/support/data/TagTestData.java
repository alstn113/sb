package com.sb.support.data;

import com.sb.domain.mission.Tag;

public class TagTestData {

    public static TagBuilder defaultTag() {
        return new TagBuilder()
                .withId(null)
                .withName("name");
    }

    public static class TagBuilder {

        private Long id;
        private String name;

        public TagBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TagBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Tag build() {
            return new Tag(id, name);
        }
    }
}
