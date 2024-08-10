import BaseLayout from '~/components/layouts/BaseLayout';
import styled from '@emotion/styled';
import { useParams } from 'react-router-dom';

const MissionDetailPage = () => {
  const params = useParams<{ missionId: string }>();
  const missionId = Number(params.missionId);

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
