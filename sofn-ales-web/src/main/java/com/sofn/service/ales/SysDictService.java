package com.sofn.service.ales;

import com.sofn.core.base.BaseService;
import com.sofn.core.constant.DictType;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.provider.sys.SysDictProvider;
import com.xiaoleilu.hutool.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sofn
 * @version 2016年11月16日 上午 9:50
 */
@Service
public class SysDictService extends BaseService<SysDictProvider,SysDictType>{

    @DubboReference
    public void setProvider(SysDictProvider provider){
        this.provider = provider;
    }

    /**
     * 获取数据字典选项
     * 根据传递的相应参数值,获取对应的数据字典
     * @param dictType
     * @return String
     * @Time 注释日期2018年2月26日14:04:26
     */
    public String getSysDictByCode(DictType dictType){
        List<SysDictData> list = provider.getDictByType(dictType);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }


    //获取数据字典列表选项
    public List<SysDictData> getSysDictListByCode(DictType dictType){
        return provider.getDictByType(dictType);
    }
}
