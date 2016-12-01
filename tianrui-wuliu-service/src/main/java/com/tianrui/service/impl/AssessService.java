package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.IAssessService;
import com.tianrui.api.req.front.bill.BillAssessReq;
import com.tianrui.api.resp.front.bill.BillAssessResp;
import com.tianrui.api.util.DateTypeUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.service.bean.Assess;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.CreditManage;
import com.tianrui.service.bean.CreditScore;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.mapper.AssessMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.CreditManageMapper;
import com.tianrui.service.mapper.CreditScoreMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;

@Service
public class AssessService implements IAssessService {
    
	@Autowired
	private AssessMapper assessMapper;
	@Autowired
	private BillMapper billMapper;
	@Autowired
	private CreditManageMapper creditManageMapper;
	@Autowired
	private MemberVoService memberVoService;
	@Autowired
	private MemberVehicleMapper memberVehicleMapper;
	@Autowired
	private CreditScoreMapper creditScoreMapper;

	@Transactional
	@Override
	public int saveAssess(BillAssessReq req) throws Exception {
		if(req != null){
			Assess assess = new Assess();
			PropertyUtils.copyProperties(assess, req);
			int count = assessMapper.insert(assess);
			if(count == 1){
				Bill bill = new Bill();
				bill.setId(req.getBillid());
				bill.setIsAssess("1");
				billMapper.updateByPrimaryKeySelective(bill);
			}
			Bill bill = billMapper.selectByPrimaryKey(req.getBillid());
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(bill.getCreatetime());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String credityear = String.valueOf(year);
			String creditmonth = String.valueOf(month >= 10 ? month : "0"+month);
			CreditManage cm = new CreditManage();
			cm.setVenderid(bill.getVenderid());
			cm.setCredityear(credityear);
			cm.setCreditmonth(creditmonth);
			//查询车主信用 
			List<CreditManage> list = creditManageMapper.selectByPrimaryKeySelective(cm);
			//查询车主信息
			MemberVo vo = memberVoService.get(bill.getVenderid(), true);
			cm.setVendername(vo.getRealName());
			cm.setVendertel(vo.getCellphone());
			///查询车主评价信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("venderid", bill.getVenderid());
			c.set(year, month-1, 1, 0, 0, 0);
			params.put("starttime", c.getTimeInMillis());
			c.set(year, month, 1, 0, 0, 0);
			params.put("endtime", c.getTimeInMillis());
			List<Assess> assessList = assessMapper.queryAssessByVenderId(params);
			//查询承运次数
			cm.setBillcount(String.valueOf(billMapper.queryCountByVenderId(params)));
			//查询拥有车辆数
			MemberVehicle mv = new MemberVehicle();
			mv.setMemberid(bill.getVenderid());
			mv.setStatus("1");
			cm.setVehiclesum(String.valueOf(memberVehicleMapper.selectCountByCondition(mv)));
			if(assessList != null && assessList.size() > 0){
				int timelystartCount = 0;
				int timelydeliveryCount = 0;
				int timelyreturnCount = 0;
				int cardamageCount = 0;
				int transportaccidentCount = 0;
				for(Assess a : assessList){
					if(StringUtils.equals("1", a.getTimelystart())){
						timelystartCount++;
					}
					if(StringUtils.equals("1", a.getTimelydelivery())){
						timelydeliveryCount++;
					}
					if(StringUtils.equals("1", a.getTimelyreturn())){
						timelyreturnCount++;
					}
					if(StringUtils.equals("1", a.getCardamage())){
						cardamageCount++;
					}
					if(StringUtils.equals("2", a.getTransportaccident())){
						transportaccidentCount++;
					}
				}
				cm.setTimelystart(DateTypeUtil.DoubleNumTo2decimal((timelystartCount/Double.valueOf(assessList.size()))*100)+"%");
				cm.setTimelydelivery(DateTypeUtil.DoubleNumTo2decimal((timelydeliveryCount/Double.valueOf(assessList.size()))*100)+"%");
				cm.setTimelyreturn(DateTypeUtil.DoubleNumTo2decimal((timelyreturnCount/Double.valueOf(assessList.size()))*100)+"%");
				cm.setCardamage(DateTypeUtil.DoubleNumTo2decimal((cardamageCount/Double.valueOf(assessList.size()))*100)+"%");
				cm.setTransportaccident(DateTypeUtil.DoubleNumTo2decimal((transportaccidentCount/Double.valueOf(assessList.size()))*100)+"%");
			}
			if(list == null || list.size() == 0){
				//新增信用记录
				cm.setId(UUIDUtil.getId());
				cm.setCreatetime(System.currentTimeMillis());
				creditManageMapper.insert(cm);
				CreditScore cs = new CreditScore();
				cs.setId(UUIDUtil.getId());
				cs.setCreditmanageid(cm.getId());
				cs.setAddassist(0);
				cs.setAddcontribute(0);
				cs.setAddspecial(0);
				cs.setSubaccident(0);
				cs.setSubcomplaint(0);
				cs.setSubdisrupt(0);
				cs.setSubmanned(0);
				cs.setSuboperation(0);
				cs.setSubturnover(0);
				cs.setCreatetime(System.currentTimeMillis());
				creditScoreMapper.insert(cs);
			}else{
				//更新信用记录
				cm.setId(list.get(0).getId());
				creditManageMapper.updateByPrimaryKeySelective(cm);
			}
			return count;
		}
		return 0;
	}
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2016, 10, 1, 0, 0, 0);
		System.out.println(c.getTimeInMillis());
		c.set(2016, 11, 1, 0, 0, 0);
		System.out.println(c.getTimeInMillis());
		System.out.println(1477929600609L-1480521600609L);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
	}

	@Override
	public BillAssessResp queryAssess(BillAssessReq req) throws Exception {
		if(req != null){
			Assess assess = assessMapper.queryAssessByBillId(req.getBillid());
			BillAssessResp resp = new BillAssessResp();
			PropertyUtils.copyProperties(resp, assess);
			return resp;
		}
		return null;
	}
	
}