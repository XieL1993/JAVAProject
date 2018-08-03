import Vue from 'vue'
import chooseBGM from './chooseBGM.vue'

const app = new Vue(chooseBGM)
app.$mount()
export default {
  config: {
    navigationBarTitleText: '选择BGM',
    enablePullDownRefresh: true
  }
}
