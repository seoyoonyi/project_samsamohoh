import fetcher from "../common/fetcher";

export default async function getRoomList() {
  try {
    const res = await fetcher("get", "/boards?page=0&pageSize=5"); //전체 글 조회
    if (res.code === 0) {
      const roomLists = res.data.items;
      return roomLists;
    }
  } catch (error) {
    console.log(error);
  }
}
