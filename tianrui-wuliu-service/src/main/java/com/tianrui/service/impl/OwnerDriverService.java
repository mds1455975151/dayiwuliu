package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.intf.IOwnerDriverService;
import com.tianrui.api.req.front.vehicle.AddVehicleBankCardReq;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.resp.front.vehicle.OwnerDriverResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.AddVehicleBankCard;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.OwnerDriver;
import com.tianrui.service.bean.VehicleAndDriver;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.mapper.AddVehicleBankCardMapper;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;

/**
 * 我的司机接口实现类
 * <p>
 * @author guyuke
 * @time 2016年6月1日 下午2:15:00
 */
@Service
public class OwnerDriverService implements IOwnerDriverService {
	
	@Autowired
	OwnerDriverMapper ownerDriverMapper;
	@Autowired
	CacheClient cache;
	@Autowired
	IMemberVoService memberService;
	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	AddVehicleBankCardMapper addVehicleBankCardMapper;
	
	/**
	 * 父类方法重写，根据条件进行我的未绑定车辆的司机信息查询
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#queryMyDriverOutsideByCondition(com.tianrui.api.req.front.vehicle.OwnerDriverReq)
	 */
	@Override
	public List<OwnerDriverResp> queryMyDriverOutsideByCondition(OwnerDriverReq req) 
			throws Exception {
		
		OwnerDriver reqCondition = new OwnerDriver();
		// 复制前台内容至查询条件
		PropertyUtils.copyProperties(reqCondition, req);
		
		// 查询操作
		List<OwnerDriver> ownDriverList = ownerDriverMapper.selectMyDriverOutsideByCondition(reqCondition);
		
		// 数据转换
		List<OwnerDriverResp> ownDriverRespList = convert2OwnerDriverList(ownDriverList);
		
		return ownDriverRespList;
	}
	
	/**
	 * 父类方法重写，根据条件进行我的司机信息分页查询
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#queryMyDriverInfoByPage(com.tianrui.api.req.front.vehicle.OwnerDriverReq)
	 */
	@Override
	public PaginationVO<OwnerDriverResp> queryMyDriverInfoByPage(OwnerDriverReq req)
			throws Exception {
		// 分页VO
		PaginationVO<OwnerDriverResp> pageVo =new PaginationVO<OwnerDriverResp>();
		OwnerDriver reqCondition = new OwnerDriver();
		// 复制前台内容至查询条件
		PropertyUtils.copyProperties(reqCondition, req);
		
		long total = ownerDriverMapper.selectCountByCondition(reqCondition);
		
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
			List<OwnerDriver> ownDriverList = ownerDriverMapper.selectMyDriverByCondition(reqCondition);
			
			// 数据转换
			List<OwnerDriverResp> ownDriverRespList = convert2OwnerDriverList(ownDriverList);
			pageVo.setList(ownDriverRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	/**
	 * 父类方法重写，根据条件进行我的司机信息查询
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#queryMyDriverInfoByCondition(com.tianrui.api.req.front.vehicle.OwnerDriverReq)
	 */
	@Override
	public List<OwnerDriverResp> queryMyDriverInfoByCondition(OwnerDriverReq req)
			throws Exception {
		
		OwnerDriver reqCondition = new OwnerDriver();
		// 复制前台内容至查询条件
		PropertyUtils.copyProperties(reqCondition, req);
		
		// 查询操作
		List<OwnerDriver> ownDriverList = ownerDriverMapper.selectMyDriverByCondition(reqCondition);
		
		// 数据转换
		List<OwnerDriverResp> ownDriverRespList = convert2OwnerDriverList(ownDriverList);
		
		return ownDriverRespList;
	}
	
	/**
	 * 父类方法重写，根据条件进行我的司机车辆联合信息查询，我的司机为主LEFT JOIN车辆信息
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#queryDriverAndVehicleByCondition(com.tianrui.api.req.front.vehicle.VehicleAndDriverReq)
	 */
	@Override
	public List<VehicleAndDriverResp> queryDriverAndVehicleByCondition(
			VehicleAndDriverReq req) throws Exception {
		
		VehicleAndDriver reqCondition = new VehicleAndDriver();
		// 复制前台内容至查询条件
		copyReq2VehiAndDriver(reqCondition, req);
		
		// 查询操作
		List<VehicleAndDriver> vehiAndDriverList = ownerDriverMapper.selectDriverAndVehicleByCondition(reqCondition);
		
		// 数据转换
		List<VehicleAndDriverResp> vehiAndDriverRespList = convert2VehiAndDriverList(vehiAndDriverList);
		
		return vehiAndDriverRespList;
	}
	
	/**
	 * 父类方法重写，我的司机信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#insert(com.tianrui.api.req.front.vehicle.OwnerDriverReq)
	 */
	@Override
	public Result insert(OwnerDriverReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		OwnerDriver ownDriver = new OwnerDriver();
		// 复制操作
		PropertyUtils.copyProperties(ownDriver, req);
		
		// 设置创建时间
		if (req.getCreatetime() != null) {
			ownDriver.setCreatetime(Long.parseLong(req.getCreatetime()));
		} else {
			ownDriver.setCreatetime(new Date().getTime());
		}
		
		// 数据插入操作
		long count = ownerDriverMapper.insert(ownDriver);
		long a = 1;
		if(count!=a){
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		
		return rs;
	}
	
	/**
	 * 父类方法重写，我的司机信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#updateByPrimaryKeySelective(com.tianrui.api.req.front.vehicle.OwnerDriverReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(OwnerDriverReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		OwnerDriver ownDriver = new OwnerDriver();
		// 复制操作
		PropertyUtils.copyProperties(ownDriver, req);
		
		// 数据更新操作
		long count = ownerDriverMapper.updateByPrimaryKeySelective(ownDriver);
		
		rs.setData(count);
		
		return rs;
	}
	
	/**
	 * 父类方法重写，我的司机信息删除功能操作
	 * 
	 * @see com.tianrui.api.intf.IOwnerDriverService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		// 数据删除操作
		long count = ownerDriverMapper.deleteByPrimaryKey(id);
		if(count!=1){
			rs.setCode("1");
			rs.setError("操作失败");
		}
		return rs;
	}
	
	/**
	 * 不同类型的List间转换操作
	 * <p>
	 * @param argOwnerDriverList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:23:00
	 */
	private List<OwnerDriverResp> convert2OwnerDriverList(List<OwnerDriver> argOwnerDriverList) {
		List<OwnerDriverResp> ownDriverRespList = null;
		if (argOwnerDriverList != null) {
			ownDriverRespList = new ArrayList<OwnerDriverResp>();
			for (OwnerDriver ownDriver : argOwnerDriverList) {
				// 转换操作
				ownDriverRespList.add(conver2OwnerDriverResp(ownDriver));
			}
		}
		return ownDriverRespList;
	}
	
	/**
	 * 不同类型的List间转换操作
	 * <p>
	 * @param argMemberVehicleList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月2日 上午9:29:13
	 */
	private List<VehicleAndDriverResp> convert2VehiAndDriverList(List<VehicleAndDriver> argVehicleAndDriverList) {
		List<VehicleAndDriverResp> memberVehicleRespList = null;
		if (argVehicleAndDriverList != null) {
			memberVehicleRespList = new ArrayList<VehicleAndDriverResp>();
			for (VehicleAndDriver memberVehicle : argVehicleAndDriverList) {
				VehicleAndDriverResp memberVehicleResp =  new VehicleAndDriverResp();
				// 用户主键
				memberVehicleResp.setMemberId(memberVehicle.getMemberid());
				// 车辆主键
				memberVehicleResp.setVehiId(memberVehicle.getVehiid());
				// 车牌号前缀
				memberVehicleResp.setVehiPrefix(memberVehicle.getVehiprefix());
				// 车牌号
				memberVehicleResp.setVehiNo(memberVehicle.getVehino());
				// 车辆类型
				memberVehicleResp.setVehiType(memberVehicle.getVehitype());
				// 车辆类型名称
				memberVehicleResp.setVehiTypeName(memberVehicle.getVehitypename());
				// 车辆长度
				memberVehicleResp.setVehiLength(memberVehicle.getVehilength() == null 
						? null : memberVehicle.getVehilength().toString());
				// 车辆重量
				memberVehicleResp.setVehiWeight(memberVehicle.getVehiweight() == null
						? null : memberVehicle.getVehiweight().toString());
				// 车头照片路径
				memberVehicleResp.setVehiHeadImgPath(memberVehicle.getVehiheadimgpath());
				// 车辆状态
				memberVehicleResp.setStatus(memberVehicle.getStatus());
				// 司机主键
				memberVehicleResp.setDriverId(memberVehicle.getDriverid());
				// 司机名字
				memberVehicleResp.setDriverName(memberVehicle.getDrivername());
				// 司机电话
				memberVehicleResp.setDriverTel(memberVehicle.getDrivertel());
				// 驾驶照/身份证
				memberVehicleResp.setIdentityCard(memberVehicle.getIdentityCard());
				
				memberVehicleRespList.add(memberVehicleResp);
			}
		}
		return memberVehicleRespList;
	}
	
	/**
	 * VehicleAndDriver与VehicleAndDriverReq属性的交换
	 * <p>
	 * @param argVehicleAndDriver
	 * @param argVehicleAndDriverReq
	 * <p>
	 * @author guyuke
	 * @time 2016年6月2日 上午9:29:13
	 */
	public void copyReq2VehiAndDriver(VehicleAndDriver argVehicleAndDriver, VehicleAndDriverReq argVehicleAndDriverReq) {
		
		// 用户主键
		argVehicleAndDriver.setMemberid(argVehicleAndDriverReq.getMemberId());
		// 车辆主键
		argVehicleAndDriver.setVehiid(argVehicleAndDriverReq.getVehiId());
		// 车牌号前缀
		argVehicleAndDriver.setVehiprefix(argVehicleAndDriverReq.getVehiPrefix());
		// 车牌号
		argVehicleAndDriver.setVehino(argVehicleAndDriverReq.getVehiNo());
		// 车辆类型
		argVehicleAndDriver.setVehitype(argVehicleAndDriverReq.getVehiType());
		// 车辆类型名称
		argVehicleAndDriver.setVehitypename(argVehicleAndDriverReq.getVehiTypeName());
		// 车辆长度
		argVehicleAndDriver.setVehilength(argVehicleAndDriverReq.getVehiLength() == null 
				? null : argVehicleAndDriverReq.getVehiLength().toString());
		// 车辆重量
		argVehicleAndDriver.setVehiweight(argVehicleAndDriverReq.getVehiWeight() == null
				? null : argVehicleAndDriverReq.getVehiWeight().toString());
		// 车头照片路径
		argVehicleAndDriver.setVehiheadimgpath(argVehicleAndDriverReq.getVehiHeadImgPath());
		// 车辆状态
		argVehicleAndDriver.setStatus(argVehicleAndDriverReq.getStatus());
		// 司机主键
		argVehicleAndDriver.setDriverid(argVehicleAndDriverReq.getDriverId());
		// 司机名字
		argVehicleAndDriver.setDrivername(argVehicleAndDriverReq.getDriverName());
		// 司机电话
		argVehicleAndDriver.setDrivertel(argVehicleAndDriverReq.getDriverTel());
		// 驾驶照/身份证
		argVehicleAndDriver.setIdentityCard(argVehicleAndDriverReq.getIdentityCard());
	}
	
	@Override
	public List<OwnerDriverResp> findOwnDriveById(String memberId) throws Exception {
		
		List<OwnerDriverResp> resps = new ArrayList<OwnerDriverResp>();
		List<String> list = new ArrayList<String>();
		if(memberId != null){
			List<OwnerDriver> ownerDrivers = ownerDriverMapper.findByVehOwnerIds(memberId);
			resps = conver2MemberRespList(ownerDrivers);
		}
		return resps;
	}
	
	private OwnerDriverResp conver2OwnerDriverResp(OwnerDriver driver){
		OwnerDriverResp rs = new OwnerDriverResp();
		if( driver !=null){
			rs.setId(driver.getId());
			rs.setDriverid(driver.getDriverid());
			rs.setDrivername(driver.getDrivername());
			rs.setDrivertel(driver.getDrivertel());
			rs.setVehicleownerid(driver.getVehicleownerid());
			rs.setRemarkname(driver.getRemarkname());
			// 状态
			rs.setStatus(driver.getStatus());
			rs.setCreator(driver.getCreator());
			rs.setCreatetime(driver.getCreatetime());
			rs.setCount(driver.getCount());
			rs.setAldriverid(driver.getAldriverid());
		}
		return rs;
	}
	
	private List<OwnerDriverResp> conver2MemberRespList(List<OwnerDriver> list) throws Exception{
		List<OwnerDriverResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<OwnerDriverResp>();
			for( OwnerDriver driver :list){
				OwnerDriverResp driverResp =conver2OwnerDriverResp(driver);
				MemberVo driverVo=memberService.get(driver.getDriverid());
				if(driverVo !=null ){
					driverResp.setAvatarsPath(driverVo.getAvatarspath());
				}
				rs.add(driverResp);
			}
		}
		return rs;
	}

	@Override
	public OwnerDriverResp findById(String id) throws Exception {
		// TODO Auto-generated method stub
		OwnerDriver ownerDriver = ownerDriverMapper.selectByPrimaryKey(id);
		conver2OwnerDriverResp(ownerDriver);
		return conver2OwnerDriverResp(ownerDriver);
	}
	
	/**
	 * 添加车主银行卡方法
	 * 
	 */
	@Override
	public Result addVehicleownerBankcard(AddVehicleBankCardReq addVehicle) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		AddVehicleBankCard addVehicleBankCard = new AddVehicleBankCard();
		addVehicleBankCard.setId(addVehicle.getId());
		addVehicleBankCard.setDriverid(addVehicle.getDriverid());
		addVehicleBankCard.setVehicleownerid(addVehicle.getVehicleownerid());
		List<AddVehicleBankCard> list =addVehicleBankCardMapper.selectAddVehicleBankCard(addVehicleBankCard);
		if(list.size()>=1){
			rs.setCode("111111");
			rs.setError("已经添加过该卡，请勿重复添加！");
		}else{
			int a =addVehicleBankCardMapper.insert(addVehicleBankCard);
			if(a!=1){
				rs.setCode("222222");
				rs.setError("添加失败！");
			}
		}
		return rs;
	}

	/**
	 * 根据司机id查询车主信息
	 */
//	@Override
//	public List<OwnerDriverResp> findOwnerById(String memberId) throws Exception {
//		OwnerDriver  ownerDriver = new OwnerDriver();
//		ownerDriver.setDriverid(memberId);
//		List<OwnerDriver> list = ownerDriverMapper.selectOwnerDriver(ownerDriver);
//		MemberBankCard memberBankCards=null;
//		for( OwnerDriver ownerDrivers :list){
//			MemberBankCard memberBankCard = new MemberBankCard();
//			memberBankCard.setCreater(ownerDrivers.getVehicleownerid());
//			memberBankCard.setBankstatus("1");
//			memberBankCards  = memberBankCardMapper.selectOwnerCard(memberBankCard);
//		}
//		List<MemberBankCard> lists = new ArrayList<MemberBankCard>();
//		lists.add(memberBankCards);
//		
//	}
	
}
