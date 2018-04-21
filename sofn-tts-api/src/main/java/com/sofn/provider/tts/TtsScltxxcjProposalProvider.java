package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjProposal;

import java.util.Map;

/**
 * 平台优化建议模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjProposalProvider extends BaseProvider<TtsScltxxcjProposal> {

	public PageInfo<TtsScltxxcjProposal> getPageInfo(Map<String, Object> map);


}