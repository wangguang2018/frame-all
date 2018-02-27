import http from '@/utils/http'

/**
 * 查询农资贷
 */
export function find(id) {
  return http.get('/loan/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/loan/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除农资贷
 */
export function remove(ids) {
  return http.post('/loan/delete', { 'id[]': ids })
}

/**
 * 保存农资贷
 */
export function save(params) {
  return http.post('/loan', params)
}
