package com.shuaigef.web.model.dto.generator;

import com.shuaigef.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * 制作代码生成器请求
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Data
public class GeneratorMakeRequest implements Serializable {

    /**
     * 生成器的 id
     */
    private String meta;

    private static final long serialVersionUID = 1L;

}
