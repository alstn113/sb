import styled from '@emotion/styled';
import { GITHUB_OAUTH_LOGIN_URL } from '../constants/properties';
import { Link, useLocation } from 'react-router-dom';

const Navbar = () => {
  const location = useLocation();

  const handleGithubLogin = () => {
    window.location.href = `${GITHUB_OAUTH_LOGIN_URL}?next=${location.pathname}`;
  };

  return (
    <Container>
      <Link to="/">홈</Link>
      <Link to="/about">소개</Link>
      <Link to="/settings">설정</Link>
      <div>
        <button onClick={handleGithubLogin}>깃허브 로그인</button>
      </div>
    </Container>
  );
};

const Container = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 60px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
`;

export default Navbar;
