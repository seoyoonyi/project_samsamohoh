import Headerlayout from "../components/grids/header-layout";
import Mainpage from "./mainpage";
import { RecoilRoot } from "recoil";
const Index = () => {
  return (
    <>
      <RecoilRoot>
        <div id="wrap">
          <Headerlayout />
          <Mainpage />
        </div>
      </RecoilRoot>
    </>
  );
};

export default Index;
