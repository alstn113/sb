import styled from '@emotion/styled';
import useGetSolution from '~/hooks/useGetSolution';
import formatDate from '~/lib/formatDate';
import { mediaQuery } from '~/lib/styles';
import CommentList from './CommentList';

interface SolutionContentProps {
  solutionId: number;
}

const SolutionContent = ({ solutionId }: SolutionContentProps) => {
  const { data: solution } = useGetSolution(solutionId);
  const submittedDate = formatDate(solution.submittedAt);

  return (
    <Container>
      <Thumbnail src={solution.mission.thumbnail} alt="thumbnail" />
      <Title>{solution.title}</Title>
      <Content>{solution.description}</Content>
      <Group>
        <Author>
          by <b>{solution?.member.username}</b> · {submittedDate}
        </Author>
      </Group>
      <CommentList solutionId={solutionId} />
    </Container>
  );
};

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 2rem 1rem;
  gap: 1rem;
  ${mediaQuery.tablet} {
    width: 736px;
    margin: 0 auto;
  }
`;

const Title = styled.div`
  font-size: 2rem;
  line-height: 1.2;
  font-weight: 900;
`;

const Content = styled.div`
  font-size: 1rem;
  line-height: 1.5;
  white-space: pre-wrap;
`;

const Author = styled.div`
  font-size: 0.8rem;
  font-weight: 500;
  color: #999;

  b {
    font-weight: 900;
    color: #cc6600;
  }
`;

const Group = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
`;

const Thumbnail = styled.img`
  width: 100%;
  height: 100%;
  max-height: 300px;
  object-fit: cover;
  border-radius: 0.8rem;
  margin-bottom: 1rem;
`;

export default SolutionContent;
