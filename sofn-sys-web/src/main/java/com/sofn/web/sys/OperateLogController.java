package com.sofn.web.sys;

import com.sofn.core.Constants;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.base.RedisService;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.OperateLog;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.model.generator.SysRole;
import com.sofn.service.sys.SysOperateLogService;
import com.sofn.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-27.
 * Time: 22:53.
 * Description:
 */
@RestController
@Api(value = "字典管理接口", description = "操作日志")
@RequestMapping(value = "/log")
public class OperateLogController extends BaseController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private SysOperateLogService sysOperateLogService;
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "查询日志")
    @RequestMapping(value = "/queryOperateLog", method = RequestMethod.POST)
    @SystemControllerLog(description ="查询日志",operationType = "增加")
    public Object queryOperateLog(ModelMap modelMap, Long start, Long length,@RequestParam("operateType") String operateType, @CookieValue(value = "account", defaultValue = "") String account) {
        List<OperateLog> list = new ArrayList<>();
        if (Constants.SUPERADMIN_ACCOUNT.equals(account)) {
            list = redisService.queryOperateLog(); // superadmin账户查询所有记录
        } else {
            list = redisService.queryOperateLog(account); // 其他登录账户自己所属记录
        }

        List<OperateLog> resultList = new ArrayList<>();
        List<OperateLog> operateTypeList = new ArrayList<>();
        if(list.size() != 0 && StringUtils.isNotEmpty(operateType)){
            for(int i=0;i<list.size();i++){
                if(operateType.equals(list.get(i).getOperateType())){
                    operateTypeList.add(list.get(i));
                }
            }
        }
        long startIndex = (start-1)==-1?0:(start-1);
        if(StringUtils.isNotEmpty(operateType)){
            for(long i=startIndex;i<=(start-1+length);i++){
                if(operateTypeList.size()<i+1)
                    break ;
                resultList.add(operateTypeList.get((int) i));
            }
        }else{
            for(long i=startIndex;i<=(start-1+length);i++){
                if(list.size()<i+1)
                    break ;
                resultList.add(list.get((int)i));
            }
        }
        Page page = new Page();
        if(!"".equals(operateType))
            page.setRecordsTotal(operateTypeList.size());
        else
            page.setRecordsTotal(list.size());
        page.setRecordsFiltered(resultList.size());
        page.setStart(start);
        page.setLength(length);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", resultList);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "查询历史日志")
    @RequestMapping(value = "/queryOperatehistoryLog",method = RequestMethod.POST)
    public Object getPageInfo(ModelMap modelMap,
                              @RequestParam(value = "operateType", required = false) String operateType,
                              @RequestParam(value = "operateUsername", required = false) String operateUsername,
                              @RequestParam(value = "operateDesc", required = false) String operateDesc,
                              @RequestParam(value = "operateIp", required = false) String operateIp,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,Long start, Long length, @CookieValue(value = "account", defaultValue = "") String account) {

        if (!Constants.SUPERADMIN_ACCOUNT.equals(account)) {
            operateUsername = account; // 按当前登录用户查询日志
        }
        Map<String,Object> map2 = new HashedMap();
        map2.put("operateType",operateType);
        map2.put("operateDescribe",operateDesc);
        map2.put("operateUsername",operateUsername);
        map2.put("operateIp", operateIp);
        if(startTime!=""&&startTime!=null) {
            startTime += " 00:00:00";
        }
        map2.put("startTime",startTime);
        if(endTime!=""&&endTime!=null){
            endTime += " 23:59:59";
        }
        map2.put("endTime",endTime);
        long recordsTotal = sysOperateLogService.getCount(map2);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pager",pager);
        map.put("operateType",operateType);
        map.put("operateUsername",operateUsername);
        map.put("operateIp",operateIp);
        map.put("startTime",startTime);
        map.put("operateDescribe",operateDesc);
        map.put("endTime",endTime);
        List<SysOperateLog> list = sysOperateLogService.getPageInfo(map);
        modelMap.addAttribute("page",pager);

        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据IP查询
     * @param oIp
     * @return
     */
    @ApiOperation(value = "根据IP查询历史日志")
    @RequestMapping(value = "/findByIp",method = RequestMethod.POST)
    public Map<String,Object> findByIp(@RequestParam(value = "oIp", required = false) String oIp){
        List<SysOperateLog> list  = sysOperateLogService.findByIp(oIp);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",list);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
}
