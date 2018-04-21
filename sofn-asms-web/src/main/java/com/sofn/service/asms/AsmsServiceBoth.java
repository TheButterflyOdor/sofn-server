package com.sofn.service.asms;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiBing
 */
class AsmsServiceBoth {

    /**
     * 根据所属机构行政区划拼接过滤参数
     */
    static Map<String, Object> buidRegParm(String orgLevel, String regionId) {
        Map<String, Object> map = new HashMap<>();
        switch (orgLevel) {
            case "2":
                map.put("regParm", regionId.substring(0, 2) + "%");
                break;
            case "3":
                map.put("regParm", regionId.substring(0, 4) + "%");
                break;
            case "4":
                map.put("regParm", regionId + "%");
                break;
            case "1":
                map.put("regParm", null);
                break;
        }
        return map;
    }

    /**
     * 分页参数
     */
    static Map<String, Object> buidPage(int start, int length) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", (start / length + 1));
        params.put("pageSize", length);
        return params;
    }

    /**
     * 任务列表过滤参数
     * */
    static Map<String, Object> listFilter(String theUserOrgId){
        Map<String, Object> params = new HashMap<>();
        params.put("theUserOrgId",theUserOrgId);
        return params;
    }
}
