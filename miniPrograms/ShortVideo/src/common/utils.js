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
