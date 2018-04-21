package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.asms.AsmsBaseInspectionExpandMapper;
import com.sofn.dao.generator.AsmsBaseInspectionMapper;
import com.sofn.dao.generator.AsmsBaseUserMapper;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.generator.AsmsBaseUser;
import com.sofn.model.sys.SysUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年08月25日 下午 4:35
 */
@DubboService(interfaceClass = AsmsBaseInspectionProvider.class)
public class AsmsBaseInspectionProviderImpl extends BaseProviderImpl<AsmsBaseInspection> implements AsmsBaseInspectionProvider {

    @Autowired
    private AsmsBaseInspectionMapper asmsBaseInspectionMapper;
    @Autowired
    private AsmsBaseInspectionExpandMapper baseInspectionExpandMapper;
    @Autowired
    private AsmsBaseUserMapper baseUserMapper;

    @Transactional
    public int addBaseInspection(String inspectionUserId,AsmsBaseInspection asmsBaseInspection){
        String[] id = inspectionUserId.split(",");
        for(String userId:id) {
            this.addBaseUser(userId,asmsBaseInspection);
        }
        return asmsBaseInspectionMapper.insert(asmsBaseInspection);
    }

    @Override
    public PageInfo<AsmsBaseInspection> getBaseInspectionList(Map<String,Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = baseInspectionExpandMapper.getBaseInspectionList(map);
        long count = baseInspectionExpandMapper.getBaseInspectionCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo getBaseInspectionAllList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = baseInspectionExpandMapper.getBaseInspectionAllList(map);
        long count = baseInspectionExpandMapper.getBaseInspectionAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AsmsBaseInspection findBaseInspectionById(String id) {
        return asmsBaseInspectionMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateBaseInspection(String inspectionUserId,AsmsBaseInspection baseInspection) {
        if(inspectionUserId!=null&&!inspectionUserId.equals("")) {
            String[] id = inspectionUserId.split(",");
            if(id.length>0){
                baseInspectionExpandMapper.deleteBaseUserByInspectionId(baseInspection.getId());
            }
            for(String userId:id) {
                this.addBaseUser(userId,baseInspection);
            }
        }
        return asmsBaseInspectionMapper.updateByPrimaryKey(baseInspection);
    }

    @Override
    public int deleteBaseInspection(String id) {
        return asmsBaseInspectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateElState(String elState, String baseInspectionId) {
        return baseInspectionExpandMapper.updateElState(elState,baseInspectionId);
    }

    public void addBaseUser(String userId, AsmsBaseInspection baseInspection){
        AsmsBaseUser baseUser = new AsmsBaseUser();
        baseUser.setId(StringUtils.getUUIDString());
        baseUser.setBaseInspectionId(baseInspection.getId());
        baseUser.setInspectionUserId(userId);
        baseUserMapper.insert(baseUser);
    }

    @Override
    public PageInfo<SysUserBean> getSysUserListByOrgId(Map map) {
        PageInfo pageInfo = new PageInfo();
        List<SysUserBean> list = baseInspectionExpandMapper.getSysUserListByOrgId(map);
        int count = baseInspectionExpandMapper.getSysUserCountByOrgId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}
