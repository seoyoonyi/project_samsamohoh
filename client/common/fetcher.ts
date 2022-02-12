import axios from "axios";
import TokenStorage from "../common/token";

const tokenStorage = new TokenStorage();
const saveInfo = tokenStorage.getToken();

axios.defaults.baseURL = "http://api.moleeja.ml";

if (saveInfo) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${saveInfo.token}`;
}

const fetcher = async (method, url, ...rest) => {
  const res = await axios[method](url, ...rest);

  return res.data;
};

export const deleteAuth = () => {
  delete axios.defaults.headers.common["Authorization"];
};
export default fetcher;
