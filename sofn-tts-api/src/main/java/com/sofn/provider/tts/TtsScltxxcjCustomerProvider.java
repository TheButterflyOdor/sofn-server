package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjCustomer;

import java.util.List;
import java.util.Map;

/**
 * 客户信息管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjCustomerProvider extends BaseProvider<TtsScltxxcjCustomer> {

	public PageInfo<TtsScltxxcjCustomer> getPageInfo(Map<String, Object> map);

    /**
     * 修改客户删除标识
     * @param id
     */
    void editDelFlag(String id);

    public List<TtsScltxxcjCustomer> getCustomerList(Map<String, Object> map);

    public List<TtsScltxxcjCustomer> getCusromerListNotCustomerEntityID(Map<String, Object> map);

    TtsScltxxcjCustomer checkCustomer(Map<String, Object> map);
}