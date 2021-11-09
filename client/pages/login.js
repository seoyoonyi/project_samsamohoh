import { Layout } from 'antd';
import { Input, Space } from 'antd';
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';
import { UserOutlined } from '@ant-design/icons';
import { Button, Radio } from 'antd';

const Login = () => {
  const { Header, Footer, Sider, Content } = Layout;
  return (
    <>
      <div id="wrap">
        <div className="main-wrap">
          <Content>
            <Space direction="vertical">
              <h4 className="login-txt">삼삼오오에 오신 것을 환영합니다! </h4>
              <Input placeholder="아이디" prefix={<UserOutlined />} />
              <Input.Password
                placeholder="비밀번호"
                iconRender={(visible) =>
                  visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                }
              />
              <Button type="primary">로그인</Button>
            </Space>
          </Content>
        </div>
      </div>
    </>
  );
};

export default Login;
