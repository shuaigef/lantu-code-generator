<template>
  <div class="register-div">
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
      <p>注册</p>
      <a-form :model="formState" name="normal_login" class="login-form" @finish="onFinish"
              @finishFailed="onFinishFailed">

        <a-form-item label="" name="userAccount" :rules="[{ required: true, min: 4, message: '请输入你的账号!' }]">
            <a-input v-model:value="formState.userAccount" placeholder="账号" size="large">

            </a-input>
        </a-form-item>

        <a-form-item label="" name="userPassword" :rules="[{ required: true, min: 8, message: '请输入你的密码!' }]">
          <a-input-password v-model:value="formState.userPassword" placeholder="请至少输入6个字符" size="large">
            <template #prefix>

            </template>
          </a-input-password>
        </a-form-item>

        <a-form-item label="" name="checkUserPassword">
          <a-input-password v-model:value="formState.checkPassword" placeholder="确认密码" size="large">
            <template #prefix>

            </template>
          </a-input-password>
        </a-form-item>


        <a-form-item>
          <a-row>
            <a-col :span="12">
              <a-button type="primary" html-type="submit" class="login-form-button" block="true" size="large">
                注册
              </a-button>
            </a-col>
            <a-col :span="12" style="text-align: right; line-height: 40px;">
              <a @click="toLogin">使用已有账户登录</a>
            </a-col>
          </a-row>
        </a-form-item>

      </a-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {ref} from "vue";
import {message} from "ant-design-vue";
import {userRegister} from "../api/user.ts";

const router = useRouter()

//#region 表单验证
const formState = ref<API.UserRegisterParams>({
  userAccount: '',
  userPassword: '',
  checkPassword: ''
});

const onFinish = async () => {
  // 密码校验
  if (formState.value.userPassword !== formState.value.checkPassword) {
    message.error('两次密码不一致')
    return
  }
  await userRegister(formState.value).then(res => {
    if (res.code == 0) {
      message.success('注册成功，请登录')
      toLogin()
    } else {
      message.error(res.message)
    }
  })
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
//#endregion

//#region 路由跳转
const toLogin = () => {
  router.push('/login')
}

//#endregion
</script>

<style scoped lang="scss">
.main {
  width: 368px;
  margin: 0 auto;
}

.main-success {
  width: 368px;
  margin: 0 auto;
}

.login-logo {
  width: 44px;
  height: 44px;
  margin-right: 16px;
  vertical-align: top;
}

.register-div {
  /* margin-top: 50px; */
  padding-top: 80px;
}

.top{
  .header {
    text-align: center;
    height: 44px;
    span {
      font-size: 33px;
      font-weight: 600;
      line-height: 44px;
    }
  }
  .desc {
    text-align: center;
    font-size: 14px;
    margin-top: 12px;
    margin-bottom: 40px;
  }
}
</style>
