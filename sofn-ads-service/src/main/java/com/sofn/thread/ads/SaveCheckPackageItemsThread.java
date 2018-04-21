package com.sofn.thread.ads;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.ads.AdsNewModelExpandMapper;
import com.sofn.model.generator.AdsCheckModelMapping;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * describe:
 *
 * @author xuquan
 * @date 2018/04/10
 */
public class SaveCheckPackageItemsThread extends Thread {

    private AdsCheckModelMapping adsCheckModelMapping;
    private AdsNewModelExpandMapper adsNewModelExpandMapper;

    public SaveCheckPackageItemsThread(AdsCheckModelMapping adsCheckModelMapping, AdsNewModelExpandMapper adsNewModelExpandMapper) {
        this.adsCheckModelMapping = adsCheckModelMapping;
        this.adsNewModelExpandMapper = adsNewModelExpandMapper;
    }

    @Override
    public void run() {
        if (adsCheckModelMapping != null) {
            //先提取检测包中的检测项 遍历检测包
            String ids = adsCheckModelMapping.getIds();
            String productNamesStr = adsCheckModelMapping.getProductName();
            String productIdsStr = adsCheckModelMapping.getProductId();
            String[] productNames = null;
            if (StringUtils.isNotBlank(productNamesStr)) {
                productNames = productNamesStr.split(",");
            }
            String[] productIds = null;
            if (StringUtils.isNotBlank(productIdsStr)) {
                productIds = productIdsStr.split(",");
            }
            Map<String, Object> queryParams = new HashMap<>();
            String[] arr = ids.split("、");
            //        queryParams.put("ids",ids);
            queryParams.put("modelId", adsCheckModelMapping.getModelId());

            //创建可缓存的线程池
            ExecutorService pool = Executors.newCachedThreadPool();

            for (int j = 0; j < productNames.length; j++) {
                queryParams.put("list", Arrays.asList(arr));
                queryParams.put("modelId", adsCheckModelMapping.getModelId());
                queryParams.put("productId", productIds[j]);
                queryParams.put("organId", adsCheckModelMapping.getOrganId());
                List<AdsCheckModelMapping> list = adsNewModelExpandMapper.getPackageCheckItems(queryParams);
                for (AdsCheckModelMapping adsCheckModelMapping1 : list) {
                    queryParams.put("checkId", adsCheckModelMapping1.getCheckId());


                    AdsCheckModelMapping modelMapping = new AdsCheckModelMapping();
                    String uuid = UUID.randomUUID().toString();
                    uuid = uuid.replace("-", "");
                    modelMapping.setId(uuid);//设置id
                    modelMapping.setModelId(adsCheckModelMapping.getModelId());//模型id
                    modelMapping.setProductId(productIds[j]);//产品id
                    modelMapping.setProductName(productNames[j]);
                    modelMapping.setCheckId(adsCheckModelMapping1.getCheckId());
                    modelMapping.setCheckName(adsCheckModelMapping1.getCheckName());

                    pool.execute(new CheckModelRepeatThread(queryParams, modelMapping, adsNewModelExpandMapper));

                }
            }

            pool.shutdown();
            while (true) {
                if (pool.isTerminated()) {
                    System.out.println("ModelConfig run end！");
                    JSONObject jObject = (JSONObject) RedisUtil.get(adsCheckModelMapping.getModelId());
                    jObject.put("Finished", 1);

                    RedisUtil.set(adsCheckModelMapping.getModelId(), jObject);
                    break;
                }
            }
        }
    }
}
