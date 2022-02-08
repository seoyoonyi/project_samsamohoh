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
import { useState } from "react";
import getRoomList from "../common/roomList";

const Mainpage = ({ roomLists }) => {
  const { Header, Footer, Sider, Content } = Layout;

  const [rooms, setRooms] = useState(roomLists);
  // console.log('rooms', rooms);

  const handleOrder = async (_bt) => {
    let propsName = "";
    propsName = _bt === "recent" ? "regisDate" : "good";
    console.log("propsName", propsName);
    try {
      // const res = await getRoomList(); //전체 글 조회
      let res = [...rooms];

      if (_bt === "good") {
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
    } catch (error) {
      console.error(error);
    }
  };

  const handleFileter = async (_category) => {
    console.log("_category", _category);
    try {
      let res = await getRoomList(_category); //전체 글 조회
      if (res && res.code === 1) {
        setRooms(res.data.items);
      }
    } catch (error) {
      console.warn(error);
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
              <div className="tab-menu">
                <ul className="category-list">
                  <li className="category-box is-active">
                    <button>
                      <div className="cate-icon">
                        <span className="all-icon">All</span>
                      </div>
                      <p
                        className="category-tit"
                        onClick={() => handleFileter("ALL")}
                      >
                        전체
                      </p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button onClick={() => handleFileter("EXERCISE")}>
                      <div className="cate-icon">
                        <span className="material-icons">directions_bike</span>
                      </div>
                      <p className="category-tit">운동</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button onClick={() => handleFileter("RESTAURANT")}>
                      <div className="cate-icon">
                        <span className="material-icons">local_dining</span>
                      </div>
                      <p className="category-tit">맛집</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button onClick={() => handleFileter("MOVIE")}>
                      <div className="cate-icon">
                        <span className="material-icons">movie</span>
                      </div>
                      <p className="category-tit">영화</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button onClick={() => handleFileter("STUDY")}>
                      <div className="cate-icon">
                        <span className="material-icons">school</span>
                      </div>
                      <p className="category-tit">공부</p>
                    </button>
                  </li>
                  <li className="category-box">
                    <button onClick={() => handleFileter("ETC")}>
                      <div className="cate-icon">
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
                      className="filter-btn active-is btn-40 round-btn-outlined"
                      onClick={() => handleOrder("hot")}
                    >
                      인기순
                    </button>{" "}
                  </li>
                  <li className="filter-btn-box">
                    <button
                      className="filter-btn btn-40 round-btn-outlined"
                      onClick={() => handleOrder("recent")}
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
                      boardDislike,
                      boardId,
                      boardLike,
                      category,
                      cnt,
                      content,
                      nickName,
                      regisDate,
                      title,
                      userId,
                    } = item;
                    console.log("item", item);
                    //import dayjs from "dayjs";
                    // const createDay = dayjs(regisDate).format("YYYY.MM.DD");
                    // 방생성 날짜는 디테일 페이지에서 표기
                    return (
                      <ul className="room-list" key={boardId}>
                        {/* {category} >>>>>>>>>> 카테고리 데이터 아이콘으로 표기해주세요 */}
                        <li className="room-item">
                          <Link href="/">
                            <a>
                              <div className="photo-box">
                                <div className="image">
                                  <img
                                    src="/images/room_img.jpg"
                                    alt="기본이미지"
                                  />
                                </div>
                                <button className="cnt is-active">
                                  <span className="material-icons">
                                    favorite_border
                                  </span>
                                  <span className="txt">{cnt}</span>
                                </button>
                              </div>
                              <div className="content-box">
                                <h3 className="title">{title}</h3>
                                <p className="content">{content}</p>
                                <p className="date">{regisDate}</p>
                                {/* TODO: 날짜포맷 형식 변경 */}
                                {/* TODO: 카테고리 표기할 공간 필요 => 카테고리가 전체일 경우는 구분안되서 필요 */}
                                <div className="inner">
                                  <p className="member">{nickName}</p>
                                  <div className="like-list">
                                    <button className="good is-active">
                                      <span className="material-icons">
                                        thumb_up
                                      </span>
                                      <span className="txt"> {boardLike}</span>
                                    </button>
                                    <button className="bad is-active">
                                      <span className="material-icons">
                                        thumb_down
                                      </span>
                                      <span className="txt">싫어요</span>
                                    </button>
                                  </div>
                                </div>
                              </div>
                            </a>
                          </Link>
                        </li>
                      </ul>
                    );
                  })
                ) : (
                  // 생성된 방이 없는 경우
                  <h2>{rooms}</h2>
                )}
              </div>
            </div>
          </div>
        </Content>
      </div>
    </>
  );
};

export default Mainpage;
