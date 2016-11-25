package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICreditManageService;
import com.tianrui.api.req.front.CreditScore.CreditScoreReq;
import com.tianrui.api.req.front.creditManage.CreditManageReq;
import com.tianrui.api.resp.front.CreditScore.CreditScoreResp;
import com.tianrui.api.resp.front.creditManage.CreditManageResp;
import com.tianrui.service.bean.CreditManage;
import com.tianrui.service.bean.CreditScore;
import com.tianrui.service.mapper.CreditManageMapper;
import com.tianrui.service.mapper.CreditScoreMapper;
@Service
public class CreditManageService implements ICreditManageService {
	
	@Autowired
	private CreditManageMapper creditManageMapper;
	@Autowired
	private CreditScoreMapper creditScoreMapper;
	
	public List<CreditManageResp> queryCredit(CreditManageReq req) throws Exception{
		if(req != null){
			CreditManage cm = new CreditManage();
			if(StringUtils.isBlank(req.getCredityear()) && StringUtils.isBlank(req.getCreditmonth())){
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1;
				String credityear = String.valueOf(year);
				String creditmonth = String.valueOf(month > 10 ? month : "0"+month);
				cm.setCredityear(credityear);
				cm.setCreditmonth(creditmonth);
			}else{
				cm.setCredityear(req.getCredityear());
				cm.setCreditmonth(req.getCreditmonth());
			}
			List<CreditManage> list = creditManageMapper.selectByPrimaryKeySelective(cm);
			List<CreditManageResp> listResp = null;
			if(list != null && list.size() > 0){
				listResp = new ArrayList<CreditManageResp>();
				for(int i=0;i<list.size();i++){
					CreditManageResp resp = copyBean2Resp(list.get(i));
					CreditScore cs = creditScoreMapper.queryByCreditManageId(resp.getId());
					resp.setCreditScoreResp(copyBean2Resp(cs));
					listResp.add(resp);
				}
			}
			return listResp;
		}
		return null;
	}
	
	private CreditScoreResp copyBean2Resp(CreditScore cs) throws Exception {
		CreditScoreResp resp = null; 
		if(cs != null){
			resp = new CreditScoreResp();
			PropertyUtils.copyProperties(resp, cs);
		}
		return resp;
	}
	
	private CreditManageResp copyBean2Resp(CreditManage cm) throws Exception {
		CreditManageResp resp = null; 
		if(cm != null){
			resp = new CreditManageResp();
			PropertyUtils.copyProperties(resp, cm);
		}
		return resp;
	}
	
	public int updateCreditScore(CreditScoreReq req) throws Exception {
		int index = 0;
		if(req != null){
			CreditScore cs = new CreditScore();
			PropertyUtils.copyProperties(cs, req);
			index = creditScoreMapper.updateByPrimaryKeySelective(cs);
		}
		return index;
	}
}