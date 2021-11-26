import axios from "axios";

axios.defaults.baseURL = "http://13.59.1.176:8090";

const fetcher = async (method, url, ...rest) => {
  const res = await axios[method](url, ...rest);
  return res.data;
};

export default fetcher;
