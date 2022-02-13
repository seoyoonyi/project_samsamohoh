import TokenStorage from "../common/token";
const tokenStorage = new TokenStorage();
import { deleteAuth } from "../common/fetcher";

export const onLoginOut = (e, setToken, router) => {
  e && e.preventDefault();
  tokenStorage.clearToken();
  deleteAuth(); // axios header에 들어간 인증토큰 제거
  setToken(null);
  router.pathname !== "/login" && router.push("/");
};
