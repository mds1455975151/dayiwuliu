package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianrui.api.intf.IReportService;
import com.tianrui.api.req.front.bill.AnReportReq;
import com.tianrui.api.resp.admin.AnReportResp;
import com.tianrui.api.resp.admin.WuReportResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.anlian.AnReport;
import com.tianrui.service.bean.anlian.WuReport;
import com.tianrui.service.mapper.AnWuReportMapper;

/**
 * 
 * <p>
 * Title:ReportService
 * </p>
 * <p>
 * Description:运单报表接口实现
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author yangbobo
 * @date 2017年9月6日 下午2:42:15
 */
@Service
public class ReportService implements IReportService {
	@Autowired
	AnWuReportMapper anWuReportMapper;

	/**
	 * 查询收货人开票运单报表
	 */
	@Override
	public PaginationVO<AnReportResp> selectAnReceiver(AnReportReq req) throws Exception {
		PaginationVO<AnReportResp> page = null;
		if (req != null) {
			page = new PaginationVO<AnReportResp>();
			long count = anWuReportMapper.queryAnReportCount(req);
			if (count > 0) {
				Integer start = (req.getPageNo() - 1) * req.getPageSize();
				req.setStart(start);
				req.setLimit(req.getPageSize());
				// 根据收货人id查询运单信息
				List<AnReport> list = anWuReportMapper.selectAnReportCondition(req);
				// 将查询出来的结果填充到resp里面返回
				List<AnReportResp> listAn = new ArrayList<AnReportResp>();
				for (AnReport an : list) {
					AnReportResp anReport = new AnReportResp();
					anReport.setBillno(an.getBillno());// 运单号
					anReport.setVehicleno(an.getVehicleno());// 车牌号
					anReport.setTrueweight(an.getTrueweight());// 签收重量
					anReport.setSigntime(an.getSigntime());// 签收日期
					anReport.setAmountPayable(an.getAmountPayable());// 支付金额
					anReport.setPayStatus(an.getPayStatus());// 支付状态
					anReport.setPlanCode(an.getPlanCode());// 计划编号
					anReport.setCargoname(an.getCargoname());// 货物名称
					anReport.setRoutename(an.getRoutename());//路线名称
					anReport.setStartcity(an.getStartcity());//起运地
					anReport.setEndcity(an.getEndcity());//目的地
					anReport.setBillType("1");//开票类型（1、开票运单2、普通运单）
					listAn.add(anReport);
				}
				page.setList(listAn);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}

	/**
	 * 查询收货人普通运单报表
	 */
	@Override
	public PaginationVO<WuReportResp> selectWuReceiver(AnReportReq req) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<WuReportResp> page = null;
		if (req != null) {
			page = new PaginationVO<WuReportResp>();
			long count = anWuReportMapper.queryWuReportCount(req);
			if (count > 0) {
				Integer start = (req.getPageNo() - 1) * req.getPageSize();
				req.setStart(start);
				req.setLimit(req.getPageSize());
				// 根据收货人id查询运单信息
				List<WuReport> list = anWuReportMapper.selectWuReportCondition(req);
				List<WuReportResp> listWu = new ArrayList<WuReportResp>();
				for (WuReport wu : list) {
					WuReportResp wuReportResp = new WuReportResp();
					wuReportResp.setBillno(wu.getWaybillno());// 运单号
					wuReportResp.setVehicleno(wu.getVehicleno());// 车牌号
					wuReportResp.setTrueweight(wu.getTrueweight());// 签收重量
					wuReportResp.setSigntime(wu.getOwnerSigntime());// 签收时间
					wuReportResp.setAmountPayable(wu.getAmountPayable());// 支付金额
					wuReportResp.setPayStatus(wu.getPayStatus());// 支付状态
					wuReportResp.setPlanCode(wu.getPlanCode());// 计划编号
					wuReportResp.setCargoname(wu.getCargoname());// 货物名称
					wuReportResp.setRoutename(wu.getRoutename());//路线名称
					wuReportResp.setStartcity(wu.getStartcity());//起运地
					wuReportResp.setEndcity(wu.getEndcity());//目的地
					wuReportResp.setBillType("2");//开票类型（1、开票运单2、普通运单）
					listWu.add(wuReportResp);
				}
				page.setList(listWu);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}



	@Override
	public List<AnReportResp> findAnRoutename(AnReportReq req) throws Exception {
		// TODO Auto-generated method stub
		List<AnReport> list =anWuReportMapper.findAnRoutename(req);
		List<AnReportResp> listAn = new ArrayList<AnReportResp>();
		for(AnReport an:list){
			AnReportResp anReportResp = new AnReportResp();
			anReportResp.setRoutename(an.getRoutename());
			anReportResp.setRouteid(an.getRouteid());
			listAn.add(anReportResp);
		}
		return listAn;
	}

	@Override
	public List<WuReportResp> findWuRoutename(AnReportReq req) throws Exception {
		// TODO Auto-generated method stub
		List<WuReport>  list =anWuReportMapper.findWuRoutename(req);
		List<WuReportResp> listWu = new ArrayList<WuReportResp>();
		for(WuReport wu:list){
			WuReportResp wuReportResp = new WuReportResp();
			wuReportResp.setRoutename(wu.getRoutename());
			wuReportResp.setRouteid(wu.getRouteid());
			listWu.add(wuReportResp);
		}
		return listWu;
	}

}
