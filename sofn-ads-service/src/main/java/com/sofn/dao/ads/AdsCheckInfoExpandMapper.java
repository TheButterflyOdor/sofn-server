
package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsCheckInfo;
import com.sofn.model.generator.AdsCheckProject;
import com.sofn.model.generator.AdsMonitorSample;

import java.util.List;
import java.util.Map;

/**
 * 检测信息 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsCheckInfoExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取检测信息列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);

    /**
     *  获取所有信息列表
     */
    List<Map<String,Object>> getPageInfoParam(Map<String, Object> map);

	/**
	*  获取检测信息数据条数
	*/
    long getCount(Map<String, Object> map);
/**
	*  获取检测值数据条数
	*/
    long getCountProject(Map<String, Object> map);
    /*  获取机构筛选检测项目数据条数
	*/
    long getOrgNameCount(Map<String, Object> map);

    /**
     *  获取检测信息集合
     */
    List<AdsCheckInfo> getInfo(Map<String, Object> map);

    /**
     *  获取下一个ID
     */
    String getNextId(Map<String, Object> map);

    /**
     *  获取上一个ID
     */
    String getBeforeId(Map<String, Object> map);

    /**
     *  设置检测信息结果
     */
    void updateResult(Map<String, Object> map);
    void addAdsCheckInfo(AdsCheckInfo adsCheckInfo);
    /**
     * 获取年度
     */

    List<Map<String,Object>> getYear();
    /**
     * 获取任务名称
     */

   List<Map<String,Object>> getTaskName(Map<String, Object> map);
    /**
     * 获取结果
     */

    List<Map<String,Object>> getResult();
    /**
     * 获取抽样单位
     */
    public List<Map<String,Object>> getSampleDeparment(Map<String, Object> map);
    /**
     * 获取检测单位
     */
    public List<Map<String,Object>> getCheckOrgan(Map<String, Object> map);
    /**
     * 获取检测环节
     */
    public List<Map<String,Object>> getCheckLink();
    /**
     * 获取受检单位
     */
    public List<Map<String,Object>> getTestedDeparment(Map<String, Object> map);
    /**
     * 获取样品名称
     */
    public List<Map<String,Object>> getSampleName(String monitorClass);
    /**
     * 获取监测信息列表
     */
    List<Map<String,Object>> getInfoProject(Map<String, Object> map);

    /**
     * 牵头单位通过条件查询分页
     */
    List<Map<String,Object>> getPageInfoWithParam(Map<String, Object> map);
    /**
     * 承担单位通过条件查询分页
     */
    List<Map<String,Object>> getInfoByOrgName(Map<String, Object> map);

    /**
     * 承担单位通过条件查询所有分页
     */
    List<Map<String,Object>> getInfoByOrgNameAll(Map<String, Object> map);

    /**
     * 通过样品编码查询
     */
    AdsCheckInfo queryBySampleCode(Map<String, Object> map);

    AdsCheckInfo queryBySampleCodeAndOrganId(Map<String, Object> map);

    /**
     * 逻辑删除检测信息
     */
    void deleteInFlag(Map<String, Object> map);

    /*通过样品名称获取样品详细信息
    * */
    List<Map<String,Object>> getSampleBySampleCode(String sampleCode);

    /**
     * 通过ID查询
     */
    AdsCheckInfo queryByMyId(Map<String, Object> map);

    /**
     * 通过抽样单ID查询
     */
    AdsCheckInfo queryBySampleId(Map<String, Object> map);

    /**
     * 获取区域ID
     */
    public List<Map<String,Object>> getProducingArea();

    /**
     *  根据机构任务ID获取检测信息列表
     */
    List<Map<String,Object>> getPageInfoWithOrgTaskId(Map<String, Object> map);

    /**
     *  根据机构任务ID获取检测信息数据条数
     */
    long getCountWithOrgTaskId(Map<String, Object> map);

    /**
     * 删除（重置判定结果为-1）
     */
    void resetResult(Map<String, Object> map);
    /**
     * 根据条件查询牵头监测汇总数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getCheckList(Map<String, Object> map);


    /**
     * 根据查询条件得到AdsCheckInfo集合
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getCheckListWithParam(Map<String, Object> map);

    /**
     *根複選框得到AdsCheckInfo集合
     * @param idsArr
     * @return
     */
    public List<AdsCheckInfo> getCheckListByCheck(String[] idsArr);

    /**
     * 根据条件查询监测汇总详细数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getCheckListAll(Map<String, Object> map);

    /**
     * 修改上传状态为已上报
     */
    void updateReport(Map<String, Object> map);

    /**
     * 根据条件查询承担监测汇总数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getOrgList(Map<String, Object> map);

    /**
     * 根据条件查询承担监测汇总详细数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getOrgListAll(Map<String, Object> map);
    /**
     * 根据条件查询抽样单数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getSampleList(Map<String, Object> map);
    /**
     * 根据条件查询检测项目数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<AdsCheckInfo> getProjectList(Map<String, Object> map);

    /**
     *  获取检测上报完成数量
     */
    int getFinish(Map<String, Object> map);

    /**
     * 通过orgTaskId查询任务名称
     */
    String queryTaskName(String orgTaskId);

}