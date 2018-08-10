import Vue from 'vue'
import App from './App'
Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue(App)
app.$mount()

export default {
  config: {
    pages: [
      'pages/index/main',
      'pages/mine/main',
      'pages/login/main',
      'pages/video-detail/main',
      '^pages/comment/main',
      'pages/chooseBGM/main'
    ],
    window: {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#2672FF',
      navigationBarTitleText: '云英视频',
      navigationBarTextStyle: '#ffffff',
      backgroundColor: '#303133'
    },
    tabBar: {
      list: [{
        pagePath: 'pages/index/main',
        text: '首页'
      }, {
        pagePath: 'pages/mine/main',
        text: '我的'
      }]
    }
  }
}
