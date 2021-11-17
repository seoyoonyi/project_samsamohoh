import { Layout } from "antd";
import Headerlayout from "../components/grids/header-layout";
import Head from "next/head";

const Error404 = () => {
  const { Header, Footer, Sider, Content } = Layout;

  // const defaultOptions = {
  //   loop: true,
  //   autoplay: true,
  //   animationData: animationData.default,
  //   rendererSettings: {
  //     preserveAspectRatio: "xMidYMid slice",
  //   },
  // };

  return (
    <>
      <div id="wrap">
        <Head>
          <title>찾을 수 없는 페이지</title>
        </Head>
        <Headerlayout />
        <Content className="error">
          <p className="error-txt">에러페이지</p>
        </Content>
      </div>
    </>
  );
};

export default Error404;
