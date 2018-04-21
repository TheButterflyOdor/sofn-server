package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.model.generator.AdsCheckObjectPackage;
import com.sofn.model.generator.AdsCheckPackage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public interface AdsCheckPackageProvider extends BaseProvider<AdsCheckPackage>{

    public int addCheckPackage(AdsCheckPackage adsCheckPackage);

    public PageInfo<AdsCheckPackage> getPageInfoAll(Map<String, Object> map);

    public int deleteAll(AdsCheckPackage adsCheckPackage);

    int deleteObjectPackageAll(AdsCheckObjectPackage adsCheckObjectPackage);

    public AdsCheckPackage selectByPrimaryKey(String id);

    public void updateByPrimaryKey(AdsCheckPackage adsCheckPackage);

    void updateObjectPackageByPrimaryKey(AdsCheckObjectPackage objectPackage);

    public int addCheckObjectPackage(AdsCheckObjectPackage adsCheckObjectPackage);

    public PageInfo<AdsCheckObjectPackage> getObjectPackageInfoAll(Map<String, Object> map);

    AdsCheckObjectPackage selectObjectPackageById(String id);


}
