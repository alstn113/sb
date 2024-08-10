import { useState } from 'react';

import styled from '@emotion/styled';
import { useMutation, useQueryClient } from '@tanstack/react-query';

import useGetComments from '~/hooks/useGetComments';

import sleep from '~/lib/sleep';
import { CommentAPI } from '~/api';
import { CommentRequest } from '~/api/types';
import { AxiosError } from 'axios';

interface CommentInputProps {
  solutionId: number;
  commentsCount: number;
  commentListRef: React.RefObject<HTMLDivElement>;
}

const CommentInput = ({
  solutionId,
  commentsCount,
  commentListRef,
}: CommentInputProps) => {
  const [content, setContent] = useState('');
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: (params: CommentRequest) =>
      CommentAPI.addComment(solutionId, params),
    onSuccess: async () => {
      await queryClient.refetchQueries({
        queryKey: useGetComments.getkey(solutionId),
      });
      await sleep(500);
      commentListRef.current?.scrollIntoView({
        behavior: 'smooth',
        block: 'end',
        inline: 'nearest',
      });
    },
    onError: (e: AxiosError) => {
      console.error(e);
      alert('Failed to create comment');
    },
  });

  const handleSubmit: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    if (!content) return;
    mutate({ parentCommentId: null, content });
    setContent('');
  };

  return (
    <Form onSubmit={handleSubmit}>
      <FormTitle>{commentsCount.toLocaleString()} Comments</FormTitle>
      <Input
        type="text"
        placeholder="Write Comment..."
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
    </Form>
  );
};

const Form = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const FormTitle = styled.div`
  width: 100%;
  margin: 0.5rem 0;
  font-size: 1.2rem;
  font-weight: 700;
  display: flex;
  justify-content: flex-start;
`;

const Input = styled.input`
  width: 100%;
  height: 100%;
  padding: 0 16px;
  border: 1px solid #cccccc;
  width: 100%;
  height: 45px;
  border-radius: 8px;
  &:focus {
    border: 1px solid #2c2c2c;
  }
`;

export default CommentInput;
