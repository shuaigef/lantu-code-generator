import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as VueRouter from 'vue-router'
import routes from './config/routes.ts'
import { createPinia } from 'pinia'

const router = VueRouter.createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: VueRouter.createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})

// router.beforeEach(async (to, from) => {
    // const tokenName = localStorage.getItem(LocalStorageEnum.TOKEN_NAME)
    // const tokenValue = localStorage.getItem(LocalStorageEnum.TOKEN_VALUE)
    // console.log('tokenName', tokenName)
    // console.log('tokenValue', tokenValue)
    // if ( (tokenName == null || tokenValue == null) && to.name !== 'login' ) {
        // 将用户重定向到登录页面
        // return { name: 'login' }
    // }
// })

const pinia = createPinia()
const app = createApp(App);
app.use(pinia)
app.use(router)
app.use(Antd);
app.mount('#app');
