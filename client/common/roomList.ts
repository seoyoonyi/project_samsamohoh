import fetcher from "../common/fetcher";

export default async function getRoomList(_category) {
  try {
    const res = await fetcher(
      "get",
      `/boards?category=${_category || "ALL"}&page=1&pageSize=5`
    ); //전체 글 조회
    return res;
  } catch (error) {
    console.log(error);
  }
}
