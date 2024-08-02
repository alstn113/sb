import { useQuery } from '@tanstack/react-query';
import { UserAPI } from '../api/user.api';

const useGetMe = () => {
  return useQuery({
    queryKey: getKey(),
    queryFn: fetcher(),
  });
};

const getKey = () => ['useGetMe'];
const fetcher = () => async () => await UserAPI.getMe();

useGetMe.getkey = getKey;
useGetMe.fetcher = fetcher;

export default useGetMe;
