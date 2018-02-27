import storage from '@/utils/storage'

// 判断是否有权限
export default function hasPermission(key) {
  const permissions = storage.get('permissions')

  let ret = false
  permissions && permissions.forEach((permission, index) => {
    if (permission.key === key) {
      ret = true
      return
    }
  })
  return ret
}
