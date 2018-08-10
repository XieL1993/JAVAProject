<template>
  <div id="comment">
    <div class="list">
      <div class="item" v-for="(item,index) in list" :key="index">
        <div class="left">
          <img :src="item.fromUserAvatar" class="avatar">
        </div>
        <div class="right">
          <div class="nickname">{{item.fromUserName}}</div>
          <div class="comment"><span v-if="item.toUserName">@{{item.toUserName}}</span>{{item.comment}}</div>
          <div class="createTime">{{item.createTime}}
            <span @click="reply(item)">回复</span>
          </div>
        </div>
      </div>
    </div>
    <load-more :loadMore="loadMore"></load-more>
    <div class="bottom">
      <input name="commentContent" class="input" :placeholder="placeholder" confirm-type="send" @confirm="submit"
             :focus="false"/>
    </div>
  </div>
</template>

<script>
  import { addComments, getComments } from '../../api/comment'
  import { getFormatDate } from '../../common/utils'
  import loadMore from '../../component/load-more'

  export default {
    onLoad (options) {
      // this.videoId = options.videoId
      this.videoId = 12
      this.fetchData(true)
    },
    computed: {
      placeholder () {
        if (this.currentItem) {
          return this.currentItem.fromUserName
        } else {
          return '请输入'
        }
      }
    },
    data () {
      return {
        videoId: '',
        list: [],
        pageSize: 10,
        currentPage: 1,
        loadMore: 0, // 0 不显示 1 正在加载 2 暂无更多
        currentItem: null
      }
    },
    methods: {
      async submit (e) {
        console.log(e)
        const comment = e.mp.detail.value
        const userInfo = wx.getStorageSync('userInfo')
        wx.showLoading({
          title: '提交中'
        })
        try {
          const params = {
            fromUserId: userInfo.id,
            comment,
            videoId: this.videoId
          }
          if (this.currentItem) {
            params.fatherCommentId = this.currentItem.id
            params.toUserId = this.currentItem.fromUserId
          }
          const res = await addComments(params)
          console.log(res)
        } catch (e) {
          console.log(e)
        } finally {
          this.currentItem = null
          wx.hideLoading()
        }
      },
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
          const res = await getComments({
            pageSize: this.pageSize,
            currentPage: this.currentPage,
            videoId: this.videoId
          })
          const { records, pages } = res.data
          records.forEach(item => {
            item.createTime = getFormatDate(item.createTime)
          })
          if (isRefresh) {
            wx.stopPullDownRefresh()
            this.list = records
          } else {
            this.list = this.list.concat(records)
          }
          wx.hideLoading()
          if (this.currentPage >= pages) {
            this.loadMore = 2
          } else {
            this.loadMore = 1
          }
          console.log(this.list)
        } catch (e) {
          wx.hideLoading()
          console.log(e.message)
        }
      },
      reply (item) {
        this.currentItem = item
        console.log(item)
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

  #comment {
    position: absolute;
    min-height: 100%;
    background: $color-main;
    padding-bottom: 50px;
    .list {
      .item {
        border-bottom: 1px solid $border-info;
        padding: 10px 20px;
        display: flex;
        .left {
          .avatar {
            width: 45px;
            height: 45px;
            border-radius: 50%;
          }
        }
        .right {
          margin-left: 20px;
          .nickname {
            font-size: 12px;
            color: $bg-blue;
          }
          .comment {
            font-size: 16px;
            color: #ffffff;
            margin-top: 6px;
            display: flex;
            align-items: center;
            span{
              font-size: 12px;
              color: $bg-blue;
              margin-right: 10px;
            }
          }
          .createTime {
            font-size: 12px;
            margin-top: 6px;
            color: #F2F6FC;
            span{
              margin-left: 20px;
              color: $bg-blue;
            }
          }
        }

      }
    }
    .bottom {
      position: fixed;
      bottom: 0;
      left: 0;
      right: 0;
      height: 50px;
      .input {
        height: 50px;
        color: $color-main;
        line-height: 50px;
        padding: 0 10px;
        background: #dcdcdc;
      }
    }
  }
</style>
