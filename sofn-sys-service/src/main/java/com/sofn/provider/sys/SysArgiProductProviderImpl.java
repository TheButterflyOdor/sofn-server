package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysArgiProductMapper;
import com.sofn.dao.sys.SysArgiProductExpandMapper;
import com.sofn.model.generator.SysArgiProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2016/10/11.
 */
@DubboService(interfaceClass = SysArgiProductProvider.class)
public class SysArgiProductProviderImpl extends BaseProviderImpl<SysArgiProduct> implements SysArgiProductProvider {
    @Autowired
    private SysArgiProductMapper sysArgiProductMapper;

    @Autowired
    private SysArgiProductExpandMapper sysArgiProductExpandMapper;


   /* @Override
    public PageInfo<Map<String, Object>> queryByCondition(SysArgiProduct sysArgiProduct) {
        Global.getConfig("fdfs.tracker_server");
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<Map<String, Object>>();
        PageHelper.startPage(1,2);
        List<Map<String,Object>> list=sysArgiProductExpandMapper.getByCondition(sysArgiProduct);
        List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();


        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCount(sysArgiProduct));
        pageInfo.setList(list);
        return pageInfo;
    }*/

    @Override
    public PageInfo<Map<String, Object>> queryByCondition(SysArgiProduct sysArgiProduct,String keyword,Page page) {
        PageInfo pageInfo = new PageInfo();

        int start=Integer.valueOf( page.getStart()+"");
        int length=Integer.valueOf(page.getLength()+"");

        PageHelper.startPage(start+1,length);
        Map<String, Object> map=toMap(sysArgiProduct);
        map.put("keyword",keyword);
        List<Map<String,Object>> list=sysArgiProductExpandMapper.getBykeyWord(map);

        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCountByKeyword1(map));
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> queryProductBeforeSlaughter(String keyword,Page page) {
        PageInfo pageInfo = new PageInfo();

        int start=Integer.valueOf( page.getStart()+"");
        int length=Integer.valueOf(page.getLength()+"");

        PageHelper.startPage(start+1,length);
        Map<String, Object> map=new HashMap<>();
        map.put("keyword",keyword);
        List<Map<String,Object>> list=sysArgiProductExpandMapper.getAllProductBeforeSlaughter(map);

        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCountBeforeSlaughter(map));
        pageInfo.setList(list);
        pageInfo.setPageNum(page.getStart().intValue()+1);
        pageInfo.setPageSize(page.getLength().intValue());
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> recursionQuery(SysArgiProduct sysArgiProduct) {

        PageInfo pageInfo = new PageInfo();


        //PageHelper.startPage(0,100);
//        PageHelper.startPage(1,2);
        List<Map<String,Object>> list=sysArgiProductExpandMapper.recursionQueryMap(sysArgiProduct);
        List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();

       /* for (SysArgiProduct s:list
             ) {
            listMap.add(transBean2Map(s));
        }*/

        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCount(sysArgiProduct));
        pageInfo.setList(list);
        //List<Map<String,Object>> list=sysArgiProductExpandMapper.getByCondition(sysArgiProduct);
        return pageInfo;
    }

    @Override
    public void deleteArgiProduct(Object[] ids, String userId) {

        List<String> idList=new ArrayList<String>();
        for (Object id : ids) {
            {
                List<String> tmpList = sysArgiProductExpandMapper.recursionQuery(id);
                idList.addAll(tmpList);
            }

        }

        Iterator it=idList.iterator();
        while(it.hasNext()){
            this.delete(it.next().toString(),"6");
        }

    }

    @Override
    public PageInfo<Map<String, Object>> recursionQuery(SysArgiProduct sysArgiProduct, String keyword) {
        PageInfo pageInfo = new PageInfo();


        //PageHelper.startPage(0,100);
//        PageHelper.startPage(1,2);
        Map<String, Object> map=toMap(sysArgiProduct);
        map.put("keyword",keyword);
        List<Map<String,Object>> list=sysArgiProductExpandMapper.recursionQueryMapByKeyword(map);
        List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();

       /* for (SysArgiProduct s:list
             ) {
            listMap.add(transBean2Map(s));
        }*/

        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCountByKeyword(map));
        pageInfo.setList(list);
        //List<Map<String,Object>> list=sysArgiProductExpandMapper.getByCondition(sysArgiProduct);
        return pageInfo;
    }

    @Override
    public List<Map<String,Object>> getByNameOrAlias(String codeType,String keyword) {

        Map<String, Object> map= new HashMap<>();
        map.put("codeType",codeType);
        map.put("keyword",keyword);
        //如果是畜牧业就查询屠宰前的产品
        if("02".equals(codeType)){
            return sysArgiProductExpandMapper.getAllProductBeforeSlaughter(map);
        }

        //如果是种植业就查询四五级的产品，如果是水产业就查询第六级的产品
        List<Map<String,Object>> list = sysArgiProductExpandMapper.getByNameOrAlias(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryByProductCode(String productCode) {
        Map<String, Object> map= new HashMap<>();
        map.put("productCode",productCode);
        List<Map<String, Object>> resultMap = sysArgiProductExpandMapper.getByProductCode(map);
        return resultMap;
    }

    @Override
    public PageInfo<Map<String, Object>> queryByNameOrAlias(String codeType, String keyword,int start,int length) {
        //如果是畜牧业就查询屠宰前的产品
        if("02".equals(codeType)){
            Page page=new Page();
            page.setLength((long)length);
            page.setStart((long)start);
            return queryProductBeforeSlaughter(keyword,page);
        }

        //如果是种植业就查询四五级的产品，如果是水产业就查询第六级的产品
        PageInfo pageInfo = new PageInfo();
        int startTemp=start;
        int lengthTemp=length;

        PageHelper.startPage(startTemp+1,lengthTemp);
        Map<String, Object> map= new HashMap<>();
        map.put("codeType",codeType);
        map.put("keyword",keyword);
        List<Map<String,Object>> list = sysArgiProductExpandMapper.getByNameOrAlias(map);
        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCountByNameOrAlias(map));
        pageInfo.setList(list);
        pageInfo.setPageNum(startTemp+1);
        pageInfo.setPageSize(lengthTemp);

        return pageInfo;
    }


    @Override
    public PageInfo<Map<String, Object>> queryProductForSlaughter(String productCode,String keyword,int start,int length) {

        PageInfo pageInfo = new PageInfo();
        int startTemp=start;
        int lengthTemp=length;

        PageHelper.startPage(startTemp+1,lengthTemp);
        Map<String, Object> map= new HashMap<>();
        map.put("codeType","02");
        map.put("productCode",productCode);
        map.put("keyword",keyword);
        List<Map<String,Object>> list = sysArgiProductExpandMapper.queryProductForSlaughter(map);
        pageInfo.setTotal(sysArgiProductExpandMapper.getPageCountForSlaughter(map));
        pageInfo.setList(list);
        pageInfo.setPageNum(startTemp+1);
        pageInfo.setPageSize(lengthTemp);

        return pageInfo;
    }

    @Override
    public List<Map<String,Object>> queryProductForSlaughter(String productCode,String keyword) {

        Map<String, Object> map= new HashMap<>();
        map.put("codeType","02");
        map.put("productCode",productCode);
        map.put("keyword",keyword);
        List<Map<String,Object>> list = sysArgiProductExpandMapper.queryProductForSlaughter(map);

        return list;
    }

   /* @Override
    public PageInfo<Map<String,Object>> queryByCondition(SysResource sysResource) {
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<Map<String,Object>>();

        Map<String,Object> map=transBean2Map(sysResource);
        map.put("start",2);
        map.put("end",5);
        List<Map<String,Object>> list=sysResourceExpandMapper.getByCondition(map);
        pageInfo.setList(list);
        pageInfo.setTotal(sysResourceExpandMapper.getPageCount(map));
        return pageInfo;
    }

    @Override
    @Transactional
    public void deleteResource(String ids,String userId) {
        String[] id=ids.split(",");
        for (String resourceId:id) {
            this.delete(resourceId,userId);
        }

    }*/

    private static Map<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return new HashMap<String,Object>();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

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


    @Override
    public PageInfo<Map<String, Object>> getSlaProductPageInfo(int pageNum, int pageSize, String productId) {
        PageInfo pageInfo = new PageInfo();
        int startTemp = pageNum;
        int lengthTemp = pageSize;

        PageHelper.startPage(startTemp+1,lengthTemp);
        Map<String, Object> map= new HashMap<>();
        map.put("productId",productId);
        List<Map<String,Object>> list = sysArgiProductExpandMapper.getSlaProductPageInfo(map);
        pageInfo.setTotal(sysArgiProductExpandMapper.getSlaProductPageInfoCount(map));
        pageInfo.setList(list);
        pageInfo.setPageNum(startTemp+1);
        pageInfo.setPageSize(lengthTemp);
        return pageInfo;
    }
    @Override
    public SysArgiProduct getProductInfoByName(String productName) {
        return sysArgiProductExpandMapper.getProductInfoByName(productName);
    }
}
