import axios from "axios";

// axios.defaults.baseURL = "http://localhost:8000"; //추후 api 서버 주소로 변경

const fetcher = async (method, url, ...rest) => {
  const res = await axios[method](url, ...rest);
  return res.data;
};

export default fetcher;
