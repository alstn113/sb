package com.sb;

import java.time.LocalDateTime;
import java.util.List;
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
        Member member1 = memberRepository.save(new Member(
                "alice@gmail.com",
                Provider.GITHUB,
                111111L,
                "Alice",
                "Alice",
                "https://cdn-icons-png.flaticon.com/128/4472/4472552.png"
        ));

        Member member2 = memberRepository.save(new Member(
                "bob@gmail.com",
                Provider.GITHUB,
                222222L,
                "Bob",
                "Bob",
                "https://cdn-icons-png.flaticon.com/128/4472/4472525.png"
        ));

        Member member3 = memberRepository.save(new Member(
                "charlie@gmail.com",
                Provider.GITHUB,
                333333L,
                "Charlie",
                "Charlie",
                "https://cdn-icons-png.flaticon.com/128/4472/4472516.png"
        ));

        Mission mission = missionRepository.save(new Mission(
                "title",
                Language.JAVA,
                "description",
                "https://i.pinimg.com/736x/a0/cb/f1/a0cbf19c00e37316ae5c5f69b2d69020.jpg",
                "https://url.com",
                List.of()
        ));

        Solution solution = solutionRepository.save(new Solution(
                mission,
                member1,
                "제목: 왜 사람들은 실패를 두려워할까?",
                "성공의 반대말이 정말 실패일까요? 사실 성공과 실패는 서로 뗄 수 없는 관계입니다. 실패를 경험하지 않고서는 진정한 성공을 이루기 어렵다는 말도 있죠. 그런데도 많은 사람들이 실패를 두려워합니다. 왜 그럴까요?\n" +
                "\n" +
                "첫 번째 이유는 사회적 인식입니다. 우리는 어려서부터 실패를 부정적인 것으로 배우며 자라왔습니다. 시험에서 떨어지면 혼나고, 목표를 달성하지 못하면 좌절감을 느끼죠. 이로 인해 실패는 부정적이고 피해야 할 대상으로 자리 잡게 됩니다.\n" +
                "\n" +
                "두 번째 이유는 자존감입니다. 누구나 자신을 가치 있게 여기고 싶어 합니다. 그러나 실패는 우리 자신에 대한 신뢰를 무너뜨릴 수 있습니다. 반복된 실패는 새로운 도전을 두렵게 만들고, 안전한 선택만 하게 하죠. 결국, 우리는 성장의 기회를 잃게 됩니다.\n" +
                "\n" +
                "하지만 실패는 나쁜 것만은 아닙니다. 실패를 통해 우리는 중요한 교훈을 얻고, 자신의 한계를 인식하며, 부족한 점을 보완할 기회를 갖게 됩니다. 실패는 우리의 회복력을 키워주며, 다시 일어설 수 있는 힘이야말로 진정한 성공의 열쇠가 될 것입니다.\n" +
                "\n" +
                "많은 성공한 사람들 역시 수많은 실패를 겪었습니다. 그들은 실패를 두려워하지 않고, 이를 통해 배우며 더 나은 선택을 위해 노력했습니다. 이제는 실패를 두려워하는 대신, 이를 성장의 기회로 삼는 방법을 고민해야 할 때입니다. 실패는 끝이 아니라 더 큰 성공으로 가는 여정의 일부일 뿐입니다.",
                "https://url.com",
                LocalDateTime.now()
        ));

        Comment root1 = commentRepository.save(new Comment(
                "맞아요, 저도 실패에 대해 부정적인 감정이 많았는데, 결국 실패가 저를 성장하게 만든 것 같아요. 특히 첫 직장에서 해고당했을 때, 정말 힘들었지만 그 경험이 저를 더 강하게 만들어 줬어요. 다시 일어나서 새로운 도전을 했고, 지금은 그때보다 훨씬 나은 자리에 있어요.",
                solution,
                member1
        ));
        Comment root2 = commentRepository.save(new Comment(
                "그런데 실패가 반복되면 진짜로 힘들어지지 않나요? 저는 최근 몇 년 동안 연달아 실패만 하다 보니 스스로에 대한 믿음이 많이 흔들렸어요. 이럴 때는 어떻게 해야 할까요?",
                solution,
                member2
        ));
        Comment reply1ToRoot1 = commentRepository.save(Comment.reply(
                "저도 비슷한 경험을 했어요. 첫 번째 창업에서 큰 실패를 겪었을 때 세상이 무너지는 줄 알았어요. 그런데 시간이 지나고 나서 보니 그 실패가 없었더라면 지금의 저도 없었겠다는 생각이 들어요. 실패가 오히려 저를 더 단단하게 만들어 준 것 같아요.",
                solution,
                member2,
                root1
        ));
        Comment reply1ToRoot2 = commentRepository.save(Comment.reply(
                "Alice 님, Bob 님 두 분 다 정말 대단하세요. 저는 아직도 실패가 두려워서 새로운 시도를 망설이는데, 두 분 이야기를 들으니 용기가 생기네요. 실패를 두려워하지 않고 앞으로 나아가야겠다는 생각이 들어요.",
                solution,
                member3,
                root1
        ));
        Comment reply2ToRoot1 = commentRepository.save(Comment.reply(
                "Bob 님, 그 마음 충분히 이해해요. 저도 실패가 계속될 때는 자존감이 바닥을 칠 때가 있었어요. 그럴 때 저는 잠시 멈추고, 제가 왜 이 길을 선택했는지 다시 생각해보곤 했어요. 그리고 작은 성취라도 하나씩 쌓아가면서 다시 자신감을 회복하려고 노력했죠.1",
                solution,
                member1,
                root2
        ));
    }
}
