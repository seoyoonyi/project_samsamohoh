import { Layout } from 'antd';
import Headerlayout from './headerlayout';



const App = () => {
  const { Header, Footer, Sider, Content } = Layout;
  return (
    <>
      <div className="index-wrap">
        <Headerlayout/>
      </div>
    </>
  );
};

export default App;
