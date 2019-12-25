import request from '@/utils/request'
const uri = '/dict/'
export class dictApi {
  /** 新建或更新单词 */
  static wordCreateOrUpdate(word) {
    return request({
      url: uri + 'word/createOrUpdate',
      method: 'post',
      data: word
    })
  }
  static wordDegree(degree) {
    return request({
      url: uri + 'word/degree',
      method: 'post',
      data: degree
    })
  }

  static wordInfo(word) {
    return request({
      url: uri + 'word/info/' + word,
      method: 'get'
    })
  }

  static wordList(params) {
    return request({
      url: uri + 'word/list',
      method: 'post',
      data: params
    })
  }
}