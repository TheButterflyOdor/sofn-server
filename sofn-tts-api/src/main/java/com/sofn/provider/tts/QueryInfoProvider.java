package com.sofn.provider.tts;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public interface QueryInfoProvider {
    /**
     * 根据用户类型和传入的码查询相关信息
     * @param userType
     * @param code
     * @return List集合
     */
    List<Map<String,Object>> queryInfoByCode(String userType, String code);
}
