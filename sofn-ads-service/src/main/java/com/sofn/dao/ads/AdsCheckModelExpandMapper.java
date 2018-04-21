package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsCheckModel;

import java.util.List;
import java.util.Map;

/**
 * 检测模型 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsCheckModelExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取检测模型列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取检测模型数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取全部检测模型
     */
    List<Map<String,Object>> selectAll(Map<String, Object> map);

    /**
     *  获取全部检测模型数据列表
     *  @author TC
     */
    List<Map<String, Object>> getPageInfoAll(Map<String, Object> map);

    /**
     *  获取全部检测模型数据条数
     *  @author TC
     */
    long getCountAll(Map<String, Object> map);

    /**
     *  查询该检测模型名称是否已有
     *  @author TC
     */
    long selectByName(AdsCheckModel adsCheckModel);

    /**
     *  获取检测模型含有的子表数据列表
     */
    List<Map<String,Object>> getPageInfoChecked(Map<String, Object> map);

    /**
     *  获取检测模型含有的子表数据条数
     */
    long getCountChecked(Map<String, Object> map);

    /**
     *  获取检测模型未含有的子表数据列表
     */
    List<Map<String,Object>> getPageInfoUnChecked(Map<String, Object> map);


    /**
     *  获取检测模型未含有的子表数据条数
     */
    long getCountUnChecked(Map<String, Object> map);

    /**
     *  获取检测模型的行业
     */
    String getIndustryByModelId(String id);

    /**
     * 根据ID查询检测模型关联了几个类型数据
     */
    long findCountOfCheckModel(Map<String, Object> map);

    /**
     * 根据ID修改启用状态
     */
    void updateIsEnable(Map<String, Object> map);


    /**
     *  获取检测模型相关的判定标准数据
     */
    List<Map<String,Object>> getPageInfoCheckedAndJudgeStandard(Map<String, Object> map);

    /**
     *  获取检测模型相关的判定标准数据条数
     */
    long getCountCheckedAndJudgeStandard(Map<String, Object> map);
}
