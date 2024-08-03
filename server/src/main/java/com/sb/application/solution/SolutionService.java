package com.sb.application.solution;

import com.sb.application.auth.Accessor;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import com.sb.domain.solution.Solution;
import com.sb.domain.solution.SolutionRepository;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final SolutionMapper solutionMapper;

    public SolutionService(
            SolutionRepository solutionRepository,
            MissionRepository missionRepository,
            MemberRepository memberRepository,
            SolutionMapper solutionMapper
    ) {
        this.solutionRepository = solutionRepository;
        this.missionRepository = missionRepository;
        this.memberRepository = memberRepository;
        this.solutionMapper = solutionMapper;
    }

    @Transactional
    public SolutionResponse startSolution(Accessor accessor, StartSolutionRequest request) {
        Member member = memberRepository.findById(accessor.id())
                .orElseThrow(() -> new SbException(ExceptionType.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new SbException(ExceptionType.MISSION_NOT_FOUND));

        solutionRepository.findByMember_IdAndMission_IdAndSubmittedAtIsNull(accessor.id(), request.missionId())
                .orElseThrow(() -> new SbException(ExceptionType.SOLUTION_ALREADY_STARTED));

        Solution solution = Solution.createInitialSolution(mission, member);
        Solution savedSolution = solutionRepository.save(solution);

        return solutionMapper.toResponse(savedSolution);
    }

    @Transactional
    public SolutionResponse submitSolution(Accessor accessor, SubmitSolutionRequest request) {
        Solution solution = solutionRepository.findById(request.solutionId())
                .orElseThrow(() -> new SbException(ExceptionType.SOLUTION_NOT_FOUND));

        if (!solution.isOwnedBy(accessor.id())) {
            throw new SbException(ExceptionType.SOLUTION_NOT_OWNED);
        }

        solution.submit(request.title(), request.description(), request.url());
        Solution savedSolution = solutionRepository.save(solution);

        return solutionMapper.toResponse(savedSolution);
    }
}
