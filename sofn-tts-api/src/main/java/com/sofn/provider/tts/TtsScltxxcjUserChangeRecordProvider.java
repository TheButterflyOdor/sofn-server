package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjUserChangeRecord;

import java.util.List;
import java.util.Map;

/**
 * 注册主体信息表变更记录表模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjUserChangeRecordProvider extends BaseProvider<TtsScltxxcjUserChangeRecord> {

	public PageInfo<TtsScltxxcjUserChangeRecord> getPageInfo(Map<String, Object> map);



	//================================以下为提供接口========================================================

	/**
	 * 添加变更记录(撤销)
	 * map
	 *
	 * @param ttsScltxxcjUserChangeRecord
	 * 必填字段为：
	 * 	id,//编号
	 * 	entityId,//编号
	 * 	realName,//真实姓名
	 * 	account,//账号
	 *  entityScale; //主体组织形式
	 *  address; //企业地址
	 *  area; //所属区域
	 *  legalName; //法人姓名
	 *  legalIdnumber; //法人身份证号码
	 *  legalPhone; //法人电话
	 *  legalImages; //法人相关照片
	 *  registerTime; //注册时间
	 *  approveStatus; //审批状态
	 *  approveType; //变更类型
	 *  reason; //变更原因
	 *  applyUserId; //申请人用户ID
     *  applyUpdateName; //申请人
	 *  applyUpdateTime; //申请时间
	 *  delFlag //N
	 * @return
	 */
	int insert(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord);

	/**
	 * 添加变更记录(注销,变更)
	 * map
	 *
	 * @param ttsScltxxcjUserChangeRecord
	 * 必填字段为：
	 * 	id,//编号
	 * 	entityId,//编号
	 * 	realName,//真实姓名
	 * 	account,//账号
	 *  entityScale; //主体组织形式
	 *  address; //企业地址
	 *  area; //所属区域
	 *  legalName; //法人姓名
	 *  legalIdnumber; //法人身份证号码
	 *  legalPhone; //法人电话
	 *  legalImages; //法人相关照片
	 *  registerTime; //注册时间
	 *  approveStatus; //审批状态
	 *  approveType; //变更类型
	 *  reason; //变更原因
	 *  applyUserId; //申请人用户ID
	 *  applyUpdateName; //申请人
	 *  applyUpdateTime; //申请时间
	 *  delFlag //N
	 * @return
	 */
	int insertRe(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord);
	/**
	 *
	 * @param ttsScltxxcjUserChangeRecord
	 * @return
	 */
	int updateEntityByZX(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord);


	/**
	 * 通过条件查询变更数据分页数据
	 * @important
	 * 操作类型approveType = 1:注销待审核,2:备案变更待审核,3:撤销待审核列表
	 * 企业名称enterpriseName = keyword 模糊查询
	 * 所属区域 area = 可以传省市区 例如 510725 四川省绵阳市梓潼县 如果值传51:四川省所有数据,5107:绵阳市所有数据,传510725：梓潼县所有数据
	 * 组织形式 entityScale = 字典UUID,,,0:企业/个体工商户,1:合作社,2:家庭农场.3:个人 (来源字典表接口)
	 * 主体类别 entityType = 字典UUID,,,0:生产主体,1:经营主体,2:生产经营主体,3:其他(来源字典表接口)
	 * 行业 entityIndustry = 字典UUID,,,0,1,2,3(来源字典表接口)
	 * 审批状态 approveStatus = 0:未审批,1:审批通过,2:审批不通过
	 * 申请起日 beginDate
	 * 申请止日 endDate
	 * pageTail 直到下一页总数
	 * pageOffset 直到下一页起数
	 * @param map
	 * @return
     */
	List<Map<String,Object>> getEntityUserChangeRecordPageInfoByCondition(Map<String, Object> map);

	/**
	 * getEntityUserChangeRecordPageInfoByCondition
	 * 列表总数 参数如上
	 * @param map
	 * @return
     */
	long getTotal(Map<String, Object> map);

	/**
	 * 通过单个数据
	 * @param id
	 * @return
     */
	TtsScltxxcjUserChangeRecord selectById(String id);

	/**
	 * 审批接口
	 * @param map
	 * 编号 id
	 * 审批类型 approveStatus
	 * 			1：注销
	 * 					entityId  主体的ID
	 * 			2：变更
	 * 					如果是变更则增加变更参数
	 * 						entityId  主体的ID
	 * 					    enterpriseName
							area
							creditCode
							address
							contactName
							contactPhone
							documentImages
							updateBy
							updateTime
	 * 			3：撤销
	 * 				  entityId  主体的ID
	 * 审批状态 approveStatus=0:未审核，1:已审核通过,2:审核未通过
	 * 审批意见 approveOpinion
	 * 审批人 approveName
	 * 审批时间 approveTime
	 * 审批人主体用户码 approveUserIdcode
	 * @return TtsScltxxcjRegiter
	 */
	Integer updateEntityUserChangeRecordByMap(Map<String, Object> map);
}