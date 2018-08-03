import fetch from './httpFetch'

export function getBgmList () {
  return fetch.get('bgm/list')
}
