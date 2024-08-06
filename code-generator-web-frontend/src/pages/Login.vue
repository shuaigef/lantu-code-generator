<template>
  <div class="login-div">
    <div class="top">
      <div class="header">
        <img class="login-logo" src="../assets/logo.png">
        <span>蓝图代码生成器</span>
      </div>
      <div class="desc">
        <a-typography-text type="secondary">提高你的项目开发效率</a-typography-text>
      </div>
    </div>
    <div class="main">
      <a-form :model="userLoginParams" name="normal_login" class="login-form" @finish="onFinish"
              @finishFailed="onFinishFailed">
        <a-form-item label="" name="userAccount" :rules="[{ required: true, message: '请输入你的账户!' }]">
          <a-input v-model:value="userLoginParams.userAccount" placeholder="账户" size="large">
            <template #prefix>
              <UserOutlined class="site-form-item-icon" />
            </template>
          </a-input>
        </a-form-item>

        <a-form-item label="" name="userPassword" :rules="[{ required: true, message: '请输入你的密码!' }]">
          <a-input-password v-model:value="userLoginParams.userPassword" placeholder="密码" size="large">
            <template #prefix>
              <LockOutlined class="site-form-item-icon" />
            </template>
          </a-input-password>
        </a-form-item>

        <a-form-item>
          <a-row>
            <a-col :span="8">
              <a-form-item name="remember" no-style>
                <a-checkbox>自动登录</a-checkbox>
              </a-form-item>
            </a-col>
            <a-col :span="8" push="8" style="text-align: right;">
              <a class="login-form-forgot" href="">忘记密码</a>
            </a-col>
          </a-row>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" html-type="submit" class="login-form-button" block="true" size="large">登录</a-button>
        </a-form-item>

        <a-form-item>
          <a-row>
            <a-col :span="8">
              <p class="login-other">其他登录方式</p>
            </a-col>
            <a-col :span="8" push="8" style="text-align: right;">
              <a class="login-form-forgot" @click="toRegister">注册账户</a>
            </a-col>
          </a-row>
        </a-form-item>

      </a-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {userLogin} from "../api/user.ts";
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";
import {LocalStorageEnum} from "../constants";

const router = useRouter();

const userLoginParams = ref<API.UserLoginParams>({
  userAccount: "",
  userPassword: ""
})

const onFinish = () => {
  userLogin(userLoginParams.value).then(res => {
    if (res.code === 0) {
      localStorage.setItem(LocalStorageEnum.TOKEN_NAME, res.data.tokenName)
      localStorage.setItem(LocalStorageEnum.TOKEN_VALUE, res.data.tokenValue)
      message.success("登录成功")
      router.push('/index')
    } else {
      message.error(res.message)
    }
  })
}
const onFinishFailed = () => {

}

const toRegister = () => {
  router.push('/register')
}
</script>

<style scoped lang="scss">
.main {
  width: 368px;
  margin: 0 auto;
}

.login-logo {
  width: 44px;
  height: 44px;
  margin-right: 16px;
  vertical-align: top;
}

.login-div {
  /* margin-top: 50px; */
  padding-top: 80px;
}

.top{
  .header {
    text-align: center;
    height: 44px;
  }
}

.header{
  span {
    font-size: 33px;
    font-weight: 600;
    line-height: 44px;
  }
}

.top{
  .desc {
    text-align: center;
    font-size: 14px;
    margin-top: 12px;
    margin-bottom: 40px;
  }
}
</style>
