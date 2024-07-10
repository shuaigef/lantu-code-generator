<template>
  <a-form
      :model="userLoginParams"
      name="basic"
      :label-col="{ span: 8 }"
      :wrapper-col="{ span: 16 }"
      autocomplete="off"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
  >
    <a-form-item
        label="用户名"
        name="username"
    >
      <a-input v-model:value="userLoginParams.userAccount" />
    </a-form-item>

    <a-form-item
        label="密码"
        name="password"
    >
      <a-input-password v-model:value="userLoginParams.userPassword" />
    </a-form-item>
    <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
      <a-button type="primary" html-type="submit">登录</a-button>
    </a-form-item>
  </a-form>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {userLogin} from "../api/user.ts";
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";
import {LocalStorageEnum} from "../constant";

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
</script>

<style scoped>

</style>
