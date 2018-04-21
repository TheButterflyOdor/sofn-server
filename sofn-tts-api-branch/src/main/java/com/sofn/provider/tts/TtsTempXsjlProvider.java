package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsTempXsjl;

import java.util.List;
import java.util.Map;

/**
 * 销售记录临时数据表模型对象
 * @author moon.l
 *
 */
public interface TtsTempXsjlProvider extends BaseProvider<TtsTempXsjl> {

	public PageInfo<TtsTempXsjl> getPageInfo(Map<String, Object> map);


	public List<Map<String,Object>> getGroupProduct(Map<String,Object> map);

	public void deleteAll();

	public TtsTempXsjl insertData(TtsTempXsjl record);

	public void deleteById(String id);
}