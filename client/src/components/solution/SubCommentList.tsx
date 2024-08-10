import styled from '@emotion/styled';

import CommentItem from './CommentItem';

import { ReplyResponse } from '~/api/types';

interface SubCommentListProps {
  replies: ReplyResponse[];
  solutionId: number;
}

const SubCommentList = ({ replies, solutionId }: SubCommentListProps) => {
  if (replies.length === 0) return null;
  return (
    <Container>
      {replies.map((reply) => {
        return (
          <CommentItem
            key={reply.id}
            isSubcomment
            comment={reply}
            solutionId={solutionId}
          />
        );
      })}
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding-left: 1.5rem;
  padding-top: 1.5rem;
  gap: 1.5rem;
`;

export default SubCommentList;
