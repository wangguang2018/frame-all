import http from '@/utils/http'

/**
 * 查询角色
 */
export function find(id) {
  return http.get('/role/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/role/status', { 'id[]': ids, 'status': status })
}

/**
 * 删除角色
 */
export function remove(ids) {
  return http.post('/role/delete', { 'id[]': ids })
}

/**
 * 保存角色
 */
export function save(params) {
  console.info(params)
  return http.post('/role', params)
}

/**
 * 查询菜单
 */
export function findMenus(roleId) {
  return http.get('/role/menus', { roleId: roleId })
}

/**
 * 查询可用角色
 */
export function findEnableRoles(adminId) {
  return http.get('/roles/enable', { adminId: adminId })
}

