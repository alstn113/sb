import React from 'react';
import Header from '~/components/base/Header';
import FullHeightPage from '../base/FullHeightPage';
import styled from '@emotion/styled';

interface Props {
  children: React.ReactNode;
}

const BaseLayout = ({ children }: Props) => {
  return (
    <FullHeightPage>
      <Header />
      <Content>{children}</Content>
    </FullHeightPage>
  );
};

const Content = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
  overflow: scroll;
  overflow-x: hidden;
  background: #fff;
  padding-top: 4rem;
`;

export default BaseLayout;
