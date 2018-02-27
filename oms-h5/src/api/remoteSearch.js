import fetch from '@/utils/http'

export function userSearch(name) {
  return fetch({
    url: '/search/user',
    method: 'get',
    params: { name }
  })
}
