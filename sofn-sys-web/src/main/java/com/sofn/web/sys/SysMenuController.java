package com.sofn.web.sys;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.json.JsonUtils;
import com.sofn.core.util.json.ResponseJson;
import com.sofn.model.generator.SysMenu;
import com.sofn.service.sys.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@Api(value = "菜单管理",description = "菜单管理")
@RequestMapping(value = "/sysMenu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询菜单列表
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/queryMenues.do")
    public void queryMenues(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseJson responseJson = new ResponseJson();
        try {
            Boolean isApp = true;
            String id = request.getParameter("id");
            List<Map> data = new ArrayList<Map>();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("id", -1);
            dataMap.put("name","内部管理系统");
            dataMap.put("menuRight", "true");
            dataMap.put("level", "一级");
            dataMap.put("right1", "--");
            dataMap.put("right2", "--");
            dataMap.put("right3", "--");
            dataMap.put("right4", "--");
            dataMap.put("right5", "--");
            dataMap.put("right6", "--");
            dataMap.put("right7", "--");
            dataMap.put("right8", "--");
            dataMap.put("code", "--");
            data.add(dataMap);
            List<SysMenu> menues = sysMenuService.getAllByDesc();

            if(menues.size()>0){
                this.rec(menues, data);
            }

            responseJson.setTotal(data.size());
            responseJson.setRows(data);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseJson.setSuccess(Boolean.FALSE);
            responseJson.setMsg("查询菜单失败！");
            JsonUtils.responseJson(response, responseJson);
        }
        JsonUtils.responseJson(response, responseJson);
    }

    /**
     * 查询菜单树
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/menuTree.do")

    public void menuTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseJson responseJson = new ResponseJson();
        try {
            List<SysMenu> menues = sysMenuService.getAllByDesc();

            responseJson.setTotal(menues.size());
            responseJson.setRows(menues);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseJson.setSuccess(Boolean.FALSE);
            responseJson.setMsg("查询菜单失败！");
            JsonUtils.responseJson(response, responseJson);
        }
        JsonUtils.responseJson(response, responseJson);
    }

    /**
     * 循环出菜单信息
     * @param menues
     * @param data
     */
    void rec(List<SysMenu> menues, List<Map> data) {
        if (!CollectionUtils.isEmpty(menues)) {
            for (SysMenu menu : menues) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("id", menu.getId());
                dataMap.put("name", menu.getMenuName());
                dataMap.put("menuRight", "true");
                int menuLevel = Integer.parseInt(menu.getMenuType());
                switch (menuLevel) {
                    case 2:
                        dataMap.put("level", "二级");
                        break;
                    case 3:
                        dataMap.put("level", "三级");
                        break;
                    case 4:
                        dataMap.put("level", "四级");
                        break;
                    case 5:
                        dataMap.put("level", "五级");
                        break;
                    case 6:
                        dataMap.put("level", "六级");
                        break;
                    case 7:
                        dataMap.put("level", "七级");
                        break;
                    case 8:
                        dataMap.put("level", "八级");
                        break;
                    case 9:
                        dataMap.put("level", "九级");
                        break;
                    case 10:
                        dataMap.put("level", "十级");
                        break;
                    default:
                        break;
                }
                dataMap.put("right1", "false");
                dataMap.put("right2", "false");
                dataMap.put("right3", "false");
                dataMap.put("right4", "false");
                dataMap.put("right5", "false");
                dataMap.put("right6", "false");
                dataMap.put("right7", "false");
                dataMap.put("right8", "false");
                String remark = menu.getRemark();
                if (null != remark) {
                    String[] rightArr = remark.split(",");
                    if (null != rightArr && rightArr.length > 0) {
                        for (String string : rightArr) {
                            String key = "right" + string;
                            dataMap.put(key, "true");
                        }
                    }
                }
                dataMap.put("code", "--");
                data.add(dataMap);
//                List<SysMenu> children = new ArrayList<>();
//                rec(children, data);
            }
        }
    }



    /**
     * "新增菜单
     * @param
     * @return
     */
    @Authorization
    @ApiOperation(value = "新增菜单")
    @RequestMapping(value = "/addSysMenu",method = RequestMethod.POST)
    @SystemControllerLog(description = "新增菜单",operationType="新增")
    public Map<String, Object> addSysMenu(@RequestBody SysMenu sysMenu){
        String userId = WebUtil.getCurrentUserId();
        sysMenu.setId(UUID.randomUUID().toString());
        sysMenu.setCreateBy(userId);
        sysMenu.setCreateTime(new Date());
        sysMenu.setDelFlag("N");

        String tempNum = sysMenu.getNumbers();
        if (isNumeric(tempNum)){
            List<SysMenu> list = sysMenuService.getByNumber(tempNum);
            if (list.size()>0){
                sysMenuService.updateAllNumber(tempNum);
            }
        }else {
            Long temp = sysMenuService.getMaxNumber();
            Integer tempInt = Integer.parseInt(temp.toString())+1;
            sysMenu.setNumbers(tempInt.toString());
        }
        sysMenuService.addSysMenu(sysMenu);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    /**
     * 判断是否是数字和是否为空
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        if (str.length() == 0){
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 获取当前登陆用户的可分配菜单
     * @param
     * @return
     */
    @Authorization
    @ApiOperation(value = "获取菜单列表")
    @RequestMapping(value = "/getSysMenuListByToken",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取菜单列表",operationType="查询")
    public Object getSysMenuListByToken(ModelMap modelMap,@RequestHeader(value = "token", defaultValue = "") String token){ CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if(u==null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        Map<String ,Object> param=new HashedMap();
        if(u.getOrganizationId()!=null&&u.getOrganization()==null)
            return setModelMap(modelMap, HttpCode.NOT_FOUND_DATA);
        if("SYSADMIN".equals(u.getUserType()) && "ASMS".equals(u.getOrganization().getOrgType())){
            if("1".equals(u.getOrganization().getOrgLevel())){
                param.put("sysFlag","1");
            }else{
                param.put("sysFlag","2");
            }
        }else if("SYSADMIN".equals(u.getUserType()) && u.getAccount().indexOf("JC-")==0){
            param.put("sysFlag","3");
        }else if("SYSADMIN".equals(u.getUserType()) && u.getAccount().indexOf("ZF-")==0){
            param.put("sysFlag","4");
        }
        List<SysMenu> list = sysMenuService.queryByParam(param);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据条件获取菜单列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取菜单列表")
    @RequestMapping(value = "/getSysMenuList",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取菜单列表",operationType="查询")
    public Object getSysMenuList(ModelMap modelMap, @ApiParam(required = true, value = "菜单名字")
    @RequestParam(value = "menuName", required = false) String menuName,
                                 @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                                 @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,
                                 @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length){



        Page page = new Page();
        page.setDraw(draw);
        page.setStart(start);
        page.setLength(length);

        long recordsTotal = sysMenuService.getRecordsTotal(menuName);
        page.setRecordsTotal(recordsTotal);

        page.doPage();

        List<SysMenu> list = sysMenuService.getSysMenuLists(page,menuName);
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);

        return setSuccessModelMap(modelMap);


    }




    /**
     * 根据ID获取单个菜单
     * @param
     * @return
     */
    @Authorization
    @ApiOperation(value = "获取菜单")
    @RequestMapping(value = "/findMenuById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取菜单",operationType="查询")
    public Map<String, Object> findMenuById(@RequestBody String data,HttpServletRequest request, HttpServletResponse response){

        SysMenu temp = sysMenuService.findSysMenuByst(data);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("data",temp);
//        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
//        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);

//        modelMap.addAttribute("sysMenu",temp);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sysMenu",temp);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @Authorization
    @ApiOperation(value = "修改菜单信息")
    @RequestMapping(value = "updateMenu",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改菜单信息",operationType="修改")
    public Map<String,Object> updateMenu(@RequestBody SysMenu sysMenu){
        String userId = WebUtil.getCurrentUserId();
        sysMenu.setUpdateBy(userId);
        sysMenu.setUpdateTime(new Date());
        sysMenu.setDelFlag("N");

        String tempNum = sysMenu.getNumbers();
        if (isNumeric(tempNum)){
            List<SysMenu> list = sysMenuService.getByNumber(tempNum);
            if (list.size()>0 && !(list.get(0).getId()).equals(sysMenu.getId())){
                sysMenuService.updateAllNumber(tempNum);
            }
        }else {
            Long temp = sysMenuService.getMaxNumber();
            Integer tempInt = Integer.parseInt(temp.toString())+1;
            sysMenu.setNumbers(tempInt.toString());
        }

        sysMenuService.updateSysMenu(sysMenu);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "查询所有菜单信息")
    @RequestMapping(value = "getAllByDesc",method = RequestMethod.POST)
    @SystemControllerLog(description = "查询所有菜单信息",operationType="查询")
    public Map<String,Object> getAllByDesc(){

        List<SysMenu> list = sysMenuService.getAllByDesc();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",list);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @Authorization
    @ApiOperation(value = "删除菜单信息")
    @RequestMapping(value = "deleteMenu",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除菜单信息",operationType="删除")
    public Map<String,Object> deleteMenu(@ApiParam(required = true, value = "菜单ID")
                                         @RequestParam(value = "id", required = false) String id){
        sysMenuService.deleteSysMenu(id);

//        SysMenu temp = sysMenuService.findSysMenuByst(id);
//        temp.setDelFlag("Y");
//        sysMenuService.updateSysMenu(temp);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "菜单树")
    @RequestMapping("/queryMenu")
    @SystemControllerLog(description = "菜单树",operationType="查询")
    public Object queryMenu(ModelMap modelMap,@ApiParam(required = true, value = "菜单ID")
                                @RequestParam(value = "parentId", required = false)String parentId,
                                @ApiParam(required = true, value = "token")
                                @RequestParam(value = "token", defaultValue = "") String token) {

        List<SysMenu> list=sysMenuService.getAllByParentId(parentId,null);
        List<Tree> treeList=new ArrayList<Tree>();

        for (int i = 0; i < list.size(); i++) {
            SysMenu sysTemp=list.get(i);
            Tree tree=new Tree();
            tree.setParentId(sysTemp.getParentId());
            tree.setId(sysTemp.getId());
            tree.setMenuUrl("/sofn-sys-web/sysMenu/getSysMenuList");
            tree.setText(sysTemp.getMenuName());

            List<SysMenu> list1=sysMenuService.getAllByParentId(sysTemp.getId(),null);
            tree.setState(list1.size()==0 ? "open" : "closed");
            treeList.add(tree);
        }

        return treeList.size()==0 ? null : treeList;
    }

    @Authorization
    @ApiOperation(value = "根据父id查询列表")
    @PostMapping("/queryByKeyID")
//    @SystemControllerLog(description = "根据父id查询列表",operationType="查询")
    public Object queryByKeyID(ModelMap modelMap,@ApiParam(required = true, value = "菜单ID")
                                   @RequestParam(value = "parentId", required = false) String parentId,
                               @ApiParam(required = true, value = "菜单名字")
                               @RequestParam(value = "menuName", required = false) String menuName) {

        String menuNamet = menuName == null ? "" : menuName.trim();

        List<SysMenu> list = sysMenuService.getAllByParentId(parentId,menuNamet);
        for (SysMenu sys:list) {
            if(("0").equals(sys.getMenuType())){
                sys.setMenuType("系统");
            }else if (("1").equals(sys.getMenuType())){
                sys.setMenuType("菜单");
            }else if (("2").equals(sys.getMenuType())){
                sys.setMenuType("操作");
            }else {
                sys.setMenuType("");
            }

        }
        modelMap.addAttribute("list",list);

        return setSuccessModelMap(modelMap);

    }

    @ApiOperation(value = "根据id查询列表")
    @PostMapping("/recursionQuery")
    @SystemControllerLog(description = "根据id查询列表",operationType="查询")
    public Object recursionQuery(ModelMap modelMap,@ApiParam(required = true, value = "菜单ID")
    @RequestParam(value = "id", required = false) String id) {

        List<SysMenu> list = sysMenuService.recursionQuery(id);

        List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
        SysMenu s=new SysMenu();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getParentId().equals("0")){
                s=list.get(i);
                break;
            }
        }
        Map<String,Object> map=toMap(s);

        List<Map<String,Object>> tmpList=new ArrayList<Map<String,Object>>();
        tree(tmpList,list,s.getId());
        map.put("children", tmpList );
        map.put("text", s.getMenuName() );
        listMap.add(map);


        return listMap;
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

    public void tree(List<Map<String,Object>> listTree,List<SysMenu> list,String productCode){
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
                    map.put("text", list.get(i).getMenuName() );
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
