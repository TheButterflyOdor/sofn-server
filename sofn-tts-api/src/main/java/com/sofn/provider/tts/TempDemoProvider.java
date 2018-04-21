package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TempDemo;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
public interface TempDemoProvider extends BaseProvider<TempDemo> {

    public PageInfo<TempDemo> getPageInfo(Map<String,Object> map);

}
