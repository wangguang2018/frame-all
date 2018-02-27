import http from '@/utils/http'

/**
 * 查询管理员
 */
export function find(id) {
  return http.get('/admin/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/admin/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除管理员
 */
export function remove(ids) {
  return http.post('/admin/delete', { 'id[]': ids })
}

/**
 * 保存管理员
 */
export function save(params) {
  return http.post('/admin', params)
}

/**
 * 手机号码是否存在
 */
export function isMobileExists(mobile, id) {
  return http.get('/admin/exists/mobile', { 'mobile': mobile, 'excludeId': id })
}

/**
 * 邮箱是否存在
 */
export function isEmailExists(email, id) {
  return http.get('/admin/exists/email', { 'email': email, 'excludeId': id })
}
