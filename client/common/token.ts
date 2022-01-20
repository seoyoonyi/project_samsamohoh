const SAVEINFO = "saveInfo";
let localStorage;
if (typeof window == "undefined") {
  localStorage = global.localStorage;
} else {
  localStorage = window.localStorage;
}

export default class TokenStorage {
  saveToken(info) {
    localStorage && localStorage.setItem(SAVEINFO, JSON.stringify(info));
  }
  getToken() {
    return localStorage && JSON.parse(localStorage.getItem(SAVEINFO));
  }
  clearToken() {
    localStorage && localStorage.removeItem(SAVEINFO);
  }
}
