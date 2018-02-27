import http from '@/utils/http'

/**
 * 查询订单
 */
export function find(id) {
  return http.get('/order/' + id)
}

/**
 * 更新状态
 */
export function updateStatus(ids, status) {
  return http.post('/order/status', {
    'id[]': ids,
    'status': status
  })
}

/**
 * 删除订单
 */
export function remove(ids) {
  return http.post('/order/delete', {
    'id[]': ids
  })
}

/**
 * 保存订单
 */
export function save(params) {
  return http.post('/order', params)
}

/**
 * 获取订单状态
 */
export function getOrderStatus() {
  return http.get('/order/orderStatus')
}

/**
 * 获取支付状态
 */
export function getPayStatus() {
  return http.get('/order/payStatus')
}

/**
 * 获取订单类型
 */
export function getOrderType() {
  return http.get('/order/orderType')
}
/**
 * 获取支付类型
 */
export function getPaymentType() {
  return http.get('/order/paymentType')
}

export function findByOrderSn(sn) {
  return http.get('/orders/goods/info', {
    'orderSn': sn
  })
}
