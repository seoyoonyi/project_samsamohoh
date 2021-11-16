import { Layout } from 'antd';
import { Input } from 'antd';
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';
import { Button, Radio } from 'antd';
import { Row, Col } from 'antd';

const Register = () => {
  const { Header, Footer, Sider, Content } = Layout;
  return (
    <>
      <div id="wrap">
        <div className="main-wrap">
          <Content className="register">
            <Row>
              <Col span={12} offset={6}>
                <h2 className="logo">
                  <img src="/images/logo.png" alt="삼삼오오 로고" />
                </h2>
                <form method="post">
                  <fieldset>
                    <legend>회원가입</legend>
                    <ul>
                      <li>
                        <p>아이디</p>
                        <Input />
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

                    <Button type="primary">회원가입</Button>
                  </fieldset>
                </form>
              </Col>
            </Row>
          </Content>
        </div>
      </div>
    </>
  );
};

export default Register;
