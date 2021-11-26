const TOKEN = "token";

export default class TokenStorage {
  saveToken(token) {
    localStorage.setItem(TOKEN, JSON.stringify(token));
  }
  getToken() {
    return JSON.parse(localStorage.getItem(TOKEN));
  }
  clearToken() {
    localStorage.removeItem(TOKEN);
  }
}
