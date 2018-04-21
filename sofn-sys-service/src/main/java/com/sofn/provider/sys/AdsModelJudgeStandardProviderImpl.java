package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsModelJudgeStandardMapper;
import com.sofn.dao.sys.AdsModelJudgeStandardExpandMapper;
import com.sofn.model.generator.AdsModelJudgeStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
*	模型_判定标准 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsModelJudgeStandardProvider.class)
public class AdsModelJudgeStandardProviderImpl extends BaseProviderImpl<AdsModelJudgeStandard> implements AdsModelJudgeStandardProvider {

    @Autowired
    private AdsModelJudgeStandardExpandMapper AdsModelJudgeStandardExpandMapper;
    @Autowired
    private AdsModelJudgeStandardMapper adsModelJudgeStandardMapper;

    public PageInfo<AdsModelJudgeStandard> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelJudgeStandardExpandMapper.getPageInfo(map);
        long count = AdsModelJudgeStandardExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int batchDelete(AdsModelJudgeStandard adsModelJudgeStandard) {
        int result =0;
        if(adsModelJudgeStandard!=null){
            String items = adsModelJudgeStandard.getJudge_ids();
            if(items!=null && items.length() > 0){
                String[] ids = items.split(",");
                if(ids!=null && ids.length > 0){
                    for(String id : ids){
                        AdsModelJudgeStandard item = new AdsModelJudgeStandard();
                        item.setId(id);
                        item.setUpdateBy(adsModelJudgeStandard.getUpdateBy());
                        adsModelJudgeStandardMapper.deleteByLogic(item);    //逻辑删除
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

}
