package com.sofn.provider.qry;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TestExpandMapper;
import com.sofn.model.qry.SysTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Liw on 2017/3/30.
 */
@DubboService(interfaceClass = TestExpandProvider.class)
public class TestExpandProviderImpl implements TestExpandProvider{
    @Autowired
    private TestExpandMapper testExpandMapper;
    @Override
    public List<SysTemplate> getPageInfo(Map<String, Object> map) {
        return testExpandMapper.getPageInfo(map);
    }

    @Override
    public long getCount(Map<String, Object> map) {
        return testExpandMapper.getCount(map);
    }

    public void getTraceLink(String traceCode){
        Set set = new HashSet();//存放生产经营主体
        List list = new LinkedList();//存放来源和去向
        //第一步，先根据当前追溯码找出卖家和买家
        //SELECT * FROM TTS_SCLTXXCJ_XSDJ a WHERE a.TOZSM='1.2.156.326.8.1.3.1.1.0000000014060319-8.020101.20170405111552'
        //select * from TTS_SCLTXXCJ_CGGL a WHERE a.FROMZSM='1.2.156.326.8.1.3.1.1.0000000014060319-8.020101.20170405111552'
        set.add("1.2.156.326.8.1.0.0.0.044122319850917100");//卖家
        set.add("1.2.156.326.8.1.0.1.0.000014060319800000");//买家
        //查销售登记记录里面对应的产品，是个List
        //SELECT * FROM TTS_SCLTXXCJ_XSDJJL a WHERE a.TOZSM='1.2.156.326.8.1.3.1.1.0000000014060319-8.020101.20170405111552'
        //
        //首先查采购表（TTS_SCLTXXCJ_CGGL）确定买家一条来源，再查销售表，确定卖家一条去向
        //第二步，查出卖家去掉该去向后的所有去向，和所有来源
        //第三步，查出买家去掉该来源后的所有来源，和所有去向
        //        getTraceLink(set,list,traceCode);

    }
}
