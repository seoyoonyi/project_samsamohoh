import { Layout } from "antd";
import { useEffect } from "react";
import fetcher from "../common/fetcher";

const Mainpage = ({ token }) => {
  const { Header, Footer, Sider, Content } = Layout;

  useEffect(() => {
    async () => {
      try {
        const result = await fetcher("get", "/boards?page=0&pageNum=2"); //전체 글 조회
        //const result = await fetcher("get", "/boards/5");
        console.log("result2222", result);
      } catch (error) {
        console.log(error);
      }
    };
  });

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
