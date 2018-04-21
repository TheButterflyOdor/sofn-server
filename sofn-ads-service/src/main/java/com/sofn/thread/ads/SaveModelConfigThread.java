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
public class SaveModelConfigThread extends Thread {

    private AdsCheckModelMapping adsCheckModelMapping;
    private AdsNewModelExpandMapper adsNewModelExpandMapper;
    int result = 0;   //result 忽略冲突项个数

    public SaveModelConfigThread(AdsCheckModelMapping adsCheckModelMapping, AdsNewModelExpandMapper adsNewModelExpandMapper) {
        this.adsCheckModelMapping = adsCheckModelMapping;
        this.adsNewModelExpandMapper = adsNewModelExpandMapper;
    }

    @Override
    public void run() {
        if (adsCheckModelMapping != null) {
            String ids = adsCheckModelMapping.getIds();
            if (ids != null && ids.length() > 0) {
                String[] arr = ids.split("、");
                ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; 2 * i < arrayList.size(); i++) {
                    list.add(arrayList.get(2 * i + 1));
                }
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
                queryParams.put("modelId", adsCheckModelMapping.getModelId());
                queryParams.put("organId", adsCheckModelMapping.getOrganId());

                //创建可缓存的线程池
                ExecutorService pool = Executors.newCachedThreadPool();

                for (int j = 0; j < productNames.length; j++) {
                    queryParams.put("productId", productIds[j]);
                    if (arr != null && arr.length > 0) {
                        for (int i = 0; 2 * i < arrayList.size(); i++) {
                            //判断是否已重复
                            queryParams.put("checkId", arrayList.get(2 * i + 1));

                            AdsCheckModelMapping modelMapping = new AdsCheckModelMapping();
                            modelMapping.setCheckName(arrayList.get(2 * i));
                            modelMapping.setCheckId(arrayList.get(2 * i + 1));
                            String uuid = UUID.randomUUID().toString();
                            uuid = uuid.replace("-", "");
                            modelMapping.setId(uuid);
                            modelMapping.setModelId(adsCheckModelMapping.getModelId());
                            modelMapping.setProductId(productIds[j]);
                            modelMapping.setProductName(productNames[j]);

                            pool.execute(new CheckModelRepeatThread(queryParams,modelMapping,adsNewModelExpandMapper));

                        }

                    }
                }

                pool.shutdown();
                while (true){
                    if(pool.isTerminated()){
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
}
