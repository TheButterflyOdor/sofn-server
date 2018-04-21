package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.model.generator.SysResource;
import com.sofn.service.sys.SysResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
@RestController
@RequestMapping("/sysResource")
public class SysResourceController extends BaseController {
    @Autowired
    private SysResourceService sysResourceService;


    /**
     * 查询所有资源信息
     *
     * @param modelMap
     * @param resourcetype
     * @param professionalfiled
     * @param location
     * @param name
     * @param start
     * @param length
     * @return
     */
    @Authorization
    @ApiOperation(value = "资源查询")
    @SystemControllerLog(description = "资源查询",operationType="查询")
    /*@RequiresPermissions("sys.resource.read")*/
    @RequestMapping("/read/list")
    public Object getList(ModelMap modelMap, String resourcetype, String professionalfiled, String location, String name, int start, int length) {
        logger.debug("进入列表方法****************************");
        PageInfo<?> pageInfo = sysResourceService.getSysResourceList(resourcetype, professionalfiled, location, name, ((start + 1) / length + 1), length);
        return setSuccessModelMap(modelMap, pageInfo);
    }

    /**
     * 根据id查详细信息
     *
     * @return
     */
    @Authorization
    @ApiOperation(value = "资源详情")
    /*@RequiresPermissions("sys.resource.read")*/
    @RequestMapping(value = "/read/detail")
    @SystemControllerLog(description = "资源详情",operationType="查询")
    public Object detail(@RequestBody SysResource sysResource) {
        logger.debug("进入查询方法**************");
        ModelMap modelMap = new ModelMap();
        SysResource record = sysResourceService.queryById(sysResource.getId());
        return setSuccessModelMap(modelMap, record);
    }

    /**
     * 添加资源
     *
     * @return
     */
    @Authorization
    @ApiOperation(value = "添加")
   /* @RequiresPermissions("sys.resource.add")*/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "添加",operationType="添加")
    public Object add(@RequestBody SysResource sysResource) {
        ModelMap modelMap = new ModelMap();
        logger.debug("进入添加方法**************");
            sysResourceService.add(sysResource);
        return setSuccessModelMap(modelMap);
    }

    @Authorization
    @ApiOperation(value = "修改")
   /* @RequiresPermissions("sys.resource.add")*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @SystemControllerLog(description = "修改",operationType="修改")
    public Object update(@RequestBody SysResource sysResource) {
        ModelMap modelMap = new ModelMap();
        logger.debug("进入修改方法**************");
        sysResourceService.update(sysResource);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 删除资源
     *
     * @return
     */
    @Authorization
    @ApiOperation(value = "删除资源")
   /* @RequiresPermissions("sys.resource.delete")*/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @SystemControllerLog(description = "删除资源",operationType="删除")
    public Object delete(String dataInfo) {
        String[] jsonStr=dataInfo.split("\\|");
        ModelMap modelMap = new ModelMap();
        for (int i = 0; i < jsonStr.length; i++) {
            logger.debug(jsonStr[i] + "**************************************************************");
            String oStr=jsonStr[i].toString();
            if(oStr==null||oStr.equals("")){
                continue;
            }
            SysResource sysResource = sysResourceService.queryById(oStr);
            sysResource.setDelFlag("Y");
            sysResourceService.update(sysResource);
        }
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/repeatCount", method = RequestMethod.POST)
    public Object getRepeatResourceCount(String name, String unit) {
        ModelMap modelMap = new ModelMap();
        Map<String, Integer> data = new HashMap<>();
        data.put("count", sysResourceService.getRepeatResourceCount(name, unit));

        return setSuccessModelMap(modelMap, data);
    }
}
