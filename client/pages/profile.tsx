import { Layout } from "antd";
import { Input, Button, Radio, Form } from "antd";
import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { useEffect } from "react";
import fetcher from "../common/fetcher";
import { GetServerSideProps } from "next";

const Profile = () => {
  const { Header, Footer, Sider, Content } = Layout;

  // async function randomNickname() {
  //   return await fetcher(
  //     "get",
  //     "https://nickname.hwanmoo.kr/?format=json&count=1&max_length=5&whitespace=_"
  //   );
  // }

  // useEffect(() => {
  //   console.log(randomNickname());
  // }, []);

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
                  </h3>
                  <span className="gray-txt">
                    프로필 사진과 닉네임으로 여러분을 표현해보세요.
                  </span>
                </div>
                <Form>
                  <div className="user-profile">
                    <div className="user-photo">
                      <img
                        src="/images/profile-1.jpg"
                        alt="세로형-프로필사진"
                      />
                      {/* <img src="/images/profile-2.jpg" alt="가로형-프로필사진" /> */}
                      {/* <img src="/images/profile-3.jpg" alt="정사각형-프로필사진" /> */}
                    </div>
                    <button className="plus-btn">
                      <FontAwesomeIcon icon={faPlus} className="plus-icon" />
                    </button>
                  </div>
                  <div className="user-nickname">
                    <Input.Group compact>
                      <Input defaultValue="힘있는팝콘2238" />
                      <Button type="primary" className="btn-change">
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
    let minNum = 6;
    let maxNum = 9;
    let ramdomNum = Math.floor(Math.random() * (maxNum - minNum) + minNum);
    let paramNum = ramdomNum.toString();

    let res = await fetcher(
      "get",
      `https://nickname.hwanmoo.kr/?format=json&count=1&max_length=${paramNum}&whitespace=_`
    );

    console.log("res", res.words[0]);
    console.log(res.words[0].replace("_", ""));

    //  const dd = res.words[0].replace("/", "");

    // if (res && res.code === 0) {
    //   return { props: { roomLists: res.data.items } }; // 데이터가 존재하는 경우 리스트를 전달
    // } else if (res.code === -1) {
    //   return { props: { roomLists: res.message } }; // 데이터가 없는 경우 에러메세지를 전달
    // }
    return { props: {} };
  } catch (error) {
    console.log(error);
    return { props: {} };
  }
};
