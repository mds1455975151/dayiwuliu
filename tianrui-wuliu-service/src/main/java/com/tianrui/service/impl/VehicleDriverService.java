package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.admin.anlian.AnlianDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverMemberReq;
import com.tianrui.api.req.front.vehicle.VehicleDriverReq;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverMemberResp;
import com.tianrui.api.resp.front.vehicle.VehicleDriverResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Members;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.OwnerDriver;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.VehicleDriver;
import com.tianrui.service.bean.VehicleDriverMember;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleDriverMapper;

/**
 * 车辆司机关联接口实现类
 * <p>
 * @author guyuke
 * @time 2016年6月6日 下午2:15:00
 */
@Service
public class VehicleDriverService implements IVehicleDriverService {
	
	@Autowired
	VehicleDriverMapper vehicleDriverMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	IAnlianService anlianService;
	@Autowired
	OwnerDriverMapper ownerDriverMapper;
	
	/**
	 * 父类方法重写，根据条件进行车辆司机关联信息查询
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#queryVehiDriverByPage(com.tianrui.api.req.admin.VehicleDriverReq)
	 */
	@Override
	public PaginationVO<VehicleDriverResp> queryVehiDriverByPage(VehicleDriverReq req)
			throws Exception {
		
		// 分页VO
		PaginationVO<VehicleDriverResp> pageVo =new PaginationVO<VehicleDriverResp>();
		VehicleDriver reqCondition = new VehicleDriver();
		copyProperties(reqCondition, req);
		
		long total = vehicleDriverMapper.selectCountByCondition(reqCondition);
		
		if (total == 0) {
			return pageVo;
		} else {
			if (req.getPageNo() <= 0) {
				req.setPageNo(1);
			}
			// 查询开始位置
			reqCondition.setStart((req.getPageNo()-1)*req.getPageSize());
			// 查询数量
			reqCondition.setLimit(req.getPageSize());
			// 查询操作
			List<VehicleDriver> orgCargoList = vehicleDriverMapper.selectMyVehiDriverByCondition(reqCondition);
			
			// 数据转换
			List<VehicleDriverResp> orgCargoRespList = convert2VehicleDriverList(orgCargoList);
			pageVo.setList(orgCargoRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	/**
	 * 父类方法重写，根据条件进行车辆司机关联信息查询
	 * 
	 * @see com.tianrui.api.intf.IVehicleDriverService#queryVehiDriverByCondition(com.tianrui.api.req.front.vehicle.VehicleDriverReq)
	 */
	@Override
	public List<VehicleDriverResp> queryVehiDriverByCondition(VehicleDriverReq req)
			throws Exception {
		
		VehicleDriver reqCondition = new VehicleDriver();
		// 复制前台内容至查询条件
		copyProperties(reqCondition, req);
		// 查询操作 
		List<VehicleDriver> ownDriverList = vehicleDriverMapper.selectMyVehiDriverByCondition(reqCondition);
		
		// 数据转换
		List<VehicleDriverResp> ownDriverRespList = convert2VehicleDriverList(ownDriverList);
		
		return ownDriverRespList;
	}
	
	/**
	 * 父类方法重写，车辆司机关联信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#insert(com.tianrui.api.req.admin.VehicleDriverReq)
	 */
	@Override
	public Result insert(VehicleDriverReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		VehicleDriver vehicleDriver = new VehicleDriver();
		copyProperties(vehicleDriver, req);
		
		// 设置创建时间
		if (vehicleDriver.getCreatetime() == null) {
			vehicleDriver.setCreatetime(new Date().getTime());
		}
		//TODO
		MemberVehicle mv = memberVehicleMapper.selectByPrimaryKey(req.getVehicleId());
		SystemMember sm = systemMemberMapper.selectByPrimaryKey(req.getDriverId());
		//车辆通过安联认证，司机通过本地安联认证
		if("1".equals(mv.getDesc1())&&(StringUtils.isNotBlank(sm.getAldriverid()))){
			// 数据插入操作
			AnlianDriverReq r = new AnlianDriverReq();
			r.setDriverid(req.getDriverId());
			r.setVehicleNo(mv.getVehicleprefix()+mv.getVehicleno());
			rs = anlianService.driver(r);
			//安联认证成功
			if("000000".equals(rs.getCode())){
				SystemMember m = new SystemMember();
				m.setId(req.getDriverId());
				m.setAldriverid(rs.getData().toString());
				systemMemberMapper.updateByPrimaryKeySelective(m);
			}else{
				return rs;
			}
		}
		vehicleDriverMapper.insert(vehicleDriver);
		return rs;
	}
	
	/**
	 * 父类方法重写，车辆司机关联信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#update(com.tianrui.api.req.admin.VehicleDriverReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(VehicleDriverReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		VehicleDriver vehicleDriver = new VehicleDriver();
		copyProperties(vehicleDriver, req);
		
		// 数据更新操作
		long count = vehicleDriverMapper.updateByPrimaryKeySelective(vehicleDriver);
		
		rs.setData(count);
		
		return rs;
	}
	
	/**
	 * 父类方法重写，车辆司机关联信息删除功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IVehicleDriverService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		VehicleDriver vd = vehicleDriverMapper.selectByPrimaryKey(id);
		if(vd!=null){
			SystemMember member = systemMemberMapper.selectByPrimaryKey(vd.getDriverid());
			MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(vd.getVehicleid());
			//安联 司机 车辆 认证通过 不能解除绑定关系
			if(StringUtils.isNotBlank(member.getAldriverid())&&"1".equals(vehicle.getDesc1())){
				rs.setCode("1");
				rs.setError("安联认证通过的司机-车辆，不能解除绑定关系");
				return rs;
			}
		}
		
		// 数据删除操作
		long count = vehicleDriverMapper.deleteByPrimaryKey(id);
		long a = 1;
		if(count!=a){
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		
		return rs;
	}
	
	/**
	 * 不同类型的List间转换操作
	 * <p>
	 * @param argVehicleDriverList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 下午6:23:00
	 */
	private List<VehicleDriverMemberResp> convert2VehicleDriverMemberList(List<VehicleDriverMember> argVehicleDriverList) {
		List<VehicleDriverMemberResp> vehicleDriverRespList = null;
		if (argVehicleDriverList != null) {
			vehicleDriverRespList = new ArrayList<VehicleDriverMemberResp>();
			for (VehicleDriverMember vehicleDriver : argVehicleDriverList) {
				VehicleDriverMemberResp vehicleDriverResp =  new VehicleDriverMemberResp();
				// 主键
				vehicleDriverResp.setId(vehicleDriver.getId());
				vehicleDriverResp.setIds(vehicleDriver.getIds());
				// 司机主键
				vehicleDriverResp.setDriverId(vehicleDriver.getDriverid());;
				// 车辆主键
				vehicleDriverResp.setVehicleId(vehicleDriver.getVehicleid());
				// 车牌号
				vehicleDriverResp.setVehicleNo(vehicleDriver.getVehicleno());
				vehicleDriverResp.setVehicleprefix(vehicleDriver.getVehicleprefix());
				// 车型
				vehicleDriverResp.setVehicleTypeName(vehicleDriver.getVehicletypename());
				// 车辆备注名
				vehicleDriverResp.setVehicleRemark(vehicleDriver.getVehicleremark());
				// 司机名字
				vehicleDriverResp.setDriverName(vehicleDriver.getDrivername());
				// 司机电话
				vehicleDriverResp.setDriverTel(vehicleDriver.getDrivertel());
				// 司机备注名
				vehicleDriverResp.setDriverRemark(vehicleDriver.getDriverremark());
				// 创建人
				vehicleDriverResp.setCreator(vehicleDriver.getCreator());
				// 
				vehicleDriverResp.setAldriverid(vehicleDriver.getAldriverid());
				// 
				vehicleDriverResp.setDesc1(vehicleDriver.getDesc1());
				// 创建时间
				if(vehicleDriver.getCreatetime()!=null){
					vehicleDriverResp.setCreateTime(convertLongTODate(vehicleDriver.getCreatetime()));
				}
				vehicleDriverRespList.add(vehicleDriverResp);
			}
		}
		return vehicleDriverRespList;
	}
	
	private List<VehicleDriverResp> convert2VehicleDriverList(List<VehicleDriver> argVehicleDriverList) {
		List<VehicleDriverResp> vehicleDriverRespList = null;
		if (argVehicleDriverList != null) {
			vehicleDriverRespList = new ArrayList<VehicleDriverResp>();
			for (VehicleDriver vehicleDriver : argVehicleDriverList) {
				VehicleDriverResp vehicleDriverResp =  new VehicleDriverResp();
				// 主键
				vehicleDriverResp.setId(vehicleDriver.getId());
				// 司机主键
				vehicleDriverResp.setDriverId(vehicleDriver.getDriverid());;
				// 车辆主键
				vehicleDriverResp.setVehicleId(vehicleDriver.getVehicleid());
				// 车牌号
				vehicleDriverResp.setVehicleNo(vehicleDriver.getVehicleno());
				// 车型
				vehicleDriverResp.setVehicleTypeName(vehicleDriver.getVehicletypename());
				// 车辆备注名
				vehicleDriverResp.setVehicleRemark(vehicleDriver.getVehicleremark());
				// 司机名字
				vehicleDriverResp.setDriverName(vehicleDriver.getDrivername());
				// 司机电话
				vehicleDriverResp.setDriverTel(vehicleDriver.getDrivertel());
				// 司机备注名
				vehicleDriverResp.setDriverRemark(vehicleDriver.getDriverremark());
				// 创建人
				vehicleDriverResp.setCreator(vehicleDriver.getCreator());
				// 创建时间
				vehicleDriverResp.setCreateTime(convertLongTODate(vehicleDriver.getCreatetime()));
				vehicleDriverRespList.add(vehicleDriverResp);
			}
		}
		return vehicleDriverRespList;
	}
	
	/**
	 * VehicleDriver与VehicleDriverReq属性的交换
	 * <p>
	 * @param argVehicleDriver
	 * @param argVehicleDriverReq
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 下午2:12:10
	 */
	public void copyProperties(VehicleDriver argVehicleDriver, VehicleDriverReq argVehicleDriverReq) {
		// 主键
		argVehicleDriver.setId(argVehicleDriverReq.getId());
		// 司机主键
		argVehicleDriver.setDriverid(argVehicleDriverReq.getDriverId());;
		// 车辆主键
		argVehicleDriver.setVehicleid(argVehicleDriverReq.getVehicleId());
		// 车牌号
		argVehicleDriver.setVehicleno(argVehicleDriverReq.getVehicleNo());
		// 车型
		argVehicleDriver.setVehicletypename(argVehicleDriverReq.getVehicleTypeName());
		// 车辆备注名
		argVehicleDriver.setVehicleremark(argVehicleDriverReq.getVehicleRemark());
		// 司机名字
		argVehicleDriver.setDrivername(argVehicleDriverReq.getDriverName());
		// 司机电话
		argVehicleDriver.setDrivertel(argVehicleDriverReq.getDriverTel());
		// 司机备注名
		argVehicleDriver.setDriverremark(argVehicleDriverReq.getDriverRemark());
		// 创建人
		argVehicleDriver.setCreator(argVehicleDriverReq.getCreator());
		//查询关键字
		argVehicleDriver.setQueryKey(argVehicleDriverReq.getQueryKey());
		// 创建时间
		if (argVehicleDriverReq.getCreateTime() != null) {
			argVehicleDriver.setCreatetime(Long.parseLong(argVehicleDriverReq.getCreateTime()));
		} else {
			argVehicleDriver.setCreatetime(null);
		}
	}
	
	/**
	 * 时间戳转换为"yyyy-MM-dd HH:mm:ss"格式
	 * <p>
	 * @param argTime
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月6日 上午10:01:36
	 */
	public String convertLongTODate(long argTime) {
		
		// 时间戳转换为时间格式
		Date dateTime = new Date(argTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return dateFormat.format(dateTime);
	}

	@Override
	public boolean getIFvehicleOwer(String id) {
		VehicleDriver ve = new VehicleDriver();
		ve.setCreator(id);
		long a = vehicleDriverMapper.selectCountByCondition(ve);
		if(a>0){
			return true;
		}
		return false;
	}
	
	@Override
	public Result anlianInsert(VehicleDriverReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		//安联账号不能为空
		if(StringUtils.isNotBlank(req.getAlDriverid())){
			MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleId());
			String a = req.getAlDriverid().substring(0, 1);
			String b = req.getAlDriverid().substring(1, req.getAlDriverid().length());
			String al = a.toUpperCase() + b;	
			SystemMember m = new SystemMember();
			m.setId(req.getDriverId());
			m.setAldriverid(al);
			systemMemberMapper.updateByPrimaryKeySelective(m);
			SystemMember um = systemMemberMapper.selectByPrimaryKey(req.getDriverId());
			VehicleDriver record = new VehicleDriver();
			record.setId(UUIDUtil.getId());
			record.setCreatetime(System.currentTimeMillis());
			record.setVehicleid(req.getVehicleId());
			record.setDriverid(req.getDriverId());
			record.setVehicleno(vehicle.getVehicleprefix()+vehicle.getVehicleno());
			record.setVehicletypename(vehicle.getVehicletypename());
			record.setDrivername(req.getDriverName());
			record.setDrivertel(um.getCellphone());
			record.setCreator(vehicle.getMemberid());
			vehicleDriverMapper.insertSelective(record);
		}else{
			rs.setCode("1");
			rs.setError("安联账号不能为空");
		}
		return rs;
	}

	@Override
	public PaginationVO<VehicleDriverMemberResp> find(VehicleDriverMemberReq req) throws Exception {
		
		PaginationVO<VehicleDriverMemberResp> page = new PaginationVO<VehicleDriverMemberResp>();
		VehicleDriverMember record = new VehicleDriverMember();
		record.setDrivername(req.getDriverName());
		record.setDrivertel(req.getDriverTel());
		record.setVehicleno(req.getVehicleNo());
		record.setVehicleprefix(req.getVehicleprefix());
		record.setAldriverid(req.getAldriverid());
		record.setDesc1(req.getDesc1());
		record.setVehicletypename(req.getVehicleTypeName());
		record.setStart((req.getPageNo()-1)*req.getPageSize());
		record.setLimit(req.getPageSize());
		List<VehicleDriverMember>list  =vehicleDriverMapper.findList(record);
		long a =vehicleDriverMapper.findListCount(record);
		page.setList(convert2VehicleDriverMemberList(list));
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setTotal(a);
		return page;
	}

	@SuppressWarnings("null")
	@Override
	public Result unbundled(String id) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		VehicleDriver vehicleDriver =vehicleDriverMapper.selectByPrimaryKey(id);
		MemberVehicle vehicle=memberVehicleMapper.selectByVehicleid(vehicleDriver.getVehicleid());
		if(null!=vehicleDriver&&null!=vehicle){
			if(vehicle.getBillstatus().equals("5")){
				vehicleDriverMapper.deleteByPrimaryKey(id);
				rs.setCode("000000");
				rs.setError("解绑成功！");
			}else{
				rs.setCode("2223");
				rs.setError("解绑失败！");
			}
		}else{
			rs.setCode("2223");
			rs.setError("解绑失败！");
		}
		return rs;
		
	}

	@Override
	public VehicleDriverResp findUnbundledById(String id) throws Exception {
		VehicleDriver m = vehicleDriverMapper.selectByPrimaryKey(id);
//		MemberVehicle m = memberVehicleMapper.selectByPrimaryKey(id);
		VehicleDriverResp resp = new VehicleDriverResp();
		resp.setVehicleNo(m.getVehicleno());
		resp.setDriverName(m.getDrivername());
		resp.setDriverTel(m.getDrivertel());
		resp.setVehicleTypeName(m.getVehicletypename());
		resp.setCreator(m.getCreator());
		resp.setVehicleRemark(m.getVehicleremark());
		return resp;
	}
	
	/**
	 * 运力司机绑定
	 */
	@Override
	public Result bind(String id,String driverid,String alDriver) throws Exception {
		Result rs = Result.getSuccessResult();
		//查询车辆信息
		MemberVehicle memberVehicle = memberVehicleMapper.selectByPrimaryKey(id);
		VehicleDriver record = new VehicleDriver();
		record.setDriverid(driverid);
//		record.setVehicleid(memberVehicle.getVehicleid());
		List<VehicleDriver> list=vehicleDriverMapper.findWithEntity(record);
//		List<Members> member = systemMemberMapper.findByMemberIds(driverid);
		SystemMember member = systemMemberMapper.selectByPrimaryKey(driverid);
		if(list.size()==0){
			//车辆通过安联认证，司机通过本地安联认证
			if("1".equals(memberVehicle.getDesc1())&&(StringUtils.isNotBlank(member.getAldriverid()))){
				if(StringUtils.isNotBlank(alDriver)){
					SystemMember m = new SystemMember();
					m.setId(driverid);
					m.setAldriverid(alDriver);
					systemMemberMapper.updateByPrimaryKeySelective(m);
				}else{
					// 司机推送安联
					AnlianDriverReq r = new AnlianDriverReq();
					r.setDriverid(driverid);
					r.setVehicleNo(memberVehicle.getVehicleprefix()+memberVehicle.getVehicleno());
					rs = anlianService.driver(r);
					//安联认证成功
					if("000000".equals(rs.getCode())){
						SystemMember m = new SystemMember();
						m.setId(driverid);
						m.setAldriverid(rs.getData().toString());
						systemMemberMapper.updateByPrimaryKeySelective(m);
					}else{
						return rs;
					}
				}
			}
			VehicleDriver vehicleDriver = new VehicleDriver();
			vehicleDriver.setDriverid(driverid);
			vehicleDriver.setDrivername(member.getRemarkname());
			vehicleDriver.setDrivertel(member.getCellphone());
			vehicleDriver.setVehicleid(memberVehicle.getVehicleid());
			vehicleDriver.setVehicleno(memberVehicle.getVehicleprefix()+memberVehicle.getVehicleno());
			vehicleDriver.setVehicletypename(memberVehicle.getVehicletypename());
			vehicleDriver.setCreatetime(new Date().getTime());
			vehicleDriver.setCreator(memberVehicle.getMemberid());
			vehicleDriver.setId(UUIDUtil.getId());
			vehicleDriverMapper.insert(vehicleDriver);
		}else{
			rs.setCode("3");
			rs.setError("司机已绑定");
			return rs;
		}
		OwnerDriver ownerDriver = new OwnerDriver();
		ownerDriver.setDriverid(driverid);
		ownerDriver.setVehicleownerid(memberVehicle.getMemberid());
		List<OwnerDriver> list1=ownerDriverMapper.selectOwnerDriver(ownerDriver);
		if(list1.size()==0){
			OwnerDriver  ownerDrivers = new OwnerDriver();
			ownerDrivers.setId(UUIDUtil.getId());
			ownerDrivers.setVehicleownerid(memberVehicle.getMemberid());
			ownerDrivers.setDriverid(driverid);
			ownerDrivers.setDrivername(member.getRemarkname());
			ownerDrivers.setDrivertel(member.getCellphone());
			ownerDrivers.setStatus("1");
			ownerDrivers.setCreator(memberVehicle.getMemberid());
			ownerDrivers.setCreatetime(new Date().getTime());
			ownerDriverMapper.insert(ownerDrivers);
		}
		return rs;
	}
	/**
	 * 全平台司机搜索
	 */
	@SuppressWarnings("null")
	@Override
	public MemberResp bindDriver(String phone) throws Exception {
		MemberResp resp = null ;
		Members members=systemMemberMapper.bindDriver(phone);
		if(members!=null){
			resp = new MemberResp();
			resp.setUserName(members.getRemarkname());
			resp.setCellPhone(members.getCellPhone());
			resp.setId(members.getId());
			resp.setDriverpercheck(members.getDriverpercheck());
			return resp;
		}
		
		return resp;
	}

	

	
}
