import { Layout } from "antd";
import Headerlayout from "../components/grids/header-layout";
import Head from "next/head";
import { faExclamationCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Lottie from "react-lottie";
import * as animationData from "../public/images/lottie_error.json";
import Router from "next/router";

const Error404 = () => {
  const { Header, Footer, Sider, Content } = Layout;

  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: animationData.default,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };

  const goToBack = (e) => {
    e.preventDefault();
    Router.back();
  };

  const goToHome = (e) => {
    e.preventDefault();
    Router.push("/");
  };

  return (
    <>
      <div id="wrap">
        <Head>
          <title>찾을 수 없는 페이지</title>
        </Head>
        <Headerlayout />
        <section id="errorPageWrap">
          <div className="errorLottie">
            <Lottie options={defaultOptions} />
          </div>
          <div className="errorTextGroup">
            <div className="errorIconGroup">
              <FontAwesomeIcon
                icon={faExclamationCircle}
                color="#f3807c"
                className="errorIcon"
              />
              <h2 className="errorIcon404">404</h2>
            </div>
            <h2 className="errorTitle">요청하신 페이지를 찾을 수 없습니다.</h2>
            <p className="errorDescription">
              불편을 드려 죄송합니다. 확인 후 다시 시도해주세요.
            </p>
            <div className="error404Btn">
              <button onClick={goToHome} className="errorGoToHomeBtn">
                홈으로 가기
              </button>
              <button onClick={goToBack}>뒤로가기</button>
            </div>
          </div>
        </section>
      </div>
    </>
  );
};

export default Error404;
