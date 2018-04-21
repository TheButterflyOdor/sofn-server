package com.sofn.provider.tts;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjUsernumber;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/18.
 */
public interface TtsScltxxcjUsernumberProvider extends BaseProvider<TtsScltxxcjUsernumber> {

    /**
     * 通过主体身份码获取用户号
     * @param entityCode
     * @return
     */
    public Integer getUsernumByEntityCode(String entityCode);

    /**
     * 新增或修改主体身份码对应的用户号
     * @param map
     */
    public void updateUsernumByEntityCode(Map<String, Object> map);


}
