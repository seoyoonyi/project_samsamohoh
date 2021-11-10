import React from "react";

const Mainpage = ({ token }) => {
  return (
    <>
      <h1>메인 페이지</h1>
      {token && <p>반갑습니다! {token?.data?.name}님</p>}
    </>
  );
};

export default Mainpage;
