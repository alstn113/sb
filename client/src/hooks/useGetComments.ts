import { useSuspenseQuery } from '@tanstack/react-query';
import { CommentAPI } from '~/api';

const useGetComments = (solutionId: number) => {
  return useSuspenseQuery({
    queryKey: getKey(solutionId),
    queryFn: fetcher(solutionId),
  });
};

const getKey = (solutionId: number) => ['useGetComments', solutionId];
const fetcher = (solutionId: number) => async () =>
  await CommentAPI.getCommentsWithReplies(solutionId);

useGetComments.getkey = getKey;
useGetComments.fetcher = fetcher;

export default useGetComments;
