# 数据库初始化

-- 创建库
create database if not exists code_generate;

-- 切换库
use code_generate;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    user_name     varchar(256)                           null comment '用户昵称',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    index idx_user_account (user_account)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 代码生成器表
create table if not exists generator
(
    id           bigint auto_increment comment 'id' primary key,
    name         varchar(128)                       null comment '名称',
    description  text                               null comment '描述',
    base_package varchar(128)                       null comment '基础包',
    version      varchar(128)                       null comment '版本',
    author       varchar(128)                       null comment '作者',
    tags         varchar(1024)                      null comment '标签列表（json 数组）',
    picture      varchar(256)                       null comment '图片',
    fileConfig   text                               null comment '文件配置（json 字符串）',
    modelConfig  text                               null comment '模型配置（json 字符串）',
    distPath     text                               null comment '代码生成器产物路径',
    status       int      default 0                 not null comment '状态',
    user_id       bigint                             not null comment '创建用户 id',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete    tinyint  default 0                 not null comment '是否删除',
    index idx_user_id (user_id)
    ) comment '用户' collate = utf8mb4_unicode_ci;

