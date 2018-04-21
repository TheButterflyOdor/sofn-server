package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.excel.ExportExcelUtil;
import com.sofn.model.qry.CheckProject;
import com.sofn.service.qry.CheckProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
@RestController
@Api(value = "检测项目", description = "检测项目")
@RequestMapping(value = "/checkProject", method = RequestMethod.POST)
public class CheckProjectController extends BaseController {
    @Autowired
    private CheckProjectService checkProjectService;

    /**
     * 根据条件获取监测任务列表
     *
     * @param checkProject
     * @param start        起始位置
     * @param length       分页个数
     * @return
     */
    @ApiOperation(value = "获取监测项目信息列表")
    @RequestMapping(value = "/getPageInfoParams")
    @SystemControllerLog(description = "获取监测项目信息列表", operationType = "查询")
    @Authorization
    public Object getPageInfoParams(CheckProject checkProject, int start, int length, String token) {
        try {
            Assert.isNotBlank(token, "token");
            String regId=null;//区域编号
            String orgId=null;//机构id
//            String orgLevel=null;//机构级别
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if (ut != null) {
                if ("ADS".equals(ut.getOrgType())) {
                    orgId = ut.getOrgId();
                } else if ("ASMS".equals(ut.getOrgType())) {
                    if ("1".equals(ut.getOrgLevel())) {
                    } else if ("2".equals(ut.getOrgLevel())) {
                        regId = ut.getOrgId().substring(0, 2);
                    } else if ("3".equals(ut.getOrgLevel())) {
                        regId = ut.getOrgId().substring(0, 4);
                    } else if ("4".equals(ut.getOrgLevel())) {
                        regId = ut.getOrgId().substring(0, 6);
                    }
                }
                PageInfo<CheckProject> pageInfo = checkProjectService.getPageInfoParams(checkProject, ((start + 1) / length) + 1, length, regId, orgId);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据受检单位获取监测项目信息")
    @RequestMapping(value = "/getTestedDeaparment")
    @SystemControllerLog(description = "根据受检单位获取监测项目信息", operationType = "查询")
    @Authorization
    public Object getTestedDeaparment(CheckProject checkProject,String token, int start, int length) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                PageInfo<CheckProject> pageInfo = checkProjectService.getTestedDeaparment(checkProject, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取监测任务列表
     *
     * @param checkProject
     * @param start        起始位置
     * @param length       分页个数
     * @return
     */
    @ApiOperation(value = "获取项目名称信息列表")
    @RequestMapping(value = "/getCheckInfo")
    @SystemControllerLog(description = "获取项目名称信息列表", operationType = "查询")
    public Object getCheckInfo(CheckProject checkProject, int start, int length ) {
        try {
//            String regId=null;//区域编号
//            String orgId=null;//机构id
////            String orgLevel=null;//机构级别
//            CurrentUser ut = WebUtil.getCurrentUser(token);
//            if ("ADS".equals(ut.getOrgType())) {
//                orgId = ut.getOrgId();
//            } else if ("ASMS".equals(ut.getOrgType())) {
//                if ("1".equals(ut.getOrgLevel())) {
//                } else if ("2".equals(ut.getOrgLevel())) {
//                    regId = ut.getOrgId().substring(0, 2);
//                } else if ("3".equals(ut.getOrgLevel())) {
//                    regId = ut.getOrgId().substring(0, 4);
//                } else if ("4".equals(ut.getOrgLevel())) {
//                    regId = ut.getOrgId().substring(0, 6);
//                }
//            }
            PageInfo<CheckProject> pageInfo = checkProjectService.getCheckInfo(checkProject, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取行业列表
     *
     * @param checkProject
     * @return
     */
    @ApiOperation(value = "获取行业列表")
    @RequestMapping(value = "/getHangye", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取行业列表", operationType = "查詢")

    public Object getHangye(CheckProject checkProject) {
        try {
//            String orgId = null;
//            CurrentUser ut = WebUtil.getCurrentUser(token);
//            if (ut != null) {
//                orgId = ut.getOrgId();
//            }
            List<Map<String, Object>> checkModel = checkProjectService.getHangye(checkProject);
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取行业列表
     *
     * @param checkProject
     * @return
     */
    @ApiOperation(value = "获取产品名称列表")
    @RequestMapping(value = "/getSampleName", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品名称列表", operationType = "查詢")

    public Object getSampleName(CheckProject checkProject, int start,int length) {
        try {
            PageInfo<CheckProject> pageInfo = checkProjectService.getSampleName(checkProject, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 通过主键获取对象
     */
    @ApiOperation(value = "获取检测项目详情")
    @RequestMapping(value = "/getCkeckById", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测项目详情", operationType = "查詢")
    public Object getCkeckById(String id) {

        try {
//            String orgId = null;
//            CurrentUser ut = WebUtil.getCurrentUser(token);
//            if (ut != null) {
//                orgId = ut.getOrgId();
//            }
            List<Map<String, Object>> checkProject = checkProjectService.getCkeckById(id);
            return setSuccessModelMap(new ModelMap(), checkProject);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }


    }


    /**
     * 获取检测项目类型条数
     */
    @ApiOperation(value = "获取检测项目类型条数")
    @RequestMapping(value = "/getTypeCount", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取检测项目类型条数", operationType = "查詢")
    public Object getTypeCount(CheckProject checkProject) {

        try {
//            String orgId = null;
//            CurrentUser ut = WebUtil.getCurrentUser(token);
//            if (ut != null) {
//                orgId = ut.getOrgId();
//            }
            long count = checkProjectService.getTypeCount(checkProject);
            return setSuccessModelMap(new ModelMap(), count);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }


    }


    /**
     * 导出检测项目excel
     *
     * @param
     * @param response
     */
    @ApiOperation(value = "导出检测项目excel")
    @RequestMapping(value = "/getCheckList", method = RequestMethod.POST)
    @SystemControllerLog(description = "导出检测项目excel", operationType = "查詢")
    public void getCheckList(HttpServletResponse response, String token) {
        try {
            String regId=null;//区域编号
            String orgId=null;//机构id
//            String orgLevel=null;//机构级别
            CurrentUser ut = WebUtil.getCurrentUser(token);
            if ("ADS".equals(ut.getOrgType())) {
                orgId = ut.getOrgId();
            } else if ("ASMS".equals(ut.getOrgType())) {
                if ("1".equals(ut.getOrgLevel())) {
                } else if ("2".equals(ut.getOrgLevel())) {
                    regId = ut.getOrgId().substring(0, 2);
                } else if ("3".equals(ut.getOrgLevel())) {
                    regId = ut.getOrgId().substring(0, 4);
                } else if ("4".equals(ut.getOrgLevel())) {
                    regId = ut.getOrgId().substring(0, 6);
                }
            }
            List<CheckProject> checkProjectList = checkProjectService.getCheckList(regId,orgId);
            if (checkProjectList != null && checkProjectList.size() > 0) {
                List<Map> list = new ArrayList<>();
                String[] columns = {"任务年度", "产品名称", "样品编码", "检测项目", "检测结果"};
                String[] propertyNames = {"year", "sampleName", "sampleCode",  "checkProject", "result"};
                for (CheckProject project : checkProjectList) {
                    HashMap<String, Object> map = new HashMap<>();

                    map.put("year", project.getYear());
                    map.put("sampleName", project.getSampleName());
                    map.put("sampleCode", project.getSampleCode());
                    map.put("checkProject", project.getCheckProject());

                    if (("1").equals(project.getResult())) {
                        project.setResult("合格");
                        map.put("result", project.getResult());
                    } else if (("0").equals(project.getResult())) {
                        project.setResult("不合格");
                        map.put("result", project.getResult());
                    } else if (("-1").equals(project.getResult())) {
                        project.setResult("暂未检测");
                        map.put("result", project.getResult());
                    }

                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response, list, columns, propertyNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
