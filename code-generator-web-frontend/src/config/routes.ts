import Login from "../pages/Login.vue";
import Index from "../pages/Index.vue";
import {useUserStore} from "../store";
import {getLoginUser} from "../api/user.ts";
import BasicLayout from "../layouts/BasicLayout.vue";
import {message} from "ant-design-vue";
import GeneratorManager from "../pages/admin/GeneratorManager.vue";
import CreateGenerator from "../pages/generator/CreateGenerator.vue";
import GeneratorDetail from "../pages/generator/GeneratorDetail.vue";
import UseGenerator from "../pages/generator/UseGenerator.vue";
import Register from "../pages/Register.vue";

const routes = [
    {
      path: '/',
      redirect: '/login',
    },
    {
        path: '/login',
        component: Login,
        name: 'login'
    },
    {
        path: '/register',
        component: Register,
        name: 'register'
    },
    {
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
            { path: 'generator/manager', name: 'generatorManager', component: GeneratorManager },
            { path: 'generator/create', name: 'createGenerator', component: CreateGenerator },
            { path: 'generator/update/:id', name: 'updateGenerator', props: true, component: CreateGenerator },
            { path: 'generator/detail/:id', name: 'generatorDetail', props: true, component: GeneratorDetail },
            { path: 'generator/use/:id', name: 'useGenerator', props: true, component: UseGenerator },
        ]
    }
]

export default routes;

