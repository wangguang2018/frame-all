import http from '@/utils/http'

/**
 * 查询问卷调查
 */
export function find(id) {
  return http.get('/questionnaire/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/questionnaire/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除问卷调查
 */
export function remove(ids) {
  return http.post('/questionnaire/delete', { 'id[]': ids })
}

/**
 * 保存问卷调查
 */
export function save(params) {
  return http.post('/questionnaire', params)
}
