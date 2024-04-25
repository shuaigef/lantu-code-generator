package com.shuaigef.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaigef.web.model.dto.generator.GeneratorQueryRequest;
import com.shuaigef.web.model.entity.Generator;
import com.shuaigef.web.model.vo.GeneratorVO;

/**
 * 生成器服务
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public interface GeneratorService extends IService<Generator> {

    /**
     * 校验
     *
     * @param generator
     * @param add
     */
    void validGenerator(Generator generator, boolean add);

    /**
     * 获取查询条件
     *
     * @param generatorQueryRequest
     * @return
     */
    QueryWrapper<Generator> getQueryWrapper(GeneratorQueryRequest generatorQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param generator
     * @return
     */
    GeneratorVO getGeneratorVO(Generator generator);

    /**
     * 分页获取帖子封装
     *
     * @param generatorPage
     * @return
     */
    Page<GeneratorVO> getGeneratorVOPage(Page<Generator> generatorPage);


}
