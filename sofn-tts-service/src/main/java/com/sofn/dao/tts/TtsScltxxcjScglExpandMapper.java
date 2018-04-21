package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjScglAndUserInfo;
import jxl.write.Boolean;

import java.util.List;
import java.util.Map;

/**
 * 生产管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjScglExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取生产管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取生产管理数据条数
	*/
    long getCount(Map<String, Object> map);


    /**
     * 获取指定的产品登记数据
     * @param map
     * @return
     */
    List<TtsScltxxcjScgl> getSphcByIds(Map<String, Object> map);


    /**
     * 通过产品的批次号，来修改产品的状态和库存  TTS_VIEW_CPXS  Oracle 新建视图，目的统计产品的销售总数量
     * @param map
     */
    void updateProductStoreCount(Map<String, Object> map);

    void updateProductStoreFreezeCount(Map<String, Object> map);

    /**
     * 采购管理通过，销售登记ID，修改生产管理库存
     * @param id
     */
    long updateStoreCountForCgqr(String id);

    /**
     * 校验获取到的数据是否：产品id唯一，计量单位是否统一
     * @param map
     * @return
     */
    List<Map<String,Object>> getCheckProduct(Map<String, Object> map);


    /**
     *  获取屠宰产品管理列表
     */
    List<Map<String,Object>> getslaughterPageInfo(Map<String, Object> map);


    /**
     *  获取屠宰产品管理数据条数
     */
    long getslaughterCount(Map<String, Object> map);

    void updateProductAmount(Map<String, Object> params);

    List<TtsScltxxcjScgl> getTypeList(String entityIdcode);

    Map<String,Object> getBaseForPc(Map<String, Object> map);

    TtsScltxxcjScglAndUserInfo getInfoByPcCodeForUsual(String code);

    TtsScltxxcjScglAndUserInfo getInfoByPcCodeForGov(String code);

    List<Map<String,Object>> getInfoByPcCode(Map<String, Object> map);

    TtsScltxxcjScgl getproductInfo(String id);

    void updateState(String id);
}
