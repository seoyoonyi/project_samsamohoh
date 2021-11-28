import { GetServerSideProps } from "next";
import Headerlayout from "../components/grids/header-layout";
import Mainpage from "./mainpage";
import fetcher from "../common/fetcher";

interface IRoomListPops {
  [key: string]: any;
}

const Index = ({ roomList }: IRoomListPops) => {
  return (
    <>
      <div id="wrap">
        <Headerlayout />
        <Mainpage roomList={roomList} />
      </div>
    </>
  );
};

export default Index;

export const getStaticProps: GetServerSideProps = async (context) => {
  try {
    // const getData = await fetcher("get", "/boards?page=0&pageNum=2"); //전체 글 조회
    //const result = await fetcher("get", "/boards/5");
    const res = await fetcher("get", "/boards?page=0&pageNum=2");
    if (res.code === 0) {
      const roomList = res.data;
      return { props: { roomList } };
    }

    return { props: {} };
  } catch (error) {
    console.log(error);
    return { props: {} };
  }
};
