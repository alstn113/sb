import Navbar from '../components/Navbar';
import styled from '@emotion/styled';

const NotFoundPage = () => {
  return (
    <div>
      <Navbar />
      <Text>404 Not Found</Text>
    </div>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default NotFoundPage;
