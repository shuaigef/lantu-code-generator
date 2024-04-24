package com.shuaigef.maker.meta;

/**
 * 元信息异常
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/10 14:18
 */
public class MetaException extends RuntimeException{

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
