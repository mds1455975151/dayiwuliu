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
import com.tianrui.service.admin.bean.FilePositoin;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.mapper.FilePositoinMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
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
	@Autowired
	FileRouteMapper fileRouteMapper;
	@Autowired
	FilePositoinMapper filePositoinMapper;

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
				FileRoute route = fileRouteMapper.selectByPrimaryKey(report.getRouteid());
				//提货位置
				BillPosition bp = billPositionDao.findBillIdAndStatus(report.getId(), "2");
				if(bp!=null){
					ab.setBegintime(bp.getCreatetime());
					ab.setTslat(bp.getLat());
					ab.setTslon(bp.getLon());
					//始发地
					FilePositoin op = filePositoinMapper.selectByPrimaryKey(route.getOpositionid());
					ab.setTjlat(op.getLat());
					ab.setTjlon(op.getLng());
				}
				//卸货位置
				BillPosition xp = billPositionDao.findBillIdAndStatus(report.getId(), "3");
				if(xp!=null){
					ab.setBegintime(bp.getCreatetime());
					ab.setDslat(xp.getLat());
					ab.setDslon(xp.getLon());
					//目的地
					FilePositoin dp = filePositoinMapper.selectByPrimaryKey(route.getDpositionid());
					ab.setDjlat(dp.getLat());
					ab.setDjlon(dp.getLng());
				}
			}
			resp.add(ab);
		}
		return resp;
	}

}
