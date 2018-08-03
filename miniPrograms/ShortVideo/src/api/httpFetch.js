import Fly from 'flyio/dist/npm/wx'
import { apiUrl } from '../config/env'

const httpFetch = new Fly()
httpFetch.config.baseURL = apiUrl
httpFetch.interceptors.request.use(request => {
  request.headers['Content-Type'] = 'application/json;charset=UTF-8'
  request.headers['token'] = wx.getStorageSync('token') || ''
  return request
})
httpFetch.interceptors.response.use(response => {
  if (response.data && typeof response.data === 'string') {
    response.data = JSON.parse(response.data)
  }
  if (!response || response.status !== 200) {
    console.error(`response.status:${response.status}`)
    return Promise.reject(new Error(`response.status:${response.status}`))
  } else if (response.data.code !== 0) {
    if (/103|104|105/.test(response.data.code)) {
      // 清除登录相关内容
      try {
        wx.removeStorageSync('userInfo')
        wx.removeStorageSync('token')
      } catch (e) {
        // Do something when catch error
      }
      // 切换到登录页面
      wx.navigateTo({
        url: '/pages/login/main'
      })
    }
    console.error(response.data.msg)
    return Promise.reject(new Error(response.data.msg))
  } else {
    return response.data
  }
}, error => {
  console.error(error.message)
  return Promise.reject(error)
})
export default httpFetch
