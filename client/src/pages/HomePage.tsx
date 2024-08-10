import styled from '@emotion/styled';
import Post from '../components/Post';
import BaseLayout from '~/components/layouts/BaseLayout';
import { Link } from 'react-router-dom';
import { Button } from '~/components/common';

const HomePage = () => {
  return (
    <BaseLayout>
      <Container>
        <Link to="/solutions/1">
          <Button shadow color="secondary">
            Go to Solution Page
          </Button>
        </Link>
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
  margin-top: 1rem;
`;

export default HomePage;
