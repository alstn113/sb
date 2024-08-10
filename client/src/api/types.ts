export interface MemberResponse {
  id: number;
  email: string | null;
  username: string;
  displayName: string;
  avatarUrl: string;
}

export interface MissionResponse {
  id: number;
  title: string;
  language: string;
  description: string;
  thumbnail: string;
  url: string;
}

export interface SolutionResponse {
  id: number;
  mission: MissionResponse;
  member: MemberResponse;
  title: string;
  description: string;
  url: string;
  submittedAt: string;
}

export interface StartSolutionRequest {
  missionId: number;
}

export interface SubmitSolutionRequest {
  solutionId: number;
  title: string;
  description: string;
  url: string;
}

export interface CommentRequest {
  parentCommentId: number | null;
  content: string;
}

export type CommentWithRepliesResponse = RootCommentResponse[];

export interface RootCommentResponse {
  id: number;
  content: string;
  member: MemberResponse;
  replies: ReplyResponse[];
  createdAt: string;
}

export interface ReplyResponse {
  id: number;
  parentCommentId: number;
  content: string;
  member: MemberResponse;
  createdAt: string;
}

export interface CommentResponse {
  id: number;
  content: string;
  member: MemberResponse;
  createdAt: string;

  parentCommentId?: number | null;
  replies?: ReplyResponse[];
}
