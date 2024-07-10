declare namespace API {
    // region 通用
    /** 自定义请求返回 data 格式 */
    type BaseResponse<T = any> = {
        code: number;
        data: T; // 这里定义请求返回data数据类型
        message: string;
    }

    /** 分页请求参数 */
    type PageRequest = {
        current: number,
        pageSize: number,
        sortField?: string,
        sortOrder?: string,
    }

    type Page<T> = {
        records: T[],
        total: number,
        size: number,
        current: number,
    }

    /** SaTokenInfo 信息 */
    type SaTokenInfo = {
        tokenName: string,
        tokenValue: string
    }
    // endregion

    // region 用户相关
    /** 用户登录请求参数 */
    type UserLoginParams = {
        userAccount: string,
        userPassword: string
    }

    /** 当前登录用户信息 */
    type LoginUserInfo = {
        id: string,
        userName: string,
        userAvatar: string,
        userProfile: string,
        userRole: string,
        createTime: Date | undefined,
        updateTime: Date | undefined
    }

    /** 用户信息 */
    type User = {
        id: string,
        userName: string,
        userAvatar: string,
        userGender: number,
        phoneNumber: string,
        userRole: string,
        createTime: Date
    }

    /** 用户信息 */
    type UserVO = {
        id: string,
        userName: string,
        userAvatar: string,
        userProfile: number,
        userRole: string,
        createTime: Date | undefined
    }

    // endregion

    // region 代码生成器相关
    /** 代码生成器搜索请求参数 */
    type CodeGeneratorSearchParams = {
        id: string,
        userName: string,
        userAvatar: string,
        userGender: number,
        phoneNumber: string,
        userRole: string,
        createTime: Date
    }

    /** 代码生成器查询请求参数 */
    type GeneratorQueryParams = {
        id: string,
        notId:string,
        searchText: string,
        tags: string[],
        orTags: string[],
        userId: string,
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        distPath: string,
        status: number
    } & PageRequest

    /** 代码生成器 */
    type Generator = {
        id: string,
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        tags: string,
        picture: string,
        fileConfig: string,
        modelConfig: string,
        distPath: string,
        status: number,
        userId: string,
        createTime: Date | undefined,
        updateTime: Date | undefined,
        isDelete: number
    }

    /** 代码生成器 */
    type GeneratorVO = {
        id: string,
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        tags: string[],
        picture: string,
        fileConfig: string,
        modelConfig: string,
        distPath: string,
        status: number,
        userId: string,
        createTime: Date | undefined,
        updateTime: Date | undefined,
        user: UserVO
    }

    /** 更新代码生成器请求参数 */
    type GeneratorUpdateParams = {
        id: string,
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        tags: string[],
        picture: string,
        fileConfig: string,
        modelConfig: string,
        distPath: string,
        status: number,
        userId: string
    }

    // endregion

}
