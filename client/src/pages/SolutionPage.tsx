import BaseLayout from '~/components/layouts/BaseLayout';
import { useParams } from 'react-router-dom';
import SolutionContent from '~/components/solution/SolutionContent';
import SuspensedErrorBoundary from '~/components/SuspensedErrorBoundary';

const SolutionPage = () => {
  const params = useParams<{ solutionId: string }>();
  const solutionId = Number(params.solutionId);

  return (
    <BaseLayout>
      <SuspensedErrorBoundary>
        <SolutionContent solutionId={solutionId} />
      </SuspensedErrorBoundary>
    </BaseLayout>
  );
};

export default SolutionPage;
