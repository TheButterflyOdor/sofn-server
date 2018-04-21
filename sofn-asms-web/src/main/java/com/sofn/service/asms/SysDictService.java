package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.DictType;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysTestStandardProvider;
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
    private SysTestStandardProvider sysTestStandardProvider;
    @DubboReference
    public void setProvider(SysTestStandardProvider sysTestStandardProvider) {
        this.sysTestStandardProvider = sysTestStandardProvider;
    }

    //获取数据字典选项
    public String getSysDictByCode(DictType dictType){
        List<SysDictData> list = provider.getDictByType(dictType);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //获取数据字典列表选项
    public List<SysDictData> getSysDictListByCode(DictType dictType){
        return provider.getDictByType(dictType);
    }

    //获取标准号数据字典
    public PageInfo<SysTestStandardModel> getStandardList(Page page){
        return sysTestStandardProvider.getTestStandards(null,null,page);
    }
}
