package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjRegiterAndUser;
import com.sofn.model.qry.TtsScltxxcjRegiterDto;

import java.util.List;
import java.util.Map;

/**
 * 注册主体信息表模型对象
 *
 * @author moon.l
 */
public interface TtsScltxxcjRegiterProvider extends BaseProvider<TtsScltxxcjRegiter> {

    public PageInfo<TtsScltxxcjRegiter> getPageInfo(Map<String, Object> map);

    /**
     * 申请备案
     *
     * @param ttsScltxxcjRegiter
     * @return
     */
    int applyRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter) throws Exception;

    /**
     * 注册审核未通过主体申请注册备案
     *
     * @param ttsScltxxcjRegiter
     * @return
     */
    int updateRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter) throws Exception;

    /**
     * 通过ACCOUNT查询实体对象
     *
     * @param map
     * @return
     */
    TtsScltxxcjRegiter selectByAccount(Map<String, Object> map);

    /**
     * 通过UserIdcode查询实体对象
     *
     * @param map set userIdcode
     * @return
     */
    TtsScltxxcjRegiter selectByUserIdCode(Map<String, Object> map);

    /**
     * 通过EntityIdCode查询实体对象
     *
     * @param map set entityIdCode
     * @return
     */
    TtsScltxxcjRegiter selectByEntityIdCode(Map<String, Object> map);

    /**
     * 验证企业注册号
     *
     * @param map
     * @return
     */
    long queryByCreditCode(Map<String, Object> map);

    /**
     * 验证身份证号
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


    //================================以下为提供接口========================================================

    /**
     * 通过
     * 企业名称enterpriseName = keyword 模糊查询
     * 所属区域 area = 可以传省市区 例如 510725 四川省绵阳市梓潼县 如果值传51:四川省所有数据,5107:绵阳市所有数据,传510725：梓潼县所有数据
     * 组织形式 entityScale = 0:企业/个体工商户,1:合作社,2:家庭农场.3:个人 (来源字典表接口)
     * 主体类别 entityType = 0:生产主体,1:经营主体,2:生产经营主体,3:其他(来源字典表接口)
     * 行业 entityIndustry = 0,1,2,3(来源字典表接口)
     * 审批状态 approveStatus = 0:未审批,1:审批通过,2:审批不通过
     * 申请起日 beginDate
     * 申请止日 endDate
     * pageTail 直到下一页总数
     * pageOffset 直到下一页起数
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiter> getEntityPageInfoByCondition(Map<String, Object> map);

    /**
     * getEntityUserChangeRecordPageInfoByCondition
     * 列表总数 参数如上
     *
     * @param map
     * @return
     */
    long getTotal(Map<String, Object> map);

    /**
     * 监管系统主体管理里生产经营主体信息专用
     * 条件跟上面的getEntityPageInfoByCondition一样，多一个不良记录badRecord
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiterDto> getSubjEntList(Map<String, Object> map);

    /**
     * getSubjEntList列表总数
     *
     * @param map
     * @return
     */
    long getSubjEntTotal(Map<String, Object> map);

    /**
     * 通过id查询实体对象
     *
     * @param id
     * @return
     */
    TtsScltxxcjRegiter selectByEntityId(String id);


    /**
     * 审批接口
     *
     * @param map 编号 id
     *            审批类型 approveStatus= 1：注销,2：变更，3：撤销
     *            审批状态 approveStatus=0:未审核，1:已审核通过,2:审核未通过
     *            审批意见 approveOpinion
     *            审批人 approveName
     *            审批时间 approveTime
     *            审批人主体用户码 approveUserIdcode
     * @return Integer
     */
    Integer updateEntityByEntity(Map<String, Object> map) throws Exception;

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
     * 通过主体身份码查询主体信息(内测)
     *
     * @param code
     * @return TtsScltxxcjRegiter
     */
    TtsScltxxcjRegiter getInfoForBody(String code);

    /**
     * 通过主体用户码查询主体信息(内测)
     *
     * @param code
     * @return TtsScltxxcjRegiter
     */
    TtsScltxxcjRegiter getInfoForUser(String code);

    TtsScltxxcjRegiter getInfoByUserInfo(Map<String, Object> map);

//	/**
//	 * 通过主体身份码查询主体信息
//	 * @param code
//	 * @param userType
//	 * @return TtsScltxxcjRegiter
//	 */
//	TtsScltxxcjRegiter getBodyInfoByEntityCode(String code,String userType);
//	/**
//	 * 通过主体身份码查询主体信息
//	 * @param code
//	 * @param userType
//	 * @return TtsScltxxcjRegiter
//	 */
//	TtsScltxxcjRegiter getBodyInfoByUserCode(String code,String userType);

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

    TtsScltxxcjRegiter getMainByUserIdCode(String userIdCode);


    TtsScltxxcjRegiter getUserInfoByRegiter(Map<String, Object> map);
}