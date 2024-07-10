import Login from "../pages/Login.vue";
import Index from "../pages/Index.vue";
import {useUserStore} from "../store";
import {getLoginUser} from "../api/user.ts";
import BasicLayout from "../layouts/BasicLayout.vue";
import {message} from "ant-design-vue";
import GeneratorManager from "../pages/GeneratorManager.vue";
import CreateGenerator from "../pages/CreateGenerator.vue";

const routes = [
    {
      path: '/',
      redirect: '/login',
    },
    {
        path: '/login',
        component: Login,
        name: 'login'
    }, {
        path: '/home',
        name: 'basicLayout',
        alias: '/',
        component: BasicLayout,
        beforeEnter: (to, from, next) => {
            const userStore = useUserStore()
            getLoginUser().then(res => {
                if (res.code === 0) {
                    userStore.loginUser = { ...res.data }
                    console.log(userStore.loginUser)
                    next()
                } else {
                    message.error("请先登录")
                    next('/login')
                }
            })
        },
        children: [
            { path: 'index', name: 'index', component: Index },
            { path: 'generatorManager', name: 'generatorManager', component: GeneratorManager },
            { path: 'createGenerator', name: 'createGenerator', component: CreateGenerator },
        ]
    }
]

export default routes;

