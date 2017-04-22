package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IAnlianBillReportService;
import com.tianrui.api.req.front.adminReport.AnlianBillReportReq;
import com.tianrui.api.resp.admin.AnlianBillReportResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.BillPosition;
import com.tianrui.service.bean.anlian.AnlianBillReport;
import com.tianrui.service.mapper.AnlianBillReportMapper;
import com.tianrui.service.mongo.BillPositionDao;
@Service
public class AnlianBillReportService implements IAnlianBillReportService{

	@Autowired
	AnlianBillReportMapper anlianBillReportMapper;
	@Autowired
	BillPositionDao billPositionDao;
	
	@Override
	public PaginationVO<AnlianBillReportResp> select(AnlianBillReportReq req) throws Exception{
		// TODO Auto-generated method stub
		
		PaginationVO<AnlianBillReportResp> page = new PaginationVO<AnlianBillReportResp>();
		AnlianBillReport record = new AnlianBillReport();
		PropertyUtils.copyProperties(record, req);
		if(null != req.getPageNo()){
			record.setPageNo(req.getPageNo()*req.getPageSize());
			record.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<AnlianBillReport> list = anlianBillReportMapper.selectByCondition(record);
		long a = anlianBillReportMapper.selectBycount(record);
		page.setList(copyProperties(list));
		page.setTotal(a);
		return page;
	}
	
	public List<AnlianBillReportResp> copyProperties(List<AnlianBillReport> list) throws Exception{
		List<AnlianBillReportResp> resp = new ArrayList<AnlianBillReportResp>();
		for(AnlianBillReport report:list){
			AnlianBillReportResp ab = new AnlianBillReportResp();
			PropertyUtils.copyProperties(ab, report);
			if(StringUtils.equals("wuliu", report.getType())){
				BillPosition bp = billPositionDao.findBillIdAndStatus(report.getId(), "2");
				if(bp!=null){
					ab.setBegintime(bp.getCreatetime());
				}
			}
			resp.add(ab);
		}
		return resp;
	}

}
