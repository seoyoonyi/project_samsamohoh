import React, { useCallback, useState } from "react";
import { Layout, Input, Form, Button, Checkbox } from "antd";
import { EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import { UserOutlined } from "@ant-design/icons";
import Link from "next/link";
import fetcher from "../common/fetcher";
import TokenStorage from "../common/token";
import { useRouter } from "next/router";
import alertInfo, { timer } from "../common/alert";
import { validateID, validatePW } from "../common/validate_check";
import { useRecoilState } from "recoil";
import { tokenAtrom } from "../atoms/token";
import { useEffect } from "react";
import { onLoginOut } from "../common/logout";
import Headerlayout from "../components/grids/header-layout";

const Login = () => {
  const { Content } = Layout;
  const router = useRouter();
  const tokenStorage = new TokenStorage();
  const [submitLoading, setSubmitLoading] = useState(false);
  const [_, setToken] = useRecoilState(tokenAtrom);

  useEffect(() => {
    onLoginOut(_, setToken, router);
  }, []);

  const movePage = (loginItem, _msg, _pageName) => {
    alertInfo(_msg, null, "info");
    setTimeout(() => {
      setSubmitLoading(false);
      loginItem && router.push(_pageName);
    }, timer);
  };

  const handleFinish = async (values) => {
    setSubmitLoading(true);
    const loginForm = {
      id: values.id,
      password: values.pw,
    };

    try {
      const loginItem = await fetcher("post", "/auth/signin", loginForm);
      console.log("loginItem", loginItem);
      if (loginItem.code === 1) {
        tokenStorage.saveToken(loginItem.data); // 브라우저 종료 후에도 로그인 유지하기 위함

        // 사용자의 닉네임 유무에 따른 페이지 이동 분기처리
        const { nickName } = loginItem.data;

        if (nickName) {
          movePage(loginItem, loginItem.message, "/");
        } else {
          //닉네임이 없는 경우
          movePage(loginItem, "프로필 작성 페이지로 이동합니다.", "/profile");
        }
      } else {
        alertInfo(loginItem.message, null, "warning");
      }
    } catch (error) {
      // axios에서 에러발생시 처리
      setSubmitLoading(false);
      console.error("error", error.response);
      if (error.response) {
        const { status, statusText } = error.response;
        switch (status) {
          case 401:
            return alertInfo(
              "오류",
              "닉네임 또는 비밀번호를 잘못 입력하였습니다.",
              "error"
            );
          case 400:
            return alertInfo("오류", "올바르게 입력해주세요.", "error");
          case 404:
            return alertInfo("오류", "사용자를 찾을 수 없습니다.", "error");
          case 409:
            return alertInfo("오류", "이 사용자는 이미 존재합니다.", "wraning");
          case 415:
          case 500:
            return alertInfo("서버에서 오류가 발생하였습니다.", null, "error");
          default:
            alertInfo("안내", "관리자에게 문의해주세요.", "info");
            console.log("매개변수가 잘못되었습니다!");
        }
      }
    }
  };

  return (
    <>
      <div id="wrap">
        <Headerlayout />
        <div className="main-wrap login-wrap">
          <Content className="login">
            <div className="container">
              {/* 헤드가 생긴후 로고 주석처리 */}
              {/* <h2 className="logo">
                <Link href="/">
                  <a>
                    <img src="/images/logo.svg" alt="삼삼오오 로고" />
                  </a>
                </Link>
              </h2> */}
              <Form onFinish={handleFinish}>
                <Form.Item
                  name="id"
                  rules={[{ validator: validateID(useCallback) }]}
                >
                  <Input
                    className="login-box"
                    placeholder="아이디"
                    prefix={<UserOutlined />}
                  />
                </Form.Item>
                <Form.Item
                  name="pw"
                  rules={[{ validator: validatePW(useCallback) }]}
                >
                  <Input.Password
                    className="login-box"
                    placeholder="비밀번호"
                    iconRender={(visible) =>
                      visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                    }
                  />
                </Form.Item>
                <div className="auto-login">
                  <Checkbox>로그인 상태 유지</Checkbox>
                </div>

                {/* button에서 앤트디자인 버튼으로 바꿔서 호버시 버튼색상 꺠지니 scss 조절해주세요 */}
                <Button
                  type="primary"
                  className="login-btn btn-50 btn-main"
                  htmlType="submit"
                  loading={submitLoading}
                >
                  로그인
                </Button>
                <ul className="login-sub-menu">
                  <li>
                    <Link href="/">
                      <a>비밀번호 찾기</a>
                    </Link>
                  </li>
                  <li>
                    <Link href="/sign_up">
                      <a>회원가입</a>
                    </Link>
                  </li>
                </ul>
              </Form>
            </div>
          </Content>
        </div>
      </div>
    </>
  );
};

export default Login;
