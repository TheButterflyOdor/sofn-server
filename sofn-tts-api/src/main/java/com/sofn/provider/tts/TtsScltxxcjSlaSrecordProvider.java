package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjSlaSrecord;

import java.util.List;
import java.util.Map;

/**
 * 屠宰产品销售记录模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjSlaSrecordProvider extends BaseProvider<TtsScltxxcjSlaSrecord> {

	PageInfo<TtsScltxxcjSlaSrecord> getPageInfo(Map<String, Object> map);


    int insertByTemp(TtsScltxxcjSlaSale ttsScltxxcjXsdj);

    void updateXsmxStatus(Map<String, Object> map);

    List<TtsScltxxcjSlaSrecord> queryBySaleId(Map<String, Object> map);

    void deleteRecordFlag(String id);
}