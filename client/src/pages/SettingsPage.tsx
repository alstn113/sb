import Navbar from '../components/Navbar';
import styled from '@emotion/styled';

const SettingsPage = () => {
  return (
    <div>
      <Navbar />
      <Text>Settings</Text>
    </div>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default SettingsPage;
