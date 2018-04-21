package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjBase;

import java.util.Map;

/**
 * 基地信息管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjBaseProvider extends BaseProvider<TtsScltxxcjBase> {

	public PageInfo<TtsScltxxcjBase> getPageInfo(Map<String, Object> map);

	int updateStatus(TtsScltxxcjBase tscltxxcjbase);

    void deleteById(Map<String, Object> queryParams);

    void batchDeleteByIds(String[] ids);

    PageInfo<TtsScltxxcjBase> queryBaseByEntity(Map<String, Object> queryParams);

    void addBase(TtsScltxxcjBase ttsScltxxcjBase);

    String findBaseMainProcById(Map<String,Object> params);

    void updateMainProcs(Map<String, Object> params);

    boolean isExistedBase(TtsScltxxcjBase tscltxxcjbase);

}