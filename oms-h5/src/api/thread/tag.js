import http from '@/utils/http'

/**
 * 查询互动标签
 */
export function find(id) {
  return http.get('/thread/tag/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/thread/tag/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除互动标签
 */
export function remove(ids) {
  return http.post('/thread/tag/delete', { 'id[]': ids })
}

/**
 * 保存互动标签
 */
export function save(params) {
  return http.post('/thread/tag', params)
}
