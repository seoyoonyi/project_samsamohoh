import { GetServerSideProps } from "next";
import Headerlayout from "../components/grids/header-layout";
import Mainpage from "./mainpage";
import getRoomList from "../common/roomList";

enum Role {
  ROLE_ADMIN = "ROLE_ADMIN",
  ROLE_MEMBER = "ROLE_MEMBER",
}

interface IRoomList {
  seq: number;
  title: string;
  content: string;
  regisDate: string;
  cnt: number;
  good: number;
  bad: number;
  member: {
    id: string;
    password: string;
    name: string;
    email: string;
    role: Role;
    enabled: boolean;
    regisDate: string;
  };
}

interface IRoomListProps {
  roomLists: IRoomList[];
}

const Index = ({ roomLists }: IRoomListProps) => {
  return (
    <>
      <div id="wrap">
        <Headerlayout />
        <Mainpage roomLists={roomLists} />
      </div>
    </>
  );
};

export default Index;

export const getServerSideProps: GetServerSideProps = async (context) => {
  try {
    let res = await getRoomList("ALL"); //전체 글 조회
    if (res && res.code === 1) {
      return { props: { roomLists: res.data.items } }; // 데이터가 존재하는 경우 리스트를 전달
    } else if (res.code === -1) {
      return { props: { roomLists: res.message } }; // 데이터가 없는 경우 에러메세지를 전달
    }
    return { props: {} };
  } catch (error) {
    console.log(error);
    return { props: {} };
  }
};
