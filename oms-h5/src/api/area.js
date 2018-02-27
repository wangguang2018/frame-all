import http from '@/utils/http'

/**
 * 查询区域
 */
export function find(id) {
  return http.get('/area/' + id)
}

/**
 * 删除区域
 */
export function remove(ids) {
  return http.post('/area/delete', { 'id[]': ids })
}

/**
 * 保存区域
 */
export function save(params) {
  return http.post('/area', params)
}

/**
 * 所有区域
 */
export function findALLAreas() {
  return http.get('/areas/all')
}
