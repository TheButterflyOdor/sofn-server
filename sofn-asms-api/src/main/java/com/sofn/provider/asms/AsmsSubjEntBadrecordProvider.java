package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsSubjEntBadrecord;
import com.sofn.model.generator.AsmsSubjEntTemp;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
public interface AsmsSubjEntBadrecordProvider extends BaseProvider<AsmsSubjEntBadrecord> {
    /**
     * 获取不良记录列表
     * @param map
     * @return
     */
    public PageInfo<AsmsSubjEntBadrecord> getAsmsSubjEntBadrecordList(Map<String, Object> map);

    public Map<String,Object> findEnterpriseById(String enterpriseId);

    /**
     * 根据id获取不良记录信息
     * @param id
     * @return
     */
    public AsmsSubjEntBadrecord findBadrecordById(String id);

    /**
     * 根据生产经营主体的主体身份码查询主体的不良记录列表
     * @param map
     * @return
     */
    public PageInfo getAsmsSubjEntBadrecordByIdList(Map<String,Object> map);

    /**
     * 添加生产经营主体的不良记录
     * @param asmsSubjEntBadrecord
     * @return
     */
    public int addSubjEntBadrecord(AsmsSubjEntBadrecord asmsSubjEntBadrecord);

    /**
     * 删除不良记录
     * @param id
     * @return
     */
    public int deleteBadrecordByPrimaryKey( String id);

    /**
     * 此接口已作废
     * @param map
     * @return
     */
    public AsmsSubjEntTemp selectByEntityIdCode(Map<String,Object> map);

}
