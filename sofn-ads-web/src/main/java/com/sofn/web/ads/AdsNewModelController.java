package com.sofn.web.ads;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.excel.ReadExcel;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.model.generator.AdsTestItemModel;
import com.sofn.service.ads.AdsNewModelService;
import com.sofn.service.ads.AdsOrganTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@RestController
@Api(value = "新检测模型", description = "新检测模型")
@RequestMapping(value = "/adsNewModel", method = RequestMethod.POST)
public class AdsNewModelController extends BaseController {


    @Autowired
    private AdsNewModelService adsNewModelService;

    @Autowired
    private AdsOrganTaskService adsOrganTaskService;

    @ApiOperation(value = "检测项目Excel数据导入")
    @SystemControllerLog(description = "检测项目Excel数据导入", operationType = "添加")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @Authorization
    public Object importExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            //将当前上下文初始化
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest中所有的文件名
                Iterator iter = multipartRequest.getFileNames();
                while (iter.hasNext()) {
                    MultipartFile file = multipartRequest.getFile(iter.next().toString());
                    String fileName = file.getOriginalFilename();
                    String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    List<Map<String, Object>> list = ReadExcel.exportListFromExcel(file.getInputStream(), extensionName, 0);
                    List<AdsCheckModelMapping> mappingList = new ArrayList<>();
                    for (Map<String, Object> m : list) {
                        for (String k : m.keySet()) {
                            AdsCheckModelMapping adsCheckModelMapping = new AdsCheckModelMapping();
                            adsCheckModelMapping.setCheckName(m.get(k) + "");
                            String uuid = UUID.randomUUID().toString();
                            uuid = uuid.replace("-", "");
                            adsCheckModelMapping.setCheckId(uuid);
                            adsCheckModelMapping.setCreateDate(new Date());//设置创建时间
                            mappingList.add(adsCheckModelMapping);
                            int result = adsNewModelService.addCheckItems(adsCheckModelMapping);
                            if (result == 1) {
                                return setModelMap(new ModelMap(), HttpCode.CONFLICT);//409发生冲突
                            } else if (result == 2) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
                            } else {
                                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("AdsNewModelController.addCheckItems:导入检测项目数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        return map;
    }


    /**
     * 新增检测项目
     *
     * @param adsCheckModelMapping
     * @return
     */
    @ApiOperation(value = "新增检测项目")
    @SystemControllerLog(description = "新增检测项目", operationType = "添加")
    @RequestMapping(value = "/addCheckItems", method = RequestMethod.POST)
    @Authorization
    public Object addCheckItems(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                ModelMap model = new ModelMap();
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                adsCheckModelMapping.setCheckId(uuid);
                adsCheckModelMapping.setCreateDate(new Date());
                adsCheckModelMapping.setCreateBy(u.getId());
                int result = adsNewModelService.addCheckItems(adsCheckModelMapping);
                if (result == 1) {
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);//409发生冲突
                } else if (result == 2) {
                    return setModelMap(new ModelMap(), HttpCode.OK);//成功
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息

        } catch (Exception e) {
            logger.error("AdsNewModelController.addCheckItems:新增检测项目数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "查询检测项目")
    @SystemControllerLog(description = "查询检测项目", operationType = "查询")
    @RequestMapping(value = "/getPageInfoAll", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoAll(int start, int length, String itemName, String idsArray, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                PageInfo<AdsTestItemModel> pageInfo = adsNewModelService.getPageInfoAll(((start + 1) / length) + 1, length, itemName, idsArray);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询检测项目")
    @SystemControllerLog(description = "查询检测项目", operationType = "查询")
    @RequestMapping(value = "/getPageInfoAllByDetail", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoAllByDetail(int start, int length, String itemName, String nameArray, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                PageInfo<AdsTestItemModel> pageInfo = adsNewModelService.getPageInfoAllByDetail(((start + 1) / length) + 1, length, itemName, nameArray);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据id查询检测项目")
    @SystemControllerLog(description = "根据id查询检测项目", operationType = "查询")
    @RequestMapping(value = "/getCheckItemById", method = RequestMethod.POST)
    @Authorization
    public Object getCheckItemById(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                AdsCheckModelMapping adsCheckModelMapping1 = adsNewModelService.getCheckItemById(adsCheckModelMapping);
                return setSuccessModelMap(new ModelMap(), adsCheckModelMapping1);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "更新检测项目")
    @SystemControllerLog(description = "更新检测项目", operationType = "修改")
    @RequestMapping(value = "/saveCheckItem", method = RequestMethod.POST)
    @Authorization
    public Object saveCheckItem(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsCheckModelMapping.setUpdateBy(u.getId());
                adsCheckModelMapping.setUpdateDate(new Date());
                int result = adsNewModelService.saveCheckItem(adsCheckModelMapping);
                if (result == 1) {
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);//409发生冲突
                } else if (result == 2) {
                    return setSuccessModelMap(new ModelMap(), HttpCode.OK);
                } else if (result == 3) {
                    return setModelMap(new ModelMap(), HttpCode.LOCKED);//423发生冲突
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "更新检测项目")
    @SystemControllerLog(description = "更新检测项目", operationType = "修改")
    @RequestMapping(value = "/isOrNotUse", method = RequestMethod.POST)
    @Authorization
    public Object isOrNotUse(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                int result = adsNewModelService.isOrNotUse(adsCheckModelMapping);
                if (result == 3) {
                    return setModelMap(new ModelMap(), HttpCode.LOCKED);
                } else {
                    return setModelMap(new ModelMap(), HttpCode.OK);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "删除检测项目")
    @SystemControllerLog(description = "删除检测项目", operationType = "删除")
    @RequestMapping(value = "/delAdsCheckItems", method = RequestMethod.POST)
    @Authorization
    public Object delAdsCheckItems(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsNewModelService.deleteAll(adsCheckModelMapping);
                return setSuccessModelMap(new ModelMap());
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询检测模型")
    @SystemControllerLog(description = "查询检测模型", operationType = "查询")
    @RequestMapping(value = "/getModelPageInfoAll", method = RequestMethod.POST)
    @Authorization
    public Object getModelPageInfoAll(AdsCheckModelMapping adsCheckModelMapping, int start, int length, String modelName, String industry, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsCheckModelMapping.setOrganId(u.getOrgId());
                PageInfo<AdsCheckModelMapping> pageInfo = adsNewModelService.getModelPageInfoAll(adsCheckModelMapping, ((start + 1) / length) + 1, length, modelName, industry);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增检测模型
     *
     * @param adsCheckModelMapping
     * @return
     */
    @ApiOperation(value = "新增监测模型配置")
    @SystemControllerLog(description = "新增监测模型配置", operationType = "添加")
    @RequestMapping(value = "/addMonitorModel", method = RequestMethod.POST)
    @Authorization
    public Object addMonitorModel(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                ModelMap model = new ModelMap();
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                adsCheckModelMapping.setModelId(uuid);
                adsCheckModelMapping.setCreateDate(new Date());
                adsCheckModelMapping.setCreateBy(u.getId());
                adsCheckModelMapping.setOrganId(u.getOrgId());
                int result = adsNewModelService.addMonitorModel(adsCheckModelMapping);
                if (result == 1) {
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);//409发生冲突
                } else if (result == 2) {
                    return setModelMap(new ModelMap(), HttpCode.OK);//成功
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            }
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
        } catch (Exception e) {
            logger.error("AdsNewModelController.addCheckItems:新增监测模型数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "删除监测模型")
    @SystemControllerLog(description = "删除监测模型", operationType = "删除")
    @RequestMapping(value = "/delAdsCheckModel", method = RequestMethod.POST)
    @Authorization
    public Object delAdsCheckModel(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsCheckModelMapping.setUpdateDate(new Date());
                adsCheckModelMapping.setUpdateBy(u.getId());
                adsNewModelService.deleteCheckModel(adsCheckModelMapping);
                return setSuccessModelMap(new ModelMap());
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "删除监测模型配置")
    @SystemControllerLog(description = "删除监测模型配置", operationType = "删除")
    @RequestMapping(value = "/delAdsCheckModelCongif", method = RequestMethod.POST)
    @Authorization
    public Object delAdsCheckModelCongif(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                int result = adsNewModelService.delAdsCheckModelCongif(adsCheckModelMapping);
                if (result == 1) {
                    return setModelMap(new ModelMap(), HttpCode.CONFLICT);//改模型已被启用 禁止删除
                } else if (result == 2) {
                    return setSuccessModelMap(new ModelMap());
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "修改监测模型配置")
    @SystemControllerLog(description = "修改监测模型配置", operationType = "修改")
    @RequestMapping(value = "/editMonitorModel", method = RequestMethod.POST)
    @Authorization
    public Object editMonitorModel(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsCheckModelMapping.setUpdateBy(u.getId());
                adsNewModelService.updateByPrimaryKey(adsCheckModelMapping);
                return setSuccessModelMap(new ModelMap());
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "查询检测模型")
    @SystemControllerLog(description = "查询检测模型", operationType = "查询")
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
    @Authorization
    public Object selectByPrimaryKey(String modelId, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                AdsCheckModelMapping adsCheckModelMapping = adsNewModelService.selectByPrimaryKey(modelId);
                return setSuccessModelMap(new ModelMap(), adsCheckModelMapping);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询模型配置列表信息")
    @SystemControllerLog(description = "查询模型配置列表信息", operationType = "查询")
    @RequestMapping(value = "/getModelConfigPageInfoAll", method = RequestMethod.POST)
    @Authorization
    public Object getModelConfigPageInfoAll(AdsCheckModelMapping adsCheckModelMapping, int start, int length, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                start = 0;
                length = 99999;
                PageInfo<AdsCheckModelMapping> pageInfo = adsNewModelService.getModelConfigPageInfoAll(adsCheckModelMapping, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询一个模型配置中所有检测对象名称的分页信息")
    @SystemControllerLog(description = "查询一个模型配置中所有检测对象名称的分页信息", operationType = "查询")
    @RequestMapping(value = "/getModelConfigKeyInfo", method = RequestMethod.POST)
    @Authorization
    public Object getModelConfigGroupPageInfo(AdsCheckModelMapping adsCheckModelMapping, int pageNum, int pageSize, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                Map<String, List<AdsCheckModelMapping>> listInfo = adsNewModelService.getModelConfigGroupList(adsCheckModelMapping);
                Set<String> keySet = listInfo.keySet();
                List<String> listSet = new ArrayList<>(keySet);
                return setSuccessModelMap(new ModelMap(), fenye(listSet, pageNum, pageSize));
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "按检测对象名称查询其具体检测项配置信息")
    @SystemControllerLog(description = "按检测对象名称查询其具体检测项配置信息", operationType = "查询")
    @RequestMapping(value = "/getModelConfigValueInfo", method = RequestMethod.POST)
    @Authorization
    public Object getModelConfigGroupValueInfo(AdsCheckModelMapping adsCheckModelMapping, String key, int pageNum, int pageSize, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                Map<String, List<AdsCheckModelMapping>> listInfo = adsNewModelService.getModelConfigGroupList(adsCheckModelMapping);
                List<AdsCheckModelMapping> valueList = listInfo.get(key);
                return setSuccessModelMap(new ModelMap(), fenye(valueList, pageNum, pageSize));
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "批量保存模型配置信息")
    @SystemControllerLog(description = "批量保存模型配置信息", operationType = "添加")
    @RequestMapping(value = "/saveModelConfig", method = RequestMethod.POST)
    @Authorization
    public Object saveModelConfig(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                int result = adsNewModelService.saveModelConfig(adsCheckModelMapping);
                ModelMap modelMap = new ModelMap();
                if (result > 0) {
                    modelMap.addAttribute("msgInfo", result + "");
                    modelMap.addAttribute("httpCode", "200");
                    return modelMap;
                } else if (result == 0) {
                    return setModelMap(modelMap, HttpCode.OK);
                } else {
                    return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "获取批量保存模型配置结果")
    @SystemControllerLog(description = "获取批量保存模型配置结果", operationType = "查询")
    @RequestMapping(value = "/getSaveModelConfigResult", method = RequestMethod.POST)
    public Object getSaveModelConfigResult(AdsCheckModelMapping adsCheckModelMapping) {
        ModelMap modelMap = new ModelMap();
        JSONObject jObject = (JSONObject) RedisUtil.get(adsCheckModelMapping.getModelId());

        modelMap.addAttribute("finished", jObject.getInteger("Finished"));

        if (jObject.getInteger("Finished") == 1) {
            modelMap.addAttribute("msgInfo", jObject.getInteger("result"));
            modelMap.addAttribute("httpCode", "200");
            return modelMap;
        }

        return setModelMap(modelMap, HttpCode.OK);
    }


    @ApiOperation(value = "批量保存模型配置值信息")
    @SystemControllerLog(description = "批量保存模型配置值信息", operationType = "添加")
    @RequestMapping(value = "/addModelConfigValue", method = RequestMethod.POST)
    @Authorization
    public Object addModelConfigValue(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsNewModelService.addModelConfigValue(adsCheckModelMapping);
                return setModelMap(new ModelMap(), HttpCode.OK);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "查询模型配置详情信息")
    @SystemControllerLog(description = "查询模型配置详情信息", operationType = "查询")
    @RequestMapping(value = "/getModelConfigDetail", method = RequestMethod.POST)
    @Authorization
    public Object getModelConfigDetail(AdsCheckModelMapping adsCheckModelMapping, int start, int length, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                PageInfo<AdsCheckModelMapping> pageInfo = adsNewModelService.getModelConfigDetail(adsCheckModelMapping, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "批量保存检测包中的检测项")
    @SystemControllerLog(description = "批量保存检测包中的检测项", operationType = "添加")
    @RequestMapping(value = "/saveCheckPackageItems", method = RequestMethod.POST)
    @Authorization
    public Object saveCheckPackageItems(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                int result = adsNewModelService.saveCheckPackageItems(adsCheckModelMapping);
                ModelMap modelMap = new ModelMap();
                if (result > 0) {
                    modelMap.addAttribute("msgInfo", result);
                    modelMap.addAttribute("httpCode", "200");
                    return modelMap;
                } else if (result == 0) {
                    return setModelMap(modelMap, HttpCode.OK);
                } else {
                    return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "启用模型")
    @SystemControllerLog(description = "启用模型", operationType = "修改")
    @RequestMapping(value = "/enableAdsCheckModel", method = RequestMethod.POST)
    @Authorization
    public Object enableAdsCheckModel(AdsCheckModelMapping adsCheckModelMapping, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                int result = adsNewModelService.enableAdsCheckModel(adsCheckModelMapping);
                if (result == 1) {
                    return setModelMap(new ModelMap(), HttpCode.OK); //
                } else if (result == 2) {
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);   //请先进行模型配置
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
        return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 获取全部检测模型
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "获取全部检测模型")
    @RequestMapping(value = "/selectAllModel", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取全部检测模型", operationType = "查詢")
    public Object selectAllModel(@RequestBody String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null && u.getOrganization() != null) {
                String organId = u.getOrganization().getOrgId();
                List<Map<String, Object>> list = adsNewModelService.selectAllModel(organId);
                return setSuccessModelMap(new ModelMap(), list);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = " 下载检测项目模板")
    @SystemControllerLog(description = "下载检测项目模板")
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public Object download(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getSession().getServletContext().getRealPath("/");
        String fileName = "检测项目模板.xlsx";
        String showName = "检测项目模板.xlsx";
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            showName = new String(showName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + showName);
            File f = new File(path + "/template/" + fileName);
            response.setContentType("application/octet-stream");
            response.setContentType("application/OCTET-STREAM;charset=UTF-8");
            response.setHeader("Content-Length", String.valueOf(f.length()));
            bis = new BufferedInputStream(new FileInputStream(f));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] data = new byte[1024];
            int len = 0;
            while (-1 != (len = bis.read(data, 0, data.length))) {
                bos.write(data, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                System.out.print(" error");
            }
        }
        return true;
    }

    /**
     * 对list进行逻辑分页
     */
    public List fenye(List list, int pageNum, int pageSize) {
        List subList = new ArrayList<>();
        int totalCount = list.size();
        int totalPage = 0;
        int m = totalCount % pageSize;
        if (m > 0) {
            totalPage = totalCount / pageSize + 1;
        } else {
            totalPage = totalCount / pageSize;
        }

        if (totalCount <= pageSize && pageNum > 0) {
            return null;
        } else if (totalCount <= pageSize && pageNum == 0) {
            return list;
        } else if (pageSize * (pageNum + 1) <= totalCount) {
            if (m == 0) {
                subList = list.subList((pageNum) * pageSize, pageSize * (pageNum + 1));
            } else {
                if (pageNum + 1 == totalPage) {
                    subList = list.subList((pageNum) * pageSize, totalCount - 1);
                } else {
                    subList = list.subList((pageNum) * pageSize, pageSize * (pageNum + 1));
                }
            }
            return subList;
        } else if ((pageNum) * pageSize < totalCount && pageSize * (pageNum + 1) > totalCount) {
            subList = list.subList((pageNum) * pageSize, totalCount - 1);
            return subList;
        } else if ((pageNum) * pageSize > totalCount) {
            return null;
        } else return null;
    }
}
