import { useQuery } from '@tanstack/react-query';
import { MemberAPI } from '~/api';

const useGetMe = () => {
  return useQuery({
    queryKey: getKey(),
    queryFn: fetcher(),
  });
};

const getKey = () => ['useGetMe'];
const fetcher = () => async () => await MemberAPI.getMe();

useGetMe.getkey = getKey;
useGetMe.fetcher = fetcher;

export default useGetMe;
