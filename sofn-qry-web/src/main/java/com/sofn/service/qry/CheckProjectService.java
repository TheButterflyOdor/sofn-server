package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.qry.CheckProject;
import com.sofn.provider.qry.CheckProjectProvider;
import com.sofn.provider.qry.MonitorTaskProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
@Service
public class CheckProjectService extends BaseService {
    private CheckProjectProvider provider;

    @DubboReference
    public void setProvider(CheckProjectProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfoParams(CheckProject bean, int pageNum, int pageSize, String regId, String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("industry", bean.getIndustry());
        queryParams.put("year", bean.getYear());
        queryParams.put("reportTime", bean.getReportTime());
        queryParams.put("startTime", bean.getStartTime());
        queryParams.put("endTime", bean.getEndTime());
        String c = bean.getCheckProject();
        if ("" != c && null != c) {
            String d[] = c.split(","); //把这个字符串按"，" 分隔开存入String类型数组d中。
            List<String> list = new ArrayList<String>(); //创建一个集合
            //便利字符串数组，把值放入list集合中
            for (int i = 0; i < d.length; i++) {
                list.add(d[i]);
            }
            queryParams.put("checkProjects", list);
        } else {
            queryParams.put("checkProjects", null);
        }
//        queryParams.put("checkProject", bean.getCheckProject());
        queryParams.put("result", bean.getResult());
        queryParams.put("cityCode", bean.getCityCode());
        String s = bean.getName();
        if ("" != s && null != s) {
            String d[] = s.split(","); //把这个字符串按"，" 分隔开存入String类型数组d中。
            List<String> list = new ArrayList<String>(); //创建一个集合
            //便利字符串数组，把值放入list集合中
            for (int i = 0; i < d.length; i++) {
                list.add(d[i]);
            }
            queryParams.put("names", list);
        } else {
            queryParams.put("names", null);
        }
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("regionId", regId);
        queryParams.put("orgId", orgId);
//        queryParams.put("orgLevel", orgLevel);
        return provider.getPageInfoParams(queryParams);
    }
    public PageInfo getTestedDeaparment(CheckProject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass", bean.getMonitorClass());
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("testedDeparment", bean.getTestedDeparment());
        queryParams.put("sampleCode", bean.getSampleCode());
        queryParams.put("result", bean.getResult());
        queryParams.put("sampleName", bean.getSampleName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getTestedDeaparment(queryParams);
    }

    public PageInfo getCheckInfo(CheckProject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkProject", bean.getCheckProject());
//        queryParams.put("productName", bean.getProductName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
//         queryParams.put("orgId", orgId);
//        queryParams.put("regionId", regId);
//        queryParams.put("orgLevel", orgLevel);
        return provider.getCheckInfo(queryParams);
    }

    public List<Map<String, Object>> getHangye(CheckProject bean) {
        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("organId",orgId);
        return provider.getHangye(queryParams);
    }

    public PageInfo getSampleName(CheckProject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("name", bean.getName());
        return provider.getSampleName(queryParams);
    }


    public List<Map<String, Object>> getCkeckById(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
//        queryParams.put("organId", orgId);
        List<Map<String, Object>> checkProject = provider.getCkeckById(queryParams);
        return checkProject;
    }


    public List<CheckProject> getCheckList(String regId, String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("regionId", regId);
//        queryParams.put("orgLevel", orgLevel);
        queryParams.put("orgId", orgId);
        return provider.getCheckList(queryParams);
    }

    public long getTypeCount(CheckProject bean) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("productName", bean.getProductName());
//        queryParams.put("organId", orgId);
        return provider.getTypeCount(queryParams);
    }

//    public  String  getUserParams(String token){
//        CurrentUser ut = WebUtil.getCurrentUser(token);
//        if ("ADS".equals(ut.getOrgType())) {
//         String  orgId = ut.getOrgId();
//            return  orgId;
//        } else if ("ASMS".equals(ut.getOrgType())) {
//            if ("1".equals(ut.getOrgLevel())) {
//                String  regId = "";
//                return regId;
//            } else if ("2".equals(ut.getOrgLevel())) {
//               String regId = ut.getOrgId().substring(0, 2);
//                return regId;
//            } else if ("3".equals(ut.getOrgLevel())) {
//
//               String regId = ut.getOrgId().substring(0, 4);
//                return regId;
//            } else if ("4".equals(ut.getOrgLevel())) {
//              String  regId = ut.getOrgId().substring(0, 6);
//                return regId;
//            }
//        }
//         return "";
//    }
}
