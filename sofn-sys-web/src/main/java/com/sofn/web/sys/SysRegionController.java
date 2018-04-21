package com.sofn.web.sys;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.base.RedisService;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysRegion;
import com.sofn.service.sys.SysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: dong4j
 * Date: 2016-09-18
 * Time: 10:12
 * Description:
 */
@RestController
@Api(value = "行政区划", description = "行政区划接口")
@RequestMapping(value = "/region",method = RequestMethod.POST)
public class SysRegionController extends BaseController {
    /**
     * The Redis service.
     */
    @Autowired
    private RedisService redisService;

    @Autowired
    SysRegionService sysRegionService;

    @ApiOperation(value="行政区划名称查询")
    @PostMapping("/queryRegionName")
    @SystemControllerLog(description = "行政区划名称查询",operationType="查询")
    public String queryRegionName(@ApiParam(required = false, value = "regionCode") String regionCode){
        String regionName = sysRegionService.getRegionNameByRegionCode(regionCode);
        return regionName;
    }

    @GetMapping("/queryMenu")
    @ResponseBody
    @SystemControllerLog(description = "行政区划tree查询",operationType="查询")
    public Object queryMenu(//@ApiParam(required = true, value = "token")
                            //@RequestHeader(value = "token", defaultValue = "") String token,
                            String regionId,String delFlag) {
        SysRegion sysRegion=new SysRegion();
        sysRegion.setParentId(regionId);
        sysRegion.setDelFlag(delFlag);
        if("".equals(sysRegion.getDelFlag()) || null==sysRegion.getDelFlag()) sysRegion.setDelFlag("Y");
        List<Map<String, Object>> list=sysRegionService.getByRegionCondition(sysRegion);
        List<Tree> treeList=new ArrayList<Tree>();

        for (int i = 0; i < list.size(); i++) {
            SysRegion regionTmp=(SysRegion)list.get(i);
            Tree tree=new Tree();
            tree.setParentId(regionTmp.getParentId());
            tree.setId(regionTmp.getId());
            tree.setMenuUrl("/sofn-sys-web/region/query");
            tree.setText(regionTmp.getRegionName());

            SysRegion sysRegion1=new SysRegion();
            sysRegion1.setParentId(regionTmp.getId());
            List<Map<String, Object>> list1=sysRegionService.getByRegionCondition(sysRegion1);
            tree.setState(list1.size()==0 ? "open" : "closed");
//            List<Map<String,String>> childList=new ArrayList<Map<String,String>>();
//            Map<String,String> childMap=new HashMap<String,String>();
//            childMap.put("a","b");
//            childList.add(childMap);
//            tree.setChildren(childList);
            treeList.add(tree);
        }
        return treeList.size()==0 ? null : treeList;
    }
    /**
     * Logout object.
     *
     * @return the object
     */
    @ApiOperation(value = "行政区划查询")
    @PostMapping("/query")
    @SystemControllerLog(description = "行政区划查询",operationType="查询")
    public Object query(//@ApiParam(required = true, value = "token")
                        //@RequestHeader(value = "token", defaultValue = "") String token,
                        SysRegion sysRegion,long start,long length) {
        if("".equals(sysRegion.getDelFlag()) || null==sysRegion.getDelFlag()) sysRegion.setDelFlag("Y");
        Page page=new Page();
        page.setLength(length);
        page.setStart(start/10);
        // 分页计算
        //page.doPage();
        PageInfo<List<Map<String, Object>>> pageInfo=sysRegionService.getByRegionCondition(sysRegion,page);

        return setSuccessModelMap(new ModelMap(), pageInfo);
    }
    //查询关键字为编号、名称、简拼
    @Authorization
    @ApiOperation(value = "行政区划关键字查询")
    @PostMapping("/queryByKeyword")
    @SystemControllerLog(description = "行政区划关键字查询",operationType="查询")
    public Object query(//@ApiParam(required = true, value = "token")
                        //@RequestHeader(value = "token", defaultValue = "") String token,
                        SysRegion sysRegion,String keyWord,long start,long length) {
        if ("".equals(sysRegion.getDelFlag()) || null == sysRegion.getDelFlag()) sysRegion.setDelFlag("Y");
        Page page = new Page();
        page.setLength(length);
        page.setStart(start / 10);
        // 分页计算
        page.doPage();
        PageInfo<List<Map<String, Object>>> pageInfo = sysRegionService.getByRegionCondition(sysRegion, page,keyWord);
        return setSuccessModelMap(new ModelMap(), pageInfo);

    }

    @PostMapping("/recursionQuery")
    @SystemControllerLog(description = "行政区划树形结构查询",operationType="查询")
    public Object recursionQuery(SysRegion sysRegion) {
        sysRegion.setParentId("0");
        List<SysRegion> list = sysRegionService.recursionQuery(sysRegion);

        List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
        SysRegion s=new SysRegion();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getParentId().equals("0")){
                s=list.get(i);
                break;
            }
        }
        Map<String,Object> map=toMap(s);
        //list.remove(0);
        List<Map<String,Object>> tmpList=new ArrayList<Map<String,Object>>();
        tree(tmpList,list,s.getId());
        map.put("children", tmpList );
        map.put("text", s.getRegionName() );
        listMap.add(map);

      /*  net.sf.json.JSONArray array = net.sf.json.JSONArray.fromObject(listMap);
        System.out.println(array.toString());*/

        /*Map<String,Object> returnMap=new HashMap<String,Object>();
        returnMap.put("root",listMap);*/



        return listMap;//setSuccessModelMap(new ModelMap(), returnMap);
    }
    /**
     * Logout object.
     *
     * @param sysRegion    the SysRegion Class
     * @return the object
     */
    @ApiOperation(value = "行政区划新增")
    @PostMapping("/add")
    @SystemControllerLog(description = "行政区划新增",operationType="添加")
    public Object add(@RequestBody SysRegion sysRegion) {
        sysRegionService.add(sysRegion);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * Logout object
     * @param sysRegion    the SysRegion Class
     * @return the object
     */
    @ApiOperation(value = "行政区划修改")
    @PostMapping("/update")
    @SystemControllerLog(description = "行政区划修改",operationType="修改")
    public Object update(
            @ApiParam(required = false, value = "sysRegion") SysRegion sysRegion
    ) {
        SysRegion s=sysRegionService.queryById(sysRegion.getId());
        if(!("".equals( sysRegion.getParentId() )) && null != sysRegion.getParentId()){
            s.setParentId(sysRegion.getParentId());
        }
        if(!("".equals( sysRegion.getDelFlag() ))&& null != sysRegion.getDelFlag()){
            s.setDelFlag(sysRegion.getDelFlag());
        }
        if(!("".equals( sysRegion.getRegionCode() ))&& null != sysRegion.getRegionCode()){
            s.setRegionCode(sysRegion.getRegionCode());
        }
        if(!("".equals( sysRegion.getRegionFullname() ))&& null != sysRegion.getRegionFullname()){
            s.setRegionFullname(sysRegion.getRegionFullname());
        }
        if(!("".equals( sysRegion.getRegionName() ))&& null != sysRegion.getRegionName()){
            s.setRegionName(sysRegion.getRegionName());
        }
        if(!("".equals( sysRegion.getRegionPinyin() ))&& null != sysRegion.getRegionPinyin()){
            s.setRegionPinyin(sysRegion.getRegionPinyin());
        }
        if(!("".equals( sysRegion.getRegionType() ))&& null != sysRegion.getRegionType()){
            s.setRegionType(sysRegion.getRegionType());
        }
        if(!("".equals( sysRegion.getStatus() ))&& null != sysRegion.getStatus()){
            s.setStatus(sysRegion.getStatus());
        }
        if(null != sysRegion.getSortid()){
            s.setSortid(sysRegion.getSortid());
        }
        if(!("".equals(sysRegion.getRemark() ))&& null != sysRegion.getRemark()){
            s.setRemark(sysRegion.getRemark());
        }
        sysRegionService.update(s);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * Logout object.
     *
     * @param modelMap the model map
     * @param jsonIds   ids of the record will be delete .if there are more than one id ,ids can be separated by comma.
     * @return the object
     */
    @ApiOperation(value = "行政区划修改")
    @PostMapping("/delete")
    @SystemControllerLog(description = "行政区划修改",operationType="删除")
    public Object delete(ModelMap modelMap,
                         //@ApiParam(required = true, value = "token")
                         //@RequestHeader(value = "token", defaultValue = "") String token,
                         String jsonIds
    ) {
        JSONArray ids = JSONArray.parseArray(jsonIds);
        sysRegionService.deleteRegions(ids.toArray());
        return setSuccessModelMap(modelMap);
    }

    public Map<String, Object> toMap(Object bean){
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

    public void tree(List<Map<String,Object>> listTree,List<SysRegion> list,String productCode){
        try{
            if(list.size()==0){
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getParentId().equals(productCode)){
                    List<Map<String,Object>> tmpList=new ArrayList<Map<String,Object>>();
                    Map<String,Object> map=new HashMap<String,Object>();
                    map=toMap(list.get(i));
                    tree(tmpList,list,list.get(i).getId());
                    map.put("children", tmpList);
                    map.put("text", list.get(i).getRegionName() );
                    listTree.add(map);
//                    list.remove(i);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    class Tree{
        private String id;
        private String text;
        private String check;
        private String state;
        //        private Object children;
        private String menuUrl;
        private String parentId;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCheck() {
            return check;
        }

       /* public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }

        public void setCheck(String check) {

            this.check = check;
        }*/

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getMenuUrl() {
            return menuUrl;
        }

        public void setMenuUrl(String menuUrl) {
            this.menuUrl = menuUrl;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "id='" + id + '\'' +
                    ", text='" + text + '\'' +
                    ", check='" + check + '\'' +
                    ", state='" + state + '\'' +
//                    ", children='" + children + '\'' +
                    ", menuUrl='" + menuUrl + '\'' +
                    ", parentId='" + parentId + '\'' +
                    '}';
        }
    }

}
