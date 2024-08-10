import { useRef } from 'react';
import styled from '@emotion/styled';
import CommentItem from '~/components/solution/CommentItem';
import useGetComments from '~/hooks/useGetComments';
import CommentInput from './CommentInput';

interface CommentListProps {
  solutionId: number;
}

const CommentList = ({ solutionId }: CommentListProps) => {
  const { data: comments } = useGetComments(solutionId);
  const commentListRef = useRef<HTMLDivElement>(null);

  return (
    <Container ref={commentListRef}>
      <CommentInput
        solutionId={solutionId}
        commentsCount={comments.length}
        commentListRef={commentListRef}
      />
      {comments.map((comment) => {
        return (
          <CommentItem
            key={comment.id}
            solutionId={solutionId}
            comment={comment}
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
