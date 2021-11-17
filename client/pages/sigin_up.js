import { Layout } from "antd";
import { Input } from "antd";
import { EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import { Button, Radio } from "antd";
import { Row, Col } from "antd";
import Router, { useRouter } from "next/router";
import { useRef } from "react";
import fetcher from "../common/fetcher";
import alertInfo, { timer } from "../common/alert";

const SignUp = () => {
  const { Header, Footer, Sider, Content } = Layout;
  const createSignUp = useRef([]);
  const router = useRouter();

  const handleBack = (e) => {
    e.preventDefault();
    Router.back();
  };
  // const [token, setToken] = useState(null);

  // const tokenStorage = new TokenStorage();

  // useEffect(() => {
  //   const token = tokenStorage.getToken();
  //   //console.log("token", token);
  //   if (token) {
  //     setToken(token);
  //   }
  // }, []);

  const onSubmit = async () => {
    console.log("회원가입");
    const id = createSignUp.current.id.state.value;
    const password = createSignUp.current.pw.state.value;
    const rePW = createSignUp.current.rePW.state.value;
    const name = createSignUp.current.name.state.value;
    const email = createSignUp.current.email.state.value;
    //비밀번호 재확인 체크
    if (password !== rePW) {
      alertInfo("비밀번호가 일치하지 않습니다.", null, "error");
      return;
    }

    const data = {
      id,
      password,
      name,
      email,
    };

    try {
      console.log("data", data);
      const result = await fetcher("post", "/member", data);
      console.log("result2222", result);

      if (result.code === "1") {
        alertInfo(
          "축하드립니다.",
          "회원가입이 완료되었습니다. \n 로그인해주세요.",
          "success"
        );
        setTimeout(() => {
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
      console.log("error", error);
      // axios에서 에러발생시 처리
      // if (error.response) {
      //   const { status, statusText } = error.response;
      //   switch (status) {
      //     case 401:
      //       return alertInfo(
      //         "오류",
      //         "닉네임 또는 비밀번호를 잘못 입력하였습니다.",
      //         "error"
      //       );
      //     case 400:
      //       return alertInfo("오류", "올바르게 입력해주세요.", "error");
      //     case 404:
      //       return alertInfo("오류", "사용자를 찾을 수 없습니다.", "error");
      //     case 409:
      //       return alertInfo("오류", "이 사용자는 이미 존재합니다.", "wraning");
      //     case 500:
      //       return alertInfo(
      //         "오류",
      //         "서버에서 오류가 발생하였습니다.",
      //         "error"
      //       );
      //     default:
      //       alertInfo("안내", "관리자에게 문의해주세요.", "info");
      //       console.log("매개변수가 잘못되었습니다!");
      //   }
      // }
    }
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
            <Row>
              <Col span={12} offset={6}>
                <h2 className="logo">
                  <img src="/images/logo.png" alt="삼삼오오 로고" />
                </h2>
                <form onSubmit={onSubmit}>
                  <fieldset>
                    <legend>회원가입</legend>
                    <ul>
                      <li>
                        <p>아이디</p>
                        <Input
                          ref={(value) => (createSignUp.current["id"] = value)}
                        />
                        <div className="confirm">
                          <Button type="primary" className="confirm-btn">
                            중복확인
                          </Button>
                          <span className="red-txt">
                            중복된 아이디가 있습니다.
                          </span>
                        </div>
                      </li>
                      <li>
                        <p>비밀번호</p>
                        <Input.Password
                          ref={(value) => (createSignUp.current["pw"] = value)}
                          iconRender={(visible) =>
                            visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                          }
                        />
                      </li>
                      <li>
                        <p>비밀번호 재확인</p>
                        <Input.Password
                          ref={(value) =>
                            (createSignUp.current["rePW"] = value)
                          }
                          iconRender={(visible) =>
                            visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                          }
                        />
                      </li>
                      <li>
                        <p>이름</p>
                        <Input
                          ref={(value) =>
                            (createSignUp.current["name"] = value)
                          }
                        />
                      </li>
                      <li>
                        <p>이메일</p>
                        <Input
                          ref={(value) =>
                            (createSignUp.current["email"] = value)
                          }
                        />
                      </li>
                    </ul>

                    <Button type="primary" onClick={onSubmit}>
                      회원가입
                    </Button>
                  </fieldset>
                </form>
              </Col>
            </Row>
          </Content>
          <button onClick={handleBack}>돌아가기</button>
        </div>
      </div>
    </>
  );
};

export default SignUp;
