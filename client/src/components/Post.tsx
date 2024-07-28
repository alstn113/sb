import { Suspense } from 'react';
import { ErrorBoundary } from 'react-error-boundary';
import { QueryErrorResetBoundary } from '@tanstack/react-query';
import styled from '@emotion/styled';

import ErrorFallback from './ErrorFallback';
import PostContents from './PostContents';
import LoadingSpinner from './LoadingSpinner';

interface PostProps {
  id: number;
}

const Post = ({ id }: PostProps) => {
  return (
    <ContentWrapper>
      <QueryErrorResetBoundary>
        {({ reset }) => (
          <ErrorBoundary
            onReset={reset}
            fallbackRender={({ error, resetErrorBoundary }) => (
              <ErrorFallback
                error={error}
                resetErrorBoundary={resetErrorBoundary}
              />
            )}
          >
            <Suspense fallback={<LoadingSpinner />}>
              <PostContents id={id} />
            </Suspense>
          </ErrorBoundary>
        )}
      </QueryErrorResetBoundary>
    </ContentWrapper>
  );
};

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #fed286;
  border-radius: 1rem;
  width: 350px;
  height: 350px;
  padding: 3rem;
  word-wrap: break-word;
`;

export default Post;
