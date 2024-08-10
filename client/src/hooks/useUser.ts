import { useQueryClient } from '@tanstack/react-query';
import useGetMe from './useGetMe';
import { MemberResponse } from '~/api/types';

const useUser = () => {
  const queryClient = useQueryClient();
  const user = queryClient.getQueryData<MemberResponse>(useGetMe.getkey());

  return user as MemberResponse;
};

export default useUser;
