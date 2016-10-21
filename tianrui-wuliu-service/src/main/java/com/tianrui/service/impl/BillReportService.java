package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IBillReportService;
import com.tianrui.api.req.front.report.ReportReq;
import com.tianrui.api.resp.front.report.ReportResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.impl.FreightInfoService;
import com.tianrui.service.bean.BillReport;
import com.tianrui.service.mapper.BillReportMapper;
import com.tianrui.service.util.TimeUtils;

/**
 * @author zhanggaohao
 * @createtime 2016年10月18日 下午4:49:46
 * @classname 运单报表接口实现类
 */
@Service
public class BillReportService implements IBillReportService{
	
	@Autowired
	private BillReportMapper billReportMapper;
	
	@Autowired
	private MemberVoService memberVoService;
	
	@Autowired
	private FreightInfoService freightInfoService;
	
	@Override
	public PaginationVO<ReportResp> queryReport(ReportReq req) throws Exception {
		PaginationVO<ReportResp> page = null;
		if(req != null){
			page = new PaginationVO<ReportResp>();
			if(StringUtils.isNotBlank(req.getStarttimeStr())){
				req.setStarttime(TimeUtils.StringZoLong(req.getStarttimeStr() + " 00:00:00"));
			}
			if(StringUtils.isNotBlank(req.getEndtimeStr())){
				req.setEndtime(TimeUtils.StringZoLong(req.getEndtimeStr() + "00:00:00"));
			}
			long count = billReportMapper.queryBillReportCount(req);
			if(count > 0){
				Integer start = (req.getPageNo() - 1) * req.getPageSize();
				req.setStart(start);
				req.setLimit(req.getPageSize());
				List<BillReport> list = billReportMapper.queryBillReport(req);
				List<ReportResp> respList = copyBean2Resp(list);
				page.setList(respList);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}
	
	@Override
	public ReportResp querReportDetail(ReportReq req, boolean isOwner) throws Exception {
		ReportResp resp = null;
		if(req != null){
			BillReport report = billReportMapper.querReportDetail(req);
			if(isOwner){
				if(report != null && StringUtils.equals(report.getIsAppoint(), "1")){
					String memberid = report.getPathID().split(",")[1];
					MemberVo vo = memberVoService.get(memberid);
					report.setVendername(vo.getRealName());
				}
			}
			resp = copyBean2Resp(report);
			if(StringUtils.isBlank(report.getIsClearing()) || StringUtils.equals(report.getIsClearing(), "0")){
				Date date = null;
				if(report.getUnloadtime() == null){
					date = new Date();
				}else{
					date = new Date(report.getUnloadtime());
				}
				FileFreight fileFreight = (FileFreight) freightInfoService.findFreightInfo(report.getFreightid(), date).getData();
				if(fileFreight != null){
					resp.setTallage(fileFreight.getTallage());
					resp.setPrice(fileFreight.getPrice());
				}
			}
		}
		return resp;
	}
	
	private ReportResp copyBean2Resp(BillReport bean) throws Exception {
		ReportResp resp = null;
		if(bean != null){
			resp = new ReportResp();
			PropertyUtils.copyProperties(resp, bean);
			resp.setBillcreatetimeStr(TimeUtils.LongZoString(bean.getBillcreatetime()));
			resp.setPlancreatetimeStr(TimeUtils.LongZoString(bean.getPlancreatetime()));
			resp.setPlanstarttimeStr(TimeUtils.LongZoString(bean.getPlanstarttime()));
			resp.setPlanendtimeStr(TimeUtils.LongZoString(bean.getPlanendtime()));
		}
		return resp;
	}
	
	private List<ReportResp> copyBean2Resp(List<BillReport> list) throws Exception {
		List<ReportResp> respList = null;
		if(list != null && list.size() > 0){
			respList = new ArrayList<ReportResp>();
			for(BillReport br : list){
				respList.add(copyBean2Resp(br));
			}
		}
		return respList;
	}
}
