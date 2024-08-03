package com.sb.application.solution;

import com.sb.application.member.MemberMapper;
import com.sb.application.mission.MissionMapper;
import com.sb.domain.solution.Solution;
import org.springframework.stereotype.Component;

@Component
public class SolutionMapper {

    private final MissionMapper missionMapper;
    private final MemberMapper memberMapper;

    public SolutionMapper(MissionMapper missionMapper, MemberMapper memberMapper) {
        this.missionMapper = missionMapper;
        this.memberMapper = memberMapper;
    }

    public SolutionResponse toResponse(Solution solution) {
        return new SolutionResponse(
                solution.getId(),
                missionMapper.toResponse(solution.getMission()),
                memberMapper.toResponse(solution.getMember()),
                solution.getTitle(),
                solution.getDescription(),
                solution.getUrl(),
                solution.getCreatedAt(),
                solution.getSubmittedAt()
        );
    }
}
