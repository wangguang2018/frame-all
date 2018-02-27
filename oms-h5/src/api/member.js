import http from '@/utils/http'

/**
 * 查询会员管理
 */
export function find(id) {
  return http.get('/member/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/member/status', {
    'id[]': ids,
    'status': status
  })
}

/**
 * 删除会员管理
 */
export function remove(ids) {
  return http.post('/member/delete', {
    'id[]': ids
  })
}

/**
 * 保存会员管理
 */
export function save(params) {
  return http.post('/member', params)
}

// 会员收货地址
export function findAddress(id) {
  return http.get('/member/address', {
    'id': id
  })
}

// 会员代金券
export function findCoupons(id) {
  return http.get('/coupons/member', {
    'id': id
  })
}
/**
 * 查询农户统计
 */
export function findCount() {
  return http.get('/member/count')
}
/**
 * 按搜索条件查询会员统计
 */
export function memberSearch(date, startDate, endDate, type) {
  return http.post('/member/search', {
    'date': date,
    'start_date': startDate,
    'end_date': endDate,
    'type': type
  })
}
