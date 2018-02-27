import http from '@/utils/http'

/**
 * 查询常见问题
 */
export function find(id) {
  return http.get('/faq/' + id)
}

/**
 * 删除常见问题
 */
export function remove(ids) {
  return http.post('/faq/delete', { 'id[]': ids })
}

/**
 * 保存常见问题
 */
export function save(params) {
  return http.post('/faq', params)
}
