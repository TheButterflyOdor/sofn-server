package com.sofn.freemarker;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.model.generator.AdsOrganTask;
import freemarker.template.TemplateException;


/**
 * 代码生成测试main
 * @author Administrator
 *
 */
public class RunMain {

	public static void main(String[] args) throws SQLException, IOException, TemplateException {
		AdsMonitorTask task = new AdsMonitorTask();	//监测任务测试数据
		task.setTaskName("监测-蔬菜第五次");
		task.setReleaseUnit("四川xxxxxxxxx检测中");
		task.setYear("2016");
		task.setStartTime("2016-12-26");
		task.setEndTime("2017-01-05");
		task.setDeadline("2016-12-30");
		task.setMonitorClass("例行监测");
		task.setIndustry("种植业");
		task.setSeparation(BigDecimal.ZERO);
		task.setAttachmentAddress("");
		task.setComment("测试");
		task.setSampleLink("农贸市场、生产基地");
		task.setCheckObject("大白菜、马铃薯、稻谷、大豆");
		task.setCheckProject("氨基酸、贾基对磷流");
		//机构任务测试数据
		List<AdsOrganTask> adsOrganTaskList = new ArrayList<>();
		AdsOrganTask org1= new AdsOrganTask();
		org1.setSampleOrgan("四川省农业部");
		org1.setDetectionOrgan("成都市检测中心");
		org1.setCityName("四川省成都市成华区");
		org1.setNumbers(BigDecimal.TEN);
		AdsOrganTask org2= new AdsOrganTask();
		org2.setSampleOrgan("北京市农业部");
		org2.setDetectionOrgan("北京市检测中心");
		org2.setCityName("北京市海淀区");
		org2.setNumbers(BigDecimal.TEN);
		adsOrganTaskList.add(org1);
		adsOrganTaskList.add(org2);
		task.setAdsOrganTaskList(adsOrganTaskList);
		//CreateDoc.createAdsTaskDoc(task);
	}
	
}
