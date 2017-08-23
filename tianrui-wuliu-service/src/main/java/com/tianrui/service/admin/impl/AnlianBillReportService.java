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
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillPosition;
import com.tianrui.service.bean.BillTrack;
import com.tianrui.service.bean.anlian.AnlianBillReport;
import com.tianrui.service.mapper.AnlianBillReportMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mongo.BillPositionDao;
import com.tianrui.service.mongo.BillTrackDao;
import com.tianrui.service.util.MapDistanceUtil;
@Service
public class AnlianBillReportService implements IAnlianBillReportService{

	@Autowired
	AnlianBillReportMapper anlianBillReportMapper;
	@Autowired
	BillPositionDao billPositionDao;
	@Autowired
	FileRouteMapper fileRouteMapper;
	@Autowired
	BillTrackDao billTrackDao;
	@Autowired
	FilePositoinMapper filePositoinMapper;
	@Autowired
	BillMapper billMapper;

	@Override
	public PaginationVO<AnlianBillReportResp> select(AnlianBillReportReq req) throws Exception{
		
		PaginationVO<AnlianBillReportResp> page = new PaginationVO<AnlianBillReportResp>();
		AnlianBillReport record = new AnlianBillReport();
		PropertyUtils.copyProperties(record, req);
		if(null != req.getPageNo()){
			record.setPageNo(req.getPageNo()*req.getPageSize());
			record.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		if(req.getType().equals("anlian")){
			List<AnlianBillReport> list = anlianBillReportMapper.AlselectByCondition(record);
			long a = anlianBillReportMapper.AlselectBycount(record);
			page.setList(copyProperties(list));
			page.setTotal(a);
		}else if(req.getType().equals("wuliu")){
			List<AnlianBillReport> list = anlianBillReportMapper.DyselectByCondition(record);
			long a = anlianBillReportMapper.DyselectBycount(record);
			page.setList(copyProperties(list));
			page.setTotal(a);
		}else{
			List<AnlianBillReport> list = anlianBillReportMapper.selectByCondition(record);
			long a = anlianBillReportMapper.selectBycount(record);
			page.setList(copyProperties(list));
			page.setTotal(a);
		}
		return page;
	}
	
	public List<AnlianBillReportResp> copyProperties(List<AnlianBillReport> list) throws Exception{
		List<AnlianBillReportResp> resp = new ArrayList<AnlianBillReportResp>();
		for(AnlianBillReport report:list){
			AnlianBillReportResp ab = new AnlianBillReportResp();
			PropertyUtils.copyProperties(ab, report);
			resp.add(ab);
		}
		return resp;
	}

	@Override
	public void uptDistance() throws Exception {
		AnlianBillReport record = new AnlianBillReport();
		record.setType("wuliu");
		//查询报表所有运单
		int a = 0;
		List<AnlianBillReport> list = anlianBillReportMapper.selectByCondition(record);
		for(AnlianBillReport report:list){
			a = a+1;
			System.out.println("------------数据总数="+list.size()+"执行第"+a+"条数据");
			//只处理物流运单
			if(StringUtils.equals("wuliu", report.getType())){
				//获取路线信息
//				FileRoute route = fileRouteMapper.selectByPrimaryKey(report.getRouteid());
				//提货位置
				BillPosition bp = billPositionDao.findBillIdAndStatus(report.getId(), "2");
//				if(bp!=null){
//					Double th = distanceChange(route.getOpositionid(),bp.getLon(),bp.getLat());
//					Bill b = new Bill();
//					b.setId(report.getId());
//					b.setQ_deviation(th);
//					b.setBegintime(bp.getCreatetime());
//					billMapper.updateByPrimaryKeySelective(b);
//				}
				//卸货位置
				BillPosition dp = billPositionDao.findBillIdAndStatus(report.getId(), "3");
				if(dp != null){
					//计算到货地距离偏差
//					Double dh = distanceChange(route.getDpositionid(),dp.getLon(),dp.getLat());
					Bill b = new Bill();
					b.setId(report.getId());
//					b.setD_deviation(dh);
					if(bp!=null){
						b.setInterTime(dp.getCreatetime()-bp.getCreatetime());
						b.setInterDistance(MapDistanceUtil.getDistance(bp.getLon()*Math.pow(10,-6), bp.getLat()*Math.pow(10,-6), dp.getLon()*Math.pow(10,-6), dp.getLat()*Math.pow(10,-6)));
						billMapper.updateByPrimaryKeySelective(b);
					}
				}
				//签收时间
//				List<BillTrack> bl = billTrackDao.findWithBidAndStatus(report.getId(), "6");
//				if(bl.size()==1){
//					Bill cd = new Bill();
//					cd.setId(report.getId());
//					cd.setOwnerSigntime(bl.get(0).getTimestamp());
//					billMapper.updateByPrimaryKeySelective(cd);
//				}
			}
		}
	}
	
	//计算两点间距离
	public Double distanceChange(String positionId,Integer lon,Integer lat){
		
		FilePositoin position = filePositoinMapper.selectByPrimaryKey(positionId);
		Double resp = MapDistanceUtil.getDistance(lon*Math.pow(10,-6),lat*Math.pow(10,-6),position.getLng()*Math.pow(10,-6),position.getLat()*Math.pow(10,-6));
		return resp;
	}

}
