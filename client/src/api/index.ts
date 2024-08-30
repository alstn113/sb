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
    const { data } = await apiClient.delete('/auth/logout');
    return data;
  },
};

export const MemberAPI = {
  getMe: async () => {
    const { data } = await apiClient.get<MemberResponse>('/members/me');
    return data;
  },
};

export const MissionAPI = {
  getMissions: async () => {
    const { data } = await apiClient.get<MissionResponse[]>('/missions');
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
    const { data } = await apiClient.get<SolutionResponse[]>('/solutions');
    return data;
  },

  getSolutionById: async (solutionId: number) => {
    const { data } = await apiClient.get<SolutionResponse>(
      `/solutions/${solutionId}`
    );
    return data;
  },

  startSolution: async (params: StartSolutionRequest) => {
    const { data } = await apiClient.post(`/solutions/start`, params);
    return data;
  },

  submitSolution: async (params: SubmitSolutionRequest) => {
    const { data } = await apiClient.post(`/solutions/submit`, params);
    return data;
  },
};

export const CommentAPI = {
  getCommentsWithReplies: async (solutionId: number) => {
    const { data } = await apiClient.get<CommentWithRepliesResponse>(
      `/solutions/${solutionId}/comments`
    );
    return data;
  },

  addComment: async (solutionId: number, params: CommentRequest) => {
    const { data } = await apiClient.post(
      `/solutions/${solutionId}/comments`,
      params
    );
    return data;
  },

  deleteComment: async (commentId: number) => {
    const { data } = await apiClient.delete(`/solutions/comments/${commentId}`);
    return data;
  },
};
