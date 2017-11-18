package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyPendingBillMoney;
import com.tianrui.service.mapper.MoneyPendingBillMoneyMapper;

@Service
public class PendingBillMoneyService implements IPendingBillMoneyService {

	Logger logger=LoggerFactory.getLogger(PendingBillMoneyService.class);
	@Autowired
	private MoneyPendingBillMoneyMapper billMoney;
	@Override
	public Result save(SaveBillMoneyReq req) {
		
		Result rs = Result.getSuccessResult();
		MoneyPendingBillMoney mbm = new MoneyPendingBillMoney();
		try {
			PropertyUtils.copyProperties(mbm, req);
			int r = 0;
			r = billMoney.insertSelective(mbm);
			if(r == 0){
				rs.setCode("2");
				rs.setError("数据保存失败");
			}
		} catch (IllegalAccessException e) {
			rs.setCode("1");
			rs.setError(e.getMessage());
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			rs.setCode("1");
			rs.setError(e.getMessage());
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			rs.setCode("1");
			rs.setError(e.getMessage());
			logger.error(e.getMessage());
		}
		return rs;
	}

}
