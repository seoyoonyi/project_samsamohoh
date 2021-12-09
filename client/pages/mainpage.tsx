import { Layout } from "antd";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFootballBall,
  faUtensils,
  faFilm,
  faPencilAlt,
  faCloud,
  faHeart,
  faThumbsUp,
  faThumbsDown,
} from "@fortawesome/free-solid-svg-icons";
import Link from "next/link";
import dayjs from "dayjs";

const Mainpage = ({ roomLists }) => {
  const { Header, Footer, Sider, Content } = Layout;
  // console.log("roomList", roomLists);
  return (
    <>
      <div className="main-wrap">
        <Content className="mainpage">
          <div className="slide">
            <img src="/images/main.png" alt="메인페이지" />
          </div>
          <div className="room-wrap">
            <div className="container">
              <h2 className="room-tit">모임 카테고리</h2>
              <ul className="category-list">
                <li className="category-box">
                  <button>
                    <div className="icon-circle is-active">
                      <span className="all-icon">All</span>
                    </div>
                    <p className="category-tit">전체</p>
                  </button>
                  {/* flaticon으로 연결해놓은 아이콘  */}
                  {/* main.scss에 import 시킴, 화면상에 flaticon이 나타나지 않아요. */}
                  {/* <i className="fi fi-rr-user"></i>
                          <i className="fi fi-br-arrow-right"></i>
                          <i className="fi fi-sr-book"></i>
                          <i className="fi fi-rr-clean"></i> */}
                </li>
                <li className="category-box">
                  <button>
                    <div className="icon-circle">
                      <FontAwesomeIcon
                        icon={faFootballBall}
                        className="sport-icon"
                      />
                    </div>
                    <p className="category-tit">운동</p>
                  </button>
                </li>
                <li className="category-box">
                  <button>
                    <div className="icon-circle">
                      <FontAwesomeIcon
                        icon={faUtensils}
                        className="food-icon"
                      />
                    </div>
                    <p className="category-tit">맛집</p>
                  </button>
                </li>
                <li className="category-box">
                  <button>
                    <div className="icon-circle">
                      <FontAwesomeIcon icon={faFilm} className="movie-icon" />
                    </div>
                    <p className="category-tit">영화</p>
                  </button>
                </li>
                <li className="category-box">
                  <button>
                    <div className="icon-circle">
                      <FontAwesomeIcon
                        icon={faPencilAlt}
                        className="study-icon"
                      />
                    </div>
                    <p className="category-tit">공부</p>
                  </button>
                </li>
                <li className="category-box">
                  <button>
                    <div className="icon-circle">
                      <FontAwesomeIcon icon={faCloud} className="etc-icon" />
                    </div>
                    <p className="category-tit">기타</p>
                  </button>
                </li>
              </ul>

              <div className="room-group">
                {roomLists.map((item) => {
                  const {
                    seq,
                    title,
                    content,
                    regisDate,
                    cnt,
                    good,
                    bad,
                    userId,
                  } = item;

                  // const createDay = dayjs(regisDate).format("YYYY.MM.DD");
                  // 방생성 날짜는 디테일 페이지에서 표기
                  return (
                    <ul className="room-list" key={seq}>
                      <li className="room-item">
                        <Link href="/">
                          <a className="title">{title}</a>
                        </Link>
                      </li>
                      <li className="room-item">
                        <Link href="/">
                          <a className="member">{userId}</a>
                        </Link>
                      </li>
                      <li className="room-item">
                        <Link href="/">
                          <a className="content">{content}</a>
                        </Link>
                      </li>
                      <li className="room-item">
                        <button className="cnt is-active">
                          <FontAwesomeIcon
                            icon={faHeart}
                            className="cnt-icon"
                          />
                          <span className="txt">{cnt}</span>
                        </button>
                        <div className="like-list">
                          <button className="good is-active">
                            <FontAwesomeIcon
                              icon={faThumbsUp}
                              className="good-icon"
                            />
                            {/* <span className="txt">9,3천</span> */}
                            <span className="txt"> {good}</span>
                          </button>
                          <button className="bad is-active">
                            <FontAwesomeIcon
                              icon={faThumbsDown}
                              className="bad-icon"
                            />
                            <span className="txt">싫어요</span>
                          </button>
                        </div>
                      </li>
                    </ul>
                  );
                })}

                {/* <ul className="room-list">
                  <li className="room-item">
                    <Link href="/">
                      <a className="title">맛집을 나누는 식탁 12회차</a>
                    </Link>
                  </li>
                  <li className="room-item">
                    <Link href="/">
                      <a className="member">이재원</a>
                    </Link>
                  </li>
                  <li className="room-item">
                    <Link href="/">
                      <a className="content">
                        요리와 술, 대화를 사랑하는 사람 입니다.<br></br>망원동
                        골목길에 작은 공간에서 글을 쓰며 모임을 열고 있습니다.
                      </a>
                    </Link>
                  </li>
                  <li className="room-item">
                    <button className="cnt">
                      <FontAwesomeIcon icon={faHeart} className="cnt-icon" />
                      <span className="txt">9999</span>
                    </button>
                    <div className="like-list">
                      <button className="good">
                        <FontAwesomeIcon
                          icon={faThumbsUp}
                          className="cnt-icon"
                        />
                        <span className="txt">9,3천</span>
                      </button>
                      <button className="bad">
                        <FontAwesomeIcon
                          icon={faThumbsDown}
                          className="cnt-icon"
                        />
                        <span className="txt">싫어요</span>
                      </button>
                    </div>
                  </li>
                </ul> */}
                {/* <ul className="room-list">
                  <li className="room-item">
                    <Link href="/">
                      <a className="title">맛집을 나누는 식탁 13회차</a>
                    </Link>
                  </li>
                  <li className="room-item">
                    <Link href="/">
                      <a className="member">이재원</a>
                    </Link>
                  </li>
                  <li className="room-item">
                    <Link href="/">
                      <a className="content">
                        요리와 술, 대화를 사랑하는 사람 입니다.<br></br>망원동
                        골목길에 작은 공간에서 글을 쓰며 모임을 열고 있습니다.
                      </a>
                    </Link>
                  </li>
                  <li className="room-item">
                    <button className="cnt">
                      <FontAwesomeIcon icon={faHeart} className="cnt-icon" />
                      <span className="txt">9999</span>
                    </button>
                    <div className="like-list">
                      <button className="good">
                        <FontAwesomeIcon
                          icon={faThumbsUp}
                          className="cnt-icon"
                        />
                        <span className="txt">9,3천</span>
                      </button>
                      <button className="bad">
                        <FontAwesomeIcon
                          icon={faThumbsDown}
                          className="cnt-icon"
                        />
                        <span className="txt">싫어요</span>
                      </button>
                    </div>
                  </li>
                </ul> */}
              </div>
            </div>
          </div>
        </Content>
      </div>
    </>
  );
};

export default Mainpage;
