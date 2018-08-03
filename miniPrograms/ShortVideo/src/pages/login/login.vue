<template>
  <div id="login">
    <button type="primary" open-type="getUserInfo" class="wx-login-btn" @getuserinfo="wxLogin">
      微信登录
    </button>
  </div>
</template>

<script>
  import { showErrorToast, back } from '../../common/utils'
  import { checkLogin, login } from '../../api/user'

  export default {
    methods: {
      wxLogin (e) {
        if (e.target.userInfo === undefined) {
          showErrorToast('微信登录失败')
        } else {
          checkLogin().then(() => {
            showErrorToast('已登录')
            setTimeout(() => {
              back()
            }, 1000)
          }).catch(() => {
            login(e.target.userInfo).then(() => {
              showErrorToast('登录成功')
              setTimeout(() => {
                back()
              }, 1000)
            }).catch(error => {
              showErrorToast(error.message)
            })
          })
        }
      }
    }
  }
</script>

<style scoped lang="scss">
#login{
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  .wx-login-btn{
    width: 80%;
  }
}
</style>
