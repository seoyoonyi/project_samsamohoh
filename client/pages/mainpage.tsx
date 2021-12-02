import { Layout } from 'antd';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faFootballBall,
  faUtensils,
  faFilm,
  faPencilAlt,
  faCloud,
} from '@fortawesome/free-solid-svg-icons';

const Mainpage = ({ roomLists }) => {
  const { Header, Footer, Sider, Content } = Layout;
  console.log('roomList', roomLists);
  return (
    <>
      <div className="main-wrap">
        <Content className="mainpage">
          <div className="slide">
            <img src="/images/main.png" alt="메인페이지" />
          </div>
          <div className="room-group">
            <div className="container">
              <h2 className="room-tit">모임 카테고리</h2>
              <ul className="category-list">
                <li className="category-box">
                  {/* flaticon으로 연결해놓은 아이콘  */}
                  {/* main.scss에 import 시킴, 화면상에 flaticon이 나타나지 않아요. */}
                  {/* <i className="fi fi-rr-user"></i>
                          <i className="fi fi-br-arrow-right"></i>
                          <i className="fi fi-sr-book"></i>
                          <i className="fi fi-rr-clean"></i> */}
                  <div className="icon-circle is-active">
                    <span className="all-icon">All</span>
                  </div>
                  <p className="category-tit">전체</p>
                </li>

                <li className="category-box">
                  <div className="icon-circle">
                    <FontAwesomeIcon
                      icon={faFootballBall}
                      className="sport-icon"
                    />
                  </div>
                  <p className="category-tit">운동</p>
                </li>
                <li className="category-box">
                  <div className="icon-circle">
                    <FontAwesomeIcon icon={faUtensils} className="food-icon" />
                  </div>
                  <p className="category-tit">맛집</p>
                </li>
                <li className="category-box">
                  <div className="icon-circle">
                    <FontAwesomeIcon icon={faFilm} className="movie-icon" />
                  </div>
                  <p className="category-tit">영화</p>
                </li>
                <li className="category-box">
                  <div className="icon-circle">
                    <FontAwesomeIcon
                      icon={faPencilAlt}
                      className="study-icon"
                    />
                  </div>
                  <p className="category-tit">공부</p>
                </li>
                <li className="category-box">
                  <div className="icon-circle">
                    <FontAwesomeIcon icon={faCloud} className="etc-icon" />
                  </div>
                  <p className="category-tit">기타</p>
                </li>
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
            </div>
          </div>
        </Content>
      </div>
    </>
  );
};

export default Mainpage;
