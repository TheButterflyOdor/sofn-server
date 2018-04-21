package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsCheckPackageExpandMapper;

import com.sofn.model.generator.AdsCheckObjectPackage;
import com.sofn.model.generator.AdsCheckPackage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@DubboService(interfaceClass = AdsCheckPackageProvider.class)
public class AdsCheckPackageProviderImpl extends BaseProviderImpl<AdsCheckPackage> implements AdsCheckPackageProvider{
    @Autowired
    private AdsCheckPackageExpandMapper adsCheckPackageExpandMapper;

    @Transactional
    public int addCheckPackage(AdsCheckPackage adsCheckPackage){
        int result=0;
        long count = adsCheckPackageExpandMapper.getCheckPackageCount(adsCheckPackage);
        if (count>0){
            result=1;//表示新增检测包已经存在
        }else {
            adsCheckPackageExpandMapper.addCheckPackage(adsCheckPackage);
            String checkIds = adsCheckPackage.getCheckIds();
            if(checkIds!=null && checkIds.length() > 0){
                String[] arr = checkIds.split(",");
                if(arr!=null && arr.length > 0){
                    for (String checkId : arr){
                        AdsCheckPackage checkPackage = new AdsCheckPackage();
                        checkPackage.setPackageId(adsCheckPackage.getId());
                        checkPackage.setCheckId(checkId);
                        adsCheckPackageExpandMapper.insertAll(checkPackage);//添加映射表信息
                    }
                }
            }
            result=2;
        }
        return result;

    }


    public PageInfo<AdsCheckPackage> getPageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckPackageExpandMapper.getPageInfoAll(map);
        long count = adsCheckPackageExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    public int deleteObjectPackageAll(AdsCheckObjectPackage adsCheckObjectPackage){
        int result = 0;
        if (adsCheckObjectPackage !=null) {
            String ids = adsCheckObjectPackage.getIds();
            if(ids != null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr != null && arr.length >0){
                    for (String id : arr){
                        AdsCheckObjectPackage objectPackage = new AdsCheckObjectPackage();
                        objectPackage.setId(id);
                        objectPackage.setCreateDate(new Date());
                        adsCheckPackageExpandMapper.deleteByObjectId(objectPackage);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }


    public int deleteAll(AdsCheckPackage adsCheckPackage){
        int result = 0;
        if(adsCheckPackage!=null){
            String ids = adsCheckPackage.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsCheckPackage checkPackage = new AdsCheckPackage();
                        checkPackage.setId(id);
                        checkPackage.setCreateDate(new Date());
                        adsCheckPackageExpandMapper.deleteByID(checkPackage);
                    }
                    result = 1;
                }
            }
        }
        return result;

    }

    public AdsCheckPackage selectByPrimaryKey(String id){
        AdsCheckPackage adsCheckPackage=this.adsCheckPackageExpandMapper.selectByPrimaryKey(id);
        return adsCheckPackage;
    }




    @Transactional
    public void updateByPrimaryKey(AdsCheckPackage adsCheckPackage){
        if(adsCheckPackage!=null){
            String checkIds = adsCheckPackage.getCheckIds();
            if(checkIds!=null && checkIds.length() > 0){
                adsCheckPackageExpandMapper.deleteAll(adsCheckPackage);//更新采取先删除再添加的方式
                String[] arr = checkIds.split(",");
                if(arr!=null && arr.length > 0){
                    for (String checkId : arr){
                        AdsCheckPackage checkPackage = new AdsCheckPackage();
                        checkPackage.setPackageId(adsCheckPackage.getId());
                        checkPackage.setCheckId(checkId);
                        adsCheckPackageExpandMapper.insertAll(checkPackage);//更新映射表
                    }
                }
            }
            adsCheckPackageExpandMapper.updateByPrimaryKey(adsCheckPackage);
        }

    }

    @Transactional
    public void updateObjectPackageByPrimaryKey(AdsCheckObjectPackage objectPackage){
        adsCheckPackageExpandMapper.updateObjectPackageByPrimaryKey(objectPackage);
    }



    @Transactional
    public int addCheckObjectPackage(AdsCheckObjectPackage adsCheckObjectPackage){
        int result=0;
        adsCheckPackageExpandMapper.addCheckObjectPackage(adsCheckObjectPackage);
        result = 2;
//        long count = adsCheckPackageExpandMapper.getCheckObjectPackageCount(adsCheckObjectPackage);
//        if(count>0){
//            result=1;//表示新增检测对象包已经存在
//        }else {
//            adsCheckPackageExpandMapper.addCheckObjectPackage(adsCheckObjectPackage);
//            String checkIds = adsCheckObjectPackage.getCheckIds();
//            if(checkIds !=null && checkIds.length() > 0){
//                String[] arr = checkIds.split(",");
//                if(arr!=null && arr.length > 0){
//                    for (String checkId : arr){
//                        AdsCheckObjectPackage checkObjectPackage = new AdsCheckObjectPackage();
//                        checkObjectPackage.setPackageId(adsCheckObjectPackage.getId());
//                        checkObjectPackage.setCheckId(checkId);
//                        adsCheckPackageExpandMapper.insertObjectPackageAll(checkObjectPackage);//添加映射表信息
//                    }
//                }
//            }
//            result = 2;
//        }
        return result;
    }

    public PageInfo<AdsCheckObjectPackage> getObjectPackageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsCheckPackageExpandMapper.getObjectPackagePageInfoAll(map);
        long count = adsCheckPackageExpandMapper.getObjectPackageCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public AdsCheckObjectPackage selectObjectPackageById(String id){
        AdsCheckObjectPackage objectPackage = this.adsCheckPackageExpandMapper.selectObjectPackageByPrimaryKey(id);
        return objectPackage;
    }
}
