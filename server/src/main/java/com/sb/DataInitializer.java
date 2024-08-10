package com.sb;

import java.time.LocalDateTime;
import com.sb.domain.member.Member;
import com.sb.domain.member.MemberRepository;
import com.sb.domain.member.Provider;
import com.sb.domain.mission.Language;
import com.sb.domain.mission.Mission;
import com.sb.domain.mission.MissionRepository;
import com.sb.domain.solution.Solution;
import com.sb.domain.solution.SolutionRepository;
import com.sb.domain.solution.comment.Comment;
import com.sb.domain.solution.comment.CommentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Profile("dev")
public class DataInitializer implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final SolutionRepository solutionRepository;
    private final CommentRepository commentRepository;

    public DataInitializer(
            MemberRepository memberRepository,
            MissionRepository missionRepository,
            SolutionRepository solutionRepository,
            CommentRepository commentRepository
    ) {
        this.memberRepository = memberRepository;
        this.missionRepository = missionRepository;
        this.solutionRepository = solutionRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        initData();
    }

    public void initData() {
        Member member = memberRepository.save(new Member(
                "example@gmail.com",
                Provider.GITHUB,
                1234567890L,
                "example",
                "Example User",
                "https://avatars.githubusercontent.com/u/1234567890"
        ));

        Mission mission = missionRepository.save(new Mission(
                "title",
                Language.JAVA,
                "description",
                "https://thumbnail.com",
                "https://url.com"
        ));

        Solution solution = solutionRepository.save(new Solution(
                mission,
                member,
                "title",
                "description",
                "https://url.com",
                LocalDateTime.now()
        ));

        Comment root1 = commentRepository.save(new Comment(
                "root-1",
                solution,
                member
        ));
        Comment root2 = commentRepository.save(new Comment(
                "root-2",
                solution,
                member
        ));
        Comment reply1ToRoot1 = commentRepository.save(Comment.reply(
                "reply-1-to-root-1",
                solution,
                member,
                root1
        ));
        Comment reply1ToRoot2 = commentRepository.save(Comment.reply(
                "reply-1-to-root-2",
                solution,
                member,
                root2
        ));
        Comment reply2ToRoot1 = commentRepository.save(Comment.reply(
                "reply-2-to-root-1",
                solution,
                member,
                root1
        ));
    }
}
