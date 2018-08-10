<template>
  <div id="page-index">
    <div class="video-list">
      <div class="video-item" v-for="(item,index) in videoList" :key="index">
        <div class="item-bg">
          <img :src="item.coverPath" mode="aspectFill" class="video-cover" @click="showVideo(item)"/>
          <div class="title">{{item.videoDesc}}</div>
          <div class="video-second">{{item.videoSeconds}}</div>
        </div>
        <div class="item-footer">
          <img :src="item.avatar" class="avatar">
          <div class="nick-name">{{item.nickname}}</div>
          <div class="create-time">{{item.createTime}}</div>
        </div>
      </div>
    </div>
    <load-more :loadMore="loadMore"></load-more>
  </div>
</template>

<script>
  import { getVideoList } from '../../api'
  import { baseUrl } from '../../config/env'
  import { getFormatDate, go } from '../../common/utils'
  import loadMore from '../../component/load-more'

  export default {
    onLoad () {
      this.fetchData(true)
    },
    data () {
      return {
        videoList: [],
        pageSize: 3,
        currentPage: 1,
        loadMore: 0 // 0 不显示 1 正在加载 2 暂无更多
      }
    },
    methods: {
      async fetchData (isRefresh) {
        if (isRefresh) {
          this.currentPage = 1
        } else {
          this.currentPage = this.currentPage + 1
        }
        wx.showLoading({
          title: '加载中...'
        })
        try {
          const res = await getVideoList({
            pageSize: this.pageSize,
            currentPage: this.currentPage
          })
          const { records, pages } = res.data
          records.forEach(item => {
            item.coverPath = baseUrl + item.coverPath
            item.videoPath = baseUrl + item.videoPath
            item.createTime = getFormatDate(item.createTime)
            item.videoSeconds = getFormatDate(item.videoSeconds * 1000, 'mm:ss')
          })
          if (isRefresh) {
            wx.stopPullDownRefresh()
            this.videoList = records
          } else {
            this.videoList = this.videoList.concat(records)
          }
          wx.hideLoading()
          if (this.currentPage >= pages) {
            this.loadMore = 2
          } else {
            this.loadMore = 1
          }
          console.log(this.videoList)
        } catch (e) {
          wx.hideLoading()
          console.log(e.message)
        }
      },
      showVideo (item) {
        go(`/pages/video-detail/main?video=${JSON.stringify(item)}`)
      }
    },
    onPullDownRefresh () {
      console.log('onPullDownRefresh')
      this.fetchData(true)
    },
    onReachBottom () {
      console.log('onReachBottom')
      if (this.loadMore === 2) {
        return
      }
      this.fetchData(false)
    },
    components: {
      loadMore
    }
  }
</script>

<style scoped lang="scss">
  @import "../../common/styles/variables";
  @import "../../common/styles/mixin";

  #page-index {
    position: absolute;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background-color: $bg-gray;
    .video-list {
      padding-top: 10px;
      .video-item {
        margin-top: 10px;
        box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.12);
        .item-bg {
          height: 210px;
          background: #000000;
          position: relative;
          .title {
            position: absolute;
            top: 10px;
            left: 10px;
            font-size: 15px;
            color: #ffffff;
          }
          .video-cover {
            width: 100%;
            height: 210px;
          }
          .video-second {
            position: absolute;
            right: 10px;
            bottom: 10px;
            color: #ffffff;
            font-size: 14px;
          }
        }
        .item-footer {
          height: 50px;
          background: #ffffff;
          display: flex;
          align-items: center;
          padding-left: 20px;
          .avatar {
            width: 35px;
            height: 35px;
            border-radius: 50%;
          }
          .nick-name {
            flex: 1;
            margin-left: 10px;
            font-size: 16px;
            color: $color-main;
          }
          .create-time {
            margin-right: 20px;
            font-size: 14px;
            color: $color-normal;
          }
        }
      }
    }
  }
</style>
