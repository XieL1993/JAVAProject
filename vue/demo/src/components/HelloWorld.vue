<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import{ getBeauty } from '../api/api';

@Component
export default class HelloWorld extends Vue {
  @Prop() private msg!: string;
  private created ():void {
    const res = await getBeauty('http://gank.io/api/data/福利',10,1)
    console.log(res)
  }
}
</script>

<style scoped lang="scss">
</style>
