package com.tianrui.test.report;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.req.front.report.ReportReq;
import com.tianrui.service.bean.BillReport;
import com.tianrui.service.mapper.BillReportMapper;
import com.tianrui.test.service.DataDictServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class BillReportTest {
	public static Logger logger =LoggerFactory.getLogger(DataDictServiceTest.class);

	@Autowired
	private BillReportMapper billReportMapper;
	
	@Test
	public void queryBillReport(){
		ReportReq req = new ReportReq();
		req.setRoutename("这是一条路");
		req.setStart(0);
		req.setLimit(10);
		List<BillReport> list = billReportMapper.queryBillReport(req);
		System.out.println(list.size());
	}
	
}
