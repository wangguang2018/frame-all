import axios from 'axios'
import moment from 'moment'
import querystring from 'querystring'
import { Message, Notification } from 'element-ui'
import router from '../router'

// 创建axios实例
const instance = axios.create({
  baseURL: '/api',        // api的base_url
  withCredentials: true,  // 保存登录凭证
  timeout: 5000           // 请求超时时间
})
instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

// request拦截器
instance.interceptors.request.use(config => {
  for (var e in config.params) {
    if (config.params[e] instanceof Date) {
      config.params[e] = moment(config.params[e]).format()
    }
  }
  return config
}, error => {
  Promise.reject(error)
})

// response拦截器
instance.interceptors.response.use(
  response => {
    // 判断请求是否正常
    if (!response.status === 200) {
      throw new Error('服务器繁忙，请重试')
    }

    // 处理异常错误
    if (response.data.err_code === 20001) {
      // 登录失效，跳转到登录页面
      router.replace('/login')
      throw new Error(response.data.err_msg)
    } else if (response.data.err_code !== 0) {
      throw new Error(response.data.err_msg)
    } else {
      // 请求成功
      if (response.config.method === 'post') {
        Notification.success({
          title: '成功',
          message: response.data.err_msg,
          duration: 2000
        })
      }
    }
    return response
  },
  error => {
    let errMessage = error.message
    if (error.message.indexOf('timeout') >= 0) {
      errMessage = '请求超时'
    }
    Message({
      message: errMessage,
      type: 'error'
    })
    return Promise.reject(error)
  }
)

export default {
  /**
   * get请求
   */
  get(url, params) {
    return new Promise((resolve, reject) => {
      instance.get(url, {
        params: params
      }).then(response => {
        resolve(response.data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  /**
   * post请求
   */
  post(url, params) {
    return new Promise((resolve, reject) => {
      instance.post(
        url,
        querystring.stringify(params)
      ).then(response => {
        resolve(response.data)
      }).catch(error => {
        Message({
          message: error.message,
          type: 'error'
        })
        reject(error)
      })
    })
  }
}
