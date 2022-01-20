import { Layout } from "antd";
import { Input, Button, Form } from "antd";
import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import fetcher from "../common/fetcher";
import { GetServerSideProps } from "next";
import { useRef, useState } from "react";
import { useEffect } from "react";
// import { useRecoilState } from "recoil";
import { tokenAtrom } from "../atoms/token";
import alertInfo, { timer } from "../common/alert";
import { useRouter } from "next/router";

const Profile = ({ nickname }) => {
  const { Content } = Layout;
  const [nicknameValue, setNicnameValue] = useState(nickname);
  const [submitLoading, setSubmitLoading] = useState(false);
  const [changeLoading, setChangeLoading] = useState(false);
  const [file, setFile] = useState(null);
  const inputRef = useRef(null);
  const [imgSrc, setImgSrc] = useState(null); // 이미지 미리보기
  const [form] = Form.useForm();
  // const [token, _] = useRecoilState(tokenAtrom);
  const router = useRouter();

  useEffect(() => {
    form.setFieldsValue({ name: nicknameValue });
  });

  const ChangeNickname = async () => {
    setChangeLoading(true);
    let clientUrl;
    if (process.env.NODE_ENV === "production") {
      clientUrl = process.env.NEXT_PUBLIC_DEPLOY_URL;
    } else {
      clientUrl = process.env.NEXT_PUBLIC_LOCAL_URL;
    }

    let res = await fetcher("get", `${clientUrl}/api/make_nickname`);
    setNicnameValue(res.data);
    setChangeLoading(false);
  };

  //진행바 초기화
  function resetFile() {
    setFile(null); //첨부 파일 초기화
    setImgSrc(null);
  }

  const movePage = (loginItem, _msg, _pageName) => {
    alertInfo(_msg, null, "info");
    setTimeout(() => {
      setSubmitLoading(false);
      loginItem && router.push(_pageName);
    }, timer);
  };

  const onImgChange = async (e) => {
    //type이 file인 input에서 onChange 함수에서 event 객체를 통해 파일정보가 넘어오기 때문에 onSubmit에서 처리하지 않고 여기서 처리함
    const imageFile = e.target.files[0];
    console.log("imageFile", imageFile);
    setFile(imageFile);

    if (imageFile) {
      const fileReader = new FileReader(); // 파일에 접근하기 위함
      fileReader.readAsDataURL(imageFile); //url을 기준으로 파일을 읽는데 사용하는 메서드
      fileReader.onload = (e) => setImgSrc(e.target.result); //파일정보가 생성됨
    } else {
      resetFile(); //사용자가 파일첨부를 취소하면 초기화
    }
  };

  const handleFinish = async (values) => {
    setSubmitLoading(true);

    const { name } = values;

    const formData = new FormData();

    formData.append("file", file); //서버에 upload.single(변수명)에 정의한 변수이름과 동일해야함
    formData.append("userInfor", JSON.stringify({ nickName: name }));

    // console.log("userInfo", token.data.userInfo.id);

    try {
      let result = await fetcher("get", `/auth/member`); //토큰을 기준으로 사용자 정보 획득

      if (result.code === 1) {
        const userID = result.data.id;

        try {
          // Start 사용자 프로필 변경
          result = await fetcher("put", `/auth/member/${userID}`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });
          console.log("result", result);
          if (result.code === 1) {
            movePage(result, "메인화면으로 이동합니다.", "/");
          }
        } catch (error) {
          console.warn(error.message);
        } // End 사용자 프로필 변경
      }
    } catch (error) {
      console.warn(error.message);
    }

    setSubmitLoading(false);
  };

  return (
    <>
      <div id="wrap">
        <div className="main-wrap">
          <Content className="profile">
            <div className="container">
              <h2 className="logo">
                <Link href="/">
                  <a>
                    <img src="/images/logo.svg" alt="삼삼오오 로고" />
                  </a>
                </Link>
              </h2>
              <div className="user-group">
                <div className="txt-box">
                  <h3 className="welcome-txt">
                    wert1591님, 가입을 환영합니다!
                    {/* 아이디 동적으로 삽입되도록 변경 필요 */}
                  </h3>
                  <span className="gray-txt">
                    프로필 사진과 닉네임으로 여러분을 표현해보세요.
                  </span>
                </div>
                <Form onFinish={handleFinish} form={form}>
                  <div className="user-profile">
                    <label htmlFor="file">
                      <div className="user-photo">
                        <img
                          src={imgSrc || "/images/profile-1.jpg"}
                          alt="세로형-프로필사진"
                        />
                        {/* <img src="/images/profile-2.jpg" alt="가로형-프로필사진" /> */}
                        {/* <img src="/images/profile-3.jpg" alt="정사각형-프로필사진" /> */}
                      </div>
                      <button
                        className="plus-btn"
                        onClick={(e) => {
                          e.preventDefault();
                          inputRef.current.click();
                        }}
                      >
                        <FontAwesomeIcon icon={faPlus} className="plus-icon" />
                      </button>
                    </label>
                    <input
                      type="file"
                      id="file"
                      className="sr-only"
                      ref={inputRef}
                      accept="image/*"
                      onChange={onImgChange}
                    />
                  </div>
                  <div className="user-nickname">
                    <Input.Group compact>
                      <Form.Item name="name" rules={[{ required: true }]}>
                        <Input />
                      </Form.Item>
                      <Button
                        type="primary"
                        className="btn-change"
                        onClick={ChangeNickname}
                        loading={changeLoading}
                      >
                        바꿀래요
                      </Button>
                    </Input.Group>
                    <span className="gray-txt">
                      닉네임은 2자 이상 15자 이하로 입력 가능
                    </span>
                  </div>
                  <Button
                    type="primary"
                    className="btn-50 btn-main btn-complete"
                    htmlType="submit"
                    loading={submitLoading}
                  >
                    완료
                  </Button>
                </Form>
              </div>
            </div>
          </Content>
        </div>
      </div>
    </>
  );
};

export default Profile;

export const getStaticProps: GetServerSideProps = async (context) => {
  try {
    let clientUrl;
    console.log("process.env.NODE_ENV", process.env.NODE_ENV);
    if (process.env.NODE_ENV === "production") {
      clientUrl = process.env.DEPLOY_URL;
    } else {
      clientUrl = process.env.LOCAL_URL;
    }
    let res = await fetcher("get", `${clientUrl}/api/make_nickname`);
    return { props: { nickname: res.data } };
  } catch (error) {
    console.log(error);
    return { props: {} };
  }
};
