package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjRegiterAndUser;
import com.sofn.model.tts.TtsScltxxcjRegiterDto;

import java.util.List;
import java.util.Map;

/**
 * 注册主体信息表 ExpandMapper
 *
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjRegiterExpandMapper extends BaseExpandMapper {


    /**
     * 获取注册主体信息表列表
     */
    List<Map<String, Object>> getPageInfo(Map<String, Object> map);


    /**
     * 获取注册主体信息表数据条数
     */
    long getCount(Map<String, Object> map);

    /**
     * 通过ACCOUNT查对象
     *
     * @param map
     * @return
     */
    TtsScltxxcjRegiter selectByAccountAndUserInfo(Map<String, Object> map);

    /**
     * 通过userIdCode查询实体对象
     *
     * @param map
     * @return
     */
    TtsScltxxcjRegiter selectByUserIdCode(Map<String, Object> map);

    /**
     * 通过EntityIdCode查询实体对象
     *
     * @param map
     * @return
     */
    TtsScltxxcjRegiter selectByEntityIdCode(Map<String, Object> map);

    /**
     * 检查注册码是否存在
     *
     * @param map
     * @return
     */
    List<String> queryByInfounique(Map<String, Object> map);

    /**
     * 检查身份证是否存在
     *
     * @param map
     * @return
     */
    Map<String, Object> queryByIdCode(Map<String, Object> map);

    /**
     * 字典表查ID差dictValue
     *
     * @param map
     * @return
     */
    Map<String, Object> queryDictValueById(Map<String, Object> map);

    /**
     * 撤销接口
     *
     * @param map
     * @return
     */
    public Integer updateEntityByCX(Map<String, Object> map);

    public Integer updateEntityByIdcode(Map<String, Object> map);


    //===========================提供监管接口=====================================

    /**
     * 通过
     * 所属区域 area
     * 行业 1,2,3,4
     * 主体类别 1,2,3,4
     * 组织形式 1,2,3,4
     * 是否有不良记录 0,1
     * 查询列表
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiter> getEntityPageInfoByCondition(Map<String, Object> map);

    /**
     * 审批更新接口
     *
     * @param map 编号 id
     *            审批状态 status
     *            审批意见 approveOpinion
     *            审批人 approveName
     *            审批时间 approveTime
     *            审批人主体用户码 approveUserIdcode
     *            更新人updateBy
     *            更新日期updateTime
     * @return TtsScltxxcjRegiter
     */
    public Integer updateEntityByEntity(Map<String, Object> map);

    /**
     * 变更数据接口
     *
     * @param map 编号 id
     *            企业名称 enterpriseName
     *            行政区划 area
     *            组织机构代码号 creditCode
     *            账号 account
     *            联系地址 address
     *            联系人名称 contactName
     *            联系方式 contactPhone
     *            组织机构代码附件 documentImages
     *            更新人updateBy
     *            更新日期updateTime
     * @return TtsScltxxcjRegiter
     */
    public Integer updateEntityByUpdate(Map<String, Object> map);

    /**
     * 获取注册号
     * map参数为
     * id 或者
     * userIdCode,entityIdCode
     *
     * @param map
     * @return
     */
    Map<String, Object> getRegisterNum(Map<String, Object> map);


    /**
     * 监管系统主体管理里生产经营主体信息专用
     * 条件跟上面的getEntityPageInfoByCondition一样，多一个不良记录badRecord
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiter> getSubjEntList(Map<String, Object> map);

    /**
     * 监管系统主体管理里生产经营主体信息专用
     * 条件跟上面的getEntityPageInfoByCondition一样，多一个不良记录badRecord
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiterDto> getSubjEntListNew(Map<String, Object> map);

    /**
     * getSubjEntList列表总数
     *
     * @param map
     * @return
     */
    long getSubjEntTotal(Map<String, Object> map);

    /**
     * 根据主体用户码查询主体信息
     *
     * @param code
     * @return
     */
    TtsScltxxcjRegiter getInfoForBody(String code);

    /**
     * 根据主体身份码码查询主体信息
     *
     * @param code
     * @return
     */
    TtsScltxxcjRegiter getInfoForUser(String code);

    List<Map<String, Object>> getBodyInfoByCode(Map<String, Object> map);

    List<Map<String, Object>> getBodyInfoByUserCode(Map<String, Object> map);

    TtsScltxxcjRegiter getInfoByUserInfo(Map<String, Object> map);

    /**
     * 通过userid查询主体信息(内测)
     *
     * @param id
     * @return TtsScltxxcjRegiter
     */
    TtsScltxxcjRegiter queryByUserId(String id);

    /**
     * 通过useridcode查询主体信息(内测)
     *
     * @param id
     * @return TtsScltxxcjRegiterAndUser
     */
    TtsScltxxcjRegiterAndUser queryByUserIdCode(String id);

    TtsScltxxcjRegiter getMainByUserIdCode(String entityIdCode);

    public Integer updateByAccount(TtsScltxxcjRegiter ttsScltxxcjRegiter);

    TtsScltxxcjRegiter getUserInfoByRegiter(Map<String, Object> map);

    void updateByFirm(TtsScltxxcjRegiter scltxxcjRegiter);

    /**
     * 查出级别=县级，被辖区域的监管机构--追溯系统_忘记账户_调用
     *
     * @param areaId
     * @return
     */
    Map<String, Object> getSubjSuperviseListByArea(String areaId);

    TtsScltxxcjRegiter getEntityByCode(String code);

    TtsScltxxcjRegiter queryEntityCodeAndUserCode(Map<String, Object> params);

    TtsScltxxcjRegiter findByAccount(Map<String, Object> map);

    TtsScltxxcjRegiter queryEntityInfoByEntity(String sourceEntity);

    TtsScltxxcjRegiter findInfoByEntityCode(String entityCode);

    List<TtsScltxxcjRegiter> getSubjEntByCondition(Map<String, Object> map);

    List<TtsScltxxcjRegiter> getSubjEntByUnionCredit(Map<String, Object> map);
}
