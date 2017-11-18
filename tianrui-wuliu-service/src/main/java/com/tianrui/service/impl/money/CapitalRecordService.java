package com.tianrui.service.impl.money;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyCapitalAccount;
import com.tianrui.service.bean.MoneyCapitalRecord;
import com.tianrui.service.mapper.MoneyCapitalAccountMapper;
import com.tianrui.service.mapper.MoneyCapitalRecordMapper;

@Service
public class CapitalRecordService implements ICapitalRecordService {

	Logger logger=LoggerFactory.getLogger(CapitalRecordService.class);
	@Autowired 
	private MoneyCapitalRecordMapper recordMapper;
	@Override
	public Result save(CapitalRecordReq req,TransactionType type) {
		Result rs = Result.getSuccessResult();
		MoneyCapitalRecord mRecord = null;
		if(null != req.getUseryhno() && !"".equals(req.getUseryhno())){
			mRecord = recordMapper.selectByUseryhno(req.getUseryhno());
		}else if (null != req.getCellphone() && !"".equals(req.getCellphone())) {
			mRecord = recordMapper.selectByCellphone(req.getCellphone());
		}else {
			rs.setCode("01");
			rs.setError("用户登录账号和银行唯一标识不能为空");
			return rs;
		}
		MoneyCapitalRecord newRecord = new MoneyCapitalRecord();
		if(type.getType() == 11){
			if(null == mRecord){
					//创建第一条资金流水
				newRecord.setAvailablemoney(req.getAvailablemoney());
				newRecord.setCapitalno(req.getCapitalno());
				newRecord.setCellphone(req.getCellphone());
				newRecord.setCreatetime(new Date().getTime());
				newRecord.setExpenditure(0L);
				newRecord.setIncome(req.getAvailablemoney());
				newRecord.setMoney(req.getAvailablemoney());
				newRecord.setOtherlockmoney(0L);
				newRecord.setTotalmoney(req.getAvailablemoney());
				newRecord.setTransactiontype((long) type.getType());
				newRecord.setUsername(req.getUsername());
				newRecord.setUseryhno(req.getUseryhno());
				newRecord.setWithdrawalslockmoney(0L);
			}else{
				//在原有资金流水基础上 新增资金流水
				newRecord.setAvailablemoney(mRecord.getAvailablemoney()+req.getAvailablemoney());
				newRecord.setCapitalno(req.getCapitalno());
				newRecord.setCreatetime(new Date().getTime());
				newRecord.setExpenditure(0L);
				newRecord.setIncome(req.getAvailablemoney());
				newRecord.setMoney(req.getAvailablemoney());
				newRecord.setTotalmoney(mRecord.getTotalmoney() + req.getAvailablemoney());
				newRecord.setTransactiontype((long) type.getType());
			}
		}else {
			rs.setCode("111111");
			rs.setError("不支持的交易类型");
		}
		int r = 0;
		r = recordMapper.insert(newRecord);
		if(r == 0){
			rs.setCode("2");
			rs.setError("数据保存失败");
		}
		return rs;
	}

}
