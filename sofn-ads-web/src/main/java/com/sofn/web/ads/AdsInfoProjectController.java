package com.sofn.web.ads;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.ExcelReadUtil;
import com.sofn.core.util.excel.ExportExcelUtil;
import com.sofn.core.util.excel.ReadExcel;
import com.sofn.model.generator.*;
import com.sofn.service.ads.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

/**
*	检测信息_检测项目 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "检测信息_检测项目", description = "检测信息_检测项目")
@RequestMapping(value = "/adsInfoProject",method = RequestMethod.POST)
public class AdsInfoProjectController extends BaseController {

    @Autowired
    private AdsInfoProjectService adsInfoProjectService;

    @Autowired
    private AdsCheckInfoService adsCheckInfoService;

    @Autowired
    private AdsNewModelService adsNewModelService;

    @Autowired
    private AdsOrganTaskService adsOrganTaskService;

    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;

    /**
     * 根据条件获取检测信息_检测项目列表
     *
     * @param adsInfoProject
     * @return
     */
    @ApiOperation(value = "获取检测信息_检测项目信息列表")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsInfoProject adsInfoProject, int start, int length) {
        try {
            PageInfo<AdsInfoProject> pageInfo = adsInfoProjectService.getPageInfo(adsInfoProject, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增检测信息_检测项目记录
     *
     * @param adsInfoProject
     * @return
     */
    @ApiOperation(value = "新增检测信息_检测项目数据")
    @RequestMapping(value = "/addAdsInfoProject", method = RequestMethod.POST)
    public Object addAdsInfoProject(@RequestBody AdsInfoProject adsInfoProject) {
        try {
            adsInfoProjectService.add(adsInfoProject);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsInfoProjectController.addAdsInfoProject:新增检测信息_检测项目记录异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID获取单条检测信息_检测项目数据信息
     *
     * @param adsInfoProject
     * @return
     */
    @ApiOperation(value = "获取单条检测信息_检测项目数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public Map<String, Object> queryById(@RequestBody AdsInfoProject adsInfoProject) {
        adsInfoProject = adsInfoProjectService.queryById(adsInfoProject.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsInfoProject", adsInfoProject);
        return map;
    }

    /**
     * 修改检测信息_检测项目数据信息
     *
     * @param adsInfoProject
     * @return
     */
    @ApiOperation(value = "修改检测信息_检测项目数据信息")
    @RequestMapping(value = "/updateAdsInfoProject", method = RequestMethod.POST)
    public Object updateAdsInfoProject(@RequestBody AdsInfoProject adsInfoProject) {
        try {
            adsInfoProjectService.update(adsInfoProject);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsInfoProjectController.updateAdsInfoProject:修改检测信息_检测项目数据信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除检测信息_检测项目信息
     *
     * @param adsInfoProject
     * @return
     */
    @ApiOperation(value = " 删除检测信息_检测项目信息")
    @RequestMapping(value = "/deleteAdsInfoProject", method = RequestMethod.POST)
    public Object deleteAdsInfoProject(AdsInfoProject adsInfoProject) {
        try {
            adsInfoProjectService.delete(adsInfoProject.getId());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsInfoProjectController.deleteAdsInfoProject:删除检测信息_检测项目信息异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改检测值和结果
     *
     * @param jsonArray,checkInfoId,result
     * @return
     */
    @ApiOperation(value = "修改检测信息_检测项目数据信息")
    @RequestMapping(value = "/updateResult", method = RequestMethod.POST)
    public Object updateResult(String jsonArray, String checkInfoId, String result,String token) {
        try {
            //修改必填项的检测值
            JSONArray array = JSONArray.parseArray(jsonArray);
            for (Object object : array) {
                JSONObject jsonObject = JSONObject.parseObject(object.toString());
                adsInfoProjectService.updateResult(jsonObject.get("id").toString(), jsonObject.get("checkNum").toString(), jsonObject.get("result").toString(),jsonObject.get("LOD").toString(),jsonObject.get("LOO").toString());
            }

            //设置非必填项
//            JSONArray no = JSONArray.parseArray(notObj);
//            for(Object object : no){
//                JSONObject jsonObject = JSONObject.parseObject(object.toString());
//                adsInfoProjectService.updateNo(jsonObject.get("id").toString());
//            }

            //设置检测信息判定结果
            adsCheckInfoService.updateResult(checkInfoId, result);

            //同步检测结果给其他系统
            AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryByMyId(checkInfoId);

            adsInfoProjectService.setInfo(adsCheckInfo.getMonitorSampleId(),result);

            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsInfoProjectController.updateResult:修改检测值和结果异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = " 导出待检测信息")
    @RequestMapping(value = "/downloadInfoProject", method = RequestMethod.POST)
    public void downloadInfoProject(String ids, HttpServletResponse response) {
        try {
            if(null != ids && !"".equals(ids)){
                String realIds = ids.substring(0,ids.length()-1);
                String[] a = realIds.split(",");

                List<Map> list = new ArrayList<>();
                String[] columns = {"产品编码", "样品编码", "产品名称", "检测项", "检测值", "上限值", "单位", "LOD","LOQ"};
                String[] propertyNames = {"productTraceabilityCode", "sampleCode", "sampleName", "testedDeparment", "cityCode", "producingAreaName", "sampleDate", "lod","loq"};
                for(String id : a){
                    AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
                    adsCheckInfo = adsCheckInfoService.queryById(id);

                    List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                    if (0 == myList.size()) {

                        String sampleName = adsCheckInfo.getSampleName();//样品名称

                        //获取机构任务
                        AdsOrganTask adsOrganTask = adsOrganTaskService.queryById(adsCheckInfo.getOrganTaskId());
                        //获取监测任务
                        AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryByMyId(adsOrganTask.getMonitorTaskId());

                        //通过模型ID和样品名称去获取所有的检测项目
                        List<AdsCheckModelMapping> adsCheckModelMappings = adsNewModelService.queryByModelIdAndName(adsMonitorTask.getCheckModel(),sampleName);

                        for (AdsCheckModelMapping adsCheckModelMapping : adsCheckModelMappings) {
                            Timestamp timestamp = new Timestamp(new Date().getTime());
                            AdsInfoProject adsInfoProject = new AdsInfoProject();
                            adsInfoProject.setCheckProject(adsCheckModelMapping.getCheckName());
                            adsInfoProject.setCheckNum(" ");
                            adsInfoProject.setJudgeNum(String.valueOf(adsCheckModelMapping.getMaxValue()));
                            adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
                            adsInfoProject.setCheckInfoId(id);
                            adsInfoProject.setCreateBy("qq");
                            adsInfoProject.setCreateTime(timestamp);
                            adsInfoProject.setUpdateBy("qq");
                            adsInfoProject.setUpdateTime(timestamp);
                            adsInfoProject.setUnit(adsCheckModelMapping.getUnitMeasure());
                            adsInfoProjectService.add(adsInfoProject);
                        }
                    }

                    List<AdsInfoProject> oneList = new ArrayList<AdsInfoProject>();
                    oneList = adsInfoProjectService.queryListByInfoId(id);
                    for (int i = 0; i < oneList.size(); i++) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("productTraceabilityCode", adsCheckInfo.getProductTraceabilityCode());
                        map.put("sampleCode", adsCheckInfo.getSampleCode());
                        map.put("sampleName", adsCheckInfo.getSampleName());
                        map.put("testedDeparment", oneList.get(i).getCheckProject());
                        map.put("cityCode", oneList.get(i).getCheckNum());
                        map.put("producingAreaName", oneList.get(i).getJudgeNum());
                        map.put("sampleDate", "mg/kg");
                        map.put("lod", oneList.get(i).getLOD());
                        map.put("loo", oneList.get(i).getLOO());
                        list.add(map);
                    }
                }
                //导出excel
                ExportExcelUtil.exportView(response, list, columns, propertyNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @ApiOperation(value = " 导出待检测信息")
    @RequestMapping(value = "/downloadDemo", method = RequestMethod.POST)
    public void downloadDemo(String ids, HttpServletResponse response) {
        try{
            List<Map> list = new ArrayList<>();
            String[] columns = { "产品编码", "样品编码", "产品名称", "检测项", "检测值", "上限值", "单位","LOD","LOO"};
            String[] propertyNames = { "productCode", "sampleCode", "productName", "test", "testValue", "upperValue", "unit","lod","loo"};
            //导出excel
            ExportExcelUtil.exportView(response, list, columns, propertyNames);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation(value = " 导出待检测信息")
    @RequestMapping(value = "/downloadInfoProjectForB", method = RequestMethod.POST)
    public void downloadInfoProjectForB(String ids, HttpServletResponse response) {
        try {
            if(null != ids && !"".equals(ids)){
                String realIds = ids.substring(0,ids.length()-1);
                String[] a = realIds.split(",");

                List<Map> list = new ArrayList<>();
                String[] columns = {"产品编码", "样品编码", "产品名称", "检测项", "检测值", "上限值", "单位", "LOD","LOO"};
                String[] propertyNames = {"productTraceabilityCode", "sampleCode", "sampleName", "testedDeparment", "cityCode", "producingAreaName", "sampleDate", "lod","loo"};
                for(String id : a){
                    AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
                    adsCheckInfo = adsCheckInfoService.queryById(id);

                    List<AdsInfoProject> myList = adsInfoProjectService.getPageInfoWithCheckInfoId(id);

                    String sampleCode = adsCheckInfo.getSampleCode();

                    if (0 == myList.size()) {
                        //不存在检测项目时
                        //根据样品编码和ID去新增检测项目
                        adsCheckInfoService.inzertInfoProject(id,sampleCode);

                        List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                    }else{
                        //存在检测项目时，更新上限值
                        adsInfoProjectService.updateInfoProject(myList,sampleCode);

                        List<AdsInfoProject> myList2 = adsInfoProjectService.getPageInfoWithCheckInfoId(id);
                    }

                    List<AdsInfoProject> oneList = new ArrayList<AdsInfoProject>();
                    oneList = adsInfoProjectService.queryListByInfoId(id);
                    for (int i = 0; i < oneList.size(); i++) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("productTraceabilityCode", adsCheckInfo.getProductTraceabilityCode());
                        map.put("sampleCode", adsCheckInfo.getSampleCode());
                        map.put("sampleName", adsCheckInfo.getSampleName());
                        map.put("testedDeparment", oneList.get(i).getCheckProject());
                        map.put("cityCode", oneList.get(i).getCheckNum());
                        map.put("producingAreaName", oneList.get(i).getJudgeNum());
                        map.put("sampleDate", "mg/kg");
                        map.put("lod", oneList.get(i).getLOD());
                        map.put("loo", oneList.get(i).getLOO());
                        list.add(map);
                    }
                }
                //导出excel
                ExportExcelUtil.exportView(response, list, columns, propertyNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 上传文件
     *
     * @param
     * @return
     */
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public Object saveInfo(String organTaskId,HttpServletRequest request, HttpServletResponse response) {
        try {
//            String address = this.uploadFile(request);
//            String kk = "http://" + address;

//            File f = new File("d:\\1.xls");
//            f.createNewFile();

//            String file = downloadFile(kk, f.getPath());

            List<String> list = new ArrayList<String>();

            //将当前上下文初始化
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                // 根据 name 获取上传的文件...
                MultipartFile file = multipartRequest.getFile("file");
                InputStream input = file.getInputStream();
                Workbook workbook = null;
                workbook = new HSSFWorkbook(input);

//                CommonsMultipartFile cf = (CommonsMultipartFile) file;
//                DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//                File file1 = fi.getStoreLocation();
//                File file1 = workbook;
                System.out.println(workbook);

                List<Object> list2 = null;
                String firstCell="";
                //默认获取第一个sheet表的数据
                Sheet sheet = workbook.getSheetAt(0);
                //默认字段为10列
                int cellSize = 10;
                //总列数
                int cellCount =0;
                if(sheet.getRow(0)!=null) {
                    cellCount =sheet.getRow(0).getLastCellNum();
                    firstCell = ExcelReadUtil.getCellValue(sheet.getRow(0).getCell(0));
                }
                if(cellCount!=cellSize){
                    throw new Exception("表格列数不正确！默认为10列,");
                }
                //校验表格数据
                if (firstCell.equals("序号")) {
                    int n=0;
                    //总行数
                    int rsRows = sheet.getRow(0) == null ? -1 : sheet.getLastRowNum();
                    // 循环行,跳过第一行
                    for (int i = 1; i <= rsRows; i++) {
                        Row row = sheet.getRow(i);
                        if (row != null) {
                            list2 = new ArrayList<Object>();
                            //循环列，跳过第一列
                            try {
                                for (int j = 1; j < cellCount; j++) {// 读取单元格
                                    list2.add(ExcelReadUtil.getCellValue(row.getCell(j)));
                                }

                                //保存信息
                                Object objectB = list2.get(0);
                                Object objectC = list2.get(1);
                                Object objectD = list2.get(2);
                                Object objectE = list2.get(3);
                                Object objectF = list2.get(4);
                                Object objectG = list2.get(5);
                                Object objectH = list2.get(6);
                                Object objectI = list2.get(7);
                                Object objectJ = list2.get(8);
                                try {
                                    if (null == objectB || null == objectC || null == objectD || null == objectE || null == objectF || null == objectG || null == objectH) {
                                        throw new IOException();
                                    }
                                } catch (IOException e) {
                                    ModelMap modelMap = new ModelMap();
                                    modelMap.put("httpCode", "999");
                                    modelMap.put("msg", "导入失败，不能含有未填写的项！");
                                    return modelMap;
                                }
                                try {
                                    if ("".equals(objectB.toString()) || "".equals(objectC.toString()) || "".equals(objectD.toString()) || "".equals(objectE.toString()) || "".equals(objectF.toString()) || "".equals(objectG.toString()) || "".equals(objectH.toString())) {
                                        throw new ClassCastException();
                                    }
                                } catch (ClassCastException e) {
                                    ModelMap modelMap = new ModelMap();
                                    modelMap.put("httpCode", "999");
                                    modelMap.put("msg", "导入失败，不能含有未填写的项！");
                                    return modelMap;
                                }
                                String SAMPLE_CODE = objectC.toString();
                                AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryBySampleCodeAndOrganId(SAMPLE_CODE, organTaskId);
                                if(null == adsCheckInfo){
                                    continue;
                                }
                                if ("1".equals(adsCheckInfo.getCheckReport())) {
                                    continue;
                                }
                                try {
                                    if (null == adsCheckInfo) {
                                        throw new IOException();
                                    }
                                } catch (IOException e) {
                                    ModelMap modelMap = new ModelMap();
                                    modelMap.put("httpCode", "999");
                                    modelMap.put("msg", "无效的样品编码！" + SAMPLE_CODE);
                                    return modelMap;
                                }
                                String checkInfoId = adsCheckInfo.getId();
                                if (!list.contains(checkInfoId)) {
                                    list.add(checkInfoId);
                                }
                                //检测项目
                                String CHECK_PROJECT = objectE.toString();
                                AdsInfoProject adsInfoProject = adsInfoProjectService.queryByProjectAndInfoId(checkInfoId, CHECK_PROJECT);
                                try {
                                    if (null == adsInfoProject) {
                                        throw new IOException();
                                    }
                                } catch (IOException e) {
                                    ModelMap modelMap = new ModelMap();
                                    modelMap.put("httpCode", "999");
                                    modelMap.put("msg", "无效的检测项目！" + CHECK_PROJECT);
                                    return modelMap;
                                }
                                //检测项目ID
                                String projectId = adsInfoProject.getId();
                                //检测项目判定值
                                Double JUDGE_NUM = Double.parseDouble(adsInfoProject.getJudgeNum());
                                Double getNum = 0.0;
                                if ("ND".equals(objectF.toString())) {

                                } else {
                                    getNum = Double.parseDouble(objectF.toString());
                                }
                                String RESULT = "1";
                                if (getNum <= JUDGE_NUM) {
                                } else {
                                    RESULT = "0";
                                }

                                String LOD = objectI.toString();
                                String LOO = objectJ.toString();

                                //修改检测项目
                                adsInfoProjectService.updateResult(projectId, getNum.toString(), RESULT, LOD, LOO);

                                n++;
                            }catch (Exception e){
                                if(i==rsRows && n==0) {
                                    throw e;
                                } else {
                                    continue;
                                }
                            }
                        }
                    }

                    //修改样品信息检测结果
                    for (String id : list) {
                        String result = "1";
                        //根据样品ID查询所有检测项目对象
                        List<AdsInfoProject> adsInfoProjects = adsInfoProjectService.queryByInfoId(id);
                        for (AdsInfoProject adsInfoProject : adsInfoProjects) {
                            if ("0".equals(adsInfoProject.getResult())) {
                                result = "0";
                            }
                        }
                        adsCheckInfoService.updateResult(id, result);
                    }

                }else {
                    throw new Exception("表格第一行第一列数据不正确！默认为“序号”,");
                }




//                List<Map<String, Object>> lists = ReadExcel.exportListFromExcel(file1, 0);
//
//                //样品集合
//
//
//                int i = 2;
//                for (Map<String, Object> map : lists) {
//                    Object objectA = map.get("A" + i);
//                    Object objectB = map.get("B" + i);
//                    Object objectC = map.get("C" + i);
//                    Object objectD = map.get("D" + i);
//                    Object objectE = map.get("E" + i);
//                    Object objectF = map.get("F" + i);
//                    Object objectG = map.get("G" + i);
//                    Object objectH = map.get("H" + i);
//                    Object objectI = map.get("I" + i);
//                    Object objectJ = map.get("J" + i);
//                    i++;
//                    try {
//                        if (null == objectA || null == objectB || null == objectC || null == objectD || null == objectE || null == objectF || null == objectG || null == objectH) {
//                            throw new IOException();
//                        }
//                    } catch (IOException e) {
//                        ModelMap modelMap = new ModelMap();
//                        modelMap.put("httpCode", "999");
//                        modelMap.put("msg", "导入失败，不能含有未填写的项！");
//                        return modelMap;
//                    }
//                    try {
//                        if ("".equals(objectA.toString()) || "".equals(objectB.toString()) || "".equals(objectC.toString()) || "".equals(objectD.toString()) || "".equals(objectE.toString()) || "".equals(objectF.toString()) || "".equals(objectG.toString()) || "".equals(objectH.toString())) {
//                            throw new ClassCastException();
//                        }
//                    } catch (ClassCastException e) {
//                        ModelMap modelMap = new ModelMap();
//                        modelMap.put("httpCode", "999");
//                        modelMap.put("msg", "导入失败，不能含有未填写的项！");
//                        return modelMap;
//                    }
//                    String SAMPLE_CODE = objectC.toString();
//                    AdsCheckInfo adsCheckInfo = adsCheckInfoService.queryBySampleCodeAndOrganId(SAMPLE_CODE, organTaskId);
//                    if ("1".equals(adsCheckInfo.getCheckReport())) {
//                        continue;
//                    }
//                    try {
//                        if (null == adsCheckInfo) {
//                            throw new IOException();
//                        }
//                    } catch (IOException e) {
//                        ModelMap modelMap = new ModelMap();
//                        modelMap.put("httpCode", "999");
//                        modelMap.put("msg", "无效的样品编码！" + SAMPLE_CODE);
//                        return modelMap;
//                    }
//                    String checkInfoId = adsCheckInfo.getId();
//                    if (!list.contains(checkInfoId)) {
//                        list.add(checkInfoId);
//                    }
//                    //检测项目
//                    String CHECK_PROJECT = objectE.toString();
//                    AdsInfoProject adsInfoProject = adsInfoProjectService.queryByProjectAndInfoId(checkInfoId, CHECK_PROJECT);
//                    try {
//                        if (null == adsInfoProject) {
//                            throw new IOException();
//                        }
//                    } catch (IOException e) {
//                        ModelMap modelMap = new ModelMap();
//                        modelMap.put("httpCode", "999");
//                        modelMap.put("msg", "无效的检测项目！" + CHECK_PROJECT);
//                        return modelMap;
//                    }
//                    //检测项目ID
//                    String projectId = adsInfoProject.getId();
//                    //检测项目判定值
//                    Double JUDGE_NUM = Double.parseDouble(adsInfoProject.getJudgeNum());
//                    Double getNum = 0.0;
//                    if ("ND".equals(objectF.toString())) {
//
//                    } else {
//                        getNum = Double.parseDouble(objectF.toString());
//                    }
//                    String RESULT = "1";
//                    if (getNum <= JUDGE_NUM) {
//                    } else {
//                        RESULT = "0";
//                    }
//
//                    String LOD = objectI.toString();
//                    String LOO = objectJ.toString();
//
//                    //修改检测项目
//                    adsInfoProjectService.updateResult(projectId, getNum.toString(), RESULT, LOD, LOO);
//                }
//
//                //修改样品信息检测结果
//                for (String id : list) {
//                    String result = "1";
//                    //根据样品ID查询所有检测项目对象
//                    List<AdsInfoProject> adsInfoProjects = adsInfoProjectService.queryByInfoId(id);
//                    for (AdsInfoProject adsInfoProject : adsInfoProjects) {
//                        if ("0".equals(adsInfoProject.getResult())) {
//                            result = "0";
//                        }
//                    }
//                    adsCheckInfoService.updateResult(id, result);
//                }
            }
            return setSuccessModelMap(new ModelMap(),list);
        } catch (Exception e) {
            logger.error("AdsInfoProjectController.saveInfo:上传文件异常",e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 上传文件
     *
     * @param
     * @return
     */
//    @ApiOperation(value = "上传文件")
//    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
//    public Object saveInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("image") File file) {
//        try {
//            List<Map<String, Object>> lists = ReadExcel.exportListFromExcel(file, 0);
//            System.out.println(lists.size());
//            return setSuccessModelMap(new ModelMap());
//        } catch (Exception e) {
//            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
//        }
//    }

    public String downloadFile(String remoteFilePath, String localFilePath) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        System.out.println(f.exists());
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f.getPath();
    }

}

