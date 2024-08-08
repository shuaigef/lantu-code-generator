package com.shuaigef.web.model.dto.generator;

import com.shuaigef.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * 制作代码生成器请求
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/26 14:43
 */
@Data
public class GeneratorMakeOldRequest implements Serializable {

    /**
     * 生成器的 id
     */
    private Meta meta;

    /**
     * 模板文件压缩包路径
     */
    private String zipFilePath;

    private static final long serialVersionUID = 1L;

}
