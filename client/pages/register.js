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
        <div className="main-wrap register">
          <Content>
            <Row>
              <Col span={12} offset={6}>
                <h2>삼삼오오</h2>
                <form method="post">
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

                  <Button type="primary">회원가입</Button>
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
