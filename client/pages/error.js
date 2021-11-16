import { Layout } from 'antd';
import Headerlayout from '../components/grids/header-layout';

const Error = () => {
  return;
  const { Header, Footer, Sider, Content } = Layout;
  <>
    <div id="wrap">
      <Headerlayout />
      <Content className="error">
        <p className="error-txt">에러페이지</p>
      </Content>
    </div>
  </>;
};

export default Error;
