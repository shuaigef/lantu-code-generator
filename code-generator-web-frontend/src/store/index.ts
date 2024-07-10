import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useIndexStore = defineStore('index', () => {

    const str = ref("hello pinia")

    return {
        str
    }
})

export const useUserStore = defineStore('user', () => {
    const loginUser = ref<API.LoginUserInfo>({
        id: "",
        userName: "",
        userAvatar: "",
        userProfile: "",
        userRole: "",
        createTime: undefined,
        updateTime: undefined
    })

    return {
        loginUser
    }
})
