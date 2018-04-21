package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjXcdjjl;
import com.sofn.model.generator.TtsScltxxcjXsdj;

import java.util.List;
import java.util.Map;

/**
 * 销售记录 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjXcdjjlExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取销售记录列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取销售记录数据条数
	*/
    long getCount(Map<String, Object> map);


    /**
     * 从临时表将数据插入销售记录表中,并关联销售登记数据
     * @param ttsScltxxcjXsdj
     */

    int insertByTemp(TtsScltxxcjXsdj ttsScltxxcjXsdj);


    int updateXsmxStatus(Map<String,Object> map);

    int deleteXsmxFlag(String id);

    /**
     * 通过条件查询销售明细记录
     * @param map
     * @return
     */
    List<TtsScltxxcjXcdjjl> queryByXsdjId(Map<String,Object> map);

    /**
     * 追溯信息查询分页
     * @param map
     * @return
     */
    List<Map<String,Object>> pageForZsm(Map<String,Object> map);
    long pageForCountZsm(Map<String,Object> map);


    /**
     * 追溯查询，本级是销售
     * @param map
     * @return
     */
    List<Map<String,Object>> getXsbaseZsm(Map<String,Object> map);
    long getXsbaseZsmCount(Map<String,Object> map);


    /**
     * 通过PC码查询销售记录
     * @param map
     * @return
     */
    List<Map<String,Object>> getInfoforpc(Map<String,Object> map);
    long getCountforpc(Map<String,Object> map);


    /**
     * 批次查询得到zsm查询销售来源
     * @param map
     * @return
     */
    List<Map<String,Object>> getPcToUpInfo(Map<String,Object> map);
    long getPcToUpCount(Map<String,Object> map);


    /**
     * 产品合成后流向
     * @param pc
     * @return
     */
    Map<String,Object> getHclx(Map<String,Object> pc);

}
