import http from '@/utils/http'

/**
 * 登录
 * @param {*} username 用户名
 * @param {*} password 密码
 */
export function loginByUsername(username, password) {
  return http.post('/login', {
    username,
    password
  })
}

/**
 * 退出登录
 */
export function logout() {
  return http.post('/logout')
}

/**
 * 获取登录用户信息
 */
export function getUserInfo() {
  return http.get('/admin')
}

