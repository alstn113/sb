package com.sb.support.data;


import com.sb.domain.member.Member;
import com.sb.domain.member.OAuthProvider;

public class MemberTestData {

    public static MemberBuilder defaultMember() {
        return new MemberBuilder()
                .withEmail("alstn113@gmail.com")
                .withProvider(OAuthProvider.GITHUB)
                .withSocialId(1234567890L)
                .withUsername("alstn113")
                .withDisplayName("Minsu Kim")
                .withAvatarUrl("https://avatars.githubusercontent.com/u/1234567890");
    }

    public static class MemberBuilder {

        private Long id;
        private String email;
        private OAuthProvider provider;
        private Long socialId;
        private String username;
        private String displayName;
        private String avatarUrl;

        public MemberBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MemberBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public MemberBuilder withProvider(OAuthProvider provider) {
            this.provider = provider;
            return this;
        }

        public MemberBuilder withSocialId(Long socialId) {
            this.socialId = socialId;
            return this;
        }

        public MemberBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public MemberBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public MemberBuilder withAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Member build() {
            return new Member(id, email, provider, socialId, username, displayName, avatarUrl);
        }
    }
}
