import { useState } from 'react';
import styled from '@emotion/styled';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import Trash from '~/components/vectors/Trash';
import formatDate from '~/lib/formatDate';
import { CommentResponse } from '~/api/types';
import { Avatar } from '../common';
import { AxiosError } from 'axios';
import useUser from '~/hooks/useUser';

import useModalStore from '~/stores/useModalStore';
import MoreVertMenu from './MoreVertMenu';
import ReplyComment from './ReplyComment';
import SubCommentList from './SubCommentList';
import useGetComments from '~/hooks/useGetComments';
import { CommentAPI } from '~/api';

interface Props {
  comment: CommentResponse;
  solutionId: number;
  isSubcomment?: boolean;
}

const CommentItem = ({ comment, solutionId, isSubcomment }: Props) => {
  const user = useUser();
  const queryClient = useQueryClient();
  const [isReplying, setIsReplying] = useState(false);
  const { openModal } = useModalStore();

  const isMyComment = user?.id === comment.member.id;
  const commentDate = formatDate(comment.createdAt);

  const { mutate } = useMutation({
    mutationFn: CommentAPI.deleteComment,
    onSuccess: async () => {
      await queryClient.refetchQueries({
        queryKey: useGetComments.getkey(solutionId),
      });
    },
    onError: (e: AxiosError) => {
      console.error(e);
      alert('Failed to delete comment');
    },
  });

  const handleDelete = () => {
    mutate(comment.id);
  };

  const handleOpenReply = () => {
    setIsReplying(true);
  };
  const handleCloseReply = () => {
    setIsReplying(false);
  };

  const items = [
    {
      icon: <Trash />,
      name: '삭제',
      onClick: () =>
        openModal({
          title: '댓글 삭제',
          message: '정말로 댓글을 삭제하시겠습니까?',
          confirmText: '확인',
          cancelText: '취소',
          onConfirm: handleDelete,
        }),
    },
  ];

  // if comment is deleted (show when comment is root comment and have replies)
  if (comment.isDeleted) {
    return (
      <Container>
        <DeletedText>삭제된 댓글입니다.</DeletedText>
        {!isSubcomment && comment.replies && (
          <SubCommentList replies={comment.replies} solutionId={solutionId} />
        )}
      </Container>
    );
  }

  // normal comment
  return (
    <Container>
      <CommentHeader>
        <LeftWrapper>
          <Avatar
            src={comment.member.avatarUrl}
            alt={`${comment.member.username}'s profile image`}
            size="lg"
          />
          <UserInfo>
            <Username>{comment.member.username}</Username>
            <Time>{commentDate}</Time>
          </UserInfo>
        </LeftWrapper>
        {isMyComment && <MoreVertMenu items={items} />}
      </CommentHeader>
      <CommentBody>
        <p>{comment.content}</p>
      </CommentBody>

      {/* if comment is not subcomment, show reply button */}
      {!isSubcomment && (
        <CommentFooter>
          <ReplyButton onClick={handleOpenReply}>답글</ReplyButton>
        </CommentFooter>
      )}

      {/* if reply button is clicked, show ReplyComment component */}
      {isReplying && (
        <ReplyComment
          isSubcomment={isSubcomment}
          parentComment={comment}
          onClose={handleCloseReply}
          solutionId={solutionId}
        />
      )}

      {/* if subcomments exist, show SubCommentList component */}
      {!isSubcomment && comment.replies && (
        <SubCommentList replies={comment.replies} solutionId={solutionId} />
      )}
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const CommentHeader = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const CommentBody = styled.div`
  margin-top: 0.3rem;
  margin-bottom: 0.5rem;
  p {
    color: #000;
    line-height: 1.5;
    font-size: 1rem;
    word-break: break-word;
  }
`;

const CommentFooter = styled.div`
  display: flex;
  align-items: center;
  flex-direction: row;
  gap: 0.5rem;
`;

const LeftWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  margin-left: 0.5rem;
`;

const Username = styled.div`
  font-size: 1rem;
  font-weight: 900;
  line-height: 1.5;
`;

const Time = styled.div`
  font-size: 0.8rem;
  line-height: 1.5;
  color: #999;
`;

const ReplyButton = styled.button`
  padding: 0;
  font-size: 0.9rem;
  color: #999;
`;

const DeletedText = styled.div`
  color: #999;
`;

export default CommentItem;
