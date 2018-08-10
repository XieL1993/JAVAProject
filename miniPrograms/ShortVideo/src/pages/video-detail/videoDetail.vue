<template>
<div id="video-detail">
  <video  class="video" :src="video.videoPath"
          :controls="true"
          :autoplay="true"
          :loop="true"
          :enable-progress-gesture="true"
          :objectFit="fit"
  >
    <cover-view class='container-bottom'>
      <cover-view class="box">
        <cover-image class='svg-icon' src='/static/image/unlike.png'></cover-image>
        <cover-view  class="des">关注</cover-view>
      </cover-view>
      <cover-view class="box" @click="toComment">
        <cover-image class='svg-icon' src='/static/image/comments.png'></cover-image>
        <cover-view  class="des">评论</cover-view>
      </cover-view>
      <cover-view class="box">
        <cover-image class='svg-icon' src='/static/image/share.png'></cover-image>
        <cover-view class="des">分享</cover-view>
      </cover-view>
    </cover-view>
  </video>
</div>
</template>
<script>
  import {go} from '../../common/utils'

export default {
    computed: {
      fit () {
        if (!this.video || !this.video.videoHeight || !this.video.videoWidth) {
          return 'contain'
        }
        const height = this.video.videoHeight
        const width = this.video.videoWidth
        if (width >= height) {
          return 'contain'
        } else {
          return 'cover'
        }
      }
    },
    data () {
      return {
        video: {}
      }
    },
    onLoad (options) {
      this.video = JSON.parse(options.video)
      console.log(this.video)
    },
    methods: {
      toComment () {
        go('/pages/comment/main?videoId=' + this.video.id)
      }
    }
  }
</script>
<style scoped lang="scss">
#video-detail{
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  .video{
    width: 100%;
    height: 100%;
  }
  .container-bottom{
    position: absolute;
    right: 0;
    left: 0;
    bottom: 60px;
    display: flex;
    .box{
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: center;
      .svg-icon{
        width: 30px;
        height: 30px;
        margin-right: 10px;
      }
      .des{
        color: #ffffff;
        font-size: 14px;
      }
    }

  }
}
</style>
