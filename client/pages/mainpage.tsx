import { Layout } from 'antd';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faFootballBall,
  faUtensils,
  faFilm,
  faPencilAlt,
  faCloud,
  faHeart,
  faThumbsUp,
  faThumbsDown,
} from '@fortawesome/free-solid-svg-icons';
import Link from 'next/link';
import { useState } from 'react';

const Mainpage = ({ roomLists }) => {
  const { Header, Footer, Sider, Content } = Layout;

  const [rooms, setRooms] = useState(roomLists);
  console.log('rooms', rooms);

  const handleOrder = async (_bt) => {
    let propsName = '';
    propsName = _bt === 'recent' ? 'regisDate' : 'good';
    console.log('propsName', propsName);
    try {
      // const res = await getRoomList(); //전체 글 조회
      let res = [...rooms];

      if (_bt === 'good') {
        //인기순으로  정렬처리
        res = res.sort((a, b) => {
          return a[propsName] > b[propsName]
            ? -1
            : b[propsName] > a[propsName]
            ? 1
            : 0;
        });
      } else {
        //최신순으로 정렬처리
        res = res.sort((a, b) => {
          return (
            Number(new Date(b[propsName])) - Number(new Date(a[propsName]))
          );
        });
        //typescript에서 뺄샘을 할때 오류가 나지 않도록 문자열을 숫자로 바꿔줌
      }
      setRooms(res);
      console.log('rrrrr', res);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <div className="main-wrap">
        <Content className="mainpage">
          <div className="slide">
            <img src="/images/main.png" alt="메인페이지" />
          </div>
          <div className="room-wrap">
            <div className="container">
              <div>
                <ul className="category-list">
                  <li className="category-box">
                    <button>
                      <div className="icon-circle is-active">
                        <span className="all-icon">All</span>
                      </div>
                      <p className="category-tit">전체</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button>
                      <div className="icon-circle">
                        <span className="material-icons">directions_bike</span>
                        {/* <FontAwesomeIcon
                        icon={faFootballBall}
                        className="sport-icon"
                      /> */}
                      </div>
                      <p className="category-tit">운동</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button>
                      <div className="icon-circle">
                        {/*  <FontAwesomeIcon
                        icon={faUtensils}
                        className="food-icon"
                      /> */}
                        <span className="material-icons">local_dining</span>
                      </div>
                      <p className="category-tit">맛집</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button>
                      <div className="icon-circle">
                        {/* <FontAwesomeIcon icon={faFilm} className="movie-icon" /> */}
                        <span className="material-icons">movie</span>
                      </div>
                      <p className="category-tit">영화</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button>
                      <div className="icon-circle">
                        {/* <FontAwesomeIcon
                        icon={faPencilAlt}
                        className="study-icon"
                      /> */}
                        <span className="material-icons">school</span>{' '}
                      </div>
                      <p className="category-tit">공부</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button>
                      <div className="icon-circle">
                        {/* <FontAwesomeIcon icon={faCloud} className="etc-icon" /> */}
                        <span className="material-icons">more</span>
                      </div>
                      <p className="category-tit">기타</p>
                    </button>
                  </li>
                </ul>
                {/* 필터링 */}
                <ul className="filter-btn-list">
                  <li className="filter-btn-box">
                    <button
                      className="filter-btn"
                      onClick={() => handleOrder('hot')}
                    >
                      인기순
                    </button>{' '}
                  </li>
                  <li className="filter-btn-box">
                    <button
                      className="filter-btn"
                      onClick={() => handleOrder('recent')}
                    >
                      최신순
                    </button>
                  </li>
                </ul>
              </div>

              <div className="room-group">
                {Array.isArray(rooms) ? ( // 데이터가 없어서 에러메세지로 넘어오는 경우는 문자열로 전달되서 false로 떨어짐
                  // 생성된 방이 있는 경우
                  rooms?.map((item) => {
                    const {
                      seq,
                      title,
                      content,
                      regisDate,
                      cnt,
                      category,
                      boardLike,
                      boardDislike,
                      name,
                    } = item;

                    //import dayjs from "dayjs";
                    // const createDay = dayjs(regisDate).format("YYYY.MM.DD");
                    // 방생성 날짜는 디테일 페이지에서 표기
                    return (
                      <ul className="room-list" key={seq}>
                        {/* {category} >>>>>>>>>> 카테고리 데이터 아이콘으로 표기해주세요 */}
                        <li className="room-item">
                          <Link href="/">
                            <a className="title">{title}</a>
                          </Link>
                        </li>
                        <li className="room-item">
                          <Link href="/">
                            <a className="member">{name}</a>
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
                              <span className="txt"> {boardLike}</span>
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
                  })
                ) : (
                  // 생성된 방이 없는 경우
                  <h2>{rooms}</h2>
                )}

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
