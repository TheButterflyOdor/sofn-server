package com.sofn.dao.asms;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dong4j on 16/9/1.
 * Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-dao.xml")
public class AsmsInspectionAssessExpandMapperTest {
    private static Logger logger = LoggerFactory.getLogger(AsmsInspectionAssessExpandMapperTest.class);
//    @Autowired
//    private AsmsInspectionAssessExpandMapper asmsInspectionAssessExpandMapper;

    /**
     * 多表联查方式一, 根据表现层创建 dto 对象,然后手写 sql 语句查询需要的字段
     * @throws Exception
     */
//    @Test
//    public void testGetSuperviseTaskInfos() throws Exception {
//        List<SuperviseCheckDto> list = asmsInspectionAssessExpandMapper.getSuperviseTaskInfos();
//        for (SuperviseCheckDto superviseTaskInfo : list){
//            System.out.println(superviseTaskInfo);
//        }
//    }

    /**
     * 使用 association 关键字
     * @throws Exception
     */
//    @Test
//    public void testGetgetSuperviseTaskInfoOther() throws Exception {
//        List<UserAndTaskDto> list = asmsInspectionAssessExpandMapper.getgetSuperviseTaskInfoOther();
//        for (UserAndTaskDto userAndTaskDto : list){
//            System.out.println(userAndTaskDto);
//        }
//    }

}