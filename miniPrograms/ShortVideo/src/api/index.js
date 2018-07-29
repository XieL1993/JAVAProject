import http from './http'

export function getActorList () {
  return http.get('actor/list')
}
