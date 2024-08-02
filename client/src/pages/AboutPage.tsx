import Navbar from '../components/Navbar';
import styled from '@emotion/styled';

const AboutPage = () => {
  return (
    <div>
      <Navbar />
      <Text>About</Text>
    </div>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default AboutPage;
