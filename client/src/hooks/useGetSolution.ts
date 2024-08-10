import { useSuspenseQuery } from '@tanstack/react-query';
import { SolutionAPI } from '~/api';

const useGetSolution = (solutionId: number) => {
  return useSuspenseQuery({
    queryKey: getKey(solutionId),
    queryFn: fetcher(solutionId),
  });
};

const getKey = (solutionId: number) => ['useGetSolution', solutionId];
const fetcher = (solutionId: number) => async () =>
  await SolutionAPI.getSolutionById(solutionId);

useGetSolution.getkey = getKey;
useGetSolution.fetcher = fetcher;

export default useGetSolution;
