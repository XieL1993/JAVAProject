<template>
  <div class="index">
    <div class="text">{{msg}}</div>

  </div>
</template>

<script>
  import { getActorList } from '../../api'

  export default {
    mounted () {
      this.getActor()
    },
    data () {
      return {
        msg: '我是首页',
        actor: []
      }
    },
    methods: {
      async getActor () {
        this.actor = await getActorList()
        console.log(this.actor)
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
