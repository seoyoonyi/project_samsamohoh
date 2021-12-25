import axios from "axios";

axios.defaults.baseURL = "https://api.moleeja.ml";

const fetcher = async (method, url, ...rest) => {
  const res = await axios[method](url, ...rest);
  return res.data;
};

export default fetcher;
