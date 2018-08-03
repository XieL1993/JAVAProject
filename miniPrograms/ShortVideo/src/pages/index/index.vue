<template>
  <div class="index">
    <a href="/pages/login/main">登录</a>
  </div>
</template>

<script>
  import { getActorList } from '../../api'

  export default {
    onShow () {
      this.getActor()
    },
    data () {
      return {
      }
    },
    methods: {
      async getActor () {
        try {
          const res = await getActorList()
          console.log(res)
        } catch (e) {
        }
        console.log(111)
      }
    }
  }
</script>

<style scoped lang="scss">
  .index {
    background: deeppink;
    .text {
      color: white
    }
  }
</style>
