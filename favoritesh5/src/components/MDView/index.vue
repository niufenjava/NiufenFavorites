<template>
  <div>
    <div
      ref="hlDiv"
      style="height:500px"
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
        code: '```javascript\nfunction(){\n\tconsole.log(123)\n}\n```'
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
    } else if (document.selection && document.selection.type != 'Control') {
        text = document.selection.createRange().text
    }
    if (text !== '') {
        this.$emit('callback', text)
    }
    },
  // 打开抽屉方法
    open (content) {
      const list = ['patterns', 'significances']
      content = this.highlight(content, list)
      console.info('content', content)
      this.code = marked(content)
    },
    // 匹配整个关键词 不拆分
    highlight(text, words, tag) {
        // 默认的标签，如果没有指定，使用span
        tag = tag || 'span'
        var i; var len = words.length; var re
        for (i = 0; i < len; i++) {
            // 正则匹配所有的文本
            re = new RegExp(words[i], 'g')
            if (re.test(text)) {
                text = text.replace(re, '<' + tag + ' class="highlight">$&</' + tag + '>')
            }
        }
        return text
      }
    }
  }
</script>
<style>
.hljs p {
  font-size: 20px;
}
.highlight {
  background: yellow;
}
</style>
