import Fly from 'flyio/dist/npm/wx'
import { baseUrl } from '../config/env'

const http = new Fly()
http.config.baseURL = baseUrl
http.interceptors.request.use(request => {
  request.headers['token'] = '123'
  return request
})
http.interceptors.response.use(response => {
  if (response.data && typeof response.data === 'string') {
    response.data = JSON.parse(response.data)
  }
  return response.data
}, error => {
  return Promise.reject(error)
})
export default http
