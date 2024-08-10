import { useSuspenseQuery } from '@tanstack/react-query';
import axios from 'axios';

const PostAPI = {
  getPostById: async (id: number) => {
    const { data } = await axios.get<{
      userId: number;
      id: number;
      title: string;
      body: string;
    }>(`https://jsonplaceholder.typicode.com/posts/${id}`);

    return data;
  },
};

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
