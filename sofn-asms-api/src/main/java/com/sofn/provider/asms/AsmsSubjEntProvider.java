package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsSubjEntTemp;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:19
 */
public interface AsmsSubjEntProvider extends BaseProvider<AsmsSubjEntTemp>{

    PageInfo getSubjEntTempList(Map<String,Object> map);

    List<AsmsSubjEntTemp> getSubjEntTempListByCode(String code);

    AsmsSubjEntTemp findSubjEntTempById(String id);

    int addSubjEntTemp(AsmsSubjEntTemp subjEntTemp);

}
