import http from "../plugins/request.ts";

/** 管理员分页获取代码生成器列表接口 POST /generator/list/page */
export async function listGeneratorByPage(body: API.GeneratorQueryParams) {
    return http.request<API.Page<API.Generator>>({
        url: "/generator/list/page",
        method: "POST",
        data: body
    })
}

/** 分页获取代码生成器封装类列表接口 POST /generator/list/page */
export async function listGeneratorVOByPage(body: API.GeneratorQueryParams) {
    return http.request<API.Page<API.GeneratorVO>>({
        url: "/generator/list/page/vo",
        method: "POST",
        data: body
    })
}


/** 管理员删除代码生成器接口 POST /generator/delete */
export async function deleteGenerator(id: string) {
    return http.request<boolean>({
        url: "/generator/delete",
        method: "POST",
        data: {
            id
        }
    })
}



