package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsProducttempoRrarycode;

import java.util.Map;

/**
 * 产品临时码模型对象
 * @author moon.l
 *
 */
public interface AdsProducttempoRrarycodeProvider extends BaseProvider<AdsProducttempoRrarycode> {

	public PageInfo<AdsProducttempoRrarycode> getPageInfo(Map<String,Object> map);
	public AdsProducttempoRrarycode findById(String id);

}