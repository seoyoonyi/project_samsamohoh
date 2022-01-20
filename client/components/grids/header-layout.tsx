import React, { useEffect } from "react";
import TokenStorage from "../../common/token";
import Link from "next/link";
import { Layout } from "antd";
import { useRecoilState } from "recoil";
import { tokenAtrom } from "../../atoms/token";
import { onLoginOut } from "../../common/logout";

const Headerlayout = () => {
  const tokenStorage = new TokenStorage();
  const [token, setToken] = useRecoilState(tokenAtrom);
  const { Header, Footer, Sider, Content } = Layout;
  useEffect(() => {
    const getToken = tokenStorage.getToken();
    setToken(getToken);
  }, []);

  return (
    <>
      <div id="header-wrap">
        <Header className="header">
          <div className="container">
            <h1 className="logo">
              <Link href="/">
                <a>
                  <img src="/images/logo.svg" alt="삼삼오오 로고" />
                </a>
              </Link>
            </h1>
            <nav className="pc-navi lg-only md-hidden sm-hidden">
              <ul>
                <li className="user-group">
                  <div className="login">
                    {token ? (
                      <Link href="/login">
                        <a onClick={(e) => onLoginOut(e, setToken)}>로그아웃</a>
                      </Link>
                    ) : (
                      <Link href="/login">
                        <a>로그인</a>
                      </Link>
                    )}
                  </div>
                  <div className="sign-up">
                    <Link href="/sign_up">
                      <a>회원가입</a>
                    </Link>
                  </div>
                </li>
                <li className="new-writing">
                  <Link href="/">
                    <a className="btn-main btn-40">새글쓰기</a>
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
                        <a onClick={(e) => onLoginOut(e, setToken)}>로그아웃</a>
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
          </div>
        </Header>
      </div>
    </>
  );
};

export default Headerlayout;
