package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsSubjEntTemp;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:25
 */
@MyBatisDao
public interface AsmsSubjEntExpandMapper extends BaseExpandMapper {

    TtsScltxxcjRegiter findSubjEntById(String id);

    List<TtsScltxxcjRegiter> getSubjEntList(Map<String,Object> map);

    long getSubjEntCount(Map<String,Object> map);

    List<AsmsSubjEntTemp> getSubjEntTempList(Map<String,Object> map);

    List<AsmsSubjEntTemp> getSubjEntTempListByCode(@Param("code") String code);

    long getSubjEntTempCount(Map<String,Object> map);

    List<TtsScltxxcjRegiter> getSubjEntRegisterList(Map<String,Object> map);

    long getSubjEntRegisterCount(Map<String,Object> map);

    List<Map<String,Object>> getSubjEntChangeList(Map<String,Object> map);

    long getSubjEntChangeCount(Map<String,Object> map);

    List<Map<String,Object>> getSubjEntRevokeList(Map<String,Object> map);

    long getSubjEntRevokeCount(Map<String,Object> map);

    List<Map<String,Object>> getSubjEntCancelList(Map<String,Object> map);

    long getSubjEntCancelCount(Map<String,Object> map);
}
