package com.shuaigef.maker.meta.enums;

/**
 * 文件生成类型枚举
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/10 18:26
 */
public enum FileGenerateTypeEnum {

    DYNAMIC("动态", "dynamic"),
    STATIC("静态", "static");

    private final String text;

    private final String value;

    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

}
