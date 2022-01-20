import fetcher from "../common/fetcher";

export default async function getRoomList() {
  try {
    const res = await fetcher("get", "/boards?page=1&pageSize=5"); //전체 글 조회
    console.log("header", res.headers);
    return res;
  } catch (error) {
    console.log(error);
  }
}
