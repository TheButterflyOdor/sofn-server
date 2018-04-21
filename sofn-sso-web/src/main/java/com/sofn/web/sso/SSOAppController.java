package com.sofn.web.sso;

/**
 * Created by qjh on 2016/12/27.
 */


import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseController;
import com.sofn.core.config.Resources;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.LoginException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.encryptApp.AES;
import com.sofn.core.util.encryptApp.EncryUtil;
import com.sofn.core.util.encryptApp.RSA;
import com.sofn.service.sso.SSOAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author qjh
 * @version 2016年12月27日 下午 5:05
 */
@RestController
@Api(value = "App端用户登录", description = "App端用户登录")
@RequestMapping(value = "/ssoApp", method = RequestMethod.POST)
public class SSOAppController extends BaseController {

    public static final String clientPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbNojYr8KlqKD/y" +
            "COd7QXu3e4TsrHd4sz3XgDYWEZZgYqIjVDcpcnlztwomgjMj9xSxdpyCc85GOGa0" +
            "lva1fNZpG6KXYS1xuFa9G7FRbaACoCL31TRv8t4TNkfQhQ7e2S7ZktqyUePWYLlz" +
            "u8hx5jXdriErRIx1jWK1q1NeEd3NAgMBAAECgYAws7Ob+4JeBLfRy9pbs/ovpCf1" +
            "bKEClQRIlyZBJHpoHKZPzt7k6D4bRfT4irvTMLoQmawXEGO9o3UOT8YQLHdRLitW" +
            "1CYKLy8k8ycyNpB/1L2vP+kHDzmM6Pr0IvkFgnbIFQmXeS5NBV+xOdlAYzuPFkCy" +
            "fUSOKdmt3F/Pbf9EhQJBANrF5Uaxmk7qGXfRV7tCT+f27eAWtYi2h/gJenLrmtke" +
            "Hg7SkgDiYHErJDns85va4cnhaAzAI1eSIHVaXh3JGXcCQQDDL9ns78LNDr/QuHN9" +
            "pmeDdlQfikeDKzW8dMcUIqGVX4WQJMptviZuf3cMvgm9+hDTVLvSePdTlA9YSCF4" +
            "VNPbAkEAvbe54XlpCKBIX7iiLRkPdGiV1qu614j7FqUZlAkvKrPMeywuQygNXHZ+" +
            "HuGWTIUfItQfSFdjDrEBBuPMFGZtdwJAV5N3xyyIjfMJM4AfKYhpN333HrOvhHX1" +
            "xVnsHOew8lGKnvMy9Gx11+xPISN/QYMa24dQQo5OAm0TOXwbsF73MwJAHzqaKZPs" +
            "EN08JunWDOKs3ZS+92maJIm1YGdYf5ipB8/Bm3wElnJsCiAeRqYKmPpAMlCZ5x+Z" +
            "AsuC1sjcp2r7xw==";

    public static final String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmzaI2K/Cpaig/8gjne0F7t3uE" +
            "7Kx3eLM914A2FhGWYGKiI1Q3KXJ5c7cKJoIzI/cUsXacgnPORjhmtJb2tXzWaRui" +
            "l2EtcbhWvRuxUW2gAqAi99U0b/LeEzZH0IUO3tku2ZLaslHj1mC5c7vIceY13a4h" +
            "K0SMdY1itatTXhHdzQIDAQAB";

    public static final String serverPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALIZ98KqgLW8IMt4" +
            "G+N+4d3DiOiEa+5s6lCMSGE/NbU9stJEqw0EuCP54MY6JkT0HCYTCrLXqww6rSQy" +
            "WF7BNCVGssk2XDcvSKiCz1ZMgabd6XVK5kvIycySydXQ0Ky6rnfxw8w2mllHABFv" +
            "s1eamaHQozv18n/XGqemjW2BFy/jAgMBAAECgYAxT3FCi3SBXKnzy7hk/z9H6Bhi" +
            "0C8V3z/stzpe+mJDYOa+wtZdD15wT4HFQFpSIwgcHo+Kvp2UEDbZ27qN2Y43AZbF" +
            "9LOalWTRUzYtr8wL8MIbgtew/QQ9YFNWdkTZ6MxCItjD/mSz3Lrkcphvbsx4VoCV" +
            "YIJ04r+Loi0t9g0guQJBANvkpfrq0bLVRYWfaigjkx47mr0trJkB7mjADe69Iqts" +
            "M/2x5dHPpClDK78yzAWxU2BrYzOd31QIOm32iMIvRxUCQQDPWJPMOzcq8Jqs1PAM" +
            "7D0hxnvF3tSJB0CJCQWdGFkJiuIYSbrWnCVF78jJyU2AK1H3RDi9BzGPL2Z3i2Si" +
            "+9kXAkAPnKtAJl3fEY9PDmNuGCCA3AB/f/eqIV345/HVSm5kt1j1oSTNAa4JE/DO" +
            "MWAU42MlDFrNtl69y5vCZOeOyeaFAkBOJieGmWcAozDZJWTYqg2cdk/eU08t2nLj" +
            "c2gPPscIRrVSzC9EhhOyWV8HVv0D6s/471inPlfajNYFBp/Goj+/AkEAiejHX/58" +
            "Vv8+ccW22RMZmyxiHcZpTw9hz7vHUCWv03+fyVGtGMhJ4xuPt8UaZm91yHSPWWar" +
            "M8Xa7errKaXN9A==";
    public static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyGffCqoC1vCDLeBvjfuHdw4jo" +
            "hGvubOpQjEhhPzW1PbLSRKsNBLgj+eDGOiZE9BwmEwqy16sMOq0kMlhewTQlRrLJ" +
            "Nlw3L0iogs9WTIGm3el1SuZLyMnMksnV0NCsuq538cPMNppZRwARb7NXmpmh0KM7" +
            "9fJ/1xqnpo1tgRcv4wIDAQAB";


    @Autowired
    private SSOAppService ssoAppService;


    /**
     * Login object.
     *
     * @param modelMap the model map
     * @param account  the account
     * @param password the password
     * @return the object
     */
    //<editor-fold desc="不再使用">
    @ApiOperation(value = "用户登录")
    @PostMapping("/loginApp")   /*等效于 @RequestMapping(value = "/login",method = RequestMethod.POST) */
    public Object loginApp(ModelMap modelMap,
                           @ApiParam(required = true, value = "登录帐号")
                           @RequestParam(value = "account", required = false) String account,
                           @ApiParam(required = true, value = "登录密码")
                           @RequestParam(value = "password", required = false) String password) {
        // 参数检查
        Assert.isNotBlank(account, "ACCOUNT");
        Assert.isNotBlank(password, "PASSWORD");

        //登录逻辑
        Map<String, Object> map = ssoAppService.loginApp(account, password);
        if (map == null) {
            throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
        }
        CurrentUser userModel = (CurrentUser) map.get("user");
        //查询机构信息并缓存
        CurrentUser user = (CurrentUser) map.get("user");
        //根据业务需要将返回数据加密后返回
        modelMap.addAttribute("token", map.get("token"));
        return setSuccessModelMap(modelMap);

    }


    /**
     * Login object.
     *
     * @param modelMap the model map
     * @param encryptData  the data
     * @param encryptKey the key
     * @return the object
     */
    //<editor-fold desc="不再使用">
    @ApiOperation(value = "用户登录")
    @PostMapping("/loginAppEncry")   /*等效于 @RequestMapping(value = "/login",method = RequestMethod.POST) */
    public Object loginAppEncry(ModelMap modelMap,
                                @ApiParam(required = true, value = "密文")
                                @RequestParam(value = "ciphertext", required = false) String encryptData,
                                @ApiParam(required = true, value = "RSA加密后的key")
                                @RequestParam(value = "desKey", required = false) String encryptKey) {
        // 参数检查
        //Assert.isNotBlank(encryptData, "ACCOUNT");
        //Assert.isNotBlank(encryptKey, "PASSWORD");

        // 验签
        boolean passSign = false;
        try {
            passSign = EncryUtil.checkDecryptAndSign(encryptData, encryptKey, clientPublicKey, serverPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (passSign) {
            // 验签通过
            String aeskey = null;
            try {
                aeskey = RSA.decrypt(encryptKey, serverPrivateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String data = AES.decryptFromBase64(encryptData, aeskey);

            JSONObject jsonObj = JSONObject.parseObject(data);
            String account = jsonObj.getString("account");
            String password = jsonObj.getString("password");

            //登录逻辑
            Map<String, Object> map = ssoAppService.loginApp(account, password);
            if (map == null) {
                throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
            }
            CurrentUser userModel = (CurrentUser) map.get("user");
            //查询机构信息并缓存
            CurrentUser user = (CurrentUser) map.get("user");
            //根据业务需要将返回数据加密后返回
            modelMap.addAttribute("token", map.get("token"));
            return setSuccessModelMap(modelMap);
        } else {
            System.out.println("验签失败");
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);

    }


    /**
     * Logout object.
     *
     * @param modelMap the model map
     * @param token    the token
     * @param type     the type
     * @return the object
     */
    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public Object logout(ModelMap modelMap,
                         @ApiParam(required = true, value = "token")
                         @RequestParam(value = "token", defaultValue = "") String token,
                         @ApiParam(required = true, value = "pc or app")
                         @RequestParam(value = "type", defaultValue = "") String type) {
        ssoAppService.logout(token, type);
        return setSuccessModelMap(modelMap);
    }


}
