import styled from '@emotion/styled';
import { AxiosError } from 'axios';

interface ErrorFallbackProps {
  error: AxiosError;
  resetErrorBoundary: () => void;
}

const ErrorFallback = ({ error, resetErrorBoundary }: ErrorFallbackProps) => {
  const status = error.response?.status;

  switch (status) {
    case 404:
      return (
        <Wrapper>
          <p>404 Not Found</p>
          <pre>{error.message}</pre>
          <Button onClick={resetErrorBoundary}>Try again</Button>
        </Wrapper>
      );
    default:
      return (
        <Wrapper>
          <p>Something went wrong:</p>
          <pre>{error.message}</pre>
          <Button onClick={resetErrorBoundary}>Try again</Button>
        </Wrapper>
      );
  }
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
`;

const Button = styled.button`
  background-color: #b2efa6;
  border: 1px solid #5cb85c;
  border-radius: 0.5rem;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  cursor: pointer;
`;

export default ErrorFallback;
