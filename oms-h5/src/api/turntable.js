import http from '@/utils/http'

/**
 * 查询抽奖奖品
 */
export function find(id) {
  return http.get('/turntable/' + id)
}

/**
 * 删除抽奖奖品
 */
export function remove(ids) {
  return http.post('/turntable/delete', { 'id[]': ids })
}

/**
 * 保存抽奖奖品
 */
export function save(params) {
  return http.post('/turntable', params)
}
