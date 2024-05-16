package com.shuaigef.web.model.dto.generator;

import com.shuaigef.web.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2024/4/24 14:15
 */
@Data
public class GeneratorQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 至少有一个标签
     */
    private List<String> orTags;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 基础包
     */
    private String basePackage;

    /**
     * 版本
     */
    private String version;

    /**
     * 作者
     */
    private String author;

    /**
     * 代码生成器产物路径
     */
    private String distPath;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

}
