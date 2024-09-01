import {
  CommentRequest,
  CommentWithRepliesResponse,
  MemberResponse,
  MissionResponse,
  SolutionResponse,
  StartSolutionRequest,
  SubmitSolutionRequest,
} from '~/api/types';
import apiClient from './apiClient';

export const AuthAPI = {
  logout: async () => {
    const { data } = await apiClient.delete('/api/v1/auth/logout');
    return data;
  },
};

export const MemberAPI = {
  getMe: async () => {
    const { data } = await apiClient.get<MemberResponse>('/api/v1/members/me');
    return data;
  },
};

export const MissionAPI = {
  getMissions: async () => {
    const { data } = await apiClient.get<MissionResponse[]>('/api/v1/missions');
    return data;
  },

  getMissionById: async (missionId: number) => {
    const { data } = await apiClient.get<MissionResponse>(
      `/missions/${missionId}`
    );
    return data;
  },
};

export const SolutionAPI = {
  getSolutions: async () => {
    const { data } = await apiClient.get<SolutionResponse[]>(
      '/api/v1/solutions'
    );
    return data;
  },

  getSolutionById: async (solutionId: number) => {
    const { data } = await apiClient.get<SolutionResponse>(
      `/api/v1/solutions/${solutionId}`
    );
    return data;
  },

  startSolution: async (params: StartSolutionRequest) => {
    const { data } = await apiClient.post(`/api/v1/solutions/start`, params);
    return data;
  },

  submitSolution: async (params: SubmitSolutionRequest) => {
    const { data } = await apiClient.post(`/api/v1/solutions/submit`, params);
    return data;
  },
};

export const CommentAPI = {
  getCommentsWithReplies: async (solutionId: number) => {
    const { data } = await apiClient.get<CommentWithRepliesResponse>(
      `/api/v1/solutions/${solutionId}/comments`
    );
    return data;
  },

  addComment: async (solutionId: number, params: CommentRequest) => {
    const { data } = await apiClient.post(
      `/api/v1/solutions/${solutionId}/comments`,
      params
    );
    return data;
  },

  deleteComment: async (commentId: number) => {
    const { data } = await apiClient.delete(
      `/api/v1/solutions/comments/${commentId}`
    );
    return data;
  },
};
