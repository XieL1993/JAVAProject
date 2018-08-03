import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue(App)
app.$mount()

export default {
  config: {
    pages: [
      '^pages/mine/main',
      'pages/login/main',
      'pages/chooseBGM/main'
    ],
    window: {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#2672FF',
      navigationBarTitleText: '云英视频',
      navigationBarTextStyle: '#ffffff'
    }
  }
}
