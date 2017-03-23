package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberVehicleService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.MemberVehicleReq;
import com.tianrui.api.req.front.vehicle.VehicleAndDriverReq;
import com.tianrui.api.req.front.vehicle.VehicleOnlyReq;
import com.tianrui.api.resp.front.vehicle.MemberVehicleResp;
import com.tianrui.api.resp.front.vehicle.VehicleAndDriverResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.VehicleAndDriver;
import com.tianrui.service.mapper.MemberVehicleMapper;

/**
 * 我的车辆接口实现类
 * <p>
 * @author guyuke
 * @time 2016年6月1日 下午2:15:00
 */
@Service
public class MemberVehicleService implements IMemberVehicleService {
	
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	IMessageService messageService;
	
	/**
	 * 父类方法重写，根据主键进行我的车辆信息查询
	 * 
	 * @see com.tianrui.api.intf.IMemberVehicleService#queryMyVehicleInfoById(java.lang.String)
	 */
	@Override
	public MemberVehicleResp queryMyVehicleInfoById(String id)
			throws Exception {
		
		// 查询操作
		MemberVehicle memberVehicle = memberVehicleMapper.selectByPrimaryKey(id);
//		convert2MemberVehicle(memberVehicle);
//		// 数据转换
//		List<MemberVehicleResp> memberVehicleRespList = convert2MemberVehicleList(memberVehicleList);
		
		return convert2MemberVehicle(memberVehicle);
	}
	
	/**
	 * 父类方法重写，根据条件进行我的车辆信息分页查询
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberVehicleService#queryMyVehicleInfoByPage(com.tianrui.api.req.admin.MemberVehicleReq)
	 */
	@Override
	public PaginationVO<MemberVehicleResp> queryMyVehicleInfoByPage(MemberVehicleReq req)
			throws Exception {
		// 分页VO
		PaginationVO<MemberVehicleResp> pageVo =new PaginationVO<MemberVehicleResp>();
		// 复制前台内容至查询条件
		MemberVehicle reqCondition = copyProperties(req);
		
		long total = memberVehicleMapper.selectCountByCondition(reqCondition);
		
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
			List<MemberVehicle> memberVehicleList = memberVehicleMapper.selectMyVehicleByCondition(reqCondition);
			
			// 数据转换
			List<MemberVehicleResp> memberVehicleRespList = convert2MemberVehicleList(memberVehicleList);
			pageVo.setList(memberVehicleRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	/**
	 * 父类方法重写，根据条件进行我的车辆信息查询
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberVehicleService#queryMyVehicleInfoByCondition(com.tianrui.api.req.admin.MemberVehicleReq)
	 */
	@Override
	public List<MemberVehicleResp> queryMyVehicleInfoByCondition(MemberVehicleReq req)
			throws Exception {
		
		// 复制前台内容至查询条件
		MemberVehicle reqCondition = copyProperties(req);
		
		// 查询操作
		List<MemberVehicle> memberVehicleList = memberVehicleMapper.selectMyVehicleByCondition(reqCondition);
		
		// 数据转换
		List<MemberVehicleResp> memberVehicleRespList = convert2MemberVehicleList(memberVehicleList);
		
		return memberVehicleRespList;
	}
	
	/**
	 * 父类方法重写，根据条件进行车辆和司机联合信息查询
	 * 
	 * @see com.tianrui.api.intf.IMemberVehicleService#queryVehicleAndDriverByCondition(com.tianrui.api.req.front.vehicle.VehicleAndDriverReq)
	 */
	@Override
	public PaginationVO<VehicleAndDriverResp> queryVehicleAndDriverByCondition(
			VehicleAndDriverReq req) throws Exception {
		
		VehicleAndDriver reqCondition = new VehicleAndDriver();
		// 复制前台内容至查询条件
		copyReq2VehiAndDriver(reqCondition, req);
		
		// 查询操作
		if (req.getPageNo() <= 0) {
			req.setPageNo(1);
		}
		// 查询开始位置
		reqCondition.setStart((req.getPageNo()-1)*req.getPageSize());
		// 查询数量
		reqCondition.setLimit(req.getPageSize());
		List<VehicleAndDriver> vehiAndDriverList = memberVehicleMapper.selectVehicleAndDriverByCondition(reqCondition);
		long total = memberVehicleMapper.selectVehicleAndDriverBycount(reqCondition);
		// 数据转换
		List<VehicleAndDriverResp> vehiAndDriverRespList = convert2VehiAndDriverList(vehiAndDriverList);
		
		PaginationVO<VehicleAndDriverResp> resp = new PaginationVO<VehicleAndDriverResp>();
		resp.setList(vehiAndDriverRespList);
		resp.setTotal(total);
		resp.setPageNo(req.getPageNo());
		return resp;
	}
	
	/**
	 * 父类方法重写，我的车辆信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberVehicleService#insert(com.tianrui.api.req.admin.MemberVehicleReq)
	 */
	@Override
	public Result insert(MemberVehicleReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		//判断车辆唯一
		MemberVehicle vehic = new MemberVehicle();
		vehic.setVehicleprefix(req.getVehiclePrefix());
		vehic.setVehicleno(req.getVehicleNo());
		long f = memberVehicleMapper.selectCountByCondition(vehic);
		long x = 0;
		if(f != x){
			rs.setCode("1");
			rs.setError("车牌号已存在");
			return rs;
		}
		// 复制操作
		MemberVehicle memberVehicle = copyProperties(req);
		
		// 设置创建时间
		if (memberVehicle.getCreatetime() == null) {
			memberVehicle.setCreatetime(new Date().getTime());
		}
		
		// 1-临时车辆 2-认证车辆
		if ("1".equals(memberVehicle.getDesc2())) {
			memberVehicle.setStatus("1");
		}else if("2".equals(memberVehicle.getDesc2())){
			memberVehicle.setStatus("2");
		}
		memberVehicle.setBillstatus("5");
		//开票认证 未认证
		memberVehicle.setDesc1("0");
		// 数据插入操作
		memberVehicleMapper.insert(memberVehicle);
		return rs;
	}
	
	/**
	 * 父类方法重写，我的车辆信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberVehicleService#update(com.tianrui.api.req.admin.MemberVehicleReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(MemberVehicleReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		// 复制操作
		MemberVehicle memberVehicle = copyProperties( req);
		// 数据更新操作
		memberVehicleMapper.updateByPrimaryKeySelective(memberVehicle);
		
		//消息推送
		MemberVehicle mass = memberVehicleMapper.selectByPrimaryKey(req.getId());
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
		strs.add(mass.getVehicleprefix()+mass.getVehicleno());
		mreq.setParams(strs);
		mreq.setType("1");
		mreq.setRecid(mass.getMemberid());
		mreq.setRecname(mass.getVehicleprefix()+mass.getVehicleno());
		if("1".equals(mass.getStatus())){//通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_VEHICLE_PASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_VEHICLE_PASS.getType());
			messageService.sendMessageInside(mreq);
		}else if("-1".equals(mass.getStatus())){//没通过审核
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_VEHICLE_NOTPASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_VEHICLE_NOTPASS.getType());
			messageService.sendMessageInside(mreq);
		}
		return rs;
	}
	
	/**
	 * 父类方法重写，我的车辆信息删除功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberVehicleService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		// 数据删除操作
		long count = memberVehicleMapper.deleteByPrimaryKey(id);
		
		rs.setData(count);
		
		return rs;
	}
	
	private MemberVehicleResp convert2MemberVehicle(MemberVehicle memberVehicle){
		MemberVehicleResp memberVehicleResp =  new MemberVehicleResp();
		// 主键
		memberVehicleResp.setId(memberVehicle.getId());
		// 用户主键
		memberVehicleResp.setMemberId(memberVehicle.getMemberid());
		// 车辆主键
		memberVehicleResp.setVehicleId(memberVehicle.getVehicleid());
		//车主名称
		memberVehicleResp.setVehiOwnerName(memberVehicle.getVehiOwnerName());
		//车主电话
		memberVehicleResp.setVehiOwnerTel(memberVehicle.getVehiOwnerTel());
		// 车牌号前缀
		memberVehicleResp.setVehiclePrefix(memberVehicle.getVehicleprefix());
		// 车牌号
		memberVehicleResp.setVehicleNo(memberVehicle.getVehicleno());
		// 车辆类型
		memberVehicleResp.setVehicleType(memberVehicle.getVehicletype());
		// 车辆类型名称
		memberVehicleResp.setVehicleTypeName(memberVehicle.getVehicletypename());
		// 车辆长度
		if (memberVehicle.getVehilength() == null) {
			memberVehicleResp.setVehiLength(null);
		} else {
			memberVehicleResp.setVehiLength(memberVehicle.getVehilength().toString());
		}
		// 车辆宽度
		if (memberVehicle.getVehiwidth() == null) {
			memberVehicleResp.setVehiWidth(null);
		} else {
			memberVehicleResp.setVehiWidth(memberVehicle.getVehiwidth().toString());
		}
		// 车辆高度
		if (memberVehicle.getVehiheight() == null) {
			memberVehicleResp.setVehiHeight(null);
		} else {
			memberVehicleResp.setVehiHeight(memberVehicle.getVehiheight().toString());
		}
		// 车辆重量
		if (memberVehicle.getVehiweight() == null) {
			memberVehicleResp.setVehiWeight(null);
		} else {
			memberVehicleResp.setVehiWeight(memberVehicle.getVehiweight().toString());
		}
		// 行驶证照片路径
		memberVehicleResp.setVehiLicenseImgPath(memberVehicle.getVehilicenseimgpath());
		// 车头照片路径
		memberVehicleResp.setVehiHeadImgPath(memberVehicle.getVehiheadimgpath());
		// 车辆左侧照片路径
		memberVehicleResp.setVehiLeftImgPath(memberVehicle.getVehileftimgpath());
		// 车辆右侧照片路径
		memberVehicleResp.setVehiRightImgPath(memberVehicle.getVehirightimgpath());
		// 人车合照照片路径
		memberVehicleResp.setVehiAndOwnerImgPath(memberVehicle.getVehiandownerimgpath());
		// 车辆状态
		memberVehicleResp.setStatus(memberVehicle.getStatus());
		//车辆运输状态
		memberVehicleResp.setBillstatus(memberVehicle.getBillstatus());
		// 认证失败原因
		memberVehicleResp.setMemo(memberVehicle.getMemo());
		// 自定义项1
		memberVehicleResp.setDesc1(memberVehicle.getDesc1());
		// 自定义项2
		memberVehicleResp.setDesc2(memberVehicle.getDesc2());
		// 自定义项3
		memberVehicleResp.setDesc3(memberVehicle.getDesc3());
		// 自定义项4
		memberVehicleResp.setDesc4(memberVehicle.getDesc4());
		// 创建人
		memberVehicleResp.setCreator(memberVehicle.getCreator());
		// 创建时间
		memberVehicleResp.setCreateTime(convertLongTODate(memberVehicle.getCreatetime()));
		// 修改人
		memberVehicleResp.setModifier(memberVehicle.getModifier());
		// 修改时间
		memberVehicleResp.setModifyTime(convertLongTODate(memberVehicle.getModifytime()));
		memberVehicleResp.setRegistcode(memberVehicle.getRegistcode());
		memberVehicleResp.setRegistimage(memberVehicle.getRegistimage());
		memberVehicleResp.setOpercode(memberVehicle.getOpercode());
		memberVehicleResp.setOperimage(memberVehicle.getOperimage());
		memberVehicleResp.setIdentitycode(memberVehicle.getIdentitycode());
		memberVehicleResp.setIdentieyimage(memberVehicle.getIdentieyimage());
		memberVehicleResp.setAgreeimage(memberVehicle.getAgreeimage());
		memberVehicleResp.setRoadtransportcode(memberVehicle.getRoadtransportcode());
		memberVehicleResp.setRoadtransportimage(memberVehicle.getRoadtransportimage());
		return memberVehicleResp;
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
	private List<MemberVehicleResp> convert2MemberVehicleList(List<MemberVehicle> argMemberVehicleList) {
		List<MemberVehicleResp> memberVehicleRespList = null;
		if (argMemberVehicleList != null) {
			memberVehicleRespList = new ArrayList<MemberVehicleResp>();
			for (MemberVehicle memberVehicle : argMemberVehicleList) {
				MemberVehicleResp memberVehicleResp =  new MemberVehicleResp();
				// 主键
				memberVehicleResp.setId(memberVehicle.getId());
				// 用户主键
				memberVehicleResp.setMemberId(memberVehicle.getMemberid());
				// 车辆主键
				memberVehicleResp.setVehicleId(memberVehicle.getVehicleid());
				// 车牌号前缀
				memberVehicleResp.setVehiclePrefix(memberVehicle.getVehicleprefix());
				// 车牌号
				memberVehicleResp.setVehicleNo(memberVehicle.getVehicleno());
				// 车辆类型
				memberVehicleResp.setVehicleType(memberVehicle.getVehicletype());
				// 车辆类型名称
				memberVehicleResp.setVehicleTypeName(memberVehicle.getVehicletypename());
				// 车辆长度
				if (memberVehicle.getVehilength() == null) {
					memberVehicleResp.setVehiLength(null);
				} else {
					memberVehicleResp.setVehiLength(memberVehicle.getVehilength().toString());
				}
				// 车辆宽度
				if (memberVehicle.getVehiwidth() == null) {
					memberVehicleResp.setVehiWidth(null);
				} else {
					memberVehicleResp.setVehiWidth(memberVehicle.getVehiwidth().toString());
				}
				// 车辆高度
				if (memberVehicle.getVehiheight() == null) {
					memberVehicleResp.setVehiHeight(null);
				} else {
					memberVehicleResp.setVehiHeight(memberVehicle.getVehiheight().toString());
				}
				// 车辆重量
				if (memberVehicle.getVehiweight() == null) {
					memberVehicleResp.setVehiWeight(null);
				} else {
					memberVehicleResp.setVehiWeight(memberVehicle.getVehiweight().toString());
				}
				// 行驶证照片路径
				memberVehicleResp.setVehiLicenseImgPath(memberVehicle.getVehilicenseimgpath());
				// 车头照片路径
				memberVehicleResp.setVehiHeadImgPath(memberVehicle.getVehiheadimgpath());
				// 车辆左侧照片路径
				memberVehicleResp.setVehiLeftImgPath(memberVehicle.getVehileftimgpath());
				// 车辆右侧照片路径
				memberVehicleResp.setVehiRightImgPath(memberVehicle.getVehirightimgpath());
				// 人车合照照片路径
				memberVehicleResp.setVehiAndOwnerImgPath(memberVehicle.getVehiandownerimgpath());
				// 车辆状态
				memberVehicleResp.setStatus(memberVehicle.getStatus());
				// 认证失败原因
				memberVehicleResp.setMemo(memberVehicle.getMemo());
				// 自定义项1
				memberVehicleResp.setDesc1(memberVehicle.getDesc1());
				// 自定义项2
				memberVehicleResp.setDesc2(memberVehicle.getDesc2());
				// 自定义项3
				memberVehicleResp.setDesc3(memberVehicle.getDesc3());
				// 自定义项4
				memberVehicleResp.setDesc4(memberVehicle.getDesc4());
				// 创建人
				memberVehicleResp.setCreator(memberVehicle.getCreator());
				// 创建时间
				memberVehicleResp.setCreateTime(convertLongTODate(memberVehicle.getCreatetime()));
				// 修改人
				memberVehicleResp.setModifier(memberVehicle.getModifier());
				// 修改时间
				memberVehicleResp.setModifyTime(convertLongTODate(memberVehicle.getModifytime()));
				
				memberVehicleRespList.add(memberVehicleResp);
			}
		}
		return memberVehicleRespList;
	}
	
	/**
	 * MemberVehicle与MemberVehicleReq属性的交换
	 * <p>
	 * @param argMemberVehicle
	 * @param argMemberVehicleReq
	 * <p>
	 * @author guyuke
	 * @time 2016年6月2日 上午9:29:13
	 */
	public MemberVehicle copyProperties( MemberVehicleReq argMemberVehicleReq) {
		MemberVehicle argMemberVehicle = new MemberVehicle();
		
		argMemberVehicle.setRegistcode(argMemberVehicleReq.getRegistcode());
		argMemberVehicle.setRegistimage(argMemberVehicleReq.getRegistimage());
		argMemberVehicle.setOpercode(argMemberVehicleReq.getOpercode());
		argMemberVehicle.setOperimage(argMemberVehicleReq.getOperimage());

		argMemberVehicle.setIdentitycode(argMemberVehicleReq.getIdentitycode());
		argMemberVehicle.setIdentieyimage(argMemberVehicleReq.getIdentieyimage());
		argMemberVehicle.setAgreeimage(argMemberVehicleReq.getAgreeimage());
		argMemberVehicle.setRoadtransportcode(argMemberVehicleReq.getRoadtransportcode());
		argMemberVehicle.setRoadtransportimage(argMemberVehicleReq.getRoadtransportimage());
		argMemberVehicle.setDesc2(argMemberVehicleReq.getDesc2());
		// 主键
		argMemberVehicle.setId(argMemberVehicleReq.getId());
		//车辆运单状态
		argMemberVehicle.setBillstatus(argMemberVehicleReq.getBillstatus());
		// 用户主键
		argMemberVehicle.setMemberid(argMemberVehicleReq.getMemberId());
		//车辆所有人姓名
		argMemberVehicle.setVehiOwnerName(argMemberVehicleReq.getVehiOwnerName());
		//车辆所有人电话
		argMemberVehicle.setVehiOwnerTel(argMemberVehicleReq.getVehiOwnerTel());
		// 车辆主键
		argMemberVehicle.setVehicleid(argMemberVehicleReq.getVehicleId());
		// 车牌号前缀
		argMemberVehicle.setVehicleprefix(argMemberVehicleReq.getVehiclePrefix());
		// 车牌号
		argMemberVehicle.setVehicleno(argMemberVehicleReq.getVehicleNo());
		// 车辆类型
		argMemberVehicle.setVehicletype(argMemberVehicleReq.getVehicleType());
		// 车辆类型名称
		argMemberVehicle.setVehicletypename(argMemberVehicleReq.getVehicleTypeName());
		// 车头照片路径
		argMemberVehicle.setVehiheadimgpath(argMemberVehicleReq.getVehiHeadImgPath());
		// 行驶证照片路径
		argMemberVehicle.setVehilicenseimgpath(argMemberVehicleReq.getVehiLicenseImgPath());
		// 审核不通过原因
		argMemberVehicle.setMemo(argMemberVehicleReq.getMemo());
		// 审核时间
		argMemberVehicle.setAudittime(argMemberVehicleReq.getAudittime());
		// 车辆长度
		if (argMemberVehicleReq.getVehiLength() == null) {
			argMemberVehicle.setVehilength(null);
		} else {
			argMemberVehicle.setVehilength(Double.parseDouble(argMemberVehicleReq.getVehiLength()));
		}
		// 车辆宽度
		if (argMemberVehicleReq.getVehiWidth() == null) {
			argMemberVehicle.setVehiwidth(null);
		} else {
			argMemberVehicle.setVehiwidth(Double.parseDouble(argMemberVehicleReq.getVehiWidth()));
		}
		// 车辆高度
		if (argMemberVehicleReq.getVehiHeight() == null) {
			argMemberVehicle.setVehiheight(null);
		} else {
			argMemberVehicle.setVehiheight(Double.parseDouble(argMemberVehicleReq.getVehiHeight()));
		}
		// 车辆重量
		if (argMemberVehicleReq.getVehiWeight() == null) {
			argMemberVehicle.setVehiweight(null);
		} else {
			argMemberVehicle.setVehiweight(Double.parseDouble(argMemberVehicleReq.getVehiWeight()));
		}
		// 车辆状态
		argMemberVehicle.setStatus(argMemberVehicleReq.getStatus());
		
		return argMemberVehicle;
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
				memberVehicleResp.setId(memberVehicle.getId());
				memberVehicleResp.setRegistcode(memberVehicle.getRegistcode());
				memberVehicleResp.setRegistimage(memberVehicle.getRegistimage());
				memberVehicleResp.setOpercode(memberVehicle.getOpercode());
				memberVehicleResp.setOperimage(memberVehicle.getOperimage());
				memberVehicleResp.setIdentieyimage(memberVehicle.getIdentieyimage());
				memberVehicleResp.setIdentitycode(memberVehicle.getIdentitycode());
				memberVehicleResp.setAgreeimage(memberVehicle.getAgreeimage());
				memberVehicleResp.setRoadtransportcode(memberVehicle.getRoadtransportcode());
				memberVehicleResp.setRoadtransportimage(memberVehicle.getRoadtransportimage());
				// 用户主键
				memberVehicleResp.setMemberId(memberVehicle.getMemberid());
				//车主电话
				memberVehicleResp.setVehiOwnerTel(memberVehicle.getVehiOwnerTel());
				memberVehicleResp.setVehiOwnerName(memberVehicle.getVehiOwnerName());
				memberVehicleResp.setVehilicenseimgpath(memberVehicle.getVehilicenseimgpath());
				//车辆运单状态
				memberVehicleResp.setBillstatus(memberVehicle.getBillstatus());
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
				// 司机车辆主键
				memberVehicleResp.setVehiDriverId(memberVehicle.getVehidriverid());
				// 司机主键
				memberVehicleResp.setDriverId(memberVehicle.getDriverid());
				// 司机名字
				memberVehicleResp.setDriverName(memberVehicle.getDrivername());
				// 司机电话
				memberVehicleResp.setDriverTel(memberVehicle.getDrivertel());
				//创建认证时间
				memberVehicleResp.setCreatetime(memberVehicle.getCreatetime());
				//审核时间
				memberVehicleResp.setAudittime(memberVehicle.getAudittime());
				
				memberVehicleResp.setDesc1(memberVehicle.getDesc1());
				memberVehicleResp.setDesc2(memberVehicle.getDesc2());
				memberVehicleResp.setMemo(memberVehicle.getMemo());
				
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
		//app搜索字段
		argVehicleAndDriver.setSearch(argVehicleAndDriverReq.getSearch());
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
		// 司机车辆主键
		argVehicleAndDriver.setVehidriverid(argVehicleAndDriverReq.getVehiDriverId());
		// 司机主键
		argVehicleAndDriver.setDriverid(argVehicleAndDriverReq.getDriverId());
		// 司机名字
		argVehicleAndDriver.setDrivername(argVehicleAndDriverReq.getDriverName());
		// 司机电话
		argVehicleAndDriver.setDrivertel(argVehicleAndDriverReq.getDriverTel());
	}
	
	/**
	 * 时间戳转换为"yyyy-MM-dd HH:mm:ss"格式
	 * <p>
	 * @param argTime
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月2日 上午10:01:36
	 */
	public String convertLongTODate(Long argTime) {
		
		if (argTime == null) {
			return null;
		} else {
			// 时间戳转换为时间格式
			Date dateTime = new Date(argTime);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			return dateFormat.format(dateTime);
		}
	}

	@Override
	public MemberVehicleResp findMemoMassageById(String id) throws Exception {
		// TODO Auto-generated method stub
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(id);
		MemberVehicleResp resp = new MemberVehicleResp();
		resp.setId(vehicle.getId());
		resp.setCreateTime(convertLongTODate(vehicle.getCreatetime()));
		resp.setMemo(vehicle.getMemo());
		resp.setAudittime(convertLongTODate(vehicle.getAudittime()));
		return resp;
	}

	@Override
	public Result updateVehiclebillStatus(MemberVehicleReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		//运单状态为2，3，4，5 修改车辆状态
		//2-发货中3-运货中4-卸货中5-空闲中
		if("|2|3|4|5|".contains("|"+req.getBillstatus()+"|")){
			String sd = req.getBillstatus();
			MemberVehicle vehicle = new MemberVehicle();
			vehicle.setId(req.getId());
			vehicle.setBillstatus(sd);
			int a = memberVehicleMapper.updateByPrimaryKeySelective(vehicle);
			if(a!=1){
				rs.setCode("1");
				rs.setError("修改失败");
			}
		}
		return rs;
	}
	
	@Override
	public List<MemberVehicleResp> selectVehicleByIds(List<String> vehicleIds) throws Exception {
		if(vehicleIds != null){
			// 查询数量
			List<MemberVehicle>  memberVehicleList =  memberVehicleMapper.selectVehicleByIds(vehicleIds);
			// 数据转换
			List<MemberVehicleResp> memberVehicleRespList = convert2MemberVehicleList(memberVehicleList);
			return memberVehicleRespList;
		}
		return null;
	}

	@Override
	public Result vehicleNOByOnly(VehicleOnlyReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberVehicle vehicle = new MemberVehicle();
		vehicle.setVehicleno(req.getVehicleNo());
		vehicle.setVehicleprefix(req.getVheicleFix());
		List<MemberVehicle> list = memberVehicleMapper.selectMyVehicleByCondition(vehicle);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("车牌号已存在");
		}
		return rs;
	}
}
