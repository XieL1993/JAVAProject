import Vue from 'vue'
import comment from './comment.vue'

const app = new Vue(comment)
app.$mount()
export default {
  config: {
    enablePullDownRefresh: true,
    onReachBottomDistance: 60
  }
}
