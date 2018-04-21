package com.sofn.service.tts;

import com.sofn.core.base.BaseService;
import com.sofn.core.constant.DictType;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.provider.sys.SysDictProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统字典表获取
 */
@Service
public class SysDictionaryService extends BaseService<SysDictProvider, SysDictType> {

    @DubboReference
    public void setSysDictProvider(SysDictProvider provider) {
        this.provider = provider;
    }

    /**
     * 获取行业字典表
     * @return
     */
    public List<SysDictData> getIndustry(){
        return this.provider.getDictByType(DictType.INDUSTRYTYPE);
    }
    /**
     * 获取单位
     * @return
     */
    public List<SysDictData> getUnit(String[] ids){
        return this.provider.getDictByType(DictType.UNITTYPE,ids);
    }
    /**
     * 获取组织形式
     * @return
     */
    public List<SysDictData> getOrgs(){
        return this.provider.getDictByType(DictType.ORGMODE);
    }
    /**
     * 获取主体类型
     * @return
     */
    public List<SysDictData> getSubjTypes(){
        return this.provider.getDictByType(DictType.SUBJTYPE);
    }
    /**
     * 获取主体属性
     * @return
     */
    public List<SysDictData> getSubjPropertys(){
        return this.provider.getDictByType(DictType.SUBJPROPERTY);
    }
    /**
     * 获取问题类型
     * @return
     */
    public List<SysDictData> getIssueType() { return this.provider.getDictByType(DictType.ISSUETYPE); }

    /**
     * 根据行业id获取不同的单位
     * @param id
     * @return
     */
    public List<SysDictData> getDictByType(String[] id) {
        return this.provider.getDictByType(DictType.UNITTYPE,id);
    }
    /**
     * 根据行业id获取行业
     * @param id
     * @return
     */
    public SysDictData getIndustryById(String id) {
        return this.provider.getDictDataById(id);
    }
    /**
     * 根据单位名称获取单位id
     * @param id
     * @return
     */
    public SysDictData getUnitNameById(String id) {
        return this.provider.getDictDataById(id);
    }

    public SysDictData getIdByName(String name) {
        return this.provider.getIdByName(name);
    }
}
