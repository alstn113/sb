package com.sb.domain.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import com.sb.support.IntegrationTestSupport;
import com.sb.support.data.MissionTestData;
import com.sb.support.data.TagTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MissionRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    void findAllById() {
        // given
        Tag tag1 = TagTestData.defaultTag().withName("tag1").build();
        Tag tag2 = TagTestData.defaultTag().withName("tag2").build();
        tagRepository.saveAll(List.of(tag1, tag2));

        Mission mission1 = MissionTestData.defaultMission()
                .withTags(List.of(tag1, tag2))
                .build();
        Mission mission2 = MissionTestData.defaultMission()
                .withTags(List.of(tag1))
                .build();
        Mission mission3 = MissionTestData.defaultMission()
                .withTags(List.of(tag2))
                .build();
        missionRepository.saveAll(List.of(mission1, mission2, mission3));


        // when
        List<Mission> missions = missionRepository.findAllByTagName("tag1");

        // then
        assertAll(
                () -> assertThat(missions).hasSize(2),
                () -> assertThat(missions).extracting(Mission::getMissionTags)
                        .allMatch(missionTags -> missionTags.stream()
                                .anyMatch(missionTag -> missionTag.isSameTagName("tag1")))

        );
    }
}
