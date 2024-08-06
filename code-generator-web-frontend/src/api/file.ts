import http from "../plugins/request.ts";

/** 文件上传接口 POST /file/upload */
export async function uploadFile(biz: String, file: any) {
    return http.request<string>({
        url: "/file/upload",
        method: "POST",
        data: {
            biz,
            file
        },
        headers: {
            "Content-Type": "multipart/form-data; charset=UTF-8",
        }
    })
}
