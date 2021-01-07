import request from '@/utils/request'

const host_RBAC = '/api/rbac'
const host_dish = '/api/dish'
const host_credit = '/api/credit'


export function userLogin(data) {
  return request({
    url: '/login',
    baseURL: host_RBAC,
    method: 'post',
    params: data
    // data:qs.stringify(data)
  })
}

export function userLogin2(data) {
  return request({
    url: '/login',
    baseURL: host_dish,
    method: 'post',
    params: data
  })
}

export function userLogin3(data) {
  return request({
    url: '/login',
    baseURL: host_credit,
    method: 'post',
    params: data
  })
}

export function userRegister(data) {
  return request({
    url: '/userCrud/save',
    baseURL: host_RBAC,
    method: 'post',
    params: data // userName, password
  })
}

export function userLogout() {
  return request({
    url: '/logout',
    baseURL: host_RBAC,
    method: 'post'
  })
}

export function getList() {
  return request({
    url: '/userCrud/findAll',
    baseURL: host_RBAC,
    method: 'get'
  })
}

export function deleteUser(uid) {
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
  return request({
    url: '/dish/findAll',
    baseURL: host_dish,
    method: 'post',
    params: data
  })
}
