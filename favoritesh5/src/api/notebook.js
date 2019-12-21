import request from '@/utils/request'
const uri = '/notebook/'
export class notebookApi {
  /** 新建或更新单词 */
  static noteCreate(note) {
    return request({
      url: uri + 'note/create',
      method: 'post',
      data: note
    })
  }
  static noteTree() {
    return request({
      url: uri + 'note/tree',
      method: 'get'
    })
  }

  static wordInfo(word) {
    return request({
      url: uri + 'word/info/' + word,
      method: 'get'
    })
  }

  static wordList() {
    return request({
      url: uri + 'word/list',
      method: 'get'
    })
  }
}