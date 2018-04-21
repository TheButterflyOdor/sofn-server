package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;

import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;

import com.sofn.model.generator.AdsCheckObjectPackage;
import com.sofn.model.generator.AdsCheckPackage;
import com.sofn.service.ads.AdsCheckPackageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@RestController
@Api(value = "检测包管理", description = "检测包管理")
@RequestMapping(value = "/adsCheckPackage",method = RequestMethod.POST)
public class AdsCheckPackageController extends BaseController{

    @Autowired
    private AdsCheckPackageService adsCheckPackageService;

    @ApiOperation(value = "检测包查询")
    @SystemControllerLog(description = "检测包查询", operationType = "查询")
    @RequestMapping(value = "/getPageInfoAll",method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoAll(AdsCheckPackage adsCheckPackage, int start, int length, String packageName, String industry, String token){
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckPackage.setOrganId(u.getOrgId());
                PageInfo<AdsCheckPackage> pageInfo =adsCheckPackageService.getPageInfoAll(adsCheckPackage,((start+1)/length)+1,length,packageName,industry);
                return setSuccessModelMap(new ModelMap(),pageInfo);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }



    @ApiOperation(value = "检测包详情")
    @SystemControllerLog(description = "检测包详情", operationType = "查询")
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.POST)
    @Authorization
    public Object selectByPrimaryKey(String id,String token){
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
               AdsCheckPackage adsCheckPackage= adsCheckPackageService.selectByPrimaryKey(id);
                return setSuccessModelMap(new ModelMap(),adsCheckPackage);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "删除检测包")
    @SystemControllerLog(description = "删除检测包", operationType = "删除")
    @RequestMapping(value = "/delCheckPackage",method = RequestMethod.POST)
    @Authorization
    public Object delCheckPackage(AdsCheckPackage adsCheckPackage,String token){
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                this.adsCheckPackageService.deleteAll(adsCheckPackage);
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "删除检测对象包")
    @SystemControllerLog(description = "删除检测对象包",operationType = "删除")
    @RequestMapping(value = "/delCheckObjectPackage",method = RequestMethod.POST)
    @Authorization
    public Object delCheckObjectPackage(AdsCheckObjectPackage adsCheckObjectPackage,String token){
        try{
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                this.adsCheckPackageService.deleteObjectPackageAll(adsCheckObjectPackage);
                return setSuccessModelMap(new ModelMap());
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e){
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "新增检测包")
    @SystemControllerLog(description = "新增检测包", operationType = "新增")
    @RequestMapping(value = "/addCheckPackage",method = RequestMethod.POST)
    @Authorization
    public Object addCheckPackage(AdsCheckPackage adsCheckPackage,String token){
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                adsCheckPackage.setId(uuid);
                adsCheckPackage.setCreateDate(new Date());
                adsCheckPackage.setCreateBy(u.getId());
                adsCheckPackage.setOrganId(u.getOrgId());
                int result=this.adsCheckPackageService.addCheckPackage(adsCheckPackage);
                if (result==1){//检测包已存在
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);//409发生冲突
                }else if (result==2){
                    return setModelMap(new ModelMap(), HttpCode.OK);//新增成功
                }else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }





    @ApiOperation(value = "更新检测包")
    @SystemControllerLog(description = "更新检测包", operationType = "更新")
    @RequestMapping(value = "/updatePackage",method = RequestMethod.POST)
    @Authorization
    public Object updatePackage(AdsCheckPackage adsCheckPackage,String token){
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsCheckPackage.setUpdateDate(new Date());
                adsCheckPackage.setUpdateBy(u.getId());
                adsCheckPackageService.updateByPrimaryKey(adsCheckPackage);
                return setModelMap(new ModelMap(), HttpCode.OK);//修改成功
            }
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "更新检测对象包")
    @SystemControllerLog(description = "更新检测对象包", operationType = "更新")
    @RequestMapping(value = "/updateObjectPackage",method = RequestMethod.POST)
    @Authorization
    public Object updateObjectPackage(AdsCheckObjectPackage objectPackage,String token){
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                objectPackage.setUpdateDate(new Date());
                objectPackage.setUpdateBy(u.getId());
                adsCheckPackageService.updateObjectPackageByPrimaryKey(objectPackage);
                return setModelMap(new ModelMap(), HttpCode.OK);//修改成功
            }
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "检测对象包查询")
    @SystemControllerLog(description = "检测对象包查询", operationType = "查询")
    @RequestMapping(value = "/getObjectPackagePageInfoAll",method = RequestMethod.POST)
    @Authorization
    public Object getObjectPackagePageInfoAll(AdsCheckObjectPackage adsCheckObjectPackage, int start, int length, String objectPackageName, String industry, String token){
        try{
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if(u != null){
                adsCheckObjectPackage.setOrganId(u.getOrgId());
                PageInfo<AdsCheckObjectPackage> pageInfo = adsCheckPackageService.getObjectPackagePageInfoAll(adsCheckObjectPackage,((start+1)/length)+1,length,objectPackageName,industry);
                return setSuccessModelMap(new ModelMap(),pageInfo);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        } catch (Exception e){
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "新增检测对象包")
    @SystemControllerLog(description = "新增检测对象包", operationType = "新增")
    @RequestMapping(value = "/addCheckObjectPackage",method = RequestMethod.POST)
    @Authorization
    public Object addCheckObjectPackage(AdsCheckObjectPackage adsCheckObjectPackage, String token){
        try{
            Assert.isNotBlank(token,"token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null){
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-","");
                adsCheckObjectPackage.setId(uuid);
                adsCheckObjectPackage.setCreateDate(new Date());
                adsCheckObjectPackage.setCreateBy(u.getId());
                adsCheckObjectPackage.setOrganId(u.getOrgId());
                int result = this.adsCheckPackageService.addCheckObjectPackage(adsCheckObjectPackage);
                if (result==1){//检测包已存在
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);//409发生冲突
                }else if (result==2){
                    return setModelMap(new ModelMap(), HttpCode.OK);//新增成功
                }else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        }catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "检测对象包详情")
    @SystemControllerLog(description = "检测对象包详情",operationType = "查询")
    @RequestMapping(value = "/selectObjectPackageById", method = RequestMethod.POST)
    @Authorization
    public Object selectObjectPackageById(String id, String token){
        try{
            Assert.isNotBlank(token,"token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if(u!= null){
                AdsCheckObjectPackage objectPackage = adsCheckPackageService.selectObjectPackageById(id);
                return setSuccessModelMap(new ModelMap(),objectPackage);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        } catch (Exception e){
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


}
