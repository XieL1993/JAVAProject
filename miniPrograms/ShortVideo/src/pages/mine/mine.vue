<template>
  <div id="mine">
    <img :src="avatar" class="avatar"/>
    <div class="user-name">{{nickname}}</div>
    <button @click="bindButtonTap">获取视频</button>
  </div>
</template>
<script>
  import { showToast, go } from '../../common/utils'

  export default {
    onShow () {
      const userInof = wx.getStorageSync('userInfo')
      if (userInof) {
        this.avatar = userInof.avatar
        this.nickname = userInof.nickname
      }
    },
    data () {
      return {
        avatar: '/static/image/cat.jpeg',
        nickname: '未登录'
      }
    },
    methods: {
      bindButtonTap () {
        // var that = this
        wx.chooseVideo({
          sourceType: ['album'],
          // sourceType: ['album', 'camera'],
          // maxDuration: 11,
          // compressed: true,
          // camera: ['front', 'back'],
          success: function (res) {
            console.log(res)
            const videoSeconds = res.duration
            const videoHeight = res.height
            const videoWidth = res.width
            const videoPath = res.tempFilePath
            if (videoSeconds > 11) {
              showToast('视频长度不能超过10秒')
            } else if (videoSeconds < 1) {
              showToast('视频长度太短，请上传超过1秒的视频')
            } else {
              go(`/pages/chooseBGM/main?videoSeconds=${videoSeconds}&videoWidth=${videoWidth}&videoHeight=${videoHeight}&videoPath=${videoPath}`)
            }
          },
          fail: function (e) {
            console.log(e)
            go('/pages/chooseBGM/main?id=1')
          }
        })
      }
    }
  }
</script>
<style scoped lang="scss">
  @import "../../common/styles/variables";

  #mine {
    position: absolute;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background-color: #f5f5f5;
    display: flex;
    flex-direction: column;
    align-items: center;
    .avatar {
      width: 110px;
      height: 110px;
      border-radius: 50%;
      margin-top: 20px;
    }
    .user-name {
      margin-top: 10px;
      color: $color-main;
      font-size: 18px;
    }
  }
</style>
