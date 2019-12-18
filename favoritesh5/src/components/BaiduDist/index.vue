<template>
  <div style="height:100%">
    <iframe
      :src="src"
      style="width:100%;height:100%"
    />
  </div>
</template>
<script>
export default {
  props: {
    word: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      src: 'https://fanyi.baidu.com/?aldtype=16047#en/zh/' + this.word
    }
  }
}
</script>
