package com.sb.application.solution;

import java.util.List;
import com.sb.application.auth.Accessor;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import com.sb.domain.solution.Solution;
import com.sb.domain.solution.SolutionRepository;
import com.sb.infra.exception.ExceptionType;
import com.sb.infra.exception.SbException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;


    public List<SolutionResponse> getSolutions() {
        List<Solution> solutions = solutionRepository.findAll();

        return solutions.stream()
                .map(SolutionResponse::from)
                .toList();
    }

    public SolutionResponse getSolutionById(Long solutionId) {
        Solution solution = solutionRepository.getSolutionById(solutionId);

        return SolutionResponse.from(solution);
    }

    public SolutionResponse startSolution(Accessor accessor, StartSolutionRequest request) {
        Member member = memberRepository.getMemberById(accessor.id());
        Mission mission = missionRepository.getMissionById(request.missionId());

        validateIsInProgressSolution(member, mission);

        Solution solution = Solution.start(mission, member);
        Solution savedSolution = solutionRepository.save(solution);

        return SolutionResponse.from(savedSolution);
    }

    private void validateIsInProgressSolution(Member member, Mission mission) {
        boolean isInProgressSolutionExists = solutionRepository
                .existsByMember_IdAndMission_IdAndSubmittedAtIsNull(member.getId(), mission.getId());
        if (isInProgressSolutionExists) {
            throw new SbException(ExceptionType.SOLUTION_ALREADY_STARTED);
        }
    }

    public SolutionResponse submitSolution(Accessor accessor, SubmitSolutionRequest request) {
        Solution solution = solutionRepository.getSolutionById(request.solutionId());

        validateSolutionOwnerShip(accessor, solution);

        solution.submit(request.title(), request.description(), request.url());
        Solution savedSolution = solutionRepository.save(solution);

        return SolutionResponse.from(savedSolution);
    }

    private void validateSolutionOwnerShip(Accessor accessor, Solution solution) {
        if (!solution.isOwnedBy(accessor.id())) {
            throw new SbException(ExceptionType.SOLUTION_NOT_OWNED);
        }
    }
}
