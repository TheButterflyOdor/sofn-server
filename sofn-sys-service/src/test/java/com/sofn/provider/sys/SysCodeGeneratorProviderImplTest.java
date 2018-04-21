package com.sofn.provider.sys;

import com.sofn.core.oid.IdGenerator;
import com.sofn.core.util.UUIDUtil;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.generator.SysDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/23/023.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-config.xml"})
public class SysCodeGeneratorProviderImplTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SysDocumentProvider sysDocumentProvider;

    //@Test
    public void testGetMainBodyIdentityCode() {
        System.out.println(" ********************** GetMainBodyIdentityCode Test Start ************************ ");
        String code = sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody, "511381198202120054");
        System.out.printf("主体身份码：%s \n", code);
        System.out.println(" ********************** GetMainBodyIdentityCode Test End ************************ \n");
    }

    //@Test
    public void testGetProductBatchCode() {
        System.out.println(" ********************** GetProductBatchCode Test Start ************************ ");
        SysCodeConvert code = sysCodeGeneratorProvider.getProductBatchCode(IdGenerator.TracingCodeType.production, IdGenerator.MainBodyCategories.MainWorkingBody, "511381198202120054", "01060123");
        System.out.printf("产品追溯码：%s \n", code);
        System.out.println(" ********************** GetProductBatchCode Test End ************************ ");
    }

    //@Test
    public void testGetProductBatchCodes() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List<Map<String, Object>> paramList = new ArrayList<>();

        map1.put("tracingCodeType", IdGenerator.TracingCodeType.production);
        map1.put("mainBodyCategories", IdGenerator.MainBodyCategories.MainWorkingBody);
        map1.put("creditCode", "511381198202120054");
        map1.put("agriculturalProductCode", "01060123");

        map2.put("tracingCodeType", IdGenerator.TracingCodeType.enterMarket);
        map2.put("mainBodyCategories", IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement);
        map2.put("creditCode", "511381198202120");
        map2.put("agriculturalProductCode", "01060123");

        paramList.add(map1);
        paramList.add(map2);

        System.out.println(" ********************** GetProductBatchCodes Test Start ************************ ");
        List<SysCodeConvert> codeList = sysCodeGeneratorProvider.getProductBatchCode(paramList);
        System.out.printf("产品追溯码列表：%s \n", codeList);
        System.out.println(" ********************** GetProductBatchCodes Test End ************************ ");
    }

    @Test
    public void testAddDocument() {
        SysDocument document = new SysDocument();
        document.setId(UUIDUtil.getUUID());
        document.setTitle("事务控制测试");
        document.setContent("单元测试");
        document.setType("帮助文档");
        document.setApplyTo("sys");

        int rows = sysDocumentProvider.addDocument(document);

        System.out.println("新增文档记录 " + rows + " 条");
    }

    //@Test
    public void testGetDocument(){
        SysDocument document = sysDocumentProvider.getDocument("b1e3f76bacfa4379b3eb50a447a824f70f3cf2fa318c43d3826e28974bf87c05");
        System.out.println("查询一条文档记录 " + document);
    }

}
