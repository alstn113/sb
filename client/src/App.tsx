import styled from '@emotion/styled';
import Post from './components/Post';

const App = () => {
  return (
    <Container>
      <h1>테스트 페이지</h1>
      <Wrapper>
        {[-1, 1, 2, -2].map((id) => (
          <Post key={id} id={id} />
        ))}
      </Wrapper>
    </Container>
  );
};

const Container = styled.div`
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

export default App;
