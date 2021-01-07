import request from '@/utils/request'
import store from '@/store/'

const host_RBAC = '/api/rbac'
const host_dish = '/api/dish'
const host_credit = '/api/credit'

export function getRBACSession() {
  document.cookie = "JSESSIONID="+store.state.session['rbac']
  console.log("set cookie as "+ "rbac",store.state.session['rbac'])
  return
}

export function getDishSession() {
  document.cookie = "JSESSIONID="+store.state.session['dish']
  console.log("set cookie as "+ "dish",store.state.session['dish'])
  return 
}

export function getCreditSession() {
  document.cookie = "JSESSIONID="+store.state.session['credit']
  console.log("set cookie as "+ "credit",store.state.session['credit'])
  return
}

export function rbacLogin(data) {
  return request({
    url: '/login',
    baseURL: host_RBAC,
    method: 'post',
    params: data
    // data:qs.stringify(data)
  })
}

export function dishLogin(data) {
  return request({
    url: '/login',
    baseURL: host_dish,
    method: 'post',
    params: data
  })
}

export function creditLogin(data) {
  return request({
    url: '/login',
    baseURL: host_credit,
    method: 'post',
    params: data
  })
}

export function userRegister(data) {
  getRBACSession()
  return request({
    url: '/userCrud/save',
    baseURL: host_RBAC,
    method: 'post',
    params: data // userName, password
  })
}

export function userLogout() {
  getRBACSession()
  return request({
    url: '/logout',
    baseURL: host_RBAC,
    method: 'post'
  })
}

export function getList() {
  getRBACSession()
  console.log("cookie:",document.cookie)
  return request({
    url: '/userCrud/findAll',
    baseURL: host_RBAC,
    method: 'get'
  })
}

export function deleteUser(uid) {
  getRBACSession()
  return request({
    url: '/userCrud/delete',
    baseURL: host_RBAC,
    method: 'post',
    params: { id: uid } // id
  })
}

export function login(data) {
  return request({
    url: '/vue-admin-template/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

export function getFoodList(data) {
  getDishSession()  
  return request({
    url: '/dish/findAll',
    baseURL: host_dish,
    method: 'post',
    params: data
  })
}
