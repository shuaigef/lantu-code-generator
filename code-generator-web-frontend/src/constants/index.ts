/** 本地缓存枚举 */
export const enum LocalStorageEnum {
  LOGIN_USER = "login_user",
  TOKEN_NAME = "token_name",
  TOKEN_VALUE = "token_value"
}

/** 本地缓存枚举 */
export const enum UserRoleEnum {
  USER = "user",
  ADMIN = "admin",
  BAN = "ban"
}

/** COS 访问地址 */
export const COS_HOST = "https://code-generator-1316577174.cos.ap-nanjing.myqcloud.com"

/** 文件上传业务类型枚举 */
export const enum FileUploadBizEnum {
  /** 用户头像 */
  USER_AVATAR = "user_avatar",
  /** 生成器图片 */
  GENERATOR_PICTURE = "generator_picture",
  /** 生成器产物包 */
  GENERATOR_DIST = "generator_dist",
  /** 生成器制作模板文件 */
  GENERATOR_MAKE_TEMPLATE = "generator_make_template",

}
