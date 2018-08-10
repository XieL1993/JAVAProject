import fetch from './httpFetch'

export function getVideoList (data) {
  return fetch.get('video/list', data)
}
