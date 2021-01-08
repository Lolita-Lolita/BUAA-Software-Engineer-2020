<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title"><span style="color:#409eff">登录</span> / <span style="color:#e6a23c">注册</span></h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:45%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>

      <el-button :loading="loading" type="warning" style="width:45%;margin-bottom:30px;float:right;" @click.native.prevent="handleRegister">注册</el-button>

      <!-- <div class="tips">
        <span style="margin-right:20px;">username: admin</span>
        <span> password: any</span>
      </div> -->

    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import { rbacLogin, creditLogin, dishLogin, userRegister, getList } from '@/api/user'
import { getToken } from '@/utils/auth'


export default {
  name: 'Login',
  data() {
    // const validateUsername = (rule, value, callback) => {
    //   res = validUsername(value)
    //   if (!res.success) {
    //     callback(new Error('Please enter the correct user name and password'))
    //   } else {
    //     callback()
    //   }
    // }
    // const validatePassword = (rule, value, callback) => {
    //   if (value.length == 0) {
    //     callback(new Error('The password can not be null'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      loginForm: {
        username: 'admin',
        password: '123456'
      },
      // loginRules: {
      //   username: [{ required: true, trigger: 'blur', validator: validateUsername }],
      //   password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      // },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', {
        username: 'admin',
        password: '123456'
      }).then(() => {
            const { username, password } = this.loginForm
            rbacLogin({ userName: username.trim(), password: password }).then(response => {
              this.$store.commit('setSession', {name:'rbac', id:response['session']} )

              creditLogin({ userName: username.trim(), password: password }).then(response => {
                this.$store.commit('setSession', {name:'credit', id:response['session']})

                dishLogin({ userName: username.trim(), password: password }).then(response => {
                  this.$store.commit('setSession', {name:'dish', id:response['session']})

                  console.log(this.$store.state.session)
                  this.listLoading = false
                  this.$store.commit('setName', this.loginForm.username)
                  this.$router.push({ path: this.redirect || '/' })
                  this.loading = false
                })
              })
            })
            .catch(error => {
              console.log(error)
              this.loading = false
            })
          })
          .catch(error => {
            console.log(error)
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleRegister() {
      // TODO : test
      this.loading = true
      const { username, password } = this.loginForm
      rbacLogin({ userName: 'admin', password: '123456' }).then(response => {
        this.$store.commit('setSession', {name:'rbac', id:response['session']})

        getList().then(response => {
          let list = response
          for(let i = 0; i<list.length;i++){
            if(list[i].username == username.trim()){
              this.$message({
                message: '注册失败，用户已存在',
                type: 'warning'
              })
              this.loading = false
              return
            }
          }
          userRegister({ userName: username.trim(), password: password, role: 'user' }).then(response => {
            this.$message({
              message: '注册成功',
              type: 'success'
            })
            this.loading = false
          }).catch(error => {
            console.log(error)
            this.$message({
              message: '注册失败',
              type: 'warning'
            })
            this.loading = false
          })
        })
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}

</style>
