import {baseUrl} from '../config/env'

export function showErrorToast (msg) {
  wx.showToast({
    title: msg,
    icon: 'none'
  })
}

export function go (url = '/pages/login/main') {
  wx.navigateTo({ url })
}

export function back (delta = 1) {
  wx.navigateBack({
    delta
  })
}

export function showToast (title = '标题', duration = 2000, icon = 'none') {
  wx.showToast({
    title,
    icon,
    duration
  })
}
export function prefixUrl (url = '') {
  return baseUrl + url
}

/**
 *转换日期对象为日期字符串
 * @param time long值
 * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss
 * @return 符合要求的日期字符串
 */
export function getFormatDate (time, pattern = 'yyyy-MM-dd hh:mm:ss') {
  const date = new Date(time)
  const o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds(),
    'q+': Math.floor((date.getMonth() + 3) / 3),
    'S': date.getMilliseconds()
  }
  if (/(y+)/.test(pattern)) {
    pattern = pattern.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (const k in o) {
    if (new RegExp('(' + k + ')').test(pattern)) {
      pattern = pattern.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length))
    }
  }
  return pattern
}
