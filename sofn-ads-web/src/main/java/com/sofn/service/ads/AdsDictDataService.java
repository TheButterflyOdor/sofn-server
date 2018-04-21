package com.sofn.service.ads;

import com.sofn.core.base.BaseService;
import com.sofn.core.constant.DictType;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.provider.sys.SysDictProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取字典数据 service 业务逻辑
 * @author yangran
 * @version 2016-11-22
 */
@Service
public class AdsDictDataService extends BaseService<SysDictProvider, SysDictType> {

    @DubboReference
    public void setProvider(SysDictProvider provider) {
        this.provider = provider;
    }

    /**
     * 根据字典枚举类型查询字典数据
     * @param dictType
     * @return
     */
    public List<SysDictData> getSysDictListByType(DictType dictType){
        return provider.getDictByType(dictType);
    }

}
