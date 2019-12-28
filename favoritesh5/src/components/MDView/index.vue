<template>
  <div style="height:100%;width:100%;background-color:#FFF;border-radius: 4px 4px 4px 4px;">
    <div
      ref="hlDiv"
      style="height:95%"
      class="hljs"
      @mouseup="selectText"
      v-html="code"
    />
  </div>
</template>

<script>
  import marked from 'marked'
  import hljs from 'highlight.js'
  import 'highlight.js/styles/monokai-sublime.css'

  export default {
    name: 'Height',
    data() {
      return {
        showChFlag: true,
        code: ''
      }
    },
    mounted() {
      marked.setOptions({
          renderer: new marked.Renderer(),
          highlight: function(code) {
            return hljs.highlightAuto(code).value
          },
          pedantic: false,
          gfm: true,
          tables: true,
          breaks: false,
          sanitize: false,
          smartLists: true,
          smartypants: false,
          xhtml: false
        }
      )
      this.code = marked(this.code)
    },

  methods: {
    selectText() {
    let text = ''
      if (window.getSelection) {
        text = window.getSelection().toString()
    } else if (document.selection && document.selection.type !== 'Control') {
        text = document.selection.createRange().text
    }
    if (text !== '') {
        this.$emit('callback', text)
    }
    },
  // 打开抽屉方法
    open (content, showChFlag, wordList) {
      const wordSignList = []
      if (wordList) {
        wordList.forEach(word => {
          word.changeList.forEach(change => {
            wordSignList.push(change)
          })
        })
      }
      console.info('wordSignList', wordSignList)
      console.info('showChFlag', showChFlag)
      let newContent = ''
      if (!showChFlag) {
        const line = /[\n\r]/
        const array = content.split(line)
        console.info('array.size', array.length)
        array.forEach(element => {
          if (element.length > 1 && element.startsWith('*') && element.endsWith('*')) {
            console.info(element)
          } else {
                        console.info(element)

            newContent = newContent + element + '\n\r'
          }
        })
        console.info(showChFlag)
      } else {
        newContent = content
      }
      newContent = this.highlight(newContent, wordSignList)
      this.code = marked(newContent)
    },
    // 匹配整个关键词 不拆分
    highlight(text, words, tag) {
        // 默认的标签，如果没有指定，使用span
        tag = tag || 'span'
        var i; var len = words.length
        console.info(words)
        for (i = 0; i < len; i++) {
            // 正则匹配所有的文本
            // re = new RegExp(words[i], 'g')
            // if (re.test(text)) {
            //     text = text.replace(re, '<' + tag + ' class="highlight">$&</' + tag + '>')
            // }
            text = text.replace(words[i], '<' + tag + ' class="highlight">' + words[i] + '</' + tag + '>')
        }
        return text
      }
    }
  }
</script>
<style>
.hljs p {
  font-size: 24px;
}
.hljs {
  box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;
  border-radius: 4px 4px 4px 4px;
}
.highlight {
  background: yellow;
}
</style>
