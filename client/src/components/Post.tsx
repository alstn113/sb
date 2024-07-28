import styled from '@emotion/styled';

import PostContents from './PostContents';
import SuspensedErrorBoundary from './SuspensedErrorBoundary';

interface PostProps {
  id: number;
}

const Post = ({ id }: PostProps) => {
  return (
    <ContentWrapper>
      <SuspensedErrorBoundary>
        <PostContents id={id} />
      </SuspensedErrorBoundary>
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
