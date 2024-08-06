import styled from '@emotion/styled';
import Post from '../components/Post';
import BaseLayout from '~/components/layouts/BaseLayout';

const HomePage = () => {
  return (
    <BaseLayout>
      <Container>
        <Wrapper>
          {[-1, 1, 2, -2].map((id) => (
            <Post key={id} id={id} />
          ))}
        </Wrapper>
      </Container>
    </BaseLayout>
  );
};

const Container = styled.div`
  margin-top: 30px;
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
