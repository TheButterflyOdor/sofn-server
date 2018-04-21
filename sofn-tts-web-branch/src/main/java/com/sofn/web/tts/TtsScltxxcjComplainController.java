package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.TtsScltxxcjComplain;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.SysDictionaryService;
import com.sofn.service.tts.TtsScltxxcjComplainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	投诉举报 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "投诉举报", description = "投诉举报")
@RequestMapping(value = "/ttsScltxxcjComplain",method = RequestMethod.POST)
public class TtsScltxxcjComplainController extends BaseController {

    @Autowired
    private TtsScltxxcjComplainService ttsScltxxcjComplainService;
    @Autowired
    public SSOLoginService ssoLoginService;
    @Autowired
    public SysDictionaryService sysDictionaryService;
     /**
     * 根据条件获取投诉举报列表
     * @param ttsScltxxcjComplain
     * @return
     */
    @ApiOperation(value = "获取投诉举报信息列表")
    @SystemControllerLog(description = "投诉举报信息",operationType = "分页查询")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(TtsScltxxcjComplain ttsScltxxcjComplain,String dateBegin,String dateEnd,String keywords,
                              int start, int length, String userId) {
    	try{
//            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(userId.trim());
            ttsScltxxcjComplain.setUserIdcode(user.getUserIdcode());
/*            ttsScltxxcjComplain.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjComplain.setCreateTime(new Date());*/
    		PageInfo<TtsScltxxcjComplain> pageInfo =ttsScltxxcjComplainService.getPageInfo(
    		        ttsScltxxcjComplain,dateBegin,dateEnd,keywords,((start+1)/length)+1,length);
    		return setSuccessModelMap(new ModelMap(),pageInfo);
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

	/**
     * 新增投诉举报记录
     * @param ttsScltxxcjComplain
     * @return
     */
    @ApiOperation(value = "新增投诉举报数据")
    @SystemControllerLog(description = "投诉举报信息",operationType = "新增")
    @RequestMapping(value = "/addTtsScltxxcjComplain",method = RequestMethod.POST)
    public Object addTtsScltxxcjComplain(@RequestBody TtsScltxxcjComplain  ttsScltxxcjComplain,String userId){
    	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(userId.trim());
            if(null != user){
                ttsScltxxcjComplain.setUserIdcode(user.getUserIdcode());
                ttsScltxxcjComplain.setEntityIdcode(user.getEntityIdcode());
                ttsScltxxcjComplain.setComplaintCopName(user.getEnterpriseName());
                ttsScltxxcjComplain.setAreaId(user.getArea());
                ttsScltxxcjComplain.setComplaintTime(new Date());
                ttsScltxxcjComplainService.add(ttsScltxxcjComplain);
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


	 /**
     * 根据ID获取单条投诉举报数据信息
     * @param ttsScltxxcjComplain
     * @return
     */
    @ApiOperation(value = "获取单条投诉举报数据信息")
    @SystemControllerLog(description = "投诉举报信息",operationType = "ID查询")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjComplain  ttsScltxxcjComplain){
        ttsScltxxcjComplain = ttsScltxxcjComplainService.queryById(ttsScltxxcjComplain.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjComplain",ttsScltxxcjComplain);
        return map;
    }
    
     /**
     * 修改投诉举报数据信息
     * @param ttsScltxxcjComplain
     * @return
     */
    @ApiOperation(value = "修改投诉举报数据信息")
    @SystemControllerLog(description = "投诉举报信息",operationType = "修改")
    @RequestMapping(value = "/updateTtsScltxxcjComplain",method = RequestMethod.POST)
    public Object updateTtsScltxxcjComplain(@RequestBody TtsScltxxcjComplain ttsScltxxcjComplain,String userId){
      	try{
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(userId.trim());
            if(null != user){
                ttsScltxxcjComplain.setAccEntityIdcode(user.getUserIdcode());
                ttsScltxxcjComplain.setAccUserIdcode(user.getEntityIdcode());
                ttsScltxxcjComplain.setAccTime(new Date());
                ttsScltxxcjComplainService.update(ttsScltxxcjComplain);
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取实体信息列表
     * @param ttsScltxxcjComplain
     * @return
     */
    @ApiOperation(value = "获取投诉举报信息列表")
    @SystemControllerLog(description = "投诉举报信息",operationType = "查询")
    @RequestMapping(value = "/getEntityPageInfo",method = RequestMethod.POST)
    public Object getEntityPageInfo(TtsScltxxcjComplain ttsScltxxcjComplain,String keywords,String id, int start, int length) {
        try{
            //获取当前登录用户信息
            //TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(StringUtils.isNotBlank(id)?id.trim():id);
            PageInfo<TtsScltxxcjComplain> pageInfo =ttsScltxxcjComplainService.getEntityPageInfo(
                    ttsScltxxcjComplain,keywords,((start+1)/length)+1,length,id);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    
     /**
     * 删除投诉举报信息
     * @param ttsScltxxcjComplain
     * @return
     */
    @ApiOperation(value = " 删除投诉举报信息")
    @SystemControllerLog(description = "投诉举报信息",operationType = "删除")
    @RequestMapping(value = "/deleteTtsScltxxcjComplain",method = RequestMethod.POST)
    public Object deleteTtsScltxxcjComplain(TtsScltxxcjComplain ttsScltxxcjComplain){
   	    try{
    		ttsScltxxcjComplainService.delete(ttsScltxxcjComplain.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取问题类型
     * @param
     * @return
     */
    @ApiOperation(value = "获取问题类型")
    @SystemControllerLog(description = "问题类型",operationType = "查询")
    @RequestMapping(value = "/getIssueType",method = RequestMethod.POST)
    public List<SysDictData> getIssueType(){
        List<SysDictData> list = sysDictionaryService.getIssueType();
        return list;
    }

    /**
     * 单纯上传文件--暂加
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "附件上传")
    @SystemControllerLog(description = "附件上传",operationType = "上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            map.put("path",this.uploadFile(request));
            //map.putAll(ttsScltxxcjComplainService.upload(request));
        }catch (Exception e){
            map.put("httpCode", ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG,ApiMsgConstants.FAILED_MSG);
        }
        map.put("httpCode", ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG,ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

}

