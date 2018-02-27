import http from '@/utils/http'

/**
 * 查询系统参数
 */
export function find(id) {
  return http.get('/config/' + id)
}

/**
 * 删除系统参数
 */
export function remove(ids) {
  return http.post('/config/delete', { 'id[]': ids })
}

/**
 * 保存系统参数
 */
export function save(params) {
  return http.post('/config', params)
}
