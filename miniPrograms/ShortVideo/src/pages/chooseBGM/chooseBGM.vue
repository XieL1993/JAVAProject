<template>
  <div id="choose-bgm">
    <form @submit='upload' class="form">
      <radio-group name="bgmId" class="radio-group">
        <div v-for="(item,index) in bgmList" :key="index" class="radio-item">
          <audio :name="item.name" :author="item.author" :src="item.path" class="audio" :controls="true"
                 :loop="true"></audio>
          <radio :value="item.id" class="radio"></radio>
        </div>
      </radio-group>
      <div class="inputView">
        <label class="label">视频描述：</label>
        <input name="desc" class="inputText" placeholder="说点什么吧"/>
      </div>
      <button class="submit" type="primary" form-type="submit">上传视频</button>
      <button class="reset" type="warn" form-type="reset">重置</button>
    </form>
  </div>
</template>

<script>
  import { getBgmList } from '../../api/bgm'
  import { baseUrl, apiUrl } from '../../config/env'

  export default {
    data () {
      return {
        videoParams: {},
        bgmList: []
      }
    },
    onLoad (options) {
      this.videoParams = options
      console.log(this.videoParams)
      this.fetchData()
    },
    methods: {
      async fetchData () {
        try {
          const res = await getBgmList()
          this.bgmList = res.data
          this.bgmList.map(item => {
            item.path = baseUrl + item.path
          })
          console.log(this.bgmList)
        } catch (e) {
          console.log(e)
        }
      },
      upload (e) {
        const bgmId = e.mp.detail.value.bgmId
        const desc = e.mp.detail.value.desc
        const userInfo = wx.getStorageSync('userInfo')
        const videoSeconds = this.videoParams.videoSeconds
        const videoHeight = this.videoParams.videoHeight
        const videoWidth = this.videoParams.videoWidth
        const videoPath = this.videoParams.videoPath
        wx.showLoading({
          title: '上传中'
        })
        const uploadTask = wx.uploadFile({
          url: `${apiUrl}video/upload`,
          filePath: videoPath,
          name: 'file',
          header: {
            'content-type': 'application/json',
            'token': wx.getStorageSync('token') || ''
          },
          formData: {
            userId: userInfo.id,
            bgmId,
            desc,
            videoSeconds,
            videoHeight,
            videoWidth
          },
          success: function (res) {
            console.log(res)
            wx.hideLoading()
          },
          fail: function (e) {
            console.log(e)
            wx.hideLoading()
          }
        })
        uploadTask.onProgressUpdate((res) => {
          console.log('上传进度', res.progress)
          console.log('已经上传的数据长度', res.totalBytesSent)
          console.log('预期需要上传的数据总长度', res.totalBytesExpectedToSend)
        })
      }
    }
  }
</script>

<style scoped lang="scss">
  #choose-bgm {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    .form {
      .radio-group {
        .radio-item {
          width: 100%;
          display: flex;
          justify-content: center;
          position: relative;
          align-items: center;
          margin-top: 20px;
          .audio {

          }
          .radio {
            position: absolute;
            right: 20px;
          }
        }
      }
      .inputView {
        margin: 10px 20px;
        .inputText {
          margin-top: 10px;
        }
      }
    }
    .submit {
      width: 80%;
      margin-top: 15px;
    }

    .reset {
      width: 80%;
      margin-top: 15px;
    }
  }
</style>
