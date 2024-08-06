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

/** 新增代码生成器接口 POST /generator/add */
export async function addGenerator(body: API.GeneratorAddParams) {
    return http.request<string>({
        url: "/generator/add",
        method: "POST",
        data: body
    })
}

/** 编辑代码生成器接口 POST /generator/edit */
export async function editGenerator(body: API.GeneratorEditParams) {
    return http.request<boolean>({
        url: "/generator/edit",
        method: "POST",
        data: body
    })
}

/** 根据 id 获取生成器信息接口 GET /generator/get/vo */
export async function getGeneratorVOById(id: string) {
    return http.request<API.GeneratorVO>({
        url: "/generator/get/vo",
        method: "GET",
        params: {
            id
        }
    })
}

/** 根据 id 下载生成器接口 GET /generator/download */
export async function downloadGeneratorById(id: string) {
    return http.request({
        url: "/generator/download",
        method: "GET",
        params: {
            id
        },
        responseType: 'blob'
    })
}

/** 使用代码生成器接口 POST /generator/use */
export async function useGenerator(body: API.GeneratorUseParams) {
    return http.request({
        url: "/generator/use",
        method: "POST",
        data: body,
        responseType: 'blob'
    })
}

/** 制作代码生成器接口 POST /generator/make */
export async function makeGenerator(body: API.GeneratorMakeParams) {
    return http.request({
        url: "/generator/make",
        method: "POST",
        data: body,
        responseType: 'blob'
    })
}



