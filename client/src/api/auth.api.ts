import apiClient from './apiClient';

export const AuthAPI = {
  logout: async () => {
    const { data } = await apiClient.delete('/auth/logout');
    return data;
  },
};
