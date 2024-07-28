import styled from '@emotion/styled';
import { useSuspenseQuery } from '@tanstack/react-query';
import PostAPI from '../api/post';

interface Props {
  id: number;
}

const PostContents = ({ id }: Props) => {
  const { data } = useSuspenseQuery({
    queryKey: ['getPostById', id],
    queryFn: () => PostAPI.getPostById(id),
  });

  return <Text>{data.body}</Text>;
};

const Text = styled.div`
  font-size: 12px;
`;

export default PostContents;
