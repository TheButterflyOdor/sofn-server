package com.sofn.web.tts;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.RabbitMQSend;
import com.sofn.model.tts.MqInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

@RestController
@Api(value = "消息推送", description = "消息推送")
@RequestMapping(value = "/message", method = RequestMethod.POST)
public class MessageController extends BaseController {
    @Autowired
    public  RabbitMQSend rabbitMQSend;

    @ApiOperation(value = "消息发送")
    @RequestMapping(value = "/messageProduct",method = RequestMethod.POST)
    @SystemControllerLog(description = "消息发送",operationType = "发送")
    public Object messageProduct(@RequestBody MqInfo mqInfo){
        String key = mqInfo.getKey();
        try{
            rabbitMQSend.DirectSend(key,mqInfo);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


}
