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
  static noteRename(note) {
    return request({
      url: uri + 'note/rename',
      method: 'post',
      data: note
    })
  }
  static noteRelWordDelete(node) {
    return request({
      url: uri + 'note/rel/word/delete',
      method: 'post',
      data: node
    })
  }
  static noteRelWordList(id) {
    return request({
      url: uri + 'note/rel/word/list/' + id,
      method: 'get'
    })
  }
  static noteRelWord(note) {
    return request({
      url: uri + 'note/rel/word',
      method: 'post',
      data: note
    })
  }
  static noteContent(note) {
    return request({
      url: uri + 'note/content',
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
  static noteDetail(id) {
    return request({
      url: uri + 'note/detail/' + id,
      method: 'get'
    })
  }
  /** 新建或更新单词 */
  static noteDelete(note) {
    return request({
      url: uri + 'note/delete',
      method: 'post',
      data: note
    })
  }
  static noteDegree(degree) {
    return request({
      url: uri + 'note/degree',
      method: 'post',
      data: degree
    })
  }
}