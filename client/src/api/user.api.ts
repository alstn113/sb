import { User } from '../types/user';
import apiClient from './apiClient';

export const UserAPI = {
  getMe: async () => {
    const { data } = await apiClient.get<User>('/members/me');
    return data;
  },
};
