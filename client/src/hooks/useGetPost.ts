import { useSuspenseQuery } from '@tanstack/react-query';
import PostAPI from '../api/post.api';

const useGetPost = (postId: number) => {
  return useSuspenseQuery({
    queryKey: getKey(postId),
    queryFn: fetcher(postId),
  });
};

const getKey = (postId: number) => ['useGetPost', postId];
const fetcher = (postId: number) => async () =>
  await PostAPI.getPostById(postId);

useGetPost.getkey = getKey;
useGetPost.fetcher = fetcher;

export default useGetPost;
