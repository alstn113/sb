import styled from '@emotion/styled';
import useGetMe from '../hooks/useGetMe';
import useUser from '../hooks/useUser';
import Navbar from '../components/Navbar';
import Post from '../components/Post';

const HomePage = () => {
  useGetMe();

  const user = useUser();

  return (
    <Container>
      <Navbar />
      <h1>테스트 페이지</h1>
      <div>{user ? `안녕하세요, ${user.username}님!` : '로그인 해주세요.'}</div>
      <Wrapper>
        {[-1, 1, 2, -2].map((id) => (
          <Post key={id} id={id} />
        ))}
      </Wrapper>
    </Container>
  );
};

const Container = styled.div`
  margin-top: 60px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
`;

const Wrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
  grid-gap: 1rem;
`;

export default HomePage;
