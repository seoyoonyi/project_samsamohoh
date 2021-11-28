import { Layout } from "antd";

const Mainpage = ({ roomList }) => {
  const { Header, Footer, Sider, Content } = Layout;
  console.log("roomList", roomList);
  return (
    <>
      <div className="main-wrap">
        <Content className="mainpage">
          <img src="/images/mainpage.jpg" alt="메인페이지" />
          <p className="mainpage-txt">
            삼삼오오에 오신 것을<br></br>
            환영합니다!
          </p>
          <ul>
            {roomList.map((item) => {
              return (
                <li key={item.seq}>
                  <div> {item.title}</div>
                  {/* 제목 */}
                  <div>{item.content}</div>
                  {/* 내용 */}
                  <div>{item.member.name}</div>
                  {/* 글쓴이 */}
                  <div>{item.cnt}</div>
                  {/* 구독수 */}
                  <div>{item.bad}</div>
                  {/* 싫어요 수 */}
                  <div>{item.good}</div>
                  {/* 좋아요 수 */}
                </li>
              );
            })}
          </ul>
        </Content>
      </div>
    </>
  );
};

export default Mainpage;
