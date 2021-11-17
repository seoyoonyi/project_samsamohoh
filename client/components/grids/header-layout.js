import React, { useEffect, useState } from "react";
import TokenStorage from "../../common/token";
import Link from "next/link";

import { Button, Radio } from "antd";
import { Layout } from "antd";

const Headerlayout = ({ token, getToken }) => {
  const tokenStorage = new TokenStorage();

  const { Header, Footer, Sider, Content } = Layout;

  const onLoginOut = (e) => {
    e.preventDefault();
    tokenStorage.clearToken();
    getToken(null); //index token state 변경
  };

  return (
    <>
      <div className="header-wrap">
        <Header className="header">
          <h1 className="logo">
            {/* <img src="../../public/images/logo.png" alt="삼삼오오 로고" /> */}
            {/* 이미지 경로 잡는 방법 src에서 /기입하면 public으로 인식함 */}
            <img src="/images/logo.png" alt="삼삼오오 로고" />
          </h1>
          <nav className="pc-navi lg-only md-hidden sm-hidden">
            <ul>
              <li className="user-group">
                <div className="login">
                  {token ? (
                    <Link href="/login">
                      <a onClick="onLoginOut">로그아웃</a>
                    </Link>
                  ) : (
                    <Link href="/login">
                      <a>로그인</a>
                    </Link>
                  )}
                </div>
                <div className="sign-up">
                  <Link href="/sigin_up">
                    <a>회원가입</a>
                  </Link>
                </div>
              </li>
              <li className="new-writing">
                <Link href="/">
                  <a>새글쓰기</a>
                </Link>
              </li>
            </ul>
          </nav>

          <nav className="mob-navi md-only sm-only lg-hidden">
            <button className="menu-btn">
              <span></span>
              <span></span>
              <span></span>
            </button>
          </nav>

          <aside className="sidebar md-only sm-only lg-hidden">
            <ul>
              <li className="user-group">
                <div className="login">
                  {token ? (
                    <Link href="/login">
                      <a onClick="onLoginOut">로그아웃</a>
                    </Link>
                  ) : (
                    <Link href="/login">
                      <a>로그인</a>
                    </Link>
                  )}
                </div>
                <div className="register">
                  <Link href="/register">
                    <a>회원가입</a>
                  </Link>
                </div>
              </li>
              <li className="new-writing">
                <Link href="/">
                  <a>새글쓰기</a>
                </Link>
              </li>
            </ul>
          </aside>
        </Header>
      </div>
    </>
  );
};

export default Headerlayout;
