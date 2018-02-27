import http from '@/utils/http'

/**
 * 查询菜单
 */
export function find(id) {
  return http.get('/menu/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/menu/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除菜单
 */
export function remove(ids) {
  return http.post('/menu/delete', { 'id[]': ids })
}

/**
 * 保存菜单
 */
export function save(params) {
  return http.post('/menu', params)
}

/**
 * 查询一级菜单
 */
export function findFirstLevel() {
  return http.get('/menu/level/first')
}

