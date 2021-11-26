import Headerlayout from "../components/grids/header-layout";
import Mainpage from "./mainpage";
import TokenStorage from "../common/token";
import { useEffect, useState } from "react";
import { RecoilRoot } from "recoil";

const Index = () => {
  const [token, setToken] = useState(null);

  const tokenStorage = new TokenStorage();

  useEffect(() => {
    const token = tokenStorage.getToken();
    console.log("loginAftertoken", token);
    if (token) {
      setToken(token);
    }
  }, []);

  const getToken = (_token) => {
    setToken(_token);
  };

  return (
    <>
      <RecoilRoot>
        <div id="wrap">
          <Headerlayout token={token} getToken={getToken} />
          <Mainpage token={token} />
        </div>
      </RecoilRoot>
    </>
  );
};

export default Index;
