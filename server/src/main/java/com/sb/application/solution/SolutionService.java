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

    @Transactional(readOnly = true)
    public void getMyInProgressSolution(Accessor accessor) {
    }

    @Transactional(readOnly = true)
    public void getMySubmittedSolution(Accessor accessor) {
    }

    @Transactional
    public SolutionResponse startSolution(Accessor accessor, StartSolutionRequest request) {
        Member member = memberRepository.getMemberById(accessor.id());
        Mission mission = missionRepository.getMissionById(request.missionId());

        validateIsInProgressSolution(member, mission);

        Solution solution = Solution.createInitialSolution(mission, member);
        Solution savedSolution = solutionRepository.save(solution);

        return solutionMapper.toResponse(savedSolution);
    }

    private void validateIsInProgressSolution(Member member, Mission mission) {
        boolean isInProgressSolutionExists = solutionRepository
                .existsInProgressSolution(member.getId(), mission.getId());
        if (isInProgressSolutionExists) {
            throw new SbException(ExceptionType.SOLUTION_ALREADY_STARTED);
        }
    }

    @Transactional
    public SolutionResponse submitSolution(Accessor accessor, SubmitSolutionRequest request) {
        Solution solution = solutionRepository.getSolutionById(request.solutionId());

        validateSolutionOwnerShip(accessor, solution);

        solution.submit(request.title(), request.description(), request.url());
        Solution savedSolution = solutionRepository.save(solution);

        return solutionMapper.toResponse(savedSolution);
    }

    private void validateSolutionOwnerShip(Accessor accessor, Solution solution) {
        if (!solution.isOwnedBy(accessor.id())) {
            throw new SbException(ExceptionType.SOLUTION_NOT_OWNED);
        }
    }
}
