import React, { useEffect, useState } from "react";
import Router from "next/router";
import fetcher from "../common/fetcher";
import Footer from "./footer";

const Mainpage = () => {
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

  const goToModalPage = () => {
    Router.push("/modal");
  };

  const goToAndtdPage = () => {
    Router.push("/antd_test");
  };

  if (item) {
    console.log("서버로부터 넘어오는 데이터 ", item);
  }

  return (
    <>
      <h1>메인 페이지</h1>
      <ul>
        {item?.map((item) => (
          <li key={item.id}>
            이름:{item.name}
            <span>비밀번호:{item.password}</span>
          </li>
        ))}
      </ul>

      <button className="goto-modalpage-Btn" onClick={goToModalPage}>
        move page
      </button>
      <button className="goto-antd-Btn" onClick={goToAndtdPage}>
        move ant-design test Page
      </button>

      <Footer />
    </>
  );
};

export default Mainpage;
