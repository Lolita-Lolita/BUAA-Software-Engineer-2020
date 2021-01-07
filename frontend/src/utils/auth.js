import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

export function getToken(tokenKey = TokenKey) {
  return Cookies.get(tokenKey)
}

export function setToken(token,tokenKey = TokenKey) {
  return Cookies.set(tokenKey, token)
}

export function removeToken(tokenKey = TokenKey) {
  return Cookies.remove(tokenKey)
}
