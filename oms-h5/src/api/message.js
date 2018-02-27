import http from '@/utils/http'

/**
 * 查询消息推送
 */
export function find(id) {
  return http.get('/message/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/message/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除消息推送
 */
export function remove(ids) {
  return http.post('/message/delete', { 'id[]': ids })
}

/**
 * 保存消息推送
 */
export function save(params) {
  return http.post('/message', params)
}
