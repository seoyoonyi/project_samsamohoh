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
  const goToRegisterpage = () => {
    Router.push('/register');
  };
  return (
    <>
      <div className="header-wrap">
        <Header>
          <h1 className="logo">
            <img src="../../public/images/logo.png" alt="삼삼오오 로고" />
          </h1>
          <nav className="navigation">
            <Button type="primary">새글쓰기</Button>
            <Button type="primary" onClick={goToLoginpage}>
              로그인
            </Button>
            <Button type="primary" onClick={goToRegisterpage}>
              회원가입
            </Button>
          </nav>
        </Header>
      </div>
    </>
  );
};

export default Headerlayout;
