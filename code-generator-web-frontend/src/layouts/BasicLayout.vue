<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="header-inner">
        <a-flex justify="space-between" align="flex-start">
          <div class="header-inner-left">
            <div class="logo">
              <a-space>
                <img style="height: 32px;" src="../assets/logo.png"></img>
                <span style="  font-size: 19px;font-weight: 600;">
                  蓝图代码生成器
                </span>
              </a-space>
            </div>
            <a-menu v-model:selectedKeys="selectedKeys" triggerSubMenuAction="click" theme="light" mode="horizontal"
                    :style="{ lineHeight: '50px' }" @click="toPage">
              <a-menu-item key="/index">
                <HomeOutlined />首页
              </a-menu-item>
              <a-menu-item key="/generator/manager" v-if="loginUser.userRole == UserRoleEnum.ADMIN">
                <ProfileOutlined />代码生成器管理
              </a-menu-item>
              <a-menu-item key="/generator/create">
                <PlusOutlined />创建生成器
              </a-menu-item>
            </a-menu>
          </div>
          <div class="header-inner-right" style="cursor: pointer;">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUser.userAvatar">
                  <template #icon>
                    <UserOutlined />
                  </template>
                </a-avatar>
                <span>{{ loginUser.userName }}</span>
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item>
                    <a @click="logout">退出登录</a>
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
        </a-flex>
      </div>
    </a-layout-header>
    <a-layout-content style="padding: 0 50px">
      <div :style="{ minHeight: '280px' }">
        <router-view></router-view>
      </div>
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      Generator Code ©2024 Created by Code
    </a-layout-footer>
  </a-layout>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {useRouter} from "vue-router";
import {userLogout} from "../api/user.ts";
import {useUserStore} from "../store";
import {storeToRefs} from "pinia";
import {LocalStorageEnum, UserRoleEnum} from "../constants";
import { UserOutlined, HomeOutlined, ProfileOutlined, PlusOutlined } from '@ant-design/icons-vue';

const userStore = useUserStore()
const { loginUser } = storeToRefs(userStore)

const selectedKeys = ref<string[]>(['/index']);

const router = useRouter();

const toPage = ({key}: {key: string}) => {
  router.push({
    path: key
  })
}

const logout = () => {
  localStorage.removeItem(LocalStorageEnum.TOKEN_NAME)
  localStorage.removeItem(LocalStorageEnum.TOKEN_VALUE)
  router.push('/login')
  userLogout().then(res => {
    console.log("登出", res)
  })
}
</script>

<style scoped lang="scss">
.header-inner {
  max-width: 1200px;
  margin: 0 auto;
}

.ant-layout-header {
  height: 50px;
  padding-inline: 50px;
  color: rgba(0, 0, 0, 0.88);
  line-height: 50px;
  background-color: #fff;
}

.layout {
  min-height: 100vh;
}
.layout .logo {
  float: left;
  width: 200px;
  height: 50px;

}

.ant-row-rtl #components-layout-demo-top .logo {
  float: right;
  margin: 16px 0 16px 24px;
}
</style>
