import { Layout } from "antd";
import { Input } from "antd";
import { EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import { Button, Radio, Form } from "antd";
import { Row, Col } from "antd";
import Router, { useRouter } from "next/router";
import { useCallback, useRef, useState } from "react";
import fetcher from "../common/fetcher";
import alertInfo, { timer } from "../common/alert";
import Link from "next/link";
import {
  validateEmail,
  validateID,
  validateName,
  validatePW,
  validatePWCheck,
} from "../common/validate_check";

const SignUp = () => {
  const { Header, Footer, Sider, Content } = Layout;
  const createSignUp = useRef<HTMLInputElement>(null);
  const router = useRouter();
  const [submitLoading, setSubmitLoading] = useState(false);

  const handleBack = (e) => {
    e.preventDefault();
    Router.back();
  };

  const handleFinish = async (values) => {
    setSubmitLoading(true);

    const data = {
      id: values.id,
      password: values.password,
      name: values.name,
      email: values.email,
    };

    try {
      // console.log("회원가입", data);
      const result = await fetcher("post", "/members/member", data);
      console.log("result2222", result);

      if (result.code === "1") {
        alertInfo(
          "축하드립니다.",
          "회원가입이 완료되었습니다. \n 로그인해주세요.",
          "success"
        );
        setTimeout(() => {
          setSubmitLoading(false);
          Router.push("/login");
        }, timer);
      } else {
        console.log("result.code", result.code);

        switch (result.code) {
          case 1001:
            return alertInfo(result.message, null, "error");

          default:
            alertInfo("안내", "관리자에게 문의해주세요.", "info");
            console.log("매개변수가 잘못되었습니다!");
        }
      }
    } catch (error) {
      setSubmitLoading(false);
      console.log("error", error);
    }
  };

  return (
    <>
      <div id="wrap">
        <div className="main-wrap">
          <Content className="sign-up">
            <div className="container">
              <h2 className="logo">
                <Link href="/">
                  <a>
                    <img src="/images/logo.svg" alt="삼삼오오 로고" />
                  </a>
                </Link>
              </h2>
              <Form onFinish={handleFinish}>
                <fieldset>
                  <legend>회원가입</legend>
                  <ul>
                    <li>
                      <p>아이디</p>
                      <Form.Item
                        className="form-item"
                        name="id"
                        rules={[{ validator: validateID(useCallback) }]}
                      >
                        <Input className="form-box" />
                      </Form.Item>
                    </li>
                    <li>
                      <p>비밀번호</p>
                      <span className="small-txt">
                        영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
                      </span>
                      <Form.Item
                        className="form-item"
                        name="password"
                        hasFeedback
                        rules={[{ validator: validatePW(useCallback) }]}
                      >
                        <Input.Password
                          className="form-box"
                          iconRender={(visible) =>
                            visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                          }
                        />
                      </Form.Item>
                    </li>
                    <li>
                      <p>비밀번호 확인</p>
                      <Form.Item
                        className="form-item"
                        name="pwRe"
                        dependencies={["password"]}
                        hasFeedback
                        rules={[
                          validatePWCheck.options,
                          ({ getFieldValue }) => ({
                            validator(_, value) {
                              return validatePWCheck.validate(
                                getFieldValue,
                                value
                              );
                            },
                          }),
                        ]}
                      >
                        <Input.Password
                          className="form-box"
                          iconRender={(visible) =>
                            visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                          }
                        />
                      </Form.Item>
                    </li>
                    <li>
                      <p>닉네임</p>
                      <span className="small-txt">
                        다른 유저와 겹치지 않는 별명을 입력해주세요. (2~8자)
                      </span>
                      <Form.Item
                        className="form-item"
                        name="name"
                        rules={[
                          () => ({
                            validator(_, value) {
                              return validateName(value);
                            },
                          }),
                        ]}
                      >
                        <Input className="form-box" />
                      </Form.Item>
                    </li>
                    <li>
                      <p>이메일</p>
                      <Form.Item
                        className="form-item"
                        name="email"
                        rules={[
                          () => ({
                            validator(_, value) {
                              return validateEmail(value);
                            },
                          }),
                        ]}
                      >
                        <Input className="form-box" />
                      </Form.Item>
                    </li>
                  </ul>

                  {/* button에서 앤트디자인 버튼으로 바꿔서 호버시 버튼색상 꺠지니 scss 조절해주세요 */}
                  <Button
                    htmlType="submit"
                    loading={submitLoading}
                    className="btn-50 btn-main"
                  >
                    회원가입
                  </Button>
                  {/* <button className="btn-40 btn-main" onClick={handleBack}>
                  돌아가기
                </button> */}
                </fieldset>
              </Form>
              <div className="login-link">
                <p>
                  이미 아이디가 있으신가요?&nbsp;
                  <Link href="/login">
                    <a>로그인</a>
                  </Link>
                </p>
              </div>
            </div>
          </Content>
        </div>
      </div>
    </>
  );
};

export default SignUp;
