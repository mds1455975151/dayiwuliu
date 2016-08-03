package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.ITransferService;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.transfer.TransferReq;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillUpdate;
import com.tianrui.service.bean.Transfer;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.TransferMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
import com.tianrui.service.mapper.BillMapper;
@Service
public class TransferService implements ITransferService{

	@Autowired
	TransferMapper transferMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	IMessageService messageService;
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	
	@Override
	public Result update(TransferReq req)throws Exception {
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(req.getStartid())){
			rs.setCode("1");
			rs.setError("请求人id不能为空");
			return rs;
		}
		if(StringUtils.isBlank(req.getSendid())){
			rs.setCode("2");
			rs.setError("接收人id不能为空");
			return rs;
		}
		//判断司机车辆当前绑定关系
		VehicleDriver vehicleDriver = new VehicleDriver();
		vehicleDriver.setVehicleno(req.getVehicleno());
		vehicleDriver.setDriverid(req.getStartid());
		List<VehicleDriver> vd = vehicleDriverMapper.selectMyVehiDriverByCondition(vehicleDriver);
		if(vd.size()!=1){//司机晕车辆绑定关系不唯一，操作失败
			rs.setCode("3");
			rs.setError("绑定关系出错，转运失败");
			return rs;
		}
		
		Transfer record = new Transfer();
		PropertyUtils.copyProperties(record, req);
		//查询司机名下所有运单未完成
		List<Bill> list = billMapper.selectByBillTransfer(req.getStartid());
		for(Bill b : list){
			record.setBillid(b.getId());
			transferMapper.updateByStatus(record);
		}
		//发送消息提醒
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(record.getSender());
		mreq.setParams(strs);
		//转运记录 发起人，接收人。请查看数据库注释
		mreq.setSendid(record.getSendid());//发信人id
		mreq.setSendname(record.getSender());//发信人名称
		mreq.setType("1");
		mreq.setRecid(record.getStartid());//收信人
		mreq.setRecname(record.getStarter());//收信人名称
		
		//判断同意 还是拒绝  0-未处理，1-同意，2 -拒绝'
		if("1".equals(record.getStatus())){
			//同意换班
			//修改车辆司机绑定关系
			VehicleDriver vhd = vd.get(0);
			vhd.setDriverid(record.getSendid());
			vhd.setDrivername(record.getSender());
			vhd.setDrivertel(record.getSendtele());
			vehicleDriverMapper.updateByPrimaryKeySelective(vhd);
			//批量修改运单司机
			BillUpdate upt = new BillUpdate();
			upt.setStartdriverid(req.getStartid());
			upt.setDriverid(req.getSendid());
			upt.setDrivername(req.getSender());
			upt.setDrivertel(req.getSendtele());
			billMapper.updateByBillTransfer(upt);
			mreq.setCodeEnum(MessageCodeEnum.DRIVER_TRANSFER_AGREE);
			messageService.sendMessageInside(mreq);
		}else if("2".equals(record.getStatus())){
			//拒绝换班
			mreq.setCodeEnum(MessageCodeEnum.DRIVER_TRANSFER_REFUSE);
			messageService.sendMessageInside(mreq);
		}
		
		return rs;
	}

	@Override
	public Result save(TransferReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Transfer qure = new Transfer();
		qure.setStartid(req.getStartid());
		qure.setStatus("0");
		List<Transfer> count = transferMapper.selectByCondition(qure);
		if(count.size()!=0){
			rs.setCode("1");
			rs.setError("有未处理转运请请求，不能重复申请转运");
			return rs;
		}
		Transfer record = new Transfer();
		PropertyUtils.copyProperties(record, req);
		List<Bill> list = billMapper.selectByBillTransfer(req.getStartid());
		for(Bill b : list){
			record.setBillid(b.getId());
			transferMapper.insertSelective(record);
		}
		//发送消息提醒
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(record.getStarter());
		//转运记录 发起人，接收人。请查看数据库注释
		mreq.setSendid(record.getStartid());//发信人id
		mreq.setSendname(record.getStarter());//发信人名称
		mreq.setParams(strs);
		mreq.setType("1");
		mreq.setRecid(record.getSendid());//收信人id
		mreq.setRecname(record.getSender());//收信人名称
		mreq.setCodeEnum(MessageCodeEnum.DRIVER_TRANSFER_BEG);
		messageService.sendMessageInside(mreq);
		return rs;
	}

	@Override
	public Result delete(String driverid) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		Transfer qure = new Transfer();
		qure.setStartid(driverid);
		qure.setStatus("0");
		List<Transfer> count = transferMapper.selectByCondition(qure);
		if(count.size()==0){
			rs.setCode("1");
			rs.setError("该司机暂无转运申请");
			return rs;
		}
		for(Transfer t : count){
			transferMapper.deleteByPrimaryKey(t.getId());
		}
		return rs;
	}

}
