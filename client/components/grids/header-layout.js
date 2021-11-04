import React, { useEffect, useState } from 'react';
import Router from 'next/router';
import fetcher from '../../common/fetcher';

import { Button, Radio } from 'antd';
import { Layout } from 'antd';
import Login from '../../pages/login';

const Headerlayout = () => {
  const { Header, Footer, Sider, Content } = Layout;
  const goToLoginpage = () => {
    Router.push('/login');
  };
  return (
    <>
      <div className="header-wrap">
        <Header>
          <Button type="primary" onClick={goToLoginpage}>
            로그인
          </Button>
        </Header>
      </div>
    </>
  );
};

export default Headerlayout;
