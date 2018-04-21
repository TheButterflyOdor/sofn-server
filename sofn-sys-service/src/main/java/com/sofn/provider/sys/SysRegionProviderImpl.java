package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysRegionMapper;
import com.sofn.dao.sys.SysRegionExpandMapper;
import com.sofn.model.generator.SysRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2016/10/11.
 */
@DubboService(interfaceClass = SysRegionProvider.class)
public class SysRegionProviderImpl extends BaseProviderImpl<SysRegion> implements SysRegionProvider {
    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Autowired
    private SysRegionExpandMapper sysRegionExpandMapper;


    @Override
    public PageInfo<List<Map<String, Object>>> getByRegionCondition(SysRegion sysRegion, Page page, String keyWord) {
        PageInfo pageInfo = new PageInfo();

        int start=Integer.valueOf( page.getStart()+"");
        int length=Integer.valueOf(page.getLength()+"");

        PageHelper.startPage(start+1,length);

        Map<String,Object> map=toMap(sysRegion);
        map.put("keyWord",keyWord);

        List<Map<String,Object>> list = sysRegionExpandMapper.getByKeyword(map);
        long count = sysRegionExpandMapper.getPageCountByKeyword(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        pageInfo.setPageSize(length);
        pageInfo.setPageNum(start);
        return pageInfo;
    }

    /**
     * query region by conditions
     * @return
     */
    @Override
    public PageInfo<List<Map<String, Object>>> getByRegionCondition(SysRegion sysRegion,Page page){

        PageInfo pageInfo = new PageInfo();

        int start=Integer.valueOf( page.getStart()+"");
        int length=Integer.valueOf(page.getLength()+"");

        PageHelper.startPage(start+1,length);

        List<Map<String,Object>> list = sysRegionExpandMapper.getByCondition(sysRegion);
        long count = sysRegionExpandMapper.getPageCount(sysRegion);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        pageInfo.setPageSize(length);
        pageInfo.setPageNum(start);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> getByRegionCondition(SysRegion sysRegion) {
        List<Map<String,Object>> list = sysRegionExpandMapper.getByCondition(sysRegion);
        return list;
    }

    @Override
    @Transactional
    public void deleteRegionsons(Object[] ids, String userId) {
        List<String> idList=new ArrayList<String>();
        for (Object id : ids) {
            {
                List<String> tmpList = sysRegionExpandMapper.recursionQuery(id);
                idList.addAll(tmpList);
            }

        }

        Iterator it=idList.iterator();
        while(it.hasNext()){
            this.delete(it.next().toString(),"6");
        }
    }

    @Override
    public String getRegionNameByRegionCode(String regionCode) {
        SysRegion sysRegion = sysRegionExpandMapper.getRegionByRegionCode(regionCode);
        List<SysRegion> tmpList = sysRegionExpandMapper.recursionQueryUp(sysRegion);
        String regionName = "";
        for (SysRegion sysRegionTmp : tmpList) {
            regionName = sysRegionTmp.getRegionName() + regionName;
        }
        return regionName;
    }

    @Override
    public List<SysRegion> recursionQuery(SysRegion sysRegion) {
        return sysRegionExpandMapper.recursionQueryMap(sysRegion);
    }

    private Map<String, Object> toMap(Object bean){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try{
            //Map<String, Object> returnMap = new HashMap<String, Object>();
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;

    }

}
