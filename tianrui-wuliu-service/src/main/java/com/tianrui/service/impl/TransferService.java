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
import com.tianrui.api.resp.front.transfer.TransferResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillUpdate;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.Message;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.Transfer;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.TransferMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.MessageMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
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
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	private MessageMapper messagemapper;
	
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
		
		//判断司机是否收回请求
		Transfer qure = new Transfer();
		qure.setStartid(req.getStartid());
		qure.setStatus("0");
		List<Transfer> count = transferMapper.selectByCondition(qure);
		if(count.size()==0){
			rs.setCode("1");
			rs.setError("该司机暂无转运申请，或司机已近收回转运请求");
			return rs;
		}
		//通过车辆id查询车辆
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(count.get(0).getVehicleid());
				
		//判断司机车主当前绑定关系
		VehicleDriver vehicleDriver = new VehicleDriver();
		vehicleDriver.setCreator(vehicle.getMemberid());
		vehicleDriver.setDriverid(req.getStartid());
		List<VehicleDriver> vd = vehicleDriverMapper.selectMyVehiDriverByCondition(vehicleDriver);
		if(vd.size()!=1){//司机晕车辆绑定关系不唯一，操作失败
			rs.setCode("3");
			rs.setError("绑定关系出错，转运失败");
			return rs;
		}
		Transfer record = new Transfer();
		PropertyUtils.copyProperties(record, req);
		//查询司机同一车主下未完成运单
		Bill bil = new Bill();
		bil.setDriverid(req.getStartid());
		bil.setVenderid(vehicle.getMemberid());
		List<Bill> list = billMapper.selectByBillTransfer(bil);
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
			
			SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getSendid());
			//批量修改运单司机
			BillUpdate upt = new BillUpdate();
			upt.setStartdriverid(req.getStartid());
			upt.setDriverid(req.getSendid());
			upt.setDrivername(req.getSender());
			upt.setDrivertel(member.getCellphone());
			upt.setVenderid(vehicle.getMemberid());
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
		Bill bil =new Bill();
		bil.setDriverid(req.getStartid());
		bil.setVenderid(req.getMemberid());
		List<Bill> list = billMapper.selectByBillTransfer(bil);
		for(Bill b : list){
			record.setId(UUIDUtil.getId());
			record.setBillid(b.getId());
			record.setVehicleid(b.getVehicleid());
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
		//TODO
		Message mass = new Message();
		mass.setSendid(driverid);
		mass.setIsreply("0");
		mass.setCode("221");
		List<Message> list = messagemapper.findByEntity(mass);
		for(Message mes : list){
			mes.setIsreply("3");
			messagemapper.updateByPrimaryKeySelective(mes);
		}
		return rs;
	}
	//是否已申请过交班
	@Override
	public int isHand(String driverid) throws Exception{
		Transfer qure = new Transfer();
		qure.setStartid(driverid);
		qure.setStatus("0");
		List<Transfer> count = transferMapper.selectByCondition(qure);
		if(count.size()==0){
			return 0;
		}else{
			return 1;
		}
	}
	//是否有收到交班申请
	@Override
	public List<TransferResp> isAccept(String driverid) throws Exception{
		Transfer qure = new Transfer();
		qure.setSendid(driverid);
		qure.setStatus("0");
		List<TransferResp> listResp = new ArrayList<TransferResp>();
		List<Transfer> list = transferMapper.selectByCondition(qure);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				list.get(i).setBillid(billMapper.selectByPrimaryKey(list.get(i).getBillid()).getWaybillno());
				TransferResp trp = conver2TransferResp(list.get(i));
				listResp.add(trp);
			}
		}
		return listResp;
	}
	public TransferResp conver2TransferResp(Transfer transfer) throws Exception{
		TransferResp resp = null;
		if(transfer!=null){
			resp = new TransferResp();
			PropertyUtils.copyProperties(resp, transfer);
		}
		return resp;
	}
}
