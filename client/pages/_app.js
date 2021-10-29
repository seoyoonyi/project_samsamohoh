// import "../../public/fonts/base.scss";
// import "../../public/fonts/font-face.scss";
import "./index.scss";
import Head from "next/head";
import "./intro.scss";
import "./footer.scss";
import "./mainpage.scss";
import "../components/button.scss";
import "./modal.scss";

const App = ({ Component, pageProps }) => {
  return (
    <>
      <Head>
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"
        />
        <link rel="icon" type="image/x-icon" href="/images/favicon.ico" />
        <title>위치프로젝트</title>
      </Head>
      <Component {...pageProps} />
    </>
  );
};

App.getInitialProps = async ({ ctx, Component }) => {
  const pageProps = await Component.getInitialProps?.(ctx);
  return { pageProps };
};

export default App;
