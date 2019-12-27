import request from '@/utils/request'
const uri = '/favorite/'
export class favoriteApi {
  static websiteClick(id) {
    return request({
      url: uri + 'website/click/' + id,
      method: 'get'
    })
  }
  static websiteInfo(id) {
    return request({
      url: uri + 'website/info/' + id,
      method: 'get'
    })
  }
  static typeList() {
    return request({
      url: uri + 'type/list',
      method: 'get'
    })
  }

  static allTypeList() {
    return request({
      url: uri + 'type/all/list',
      method: 'get'
    })
  }
  static websiteFavorite(form) {
    return request({
      url: uri + 'website/favorite',
      method: 'post',
      data: form
    })
  }
  static websiteList(id) {
    return request({
      url: uri + 'website/list/' + id,
      method: 'get'
    })
  }
  static typeEdit(form) {
    return request({
      url: uri + 'type/edit',
      method: 'post',
      data: form
    })
  }
  static websiteEdit(form) {
    return request({
      url: uri + 'website/edit',
      method: 'post',
      data: form
    })
  }
}