package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.MemberCapa;
import com.tianrui.service.bean.MemberCapaList;
import com.tianrui.service.bean.MemberOwner;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.MemberCapaMapper;
import com.tianrui.service.mapper.MemberOwnerMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;
@Service
public class MemberCapaService implements IMemberCapaService{
	
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	MemberCapaMapper memberCapaMapper;
	@Autowired
	IMessageService messageService;
	@Autowired
	SystemMemberInfoMapper systemInfoMember;
	@Autowired
	PlanMapper planMapper;
	@Autowired
	MemberOwnerMapper memberOwnerMapper;
	@Autowired
	BillMapper billMapper;
	
	@Override
	public PaginationVO<MemberCapaListResp> index(CapaReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberCapa capa = new MemberCapa();
		capa.setMemberid(req.getMemberid());
		capa.setVehicleno(req.getVehicleno());
		capa.setDrivername(req.getDrivername());
		capa.setDrivertel(req.getDrivertel());
		capa.setType(req.getType());
		capa.setSearch(req.getSearch());
		capa.setStart((req.getPageNo()-1)*req.getPageSize());
		capa.setLimit(req.getPageSize());
		List<MemberCapaList> list = memberCapaMapper.selectByCondition(capa);
		long total = memberCapaMapper.selectByVDCount(capa);
		long mc = memberCapaMapper.selectByMCCount(capa);
		PaginationVO<MemberCapaListResp> page = new PaginationVO<MemberCapaListResp>();
		page.setList(copyMembercapa(list));
		page.setPageNo(req.getPageNo());
		page.setTotal(total+mc);
		rs.setData(page);
		return page;
	}
	
	private List<MemberCapaListResp> copyMembercapa(List<MemberCapaList> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MemberCapaListResp> n = new ArrayList<MemberCapaListResp>();
		for(MemberCapaList cop : list){
			MemberCapaListResp resp = new MemberCapaListResp();
			PropertyUtils.copyProperties(resp, cop);
			n.add(resp);
		}
		return n;
	}

	@Override
	public Result selectVehicle(CapaReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		VehicleDriver vd = new VehicleDriver();
		vd.setQueryKey(req.getSearch());
		List<VehicleDriver> list = vehicleDriverMapper.selectMyVehiDriverByCondition(vd);
		if(list.size()!=1){
			rs.setErrorCode(ErrorCode.VEHICLE_CAPA_VEHICLE);
			return rs;
		}
		rs.setData(list.get(0));
		return rs;
	}

	@Override
	public Result save(CapaReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberCapa mc = new MemberCapa();
		mc.setVehicleid(req.getVehicleid());
		mc.setMemberid(req.getMemberid());
		List<MemberCapaList> list = memberCapaMapper.selectByCondition(mc);
		if(list.size() != 0){
			if("2".equals(list.get(0).getStatus())){
				memberCapaMapper.deleteByPrimaryKey(list.get(0).getId());
			}else{
				rs.setErrorCode(ErrorCode.VEHICLE_CAPA_EXIST);
				return rs;
			}
		}
		String id = UUIDUtil.getId();
		req.setId(id);
		insert(req);
		
		SystemMemberInfo info = systemInfoMember.selectByPrimaryKey(req.getOwnerid());
		String recName = "";
		if(StringUtils.isNotBlank(info.getUsername())){
			recName = info.getUsername();
		}else if(StringUtils.isNotBlank(info.getCompanyname())){
			recName = info.getCompanyname();
		}
		//发送消息
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(req.getCellphone());
		strs.add(req.getVehicleno());
		mreq.setType("2");
		mreq.setKeyid(id);
		mreq.setSendid(req.getMemberid());
		mreq.setSendname(req.getCellphone());
		mreq.setRecid(req.getOwnerid());
		mreq.setRecname(recName);
		mreq.setParams(strs);
		mreq.setCodeEnum(MessageCodeEnum.DRIVER_CAPA_BEG);
		messageService.sendMessageInside(mreq);
		return rs;
	}

	/** 数据插入*/
	protected int  insert(CapaReq req) {
		MemberCapa capa = new MemberCapa();
		capa.setId(req.getId());
		capa.setMemberid(req.getMemberid());
		capa.setVehicleid(req.getVehicleid());
		capa.setOwnerid(req.getOwnerid());
		capa.setStatus("0");
		capa.setCreater(req.getMemberid());
		capa.setCreatetime(new Date().getTime());
		return memberCapaMapper.insertSelective(capa);
	}
	
	@Override
	public Result update(CapaReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberCapa capa = new MemberCapa();
		capa.setId(req.getId());
		capa.setStatus(req.getStatus());
		int a = memberCapaMapper.updateByPrimaryKeySelective(capa);
		if(a != 1){
			rs.setCode("1");
			rs.setError("修改失败");
		}
		return rs;
	}

	@Override
	public Result delete(CapaReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		int a = memberCapaMapper.deleteByPrimaryKey(req.getId());
		if(a!=1){
			rs.setCode("1");
			rs.setError("删除失败");
		}
		return rs;
	}
	
	@Override
	public Result checkCapaState(String capaId, String venderid){
		Result rs = Result.getSuccessResult();
		List<Bill> list = billMapper.selectBillByCapaId(capaId,venderid);
		if(list != null && list.size() > 0){
			rs.setCode("000001");
			rs.setData("该运力有未完成的运单！是否继续删除？");
		}
		return rs;
	}
	
	@Override
	public List<MemberCapaListResp> createBill(CapaReq req) throws Exception {
		MemberCapa capa = new MemberCapa();
		capa.setMemberid(req.getMemberid());
		capa.setSearch(req.getSearch());
		capa.setType("capa");
		List<MemberCapaList> list = memberCapaMapper.selectByCondition(capa);
		return copyMembercapa(list);
	}
	/**判断是否为车主*/
	@Override
	public long indexCount(CapaReq req) throws Exception {
		// TODO Auto-generated method stub
		MemberCapa capa = new MemberCapa();
		capa.setMemberid(req.getMemberid());
		capa.setStatus(req.getStatus());
		MemberOwner owner = new MemberOwner();
		owner.setMemberid(req.getMemberid());
		owner.setStatus("1");
		long ow = memberOwnerMapper.selectCountByCondition(owner);
		long total = memberCapaMapper.selectByVDCount(capa);
		long mc = memberCapaMapper.selectByMCCount(capa);
		return total + mc + ow;
	}

}
