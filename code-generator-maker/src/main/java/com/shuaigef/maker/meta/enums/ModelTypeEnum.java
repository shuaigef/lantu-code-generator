package com.shuaigef.maker.meta.enums;

/**
 * 模型类型枚举
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/10 18:26
 */
public enum ModelTypeEnum {

    STRING("字符串", "string"),
    BOOLEAN("布尔", "boolean");

    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value) {
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
