
import { Layout } from 'antd';
import Loginbutton from "../components/loginbutton";


const Headerlayout = () => {
    const { Header, Footer, Sider, Content } = Layout;
    return (
        <>
         <Layout>
            <Header>
             <Loginbutton/>
            </Header>
         </Layout>
        </>
    );
};

export default Headerlayout;