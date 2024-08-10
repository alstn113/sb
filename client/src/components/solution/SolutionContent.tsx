import useGetSolution from '~/hooks/useGetSolution';

interface SolutionContentProps {
  solutionId: number;
}

const SolutionContent = ({ solutionId }: SolutionContentProps) => {
  const { data: solution } = useGetSolution(solutionId);

  return (
    <div>
      <h1>{solution.title}</h1>
      <p>{solution.description}</p>
    </div>
  );
};

export default SolutionContent;
