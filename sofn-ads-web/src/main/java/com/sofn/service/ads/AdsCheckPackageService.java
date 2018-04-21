package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;

import com.sofn.model.generator.AdsCheckObjectPackage;
import com.sofn.model.generator.AdsCheckPackage;
import com.sofn.provider.ads.AdsCheckPackageProvider;

import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@Service
public class AdsCheckPackageService extends BaseService<AdsCheckPackageProvider,AdsCheckPackage>{

    @DubboReference
    public void setAdsCheckPackageProvider(AdsCheckPackageProvider provider){
        this.provider = provider;
    }


    /**
     * 查询检测项目
     * @param adsCheckPackage
     * @param pageNum
     * @param pageSize
     * @param packageName
     * @return
     */
    public PageInfo getPageInfoAll(AdsCheckPackage adsCheckPackage, int pageNum, int pageSize, String packageName,String industry) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organId", adsCheckPackage.getOrganId());
        queryParams.put("packageName", packageName);
        queryParams.put("industry",industry);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organId",adsCheckPackage.getOrganId());
        return provider.getPageInfoAll(queryParams);
    }



    public int deleteAll(AdsCheckPackage adsCheckPackage){
        return provider.deleteAll(adsCheckPackage);
    }

    public int deleteObjectPackageAll(AdsCheckObjectPackage objectPackage){
        return provider.deleteObjectPackageAll(objectPackage);
    }

    /**
     * 根据id查询检测包信息
     * @param id
     * @return
     */
    public AdsCheckPackage selectByPrimaryKey(String id){
        return provider.selectByPrimaryKey(id);
    }



    public  int addCheckPackage(AdsCheckPackage adsCheckPackage){
        return provider.addCheckPackage(adsCheckPackage);
    }



    /**
     * 根据id更新检测包信息
     * @param adsCheckPackage
     */
    public void updateByPrimaryKey(AdsCheckPackage adsCheckPackage){
        provider.updateByPrimaryKey(adsCheckPackage);
    }

    public void updateObjectPackageByPrimaryKey(AdsCheckObjectPackage objectPackage){
        provider.updateObjectPackageByPrimaryKey(objectPackage);
    }


    /**
     * 查询检测对象包
     * @param adsCheckObjectPackage
     * @param pageNum
     * @param pageSize
     * @param objectPackageName
     * @return
     */
    public PageInfo getObjectPackagePageInfoAll(AdsCheckObjectPackage adsCheckObjectPackage, int pageNum, int pageSize, String objectPackageName,String industry){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organId", adsCheckObjectPackage.getOrganId());
        queryParams.put("objectPackageName", objectPackageName);
        queryParams.put("industry",industry);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        return provider.getObjectPackageInfoAll(queryParams);
    }

    public AdsCheckObjectPackage selectObjectPackageById(String id){
        return provider.selectObjectPackageById(id);
    }

    public int addCheckObjectPackage(AdsCheckObjectPackage adsCheckObjectPackage){
        return provider.addCheckObjectPackage(adsCheckObjectPackage);
    }

}
