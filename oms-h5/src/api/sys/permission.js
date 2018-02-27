import http from '@/utils/http'

/**
 * 查询权限点
 */
export function find(id) {
  return http.get('/permission/' + id)
}

/**
 * 删除权限单
 */
export function remove(ids) {
  return http.post('/permission/delete', { 'id[]': ids })
}

/**
 * 保存权限点
 */
export function save(params) {
  return http.post('/permission', params)
}

/**
 * 查询权限点是否存在
 */
export function isKeyExists(key, id) {
  return http.get('/permission/exists/key', { key: key, excludeId: id })
}
