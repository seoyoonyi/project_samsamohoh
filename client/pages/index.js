import { Layout } from 'antd';
import Headerlayout from '../components/grids/header-layout';

const Index = () => {
  const { Header, Footer, Sider, Content } = Layout;
  return (
    <>
      <div className="wrap">
        <Headerlayout />
      </div>
    </>
  );
};

export default Index;
