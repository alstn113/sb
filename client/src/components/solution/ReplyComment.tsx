import { useState } from 'react';

import { css } from '@emotion/react';
import styled from '@emotion/styled';
import { useMutation, useQueryClient } from '@tanstack/react-query';

import { Button } from '~/components/common';

import { AxiosError } from 'axios';
import { CommentRequest, CommentResponse } from '~/api/types';
import { CommentAPI } from '~/api';
import useGetComments from '~/hooks/useGetComments';

interface ReplyCommentProps {
  parentComment: CommentResponse;
  isSubcomment?: boolean;
  onClose: () => void;
  solutionId: number;
}

const ReplyComment = ({
  parentComment,
  onClose,
  solutionId,
}: ReplyCommentProps) => {
  const [content, setContent] = useState('');
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: (params: CommentRequest) =>
      CommentAPI.addComment(solutionId, params),
    onSuccess: async () => {
      await queryClient.refetchQueries({
        queryKey: useGetComments.getkey(solutionId),
      });
      return;
    },
    onError: (e: AxiosError) => {
      console.error(e);
      alert('Failed to create comment');
    },
  });

  const handleSubmit = () => {
    if (!content) return;
    mutate({
      parentCommentId: parentComment.id,
      content: content,
    });
    onClose();
  };

  return (
    <Container>
      <Input
        type="content"
        placeholder="Write Comment..."
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />

      <ButtonsWrapper>
        <Button shadow color="success" size="sm" onClick={handleSubmit}>
          Confirm
        </Button>
        <Button shadow color="error" size="sm" onClick={onClose}>
          Cancel
        </Button>
      </ButtonsWrapper>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding-top: 1.5rem;
  padding-left: 1.5rem;
`;

const Input = styled.input<{ mention?: boolean }>`
  width: 100%;
  padding: 0 16px;
  border: 1px solid #cccccc;
  height: 40px;
  border-radius: 8px;
  &:focus {
    border: 1px solid #2c2c2c;
  }

  ${({ mention }) =>
    mention &&
    css`
      border-radius: 0 8px 8px 0;
    `}
`;

const ButtonsWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
  gap: 1rem;
  margin-top: 1rem;
`;

export default ReplyComment;
