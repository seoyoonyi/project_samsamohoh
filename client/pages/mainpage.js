import { Layout } from "antd";

const Mainpage = ({ token }) => {
  const { Header, Footer, Sider, Content } = Layout;
  return (
    <>
      <div className="main-wrap">
        <Content className="mainpage">
          <img src="/images/mainpage.jpg" alt="메인페이지" />
          <p className="mainpage-txt">
            삼삼오오에 오신 것을<br></br>
            환영합니다!
          </p>
        </Content>
      </div>
      {/* {token && <p>반갑습니다! {token?.data?.name}님</p>} */}
    </>
  );
};

export default Mainpage;
