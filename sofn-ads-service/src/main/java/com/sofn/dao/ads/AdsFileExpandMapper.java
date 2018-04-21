package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsFile;

import java.util.List;
import java.util.Map;

/**
 * 文件 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsFileExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取文件列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    List<Map<String,Object>> getDlReportPage(Map<String, Object> map);
    

	/**
	*  获取文件数据条数
	*/
    long getCount(Map<String, Object> map);

    List<Map<String,Object>> getField();

    List<Map<String,Object>> getType();

    List<Map<String,Object>> getYear();

    List<Map<String,Object>> getTask(Map<String, Object> map);

    List<AdsFile> getIdByOrganTask(Map<String, Object> map);

    /**
     *  根据多个条件获取问题单据数据列表
     */
    List<Map<String,Object>> getPageInfoProblemFile(Map<String, Object> map);

    /**
     *  根据多个条件获取问题单据数据条数
     */
    long getCountProblemFile(Map<String, Object> map);

    int updateForFile(Map<String, Object> map);

    /**
     * 按条件获取文件
     * @param map
     * @return
     */
    public List<AdsFile> getAdsFileByCondition(Map<String, Object> map);

    /**
     * 按条件获取牵头单位上传文件
     * @param map
     * @return
     */
    List<Map<String,Object>> getAdsFileBySupId(Map<String, Object> map);

    long getCountBySupId(Map<String, Object> map);
}
