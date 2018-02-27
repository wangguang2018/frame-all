import Vue from 'vue'
import Router from 'vue-router'
import storage from '@/utils/storage'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading

Vue.use(Router)

/* layout */
import Layout from '../views/layout/Layout'

/**
* icon : the icon show in the sidebar
* hidden : if `hidden:true` will not show in the sidebar
* redirect : if `redirect:noredirect` will no redirct in the levelbar
* noDropdown : if `noDropdown:true` will has no submenu
* meta : { role: ['admin'] }  will control the page role
**/
export const constantRouterMap = [
  { path: '/login', component: _import('login/index'), hidden: true },
  { path: '/authredirect', component: _import('login/authredirect'), hidden: true },
  { path: '/404', component: _import('errorPage/404'), hidden: true },
  { path: '/401', component: _import('errorPage/401'), hidden: true },

  // 隐藏菜单
  {
    path: '/',
    component: Layout,
    icon: 'people',
    noDropdown: true,
    hidden: true,
    name: '系统管理',
    children: [
      { path: '/sys/admin/edit', component: _import('sys/admin/edit'), name: '编辑管理员', hidden: true },
      { path: '/sys/menu/edit', component: _import('sys/menu/edit'), name: '编辑菜单', hidden: true },
      { path: '/sys/role/edit', component: _import('sys/role/edit'), name: '编辑角色', hidden: true },
      { path: '/sys/permission/index', component: _import('sys/permission/index'), name: '权限点', hidden: true }
    ]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{ path: 'dashboard', component: _import('dashboard/index') }]
  },
  // {
  //   path: '/introduction',
  //   component: Layout,
  //   redirect: '/introduction/index',
  //   icon: 'people',
  //   noDropdown: true,
  //   children: [{ path: 'index', component: _import('introduction/index'), name: '简述' }]
  // },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '商品管理',
    hidden: true,
    children: [
      // {
      //   path: '/goods/category/index',
      //   name: '',
      //   redirect: '/goods/category/index',
      //   component: _import('goods/category/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('goods/category/index'), name: '商品分类' }
      //   ]
      // },
      // {
      //   path: '/goods',
      //   name: '',
      //   redirect: '/goods/index',
      //   component: _import('goods/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('goods/index'), name: '商品列表' }
      //   ]
      // },
      // {
      //   path: '/goods/attribute/index',
      //   name: '',
      //   redirect: '/goods/attribute/index',
      //   component: _import('goods/attribute/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('goods/attribute/index'), name: '商品属性' }
      //   ]
      // },
      // {
      //   path: '/goods/examine/index',
      //   name: '',
      //   redirect: '/goods/examine/index',
      //   component: _import('goods/examine/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('goods/examine/index'), name: '商品审核' }
      //   ]
      // },
      // {
      //   path: '/goods/brand/index',
      //   name: '',
      //   redirect: '/goods/brand/index',
      //   component: _import('goods/brand/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('goods/brand/index'), name: '商品品牌' }
      //   ]
      // },
      // {
      //   path: '/logistics/driver/index',
      //   name: '',
      //   redirect: '/logistics/driver/index',
      //   component: _import('logistics/driver/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('logistics/driver/index'), name: '商品配送' }
      //   ]
      // },
      // {
      //   path: '/goods/promotion/index',
      //   name: '',
      //   redirect: '/goods/promotion/index',
      //   component: _import('goods/promotion/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('goods/promotion/index'), name: '商品促销' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '门店管理',
    hidden: true,
    children: [
      // {
      //   path: '/merchant',
      //   name: '',
      //   redirect: '/merchant/index',
      //   component: _import('merchant/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('merchant/index'), name: '门店列表' }
      //   ]
      // },
    ]
  },
  // {
  //   path: '',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   icon: 'setting',
  //   name: '订单管理',
  //   children: [
  //     {
  //       path: '/order/index',
  //       name: '',
  //       redirect: '/order/index',
  //       component: _import('order/index'),
  //       noDropdown: true,
  //       children: [
  //         { path: 'index', component: _import('order/index'), name: '订单列表' }
  //       ]
  //     }
  //   ]
  // },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '订货管理',
    hidden: true,
    children: [
      // {
      //   path: '/order/goods/index',
      //   name: '',
      //   redirect: '/order/goods/index',
      //   component: _import('order/goods/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: '', component: _import('order/goods/index'), name: '订货列表' }
      //   ]
      // },
    ]
  },
  // {
  //   path: '',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   icon: 'setting',
  //   name: '农资贷',
  //   children: [
  //     {
  //       path: '/loan',
  //       name: '',
  //       redirect: '/loan/index',
  //       component: _import('loan/index'),
  //       noDropdown: true,
  //       children: [
  //         { path: 'index', component: _import('loan/index'), name: '农资贷列表' }
  //       ]
  //     }
  //   ]
  // },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '抽奖',
    hidden: true,
    children: [
      // {
      //   path: '/lottery',
      //   name: '',
      //   redirect: '/turntable/index',
      //   component: _import('turntable/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('turntable/index'), name: '奖品列表' }
      //   ]
      // },
      // {
      //   path: '/lottery',
      //   name: '',
      //   redirect: '/lottery/record/index',
      //   component: _import('lottery/record/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'record', component: _import('lottery/record/index'), name: '中奖名单' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '分销管理',
    hidden: true,
    children: [
      // {
      //   path: '/promotion/discount/index',
      //   name: '',
      //   redirect: '/promotion/discount/index',
      //   component: _import('promotion/discount/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('promotion/discount/index'), name: '折扣设置' }
      //   ]
      // },
      // {
      //   path: '/invitation/records/index',
      //   name: '',
      //   redirect: '/invitation/record/index',
      //   component: _import('invitation/record/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('invitation/record/index'), name: '推荐列表' }
      //   ]
      // },
      // {
      //   path: '/invitations/index',
      //   name: '',
      //   redirect: '/invitation/index',
      //   component: _import('invitation/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('invitation/index'), name: '收益列表' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '财务',
    hidden: true,
    children: [
      // {
      //   path: '/finance',
      //   name: '',
      //   redirect: '/finance/invoice',
      //   component: _import('finance/invoice'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'invoice', component: _import('finance/invoice'), name: '发票说明' }
      //   ]
      // },
      // {
      //   path: '/finance',
      //   name: '',
      //   redirect: '/finance/payment',
      //   component: _import('finance/payment'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'payment', component: _import('finance/payment'), name: '支付设置' }
      //   ]
      // },
      // {
      //   path: '/payment/config/param/index',
      //   name: '',
      //   redirect: '/payment/config/param/index',
      //   component: _import('payment/config/param/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'payment', component: _import('payment/config/param/index'), name: '支付参数设置' }
      //   ]
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '会员管理',
    hidden: true,
    children: [
      // {
      //   path: '/member',
      //   name: '',
      //   redirect: '/member/index',
      //   component: _import('member/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('member/index'), name: '会员列表' }
      //   ]
      // },
      // {
      //   path: '/comment/goods/index',
      //   name: '',
      //   redirect: '/comment/goods/index',
      //   component: _import('comment/goods/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('comment/goods/index'), name: '商品评论' }
      //   ]
      // },
      // {
      //   path: '/comment/article/index',
      //   name: '',
      //   redirect: '/comment/article/index',
      //   component: _import('comment/article/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('comment/article/index'), name: '文章评论' }
      //   ]
      // },
    ]
  },
  // {
  //   path: '',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   icon: 'setting',
  //   name: '统计报表',
  //   children: [
      // {
      //   path: '/statistics',
      //   name: '',
      //   redirect: '/statistics/memberStatistics',
      //   component: _import('statistics/memberStatistics'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'memberStatistics', component: _import('statistics/memberStatistics'), name: '会员统计' }
      //   ]
      // },
      // {
      //   path: '/statistics',
      //   name: '',
      //   redirect: '/statistics/merchantStatistics',
      //   component: _import('statistics/merchantStatistics'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'merchantStatistics', component: _import('statistics/merchantStatistics'), name: '推广商统计' }
      //   ]
      // }
      // {
      //   path: '/comment/goods/index',
      //   name: '',
      //   redirect: '/comment/goods/index',
      //   component: _import('comment/goods/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('comment/goods/index'), name: '商品评论' }
      //   ]
      // },
      // {
      //   path: '/comment/article/index',
      //   name: '',
      //   redirect: '/comment/article/index',
      //   component: _import('comment/article/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('comment/article/index'), name: '文章评论' }
      //   ]
      // },
      // { path: '/member/edit', component: _import('member/edit'), name: '会员详情', hidden: true }
  //   ]
  // },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '系统设置',
    hidden: true,
    children: [
      // {
      //   path: '/area/index',
      //   name: '',
      //   redirect: '/area/index',
      //   component: _import('area/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('area/index'), name: '区域划分' }
      //   ]
      // },
      // {
      //   path: '/sensitive/keywords/index',
      //   name: '',
      //   redirect: '/sensitive/keywords/index',
      //   component: _import('sensitive/keywords/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('sensitive/keywords/index'), name: '敏感词管理' }
      //   ]
      // },
      // {
      //   path: '/hot/search/keywords/index',
      //   name: '',
      //   redirect: '/hot/search/keywords/index',
      //   component: _import('hot/search/keywords/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('hot/search/keywords/index'), name: '热门词管理' }
      //   ]
      // },
      // {
      //   path: '/message/index',
      //   name: '',
      //   redirect: '/message/index',
      //   component: _import('message/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('message/index'), name: '消息推送' }
      //   ]
      // },
      // {
      //   path: '/message/category/index',
      //   name: '',
      //   redirect: '/message/category/index',
      //   component: _import('message/category/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('message/category/index'), name: '消息分类' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '文章管理',
    hidden: true,
    children: [
      // {
      //   path: '/article',
      //   name: '',
      //   redirect: '/article/index',
      //   component: _import('article/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('article/index'), name: '文章管理' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '营销',
    hidden: true,
    children: [
      // {
      //   path: '/banner',
      //   name: '',
      //   redirect: '/banner/index',
      //   component: _import('banner/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('banner/index'), name: '广告管理' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '互动区',
    hidden: true,
    children: [
      // {
      //   path: '/thread/tag/index',
      //   name: '',
      //   redirect: '/thread/tag/index',
      //   component: _import('thread/tag/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('thread/tag/index'), name: '互动标签' }
      //   ]
      // },
    ]
  },
  {
    path: '',
    component: Layout,
    redirect: 'noredirect',
    icon: 'setting',
    name: '代金券管理',
    hidden: true,
    children: [
      // {
      //   path: '/coupon/template/index',
      //   name: '',
      //   redirect: '/coupon/template/index',
      //   component: _import('coupon/template/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('coupon/template/index'), name: '代金券' }
      //   ]
      // },
      // {
      //   path: '/coupon/index',
      //   name: '',
      //   redirect: '/coupon/index',
      //   component: _import('coupon/index'),
      //   noDropdown: true,
      //   children: [
      //     { path: 'index', component: _import('coupon/index'), name: '会员代金券' }
      //   ]
      // },
    ]
  }
]
const menus = storage.get('menus')
if (menus) {
  menus.forEach((item) => {
    const menu = {
      path: '',
      component: Layout,
      redirect: 'noredirect',
      icon: item.icon,
      name: item.name,
      children: []
    }
    item.childList.forEach((child) => {
      const childMenu = {
        path: '/' + child.url,
        name: child.name,
        component: _import(child.url)
      }
      menu.children.push(childMenu)
    })
    constantRouterMap.push(menu)
  })
}

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = []

// export const asyncRouterMap = [
//   {
//     path: '/permission',
//     component: Layout,
//     redirect: '/permission/index',
//     name: '权限测试',
//     icon: 'lock',
//     meta: { role: ['admin'] },
//     noDropdown: true,
//     children: [{ path: 'index', component: _import('permission/index'), name: '权限测试页', meta: { role: ['admin'] }}]
//   },
//   {
//     path: '/icon',
//     component: Layout,
//     icon: 'icon',
//     noDropdown: true,
//     children: [{ path: 'index', component: _import('svg-icons/index'), name: 'icons' }]
//   },
//   {
//     path: '/components',
//     component: Layout,
//     redirect: '/components/index',
//     name: '组件',
//     icon: 'component',
//     children: [
//       { path: 'index', component: _import('components/index'), name: '介绍 ' },
//       { path: 'tinymce', component: _import('components/tinymce'), name: '富文本编辑器' },
//       { path: 'markdown', component: _import('components/markdown'), name: 'Markdown' },
//       { path: 'jsoneditor', component: _import('components/jsonEditor'), name: 'JSON编辑器' },
//       { path: 'dndlist', component: _import('components/dndList'), name: '列表拖拽' },
//       { path: 'splitpane', component: _import('components/splitpane'), name: 'SplitPane' },
//       { path: 'avatarupload', component: _import('components/avatarUpload'), name: '头像上传' },
//       { path: 'dropzone', component: _import('components/dropzone'), name: 'Dropzone' },
//       { path: 'sticky', component: _import('components/sticky'), name: 'Sticky' },
//       { path: 'countto', component: _import('components/countTo'), name: 'CountTo' },
//       { path: 'mixin', component: _import('components/mixin'), name: '小组件' },
//       { path: 'backtotop', component: _import('components/backToTop'), name: '返回顶部' }
//     ]
//   },
//   {
//     path: '/charts',
//     component: Layout,
//     redirect: '/charts/index',
//     name: '图表',
//     icon: 'chart',
//     children: [
//       { path: 'index', component: _import('charts/index'), name: '介绍' },
//       { path: 'keyboard', component: _import('charts/keyboard'), name: '键盘图表' },
//       { path: 'keyboard2', component: _import('charts/keyboard2'), name: '键盘图表2' },
//       { path: 'line', component: _import('charts/line'), name: '折线图' },
//       { path: 'mixchart', component: _import('charts/mixChart'), name: '混合图表' }
//     ]
//   },
//   {
//     path: '/example',
//     component: Layout,
//     redirect: 'noredirect',
//     name: '综合实例',
//     icon: 'example',
//     children: [
//       {
//         path: '/example/table',
//         component: _import('example/table/index'),
//         redirect: '/example/table/table',
//         name: 'Table',
//         icon: 'table',
//         children: [
//           { path: 'dynamictable', component: _import('example/table/dynamictable/index'), name: '动态table' },
//           { path: 'dragtable', component: _import('example/table/dragTable'), name: '拖拽table' },
//           { path: 'inline_edit_table', component: _import('example/table/inlineEditTable'), name: 'table内编辑' },
//           { path: 'table', component: _import('example/table/table'), name: '综合table' }
//         ]
//       },
//       { path: 'form/edit', icon: 'form', component: _import('example/form'), name: '编辑Form', meta: { isEdit: true }},
//       { path: 'form/create', icon: 'form', component: _import('example/form'), name: '创建Form' },
//       { path: 'tab/index', icon: 'tab', component: _import('example/tab/index'), name: 'Tab' }
//     ]
//   },
//   {
//     path: '/error',
//     component: Layout,
//     redirect: 'noredirect',
//     name: '错误页面',
//     icon: '404',
//     children: [
//       { path: '401', component: _import('errorPage/401'), name: '401' },
//       { path: '404', component: _import('errorPage/404'), name: '404' }
//     ]
//   },
//   {
//     path: '/errlog',
//     component: Layout,
//     redirect: 'noredirect',
//     name: 'errlog',
//     icon: 'bug',
//     noDropdown: true,
//     children: [{ path: 'log', component: _import('errlog/index'), name: '错误日志' }]
//   },
//   {
//     path: '/excel',
//     component: Layout,
//     redirect: '/excel/download',
//     name: 'excel',
//     icon: 'excel',
//     children: [
//       { path: 'download', component: _import('excel/index'), name: '导出excel' },
//       { path: 'download2', component: _import('excel/selectExcel'), name: '导出已选择项' },
//       { path: 'upload', component: _import('excel/uploadExcel'), name: 'upload excel' }
//     ]
//   },
//   {
//     path: '/theme',
//     component: Layout,
//     redirect: 'noredirect',
//     name: 'theme',
//     icon: 'theme',
//     noDropdown: true,
//     children: [{ path: 'index', component: _import('theme/index'), name: '换肤' }]
//   },

//   { path: '*', redirect: '/404', hidden: true }
// ]
