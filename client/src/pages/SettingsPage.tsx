import BaseLayout from '~/components/layouts/BaseLayout';
import styled from '@emotion/styled';

const SettingsPage = () => {
  return (
    <BaseLayout>
      <Text>Settings</Text>
    </BaseLayout>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default SettingsPage;
