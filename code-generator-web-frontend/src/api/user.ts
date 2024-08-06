import http from '../plugins/request'

/** 管理员登录接口 POST /user/login/admin */
export async function userLogin(body: API.UserLoginParams) {
    return http.request<API.SaTokenInfo>({
        url: "/user/login",
        method: "POST",
        data: body
    })
}

/** 获取当前登录用户信息接口 GET /user/get/login */
export async function getLoginUser() {
    return http.request<API.LoginUserInfo>({
        url: "/user/get/login",
        method: "GET"
    })
}

/** 用户登出接口 POST /user/logout */
export async function userLogout() {
    return http.request<boolean>({
        url: "/user/logout",
        method: "POST"
    })
}

/** 用户注册接口 POST /user/register */
export async function userRegister(body: API.UserRegisterParams) {
    return http.request<string>({
        url: "/user/register",
        method: "POST",
        data: body
    })
}

