import { Input, Space } from 'antd';
import { EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';

const Password = () => {
  return (
    <>
      <Space direction="vertical">
        <Input.Password placeholder="input password" />
        <Input.Password
          placeholder="input password"
          iconRender={(visible) =>
            visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
          }
        />
      </Space>
    </>
  );
};

export default Password;
