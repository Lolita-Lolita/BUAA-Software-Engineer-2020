import Cookies from 'js-cookie'
import store from '@/store/'

const TokenKey = 'vue_admin_template_token'

export function getToken(tokenKey = TokenKey) {
  if(!store){
    return false;
  }
  return Cookies.get(tokenKey)
}

export function setToken(token,tokenKey = TokenKey) {
  return Cookies.set(tokenKey, token)
}

export function removeToken(tokenKey = TokenKey) {
  return Cookies.remove(tokenKey)
}
