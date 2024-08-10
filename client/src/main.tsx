import React from 'react';
import ReactDOM from 'react-dom/client';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

import App from './App.tsx';
import GlobalStyles from './GlobalStyles.tsx';
import { ChakraProvider } from '@chakra-ui/react';
import ModalProvider from './components/base/ModalProvider.tsx';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
    },
  },
});

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ChakraProvider>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={false} />
        <ModalProvider />
        <GlobalStyles />
        <App />
      </QueryClientProvider>
    </ChakraProvider>
  </React.StrictMode>
);
