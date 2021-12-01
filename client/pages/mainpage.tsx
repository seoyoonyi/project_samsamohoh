import { Layout } from "antd";

const Mainpage = ({ roomLists }) => {
  const { Header, Footer, Sider, Content } = Layout;
  console.log("roomList", roomLists);
  return (
    <>
      <div className="main-wrap">
        <Content className="mainpage">
          <div className="slide">
            <img src="/images/main.png" alt="메인페이지" />
          </div>
          <ul className="category-list">
            <li className="category-box"></li>
          </ul>
          <ul>
            {roomLists.map((item) => {
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
