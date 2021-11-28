import React, { useRef } from "react";
import { Layout } from "antd";
import { Input, Space } from "antd";
import { EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import { UserOutlined } from "@ant-design/icons";
import { Button } from "antd";
import fetcher from "../common/fetcher";
import TokenStorage from "../common/token";
import Router, { useRouter } from "next/router";
import alertInfo, { timer } from "../common/alert";
import { useForm } from "react-hook-form";

const Login = () => {
  const { Header, Footer, Sider, Content } = Layout;
  const router = useRouter();
  const tokenStorage = new TokenStorage();

  const inputID = useRef(null);
  const inputPW = useRef(null);

  //로그인 후 페이지 이동
  const onChangeLoginAfter = async (e) => {
    e.preventDefault();

    const loginForm = {
      id: inputID.current.state.value,
      password: inputPW.current.state.value,
    };

    try {
      const loginItem = await fetcher("post", "/login", loginForm);
      // const loginItem = {
      //   code: 0,
      //   message: "로그인 성공",
      //   data: {
      //     name: "이재원",
      //     token:
      //       "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzNjUyNzIyNiwiZXhwIjoxNjM2NTI5MDI2fQ.xFXIqdBVmijGmqaORHaVWnoQbbmZRmi8qYz6mUjy98Y",
      //   },
      // };

      console.log("loginItem", loginItem);
      if (loginItem.code === 0) {
        tokenStorage.saveToken(loginItem);
        alertInfo(loginItem.message, null, "info");

        setTimeout(() => {
          loginItem && router.push("/");
        }, timer);
      } else {
        alertInfo(loginItem.message, null, "warning");
      }
      // undefined가 로컬스토리지에 저장도지 않도록 반드시 loginItem && 앞에 붙여줘야함
      // JSON.stringify는 token.js에서 하도록 위임

      // router.back()을 사용하면 회원가입에서 로그인페이지로 넘어온 경우 다시 회원가입으로 넘기기 때문에  문제가 있음
    } catch (error) {
      // axios에서 에러발생시 처리
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
          case 500:
            return alertInfo(
              "오류",
              "서버에서 오류가 발생하였습니다.",
              "error"
            );
          default:
            alertInfo("안내", "관리자에게 문의해주세요.", "info");
            console.log("매개변수가 잘못되었습니다!");
        }
      }
    }
  };

  const handleBack = (e) => {
    e.preventDefault();
    Router.back();
  };

  const onKeyPress = (e) => {
    if (e.key == "Enter") {
      onChangeLoginAfter(e);
    }
  };
  return (
    <>
      <div id="wrap">
        <div className="main-wrap login">
          <Content>
            <Space direction="vertical">
              <h4 className="login-txt">삼삼오오에 오신 것을 환영합니다! </h4>
              <Input
                placeholder="아이디"
                prefix={<UserOutlined />}
                ref={inputID}
                onKeyPress={onKeyPress}
              />
              <Input.Password
                placeholder="비밀번호"
                ref={inputPW}
                onKeyPress={onKeyPress}
                iconRender={(visible) =>
                  visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                }
              />
              <Button type="primary" onClick={onChangeLoginAfter}>
                로그인
              </Button>
            </Space>
          </Content>

          <button onClick={handleBack}>돌아가기</button>
        </div>
      </div>
    </>
  );
};

export default Login;
