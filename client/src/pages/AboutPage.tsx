import styled from '@emotion/styled';
import BaseLayout from '~/components/layouts/BaseLayout';

const AboutPage = () => {
  return (
    <BaseLayout>
      <Text>About</Text>
    </BaseLayout>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default AboutPage;
