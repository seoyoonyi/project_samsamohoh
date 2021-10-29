import React, { useEffect, useState } from "react";
import Router from "next/router";
import fetcher from "../common/fetcher";

const Intro = () => {
  const [item, setItem] = useState(null);

  useEffect(async () => {
    const response = await fetcher(
      "get",
      `https://leejeawon.herokuapp.com/getMemberList2`
    );
    if (response) {
      setItem(response);
    }
  }, []);

  const goToMainPage = () => {
    Router.push("/mainpage");
  };
  if (item) {
    console.log("서버로부터 넘어오는 데이터 ", item);
  }

  return (
    <>
      <h1>인트로 페이지</h1>
      <ul>
        {item?.map((item) => (
          <li key={item.id}>
            이름:{item.name}
            <span>비밀번호:{item.password}</span>
          </li>
        ))}
      </ul>
      <button className="goto_mainpage_Btn" onClick={goToMainPage}>
        입장하기
      </button>
    </>
  );
};

export default Intro;
