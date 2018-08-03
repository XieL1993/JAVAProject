import fetch from './httpFetch'

export function getActorList () {
  return fetch.get('vUser/test')
}
