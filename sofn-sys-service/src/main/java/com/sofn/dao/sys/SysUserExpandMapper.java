package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.core.persistence.Pager;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysUserBean;
import com.sofn.model.sys.SysUserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-17.
 * Time: 23:04.
 * Description:
 */
@MyBatisDao
public interface SysUserExpandMapper extends BaseExpandMapper {
    int getRecordsTotal(@Param("keyword") String keyword, @Param("status") String status,@Param("organizationId") String organizationId);
    int getRecordsTotalNew(@Param("keyword") String keyword, @Param("status") String status,@Param("userId") String userId,@Param("userType") String userType);
    List<SysUserDto> getSysUserList(@Param("pager") Pager pager, @Param("keyword") String keyword);
    List<SysUserBean> getSysUserListByPage(@Param("page") Page page, @Param("keyword") String keyword, @Param("status") String status,@Param("organizationId") String organizationId);
    List<SysUserBean> getSysUserListByPageNew(@Param("page") Page page, @Param("keyword") String keyword, @Param("status") String status,@Param("userId") String userId,@Param("userType") String userType);
    /** 条件查询所有 */
    List<SysUser> queryByParam(@Param("param")Map<String, Object> param);

    /** 通过账号查询用户信息 */
    SysUser queryUserByAccount(@Param("account")String account);

    SysUserBean queryUserBeanById(@Param("id")String id);

    /** 根据角色查询拥有该角色的用户 */
    List<SysUser> queryByRoleId(@Param("roleId") String roleId);
    int getRecordsTotalExternal(@Param("userName") String userName);
    List<SysUser> getRecordsExternal(@Param("page") Page page,@Param("userName") String userName);
    //根据机构id删除用户信息
    void deleteByOrganization(@Param("organizationId") String organizationId,@Param("delFlag") String delFlag,@Param("status") String status);

    /**
     * 通过账号模糊匹配获取最大区域的数据账号
     * @param param
     * @return
     */
    String getMaxAreaByAccount(Map<String, Object> param);

    /**
     * 用户是否存在
     * @param param
     * @return
     */
    int checkUserByAccount(Map<String, Object> param);

    public List<SysUserBean> getOrgUserRecordsByUserId(@Param("page") Page page, @Param("status") String status,@Param("userId") String orgId);

    public int getOrgUserRecordsCountByUserId( @Param("status") String status,@Param("userId") String orgId) ;

    public int editPasswordByAccount( @Param("account") String account,@Param("password") String password) ;
}
