import { useRef } from 'react';
import styled from '@emotion/styled';
import CommentItem from '~/components/solution/CommentItem';
import useGetComments from '~/hooks/useGetComments';
import CommentInput from './CommentInput';

interface CommentListProps {
  solutionId: number;
}

const CommentList = ({ solutionId }: CommentListProps) => {
  const { data: rootComments } = useGetComments(solutionId);
  const commentListRef = useRef<HTMLDivElement>(null);

  return (
    <Container ref={commentListRef}>
      <CommentInput
        solutionId={solutionId}
        commentsCount={rootComments.length}
        commentListRef={commentListRef}
      />
      {rootComments.map((rootComment) => {
        return (
          <CommentItem
            key={rootComment.id}
            solutionId={solutionId}
            comment={rootComment}
          />
        );
      })}
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
`;

export default CommentList;
