package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjProposal;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.TtsScltxxcjProposalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
*	平台优化建议 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "平台优化建议", description = "平台优化建议")
@RequestMapping(value = "/ttsScltxxcjProposal",method = RequestMethod.POST)
public class TtsScltxxcjProposalController extends BaseController {

	@Autowired
    public TtsScltxxcjProposalService ttsScltxxcjProposalService;
    @Autowired
    public SSOLoginService ssoLoginService;

     /**
     * 根据条件获取平台优化建议列表
     * @param ttsScltxxcjProposal
     * @return
     */
    @ApiOperation(value = "获取平台优化建议信息列表")
    @SystemControllerLog(description = "平台优化",operationType = "查询")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Map<String,Object> getPageInfo(TtsScltxxcjProposal ttsScltxxcjProposal, int start, int length) {
    	PageInfo<TtsScltxxcjProposal> pageInfo =ttsScltxxcjProposalService.getPageInfo(ttsScltxxcjProposal,((start+1)/length)+1,length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

	/**
     * 新增平台优化建议记录
     * @param ttsScltxxcjProposal
     * @return
     */
    @ApiOperation(value = "新增平台优化建议数据")
    @SystemControllerLog(description = "平台优化",operationType = "新增")
    @RequestMapping(value = "/addTtsScltxxcjProposal",method = RequestMethod.POST)
    public Map<String, Object> addTtsScltxxcjProposal(@RequestBody TtsScltxxcjProposal  ttsScltxxcjProposal, String userId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(userId.trim());
            if(null != user){
                ttsScltxxcjProposal.setUserIdcode(user.getUserIdcode());
                ttsScltxxcjProposal.setEntityIdcode(user.getEntityIdcode());
                ttsScltxxcjProposalService.add( ttsScltxxcjProposal);
                map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
                map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
                return map;
            }else{
                map.put(ApiConstants.CODE,ApiMsgConstants.FAILED_CODE);
                map.put(ApiConstants.MSG,ApiMsgConstants.FAILED_MSG);
                return map;
            }
        }catch (Exception e){
            map.put(ApiConstants.CODE,ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG,ApiMsgConstants.FAILED_MSG);
            return map;
        }
    }


	 /**
     * 根据ID获取单条平台优化建议数据信息
     * @param ttsScltxxcjProposal
     * @return
     */
    @ApiOperation(value = "获取单条平台优化建议数据信息")
    @SystemControllerLog(description = "平台优化",operationType = "查询")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjProposal ttsScltxxcjProposal){
        ttsScltxxcjProposal = ttsScltxxcjProposalService.queryById(ttsScltxxcjProposal.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjProposal",ttsScltxxcjProposal);
        return map;
    }

     /**
     * 修改平台优化建议数据信息
     * @param ttsScltxxcjProposal
     * @return
     */
    @ApiOperation(value = "修改平台优化建议数据信息")
    @SystemControllerLog(description = "平台优化",operationType = "修改")
    @RequestMapping(value = "/updateTtsScltxxcjProposal",method = RequestMethod.POST)
    public Map<String,Object> updateTtsScltxxcjProposal(@RequestBody TtsScltxxcjProposal ttsScltxxcjProposal){
        ttsScltxxcjProposalService.update(ttsScltxxcjProposal);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    
    
     /**
     * 删除平台优化建议信息
     * @param ttsScltxxcjProposal
     * @return
     */
     @ApiOperation(value = "删除平台优化建议信息")
     @SystemControllerLog(description = "平台优化",operationType = "删除")
     @RequestMapping(value = "/deleteTtsScltxxcjProposal",method = RequestMethod.POST)
    public Map<String,Object> deleteTtsScltxxcjProposal(TtsScltxxcjProposal ttsScltxxcjProposal){
        ttsScltxxcjProposalService.delete(ttsScltxxcjProposal.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "附件上传")
    @SystemControllerLog(description = "附件上传",operationType = "上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> upload(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            map.put("path",this.uploadFile(request));
           // map.putAll(ttsScltxxcjProposalService.upload(request));
        }catch (Exception e){
            map.put("httpCode", ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG,ApiMsgConstants.FAILED_MSG);
        }
        map.put("httpCode", ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG,ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
}

