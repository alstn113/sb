import { useQueryClient } from '@tanstack/react-query';
import { User } from '../types';
import useGetMe from './useGetMe';

const useUser = () => {
  const queryClient = useQueryClient();
  const user = queryClient.getQueryData<User>(useGetMe.getkey());
  return user as User;
};

export default useUser;
