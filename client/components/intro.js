import React from "react";
import Router from "next/router";

const Intro = () => {
  const goToMainPage = () => {
    Router.push("/mainpage");
  };

  return (
    <>
      <h1>인트로 페이지</h1>
      <button className="goto_mainpage_Btn" onClick={goToMainPage}>
        입장하기
      </button>
    </>
  );
};

export default Intro;
