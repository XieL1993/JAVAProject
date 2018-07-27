<template>
  <div class="container" @click="clickHandle">
    <div class="message">{{msg}}</div>
    <click-counter text="郑爽"/>
  </div>
</template>

<script>
  import ClickCounter from '../../components/click-counter'

  export default {
    data () {
      return {
        msg: 'Hello'
      }
    },
    methods: {
      clickHandle () {
        this.msg = 'Clicked!!!!!!'
      }
    },
    components: {
      ClickCounter
    }
  }
</script>

<style scoped>
  .container {
    height: 100%;
    background: yellowgreen;
  }
</style>
