import { Layout } from 'antd';
import { Input, Button, Form } from 'antd';
import Link from 'next/link';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import fetcher from '../common/fetcher';
import { GetServerSideProps } from 'next';
import { useState } from 'react';

const Profile = ({ nickname }) => {
  const { Content } = Layout;
  const [nicknameValue, setNicnameValue] = useState(nickname);

  const ChangeNickname = async () => {
    let clientUrl;
    if (process.env.NODE_ENV === 'production') {
      clientUrl = process.env.NEXT_PUBLIC_DEPLOY_URL;
    } else {
      clientUrl = process.env.NEXT_PUBLIC_LOCAL_URL;
    }

    let res = await fetcher('get', `${clientUrl}/api/make_nickname`);
    setNicnameValue(res.data);
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
                <Form>
                  <div className="user-profile">
                    <label for="file">
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
                    </label>
                    <input
                      type="file"
                      id="file"
                      name="file"
                      className="sr-only"
                    />
                  </div>
                  <div className="user-nickname">
                    <Input.Group compact>
                      <Input value={nicknameValue} />
                      <Button
                        type="primary"
                        className="btn-change"
                        onClick={ChangeNickname}
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
    console.log('process.env.NODE_ENV', process.env.NODE_ENV);
    if (process.env.NODE_ENV === 'production') {
      clientUrl = process.env.DEPLOY_URL;
    } else {
      clientUrl = process.env.LOCAL_URL;
    }
    let res = await fetcher('get', `${clientUrl}/api/make_nickname`);
    return { props: { nickname: res.data } };
  } catch (error) {
    console.log(error);
    return { props: {} };
  }
};
