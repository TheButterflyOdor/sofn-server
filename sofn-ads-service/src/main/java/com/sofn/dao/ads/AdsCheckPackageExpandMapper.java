package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.model.generator.AdsCheckObjectPackage;
import com.sofn.model.generator.AdsCheckPackage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@MyBatisDao
public interface AdsCheckPackageExpandMapper extends BaseExpandMapper {


    public void addCheckObjectPackage(AdsCheckObjectPackage adsCheckObjectPackage);

    //分页查询检测对象包
    List<Map<String, Object>> getObjectPackagePageInfoAll(Map<String, Object> map);

    long getObjectPackageCountAll(Map<String, Object> map);

//    long getCheckObjectPackageCount(AdsCheckObjectPackage adsCheckObjectPackage);

    AdsCheckObjectPackage selectObjectPackageByPrimaryKey(String id);

//    void insertObjectPackageAll(AdsCheckObjectPackage adsCheckObjectPackage);

    //新增检测包
    public void addCheckPackage(AdsCheckPackage adsCheckPackage);



    //分页查询检测项目
    List<Map<String, Object>> getPageInfoAll(Map<String, Object> map);


    /**
     *  获取全部检测项目数据条数
     */
    long getCountAll(Map<String, Object> map);


    /**
     *  根据ID删除检测模型(修改删除标志为Y)
     */
    int deleteByID(AdsCheckPackage adsCheckPackage);

    int deleteByObjectId(AdsCheckObjectPackage adsCheckObjectPackage);

    long getCheckPackageCount(AdsCheckPackage adsCheckPackage);


    /**
     * 根据Id查询检测包信息
     * @param id
     * @return
     */
    public AdsCheckPackage selectByPrimaryKey(String id);



    /**
     * 根据id更新检测包
     * @param adsCheckPackage
     */
    public void updateByPrimaryKey(AdsCheckPackage adsCheckPackage);

    void updateObjectPackageByPrimaryKey(AdsCheckObjectPackage objectPackage);

    void insertAll(AdsCheckPackage adsCheckPackage);

    void updateAll(AdsCheckPackage adsCheckPackage);
    void deleteAll(AdsCheckPackage adsCheckPackage);

}