package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesProduceAdminPunish;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.qry.AlesProduceAdminPunishDto;
import com.sofn.provider.qry.AsmsSubjEntBadrecordProvider;
import com.sofn.provider.qry.ProduceAdminPunishProvider;
import com.sofn.provider.qry.TtsScltxxcjRegiterProvider;
import com.sofn.util.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/21.
 */
@Service
public class ProduceAdminPunishService extends BaseService<ProduceAdminPunishProvider,AlesProduceAdminPunish> {
    private AsmsSubjEntBadrecordProvider asmsSubjEntBadrecordProvider;
    private TtsScltxxcjRegiterProvider ttsScltxxcjRegiterProvider;

    @DubboReference
    public void setProduceAdminPunishProvider(ProduceAdminPunishProvider provider){
        this.provider = provider;
    }

    @DubboReference
    public void setAsmsSubjEntBadrecordProvider(AsmsSubjEntBadrecordProvider provider){
        asmsSubjEntBadrecordProvider = provider;
    }

    @DubboReference
    public void setTtsScltxxcjRegiterProvider(TtsScltxxcjRegiterProvider provider){ ttsScltxxcjRegiterProvider = provider;
    }
    public PageInfo getProduceAdminPunishList(TtsScltxxcjRegiter entity, String taskYear,String dateBegin,String area,String dateEnd,
                                          int pageNum, int pageSize, String queryCon){
        Map<String,Object> queryParams = new HashMap<>();
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            queryParams.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //主体类型
        if (entity.getEntityType()!=null && !"".equals(entity.getEntityType().trim())) {
            queryParams.put("entityType",entity.getEntityType().split(","));
        }
        queryParams.put("taskYear",taskYear);
        queryParams.put("area",area);
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("queryCon",queryCon);
        return provider.getAisProduceAdminPunishList(queryParams);
    }

    //所有行政处罚数据列表（不分页）
    public List<AlesProduceAdminPunishDto> getAllProduceAdminPunishList(TtsScltxxcjRegiter entity, String taskYear,String dateBegin,String area,String dateEnd,
                                                                         String queryCon){
        Map<String,Object> queryParams = new HashMap<>();
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            queryParams.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //主体类型
        if (entity.getEntityType()!=null && !"".equals(entity.getEntityType().trim())) {
            queryParams.put("entityType",entity.getEntityType().split(","));
        }
        queryParams.put("taskYear",taskYear);
        queryParams.put("area",area);
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("queryCon",queryCon);
        return provider.getAllProduceAdminPunishList(queryParams);
    }


    public AlesProduceAdminPunish findProduceAdminPunish(String id){
        return provider.findProduceAdminPunishById(id);
    }
    //暂加
    public TtsScltxxcjRegiter findEnterpriseById(String entityIdCode){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("entityIdCode",entityIdCode);
        return ttsScltxxcjRegiterProvider.selectByEntityIdCode(params);
    }
    public PageInfo getAsmsSubjEntBadrecordByIdList(Page page, String enterpriseId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("enterpriseId",enterpriseId);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        return asmsSubjEntBadrecordProvider.getAsmsSubjEntBadrecordByIdList(queryMap);
    }

    public AlesProduceAdminPunish getPunishById(String id){
        return  provider.findPunishById(id);
    }

}
