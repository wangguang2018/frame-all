export default {
  /**
   * 保存数据
   *
   * @param {*} key 键
   * @param {*} value 值
   */
  set(key, value) {
    window.localStorage.setItem(key, JSON.stringify(value))
  },

  /**
   * 获取数据
   *
   * @param {*} key  键
   */
  get(key) {
    const value = window.localStorage.getItem(key)
    if (value == null || value === '') {
      return null
    }
    return JSON.parse(value)
  },

  /**
   * 删除数据
   *
   * @param {*} key 键
   */
  remove(key) {
    window.localStorage.removeItem(key)
  }
}
