import fetch from './httpFetch'

export function addComments (data) {
  return fetch.post('comments/add', data)
}

export function getComments (data) {
  return fetch.get('comments/list', data)
}
