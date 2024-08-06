export const BASE_URL: string =
  import.meta.env.MODE === 'development'
    ? 'http://localhost:8080'
    : 'https://api.alstn113.pro';

export const GITHUB_OAUTH_LOGIN_URL = `${BASE_URL}/auth/social/redirect/github`;

export const PAGE_LIST = {
  HOME: '/',
  ABOUT: '/about',
  MISSION_DETAIL: '/missions/:missionId',
  SOLUTION: '/solutions',
  SETTINGS: '/settings',
};
