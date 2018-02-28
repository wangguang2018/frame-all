import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import App from './App'
import router from './router'
import store from './store'
import * as filters from './filters' // 全局filter
import './icons' // icon
import './errorLog'// error log
import './permission' // 权限
import './styles/font-awesome.less'
import 'babel-polyfill'
import VueI18n from 'vue-i18n'
import enLocale from 'element-ui/lib/locale/lang/en'
import zhLocale from 'element-ui/lib/locale/lang/zh-CN'
import zh from './assets/common/lang/zh'
import en from './assets/common/lang/en'
const e = Object.assign(enLocale, en)
const z = Object.assign(zhLocale, zh)
Vue.use(VueI18n)
Vue.use(ElementUI)
Vue.config.lang = 'en'
Vue.locale('zh', z)
Vue.locale('en', e)

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
