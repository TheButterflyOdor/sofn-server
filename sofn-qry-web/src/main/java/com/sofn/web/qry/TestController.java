package com.sofn.web.qry;

import com.sofn.core.base.BaseController;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.model.qry.SysTemplate;
import com.sofn.service.qry.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
*	测试 controller 控制器实现
 * Created by Liw
 */
@RestController
@Api(value = "模板管理", description = "模板管理")
@RequestMapping(value = "/testController",method = RequestMethod.POST)
public class TestController extends BaseController {
	@Autowired
    public TestService testService;
    /**
     * 根据条件获取模板管理列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取模板管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(ModelMap modelMap, @ApiParam(required = true, value = "模板名称") @RequestParam(value = "templateName", required = false) String templateName,
                              @ApiParam(required = true, value = "模板类型") @RequestParam(value = "templateType", required = false) String templateType,
                              @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                              @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,
                              @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length) {
        if(templateType.equals("全部")){
            templateType = "";
        }
        long recordsTotal = testService.getRecordsTotal(templateName,templateType);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setDraw(draw);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        List<SysTemplate> list = testService.getPageInfo(pager,templateName,templateType);
        modelMap.addAttribute("page",pager);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "导出数据")
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public void export(HttpServletResponse response,@RequestParam(value = "name", required = false) String templateName) {
        Page pager = new Page();
        pager.setRecordsTotal(1000000l);
        pager.setDraw(1);
        pager.setStart(0l);
        pager.setLength(1000000l);
        pager.doPage();
        List<SysTemplate> list = testService.getPageInfo(pager,templateName,"");
        ResponseEntity<byte[]> responseE = null ;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            List<List<String>> datas = new ArrayList<>();
            List<String> dateTitle = new ArrayList<>();
            dateTitle.add("附件名称");
            dateTitle.add("附件文件名");
            dateTitle.add("最后更新时间");
            dateTitle.add("备注");
            datas.add(dateTitle);
            for(SysTemplate s : list){
                List<String> date = new ArrayList<>();
                date.add(s.getTemplateName());
                date.add(s.getTemplateFilename());
                date.add(sf.format(s.getUpdateTime()));
                date.add(s.getRemark());
                datas.add(date);
            }
            DownloadExcelUtil.exportDataByExcel("附件信息",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

