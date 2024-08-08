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

    /** 用户注册请求参数 */
    type UserRegisterParams = {
        userAccount: string,
        userPassword: string,
        checkPassword: string
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

    type FileInfo = {
        inputPath?: string,
        outputPath?: string,
        type?: string,
        generateType?: string,
        condition?: string,
        groupKey?: string,
        groupName?: string,
        files?: FileInfo[]
    }

    type FileConfig = {
        inputRootPath?: string,
        outputRootPath?: string,
        sourceRootPath?: string,
        type?: string,
        files: FileInfo[]
    }

    type ModelInfo = {
        fieldName?: string,
        type?: string,
        description?: string,
        defaultValue?: string,
        abbr?: string,
        groupKey?: string,
        groupName?: string,
        models?: ModelInfo[],
        condition?: string,
        allArgsStr?: string
    }

    type ModelConfig = {
        models: ModelInfo[]
    }

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
        fileConfig: FileConfig,
        modelConfig: ModelConfig,
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

    /** 新增代码生成器请求参数 */
    type GeneratorAddParams = {
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        tags: string[],
        picture: string,
        fileConfig: FileConfig,
        modelConfig: ModelConfig,
        distPath: string,
        status: number
    }

    /** 编辑代码生成器请求参数 */
    type GeneratorEditParams = {
        id: string,
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        tags: string[],
        picture: string,
        fileConfig: FileConfig | undefined,
        modelConfig: ModelConfig | undefined,
        distPath: string
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

    /** 使用代码生成器请求参数 */
    type GeneratorUseParams = {
        id: string,
        dataModel: object
    }

    /** 元信息 */
    type Meta = {
        name: string,
        description: string,
        basePackage: string,
        version: string,
        author: string,
        fileConfig: FileConfig,
        modelConfig: ModelConfig
    }

    /** 制作代码生成器请求参数 */
    type GeneratorMakeParams = {
        meta: Meta
    }

    /** 制作代码生成器请求参数 */
    type GeneratorMakeOldParams = {
        meta: Meta,
        zipFilePath: string
    }

    // endregion

}
