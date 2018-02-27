import http from '@/utils/http'

/**
 * 查询互动列表
 */
export function find(id) {
  return http.get('/thread/' + id)
}

/**
 * 删除互动列表
 */
export function remove(ids) {
  return http.post('/thread/delete', { 'id[]': ids })
}

/**
 * 保存互动列表
 */
export function save(params) {
  return http.post('/thread', params)
}
