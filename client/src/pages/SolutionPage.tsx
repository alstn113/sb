import BaseLayout from '~/components/layouts/BaseLayout';
import styled from '@emotion/styled';
import { useParams } from 'react-router-dom';
import SolutionContent from '~/components/solution/SolutionContent';
import SuspensedErrorBoundary from '~/components/SuspensedErrorBoundary';

const SolutionPage = () => {
  const params = useParams<{ solutionId: string }>();
  const solutionId = Number(params.solutionId);

  return (
    <BaseLayout>
      <Text>SolutionPage</Text>
      <SuspensedErrorBoundary>
        <SolutionContent solutionId={solutionId} />
      </SuspensedErrorBoundary>
    </BaseLayout>
  );
};

const Text = styled.div`
  margin: 4rem auto;
  font-size: 3rem;
  text-align: center;
`;

export default SolutionPage;
