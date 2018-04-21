package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjXcdjjl;
import com.sofn.model.generator.TtsScltxxcjXsdj;

import java.util.List;
import java.util.Map;

/**
 * 销售记录模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjXcdjjlProvider extends BaseProvider<TtsScltxxcjXcdjjl> {

	public PageInfo<TtsScltxxcjXcdjjl> getPageInfo(Map<String, Object> map);

	public int insertByTemp(TtsScltxxcjXsdj ttsScltxxcjXsdj);

	public int updateXsmxStatus(Map<String,Object> map);

	public int deleteXsmxFlag(String id);

	public List<TtsScltxxcjXcdjjl> queryByXsdjId(Map<String,Object> map);

	public PageInfo<Map<String,Object>> pageForZsm(Map<String,Object> map);

	public PageInfo<Map<String,Object>> getXsbaseZsm(Map<String,Object> map);

	public PageInfo<Map<String,Object>> getInfoforpc(Map<String,Object> map);


	public PageInfo<Map<String,Object>> getPcToUpInfo(Map<String,Object> map);


	public Map<String,Object> getHclx(Map<String,Object> pc);
}