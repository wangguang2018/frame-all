[![vue](https://img.shields.io/badge/vue-2.4.2-brightgreen.svg)](https://github.com/vuejs/vue)
[![element-ui](https://img.shields.io/badge/element--ui-1.4.2-brightgreen.svg)](https://github.com/ElemeFE/element)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](http://git.ydd100.cn/framework/oms-h5/blob/master/LICENSE)
[![GitHub release](https://img.shields.io/github/release/PanJiaChen/vue-element-admin.svg)]()

## Intro

> In the past half year, I have been building a backend for management dashboard using Vue. Though the backend has contained greater than 70 pages and over 10 permissions, it still takes insignificant effort to maintain the project. So I decide to make it open source so as to share my development experience and progress on backend. The tech stack is mainly [Vue.js](https://github.com/vuejs/vue)+[Element](https://github.com/ElemeFE/element)+[axios](https://github.com/mzabriskie/axios). Since it's a personal project, all data requests are simulated with [Mock.js](https://github.com/nuysoft/Mock). **Note:** if anyone wants to modify or develop based on this project, please remove the mock files.

**Live demo:** http://panjiachen.github.io/vue-element-admin

**Note: element-ui@1.4.2 is used in the project, so vue 2.3.0+ is required.**

 - vueAdmin-template: [vueAdmin-template](https://github.com/PanJiaChen/vueAdmin-template)  
 - electron-vue-admin: [electron-vue-admin](https://github.com/PanJiaChen/electron-vue-admin)
 - Donate:[donate](http://git.ydd100.cn/framework/oms-h5/blob/master/README-en.md#donate)

## Features

- Login/Logout
- Permission authentication
- Sidebar
- Breadcrumb
- Rich text editor
- Markdown editor
- JSON editor
- Drag & drop list
- SplitPane
- Dropzone
- Sticky
- CountTo
- ECharts
- 401, 404 error page
- Error log
- Exporting to Excel
- Upload Excel
- Table example
- Interactive table example
- Drag & drop table example
- Form example
- Multi-environments distribution
- Dashboard
- Two-factor authentication
- Collapsing sidebar (support nested routes)
- Mock data
- cache tabs example
- screenfull
- markdown2html
- views-tab

## Development

```bash
# Clone project
git clone http://git.ydd100.cn/framework/oms-h5.git

# Install dependencies
npm install

# Or (not recommended for cnpm due to unknown bugs, use taobao mirror instead)
npm install --registry=https://registry.npm.taobao.org

# Run local dev server
npm run dev
```

Visit in browser: http://localhost:9527

## Distribution

```bash
# Build staged environment with webpack-bundle-analyzer
npm run build:sit-preview

# Build production environment
npm run build:prod
```

## Directory structure

```
├── build                      // build 
├── config                     // config
├── src                        // source code
│   ├── api                    // all requests
│   ├── assets                 // static resource like themes, fonts
│   ├── components             // global public components
│   ├── directive              // global directive
│   ├── filters                // global filters
│   ├── mock                   // mock data
│   ├── router                 // router
│   ├── store                  // global status management
│   ├── styles                 // global styles
│   ├── utils                  // global public functions
│   ├── view                   // view
│   ├── App.vue                // entry view
│   └── main.js                // entry for loading components, initialization
├── static                     // third-party libraries not packed with Webpack
│   └── Tinymce                // rich text
├── .babelrc                   // babel-loader config
├── eslintrc.js                // eslint config
├── .gitignore                 // gitignore
├── favicon.ico                // favicon
├── index.html                 // html template
└── package.json               // package.json
```

## Changelog
Detailed changes for each release are documented in the [release notes](http://git.ydd100.cn/framework/oms-h5/releases).

## Donate
If you find this project useful, you can buy me a cup of coffee
![donate](https://panjiachen.github.io/donate/donation.png)

## State Management

Only status of user and app configuration is managed by Vuex. Other data are managed by their own business pages.

## Demo

#### Two-factor authentication, supporting WeChat and QQ

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/2login.gif)

#### Realtime switching themes

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/theme.gif)

#### tabs

![tabs](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/tabs.gif)<br />

#### Collapsing sidebar

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/leftmenu.gif)

#### Drag & drop table

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/order.gif)

#### Interactive table

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/dynamictable.gif)

#### Uploading cropped avatar

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/uploadAvatar.gif)

#### Error log

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/errorlog.gif)

#### Rich text (integrated with Qiniu, watermark and customization)

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/editor.gif)

#### Packaging table component

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/table.gif)

#### Charts

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/echarts.gif)

#### Exporting to Excel

![](http://git.ydd100.cn/framework/oms-h5/blob/master/gifs/excel.png)

#### More

http://panjiachen.github.io/vue-element-admin

## License

MIT