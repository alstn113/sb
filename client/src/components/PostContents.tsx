import styled from '@emotion/styled';
import useGetPost from '../hooks/useGetPost';

interface Props {
  id: number;
}

const PostContents = ({ id }: Props) => {
  const { data } = useGetPost(id);

  return <Text>{data.body}</Text>;
};

const Text = styled.div`
  font-size: 12px;
`;

export default PostContents;
