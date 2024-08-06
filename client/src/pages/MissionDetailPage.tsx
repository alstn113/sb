import BaseLayout from '~/components/layouts/BaseLayout';
import styled from '@emotion/styled';
import { useParams } from 'react-router-dom';

const MissionDetailPage = () => {
  const { missionId } = useParams<{ missionId: string }>();

  return (
    <BaseLayout>
      <Text>Mission Id : {missionId}</Text>
    </BaseLayout>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default MissionDetailPage;
