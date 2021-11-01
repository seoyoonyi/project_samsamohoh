import Router from "next/router";
import React from "react";
import Footer from "./footer";

const goToIntro = () => {
  Router.push("/intro");
};

const Mainpage = () => {
  return (
    <>
      <h1>메인 페이지</h1>
      <button onClick={goToIntro}>뒤로가기</button>
      <Footer />
    </>
  );
};

export default Mainpage;
