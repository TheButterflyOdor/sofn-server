package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.excel.ExportExcelUtil;
import com.sofn.model.generator.AdsCheckInfo;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.service.ads.AdsReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 *	监测报表 controller 控制器实现
 * Created by yangran
 */
@RestController
@Api(value = "监测报表", description = "监测报表")
@RequestMapping(value = "/adsReport",method = RequestMethod.POST)
public class AdsReportController extends BaseController {

    @Autowired
    private AdsReportService adsReportService;

    /**
     * 查询抽样环节报表数据
     * @return
     */
    @ApiOperation(value = "查询抽样环节报表数据")
    @RequestMapping(value = "/getSamplingLinkReportList",method = RequestMethod.POST)
    public Object getSamplingLinkReportList(String monitorTaskId) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            List<Map<String,Object>> list = adsReportService.getSamplingLinkReportList(queryParams);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询检测环节报表数据
     * @return
     */
    @ApiOperation(value = "查询检测环节报表数据")
    @RequestMapping(value = "/getCheckLinkReportList",method = RequestMethod.POST)
    public Object getCheckLinkReportList(String monitorTaskId) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            List<Map<String,Object>> list = adsReportService.getCheckLinkReportList(queryParams);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询检测对象报表分页数据")
    @RequestMapping(value = "/getCheckObjectReportPageInfo",method = RequestMethod.POST)
    public Object getCheckObjectReportPageInfo(String monitorTaskId,int start, int length) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            queryParams.put("pageNum",((start+1)/length)+1);
            queryParams.put("pageSize",length);
            PageInfo<Map<String,Object>> list = adsReportService.getCheckObjectReportPageInfo(queryParams);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询检测地区对象报表分页数据")
    @RequestMapping(value = "/getCheckAreaObjReportPageInfo",method = RequestMethod.POST)
    public Object getCheckAreaObjReportPageInfo(String monitorTaskId,int start, int length) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            queryParams.put("pageNum",((start+1)/length)+1);
            queryParams.put("pageSize",length);
            PageInfo<Map<String,Object>> list = adsReportService.getCheckAreaObjReportPageInfo(queryParams);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询抽样地区报表分页数据
     * @return
     */
    @ApiOperation(value = "查询抽样地区报表分页数据")
    @RequestMapping(value = "/getSamplingAreaReportPageInfo",method = RequestMethod.POST)
    public Object getSamplingAreaReportPageInfo(String monitorTaskId,int start, int length) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            PageInfo<Map<String,Object>> pageInfo = adsReportService.getSamplingAreaReportPageInfo(queryParams,((start+1)/length)+1,length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询检测地区报表分页数据
     * @return
     */
    @ApiOperation(value = "查询检测地区报表分页数据")
    @RequestMapping(value = "/getCheckAreaReportPageInfo",method = RequestMethod.POST)
    public Object getCheckAreaReportPageInfo(String monitorTaskId,int start, int length) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            PageInfo<Map<String,Object>> pageInfo = adsReportService.getCheckAreaReportPageInfo(queryParams,((start+1)/length)+1,length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询抽样机构报表分页数据
     * @return
     */
    @ApiOperation(value = "查询抽样机构报表分页数据")
    @RequestMapping(value = "/getSamplingOrganReportPageInfo",method = RequestMethod.POST)
    public Object getSamplingOrganReportPageInfo(String monitorTaskId,int start, int length) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            PageInfo<Map<String,Object>> pageInfo = adsReportService.getSamplingOrganReportPageInfo(queryParams,((start+1)/length)+1,length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询检测机构报表分页数据
     * @return
     */
    @ApiOperation(value = "查询检测机构报表分页数据")
    @RequestMapping(value = "/getCheckOrganReportPageInfo",method = RequestMethod.POST)
    public Object getCheckOrganReportPageInfo(String monitorTaskId,int start, int length) {
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            PageInfo<Map<String,Object>> pageInfo = adsReportService.getCheckOrganReportPageInfo(queryParams,((start+1)/length)+1,length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出抽样环节报表excel")
    @RequestMapping(value = "/exportSamplingLinkExcel",method = RequestMethod.POST)
    public void exportSamplingLinkExcel(String monitorTaskId,HttpServletResponse response){
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            List<Map<String,Object>> samplingLink = adsReportService.getSamplingLinkReportList(queryParams);
            if(samplingLink!=null && samplingLink.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"抽样环节","抽检数量","未上报数","进行中数","废标数","废标率","合格数","合格率"};
                String[] propertyNames = {"samplePlace","totalNum","noNum","inNum","returnNum","scrapRate","reportNum","passRate"};
                for(Map<String, Object> area : samplingLink){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("samplePlace",area.get("SAMPLE_PLACE"));
                    map.put("totalNum",area.get("TOTAL_NUM"));
                    map.put("noNum",area.get("NO_NUM"));
                    map.put("inNum",area.get("IN_NUM"));
                    map.put("returnNum",area.get("RETURN_NUM"));
                    map.put("scrapRate",area.get("SCRAP_RATE"));
                    map.put("reportNum",area.get("REPORT_NUM"));
                    map.put("passRate",area.get("PASS_RATE"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出抽样地区报表excel")
    @RequestMapping(value = "/exportSamplingAreaExcel",method = RequestMethod.POST)
    public void exportSamplingAreaExcel(String monitorTaskId,HttpServletResponse response){
        try{
            String type = "area";
            List<Map<String, Object>> samplingArea = adsReportService.getSamplingTypeListByCondition(monitorTaskId,type);
            if(samplingArea!=null && samplingArea.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"抽样地区","抽检数量","未上报数","进行中数","废标数","废标率","合格数","合格率"};
                String[] propertyNames = {"producingAreaName","totalNum","noNum","inNum","returnNum","scrapRate","reportNum","passRate"};
                for(Map<String, Object> area : samplingArea){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("producingAreaName",area.get("PRODUCING_AREA_NAME"));
                    map.put("totalNum",area.get("TOTAL_NUM"));
                    map.put("noNum",area.get("NO_NUM"));
                    map.put("inNum",area.get("IN_NUM"));
                    map.put("returnNum",area.get("RETURN_NUM"));
                    map.put("scrapRate",area.get("SCRAP_RATE"));
                    map.put("reportNum",area.get("REPORT_NUM"));
                    map.put("passRate",area.get("PASS_RATE"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出抽样机构报表excel")
    @RequestMapping(value = "/exportSamplingOrganExcel",method = RequestMethod.POST)
    public void exportSamplingOrganExcel(String monitorTaskId,HttpServletResponse response){
        try{
            String type = "organ";
            List<Map<String, Object>> samplingOrgan = adsReportService.getSamplingTypeListByCondition(monitorTaskId,type);
            if(samplingOrgan!=null && samplingOrgan.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"抽样机构","抽检数量","未上报数","进行中数","废标数","废标率","合格数","合格率"};
                String[] propertyNames = {"sampleDeparment","totalNum","noNum","inNum","returnNum","scrapRate","reportNum","passRate"};
                for(Map<String, Object> area : samplingOrgan){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("sampleDeparment",area.get("SAMPLE_DEPARMENT"));
                    map.put("totalNum",area.get("TOTAL_NUM"));
                    map.put("noNum",area.get("NO_NUM"));
                    map.put("inNum",area.get("IN_NUM"));
                    map.put("returnNum",area.get("RETURN_NUM"));
                    map.put("scrapRate",area.get("SCRAP_RATE"));
                    map.put("reportNum",area.get("REPORT_NUM"));
                    map.put("passRate",area.get("PASS_RATE"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出检测环节报表excel")
    @RequestMapping(value = "/exportCheckLinkExcel",method = RequestMethod.POST)
    public void exportCheckLinkExcel(String monitorTaskId,HttpServletResponse response){
        try{
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("monitorTaskId",monitorTaskId);
            List<Map<String,Object>> checkLink = adsReportService.getCheckLinkReportList(queryParams);
            if(checkLink!=null && checkLink.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"检测环节","检测数量","合格数","合格率"};
                String[] propertyNames = {"checkLing","detectionNum","qualifieNum","pass"};
                for(Map<String, Object> area : checkLink){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("checkLing",area.get("CHECK_LINK"));
                    map.put("detectionNum",area.get("DETECTION_NUM"));
                    map.put("qualifieNum",area.get("QUALIFIE_NUM"));
                    map.put("pass",area.get("PASS"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出检测地区报表excel")
    @RequestMapping(value = "/exportCheckAreaExcel",method = RequestMethod.POST)
    public void exportCheckAreaExcel(String monitorTaskId,HttpServletResponse response){
        try{
            String type = "area";
            List<Map<String, Object>> samplingArea = adsReportService.getCheckTypeListByCondition(monitorTaskId,type);
            if(samplingArea!=null && samplingArea.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"检测地区","检测数量","合格数","合格率"};
                String[] propertyNames = {"cityName","detectionNum","qualifieNum","pass"};
                for(Map<String, Object> area : samplingArea){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("cityName",area.get("CITY_NAME"));
                    map.put("detectionNum",area.get("DETECTION_NUM"));
                    map.put("qualifieNum",area.get("QUALIFIE_NUM"));
                    map.put("pass",area.get("PASS"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出检测机构报表excel")
    @RequestMapping(value = "/exportCheckOrganExcel",method = RequestMethod.POST)
    public void exportCheckOrganExcel(String monitorTaskId,HttpServletResponse response){
        try{
            String type = "organ";
            List<Map<String, Object>> samplingOrgan = adsReportService.getCheckTypeListByCondition(monitorTaskId,type);
            if(samplingOrgan!=null && samplingOrgan.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"检测机构","检测数量","合格数","合格率"};
                String[] propertyNames = {"detectionOrgan","detectionNum","qualifieNum","pass"};
                for(Map<String, Object> area : samplingOrgan){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("detectionOrgan",area.get("DETECTION_ORGAN"));
                    map.put("detectionNum",area.get("DETECTION_NUM"));
                    map.put("qualifieNum",area.get("QUALIFIE_NUM"));
                    map.put("pass",area.get("PASS"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出检测对象报表excel")
    @RequestMapping(value = "/exportCheckObjectExcel",method = RequestMethod.POST)
    public void exportCheckObjectExcel(String monitorTaskId,HttpServletResponse response){
        try{
            List<Map<String, Object>> samplingArea = adsReportService.getCheckObjectListByCondition(monitorTaskId);
            if(samplingArea!=null && samplingArea.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"检测对象","检测数量","合格数","合格率"};
                String[] propertyNames = {"sampleName","detectionNum","qualifieNum","pass"};
                for(Map<String, Object> area : samplingArea){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("sampleName",area.get("SAMPLE_NAME"));
                    map.put("detectionNum",area.get("DETECTION_NUM"));
                    map.put("qualifieNum",area.get("QUALIFIE_NUM"));
                    map.put("pass",area.get("PASS"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param monitorTaskId
     * @param response
     */
    @ApiOperation(value = "导出检测地区对象报表excel")
    @RequestMapping(value = "/exportCheckAreaObjExcel",method = RequestMethod.POST)
    public void exportCheckAreaObjExcel(String monitorTaskId,HttpServletResponse response){
        try{
            List<Map<String, Object>> samplingArea = adsReportService.getCheckAreaObjListByCondition(monitorTaskId);
            if(samplingArea!=null && samplingArea.size() > 0){
                List<Map> list = new ArrayList<>();
                String[] columns = {"区域名称","检测对象","检测数量","合格数","合格率"};
                String[] propertyNames = {"cityName","sampleName","detectionNum","qualifieNum","pass"};
                for(Map<String, Object> area : samplingArea){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("cityName",area.get("CITY_NAME"));
                    map.put("sampleName",area.get("SAMPLE_NAME"));
                    map.put("detectionNum",area.get("DETECTION_NUM"));
                    map.put("qualifieNum",area.get("QUALIFIE_NUM"));
                    map.put("pass",area.get("PASS"));
                    list.add(map);
                }
                //导出excel
                ExportExcelUtil.exportView(response,list,columns,propertyNames);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
