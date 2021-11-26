import { Layout } from "antd";
import { Input } from "antd";
import { EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import { Button, Radio } from "antd";
import { Row, Col } from "antd";
import Router, { useRouter } from "next/router";
import { useRef } from "react";
import fetcher from "../common/fetcher";
import alertInfo, { timer } from "../common/alert";
import Link from "next/link";

const SignUp = () => {
  const { Header, Footer, Sider, Content } = Layout;
  const createSignUp = useRef<HTMLInputElement>(null);
  const router = useRouter();

  const handleBack = (e) => {
    e.preventDefault();
    Router.back();
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    console.log("회원가입");
    // const id = createSignUp.current.id.state.value;
    // const password = createSignUp.current.pw.state.value;
    // const rePW = createSignUp.current.rePW.state.value;
    // const name = createSignUp.current.name.state.value;
    // const email = createSigßUp.current.email.state.value;
    // //비밀번호 재확인 체크
    // if (password !== rePW) {
    //   alertInfo("비밀번호가 일치하지 않습니다.", null, "error");
    //   return;
    // }

    // const data = {
    //   id,
    //   password,
    //   name,
    //   email,
    // };

    // try {
    //   console.log("data", data);
    //   const result = await fetcher("post", "/member", data);
    //   console.log("result2222", result);

    //   if (result.code === "1") {
    //     alertInfo(
    //       "축하드립니다.",
    //       "회원가입이 완료되었습니다. \n 로그인해주세요.",
    //       "success"
    //     );
    //     setTimeout(() => {
    //       Router.push("/login");
    //     }, timer);
    //   } else {
    //     console.log("result.code", result.code);

    //     switch (result.code) {
    //       case 1001:
    //         return alertInfo(result.message, null, "error");

    //       default:
    //         alertInfo("안내", "관리자에게 문의해주세요.", "info");
    //         console.log("매개변수가 잘못되었습니다!");
    //     }
    //   }
    // } catch (error) {
    //   console.log("error", error);

    // }
  };

  const onError = (errors, e) => {
    console.log("회원가입실패", errors);
    errors && alertInfo("회원가입 실패", "양식에 맞게 작성해주세요.", "error");
  };

  return (
    <>
      <div id="wrap">
        <div className="main-wrap">
          <Content className="sign-up">
            <h2 className="logo">
              <Link href="/">
                <a>
                  <img src="/images/logo.png" alt="삼삼오오 로고" />
                </a>
              </Link>
            </h2>
            <form onSubmit={onSubmit}>
              <fieldset>
                <legend>회원가입</legend>
                <ul>
                  <li>
                    <p>아이디</p>
                    <Input />
                  </li>
                  <li>
                    <p>비밀번호</p>
                    <Input.Password
                      iconRender={(visible) =>
                        visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                      }
                    />
                  </li>
                  <li>
                    <p>비밀번호 재확인</p>
                    <Input.Password
                      iconRender={(visible) =>
                        visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                      }
                    />
                  </li>
                  <li>
                    <p>이름</p>
                    <Input />
                  </li>
                  <li>
                    <p>이메일</p>
                    <Input />
                  </li>
                </ul>

                <button className="btn-40 btn-main" onClick={onSubmit}>
                  회원가입
                </button>
                <button className="btn-40 btn-main" onClick={handleBack}>
                  돌아가기
                </button>
              </fieldset>
            </form>
          </Content>
        </div>
      </div>
    </>
  );
};

export default SignUp;
