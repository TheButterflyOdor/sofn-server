import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.provider.sys.SysTestStandardProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author gaoyi
 * @version 2017/12/29
 */
public class TestController {
    private  final static Logger logger = Logger.getLogger("TestController");
    @Autowired
    private SysTestStandardProvider provider;

    @DubboReference
     public void setProvider(SysTestStandardProvider provider){this .provider = provider;}
   @Test
    public void testGetData(){
     /*// PageInfo<SysTestItemModel>  pageInfo = provider.getTestitemlist("腈苯唑（fenbuconazole）","GB2763-2016",null);
       List<SysTestItemModel> list = pageInfo.getList();
        for (SysTestItemModel sys : list){
            logger.info("===================itemName:"+sys.getItemName());
            logger.info("===================Scope:"+sys.getScope());
            logger.info("===================StandardCode:"+sys.getStandardCode());
        }*/
    }

}