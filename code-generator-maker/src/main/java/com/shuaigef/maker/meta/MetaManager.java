package com.shuaigef.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 * @date 2023/12/5 20:30
 */
public class MetaManager {

    private static volatile Meta meta;

    private MetaManager() {
        // 私有构造函数，防止外部实例化
    }

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    public static Meta initMeta() {
        // String metaJson = ResourceUtil.readUtf8Str("meta.json");
        String metaJson = ResourceUtil.readUtf8Str("springboot-init-meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        // 校验配置文件，处理默认值
        MetaValidator.doValidator(newMeta);
        return newMeta;
    }

}
