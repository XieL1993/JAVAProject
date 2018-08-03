import fetch from './httpFetch'

/**
 * Promise封装wx.login
 */
export function login (userInfo) {
  return new Promise(function (resolve, reject) {
    wx.login({
      success: function (res) {
        if (res.code) {
          fetch.post('user/login_by_weixin', {
            code: res.code,
            userInfo
          }).then(res => {
            // 存储用户信息
            wx.setStorageSync('userInfo', res.data.userInfo)
            wx.setStorageSync('token', res.data.token)
            resolve(res.data)
          }).catch(err => {
            reject(err)
          })
        } else {
          reject(new Error('code is null'))
        }
      },
      fail: function (err) {
        reject(err)
      }
    })
  })
}

/**
 * 判断用户是否登录
 */

/* eslint-disable prefer-promise-reject-errors */
export function checkLogin () {
  return new Promise(function (resolve, reject) {
    // 校验是否存储登录信息
    if (wx.getStorageSync('userInfo') && wx.getStorageSync('token')) {
      checkSession().then(() => {
        resolve(true)
      }).catch(() => {
        reject(false)
      })
    } else {
      reject(false)
    }
  })
}

/**
 * Promise封装wx.checkSession
 */

/* eslint-disable prefer-promise-reject-errors */
export function checkSession () {
  return new Promise(function (resolve, reject) {
    wx.checkSession({
      success: function () {
        resolve(true)
      },
      fail: function () {
        reject(false)
      }
    })
  })
}
