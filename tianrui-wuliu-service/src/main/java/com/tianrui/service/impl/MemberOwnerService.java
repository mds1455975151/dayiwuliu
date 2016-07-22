package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberOwner;
import com.tianrui.service.mapper.MemberOwnerMapper;

/**
 * 我的车主接口实现类
 * <p>
 * @author guyuke
 * @time 2016年6月6日 下午2:15:00
 */
@Service
public class MemberOwnerService implements IMemberOwnerService {
	
	@Autowired
	MemberOwnerMapper memberOwnerMapper;
	@Autowired
	MemberVoService memberVoService;
	
	/**
	 * 父类方法重写，根据条件进行我的车主信息查询
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberOwnerService#queryMyVehiOwnerByPage(com.tianrui.api.req.admin.MemberOwnerReq)
	 */
	@Override
	public PaginationVO<MemberOwnerResp> queryMyVehiOwnerByPage(MemberOwnerReq req)
			throws Exception {
		
		// 分页VO
		PaginationVO<MemberOwnerResp> pageVo =new PaginationVO<MemberOwnerResp>();
		MemberOwner reqCondition = new MemberOwner();
		copyProperties(reqCondition, req);
		
		long total = memberOwnerMapper.selectCountByCondition(reqCondition);
		
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
			List<MemberOwner> orgCargoList = memberOwnerMapper.selectMyVehiOwnerByCondition(reqCondition);
			
			// 数据转换
			List<MemberOwnerResp> orgCargoRespList = convert2MemberOwnerList(orgCargoList);
			pageVo.setList(orgCargoRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	/**
	 * 父类方法重写，根据条件进行我的车主信息查询
	 * 
	 * @see com.tianrui.api.intf.IMemberOwnerService#queryMyVehiOwnerByCondition(com.tianrui.api.req.front.vehicle.MemberOwnerReq)
	 */
	@Override
	public List<MemberOwnerResp> queryMyVehiOwnerByCondition(MemberOwnerReq req)
			throws Exception {
		
		MemberOwner reqCondition = new MemberOwner();
		// 复制前台内容至查询条件
		copyProperties(reqCondition, req);
		
		// 查询操作 
		List<MemberOwner> ownDriverList = memberOwnerMapper.selectMyVehiOwnerByCondition(reqCondition);
		
		// 数据转换
		List<MemberOwnerResp> ownDriverRespList = convert2MemberOwnerList(ownDriverList);
		
		return ownDriverRespList;
	}
	
	/**
	 * 父类方法重写，我的车主信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberOwnerService#insert(com.tianrui.api.req.admin.MemberOwnerReq)
	 */
	@Override
	public Result insert(MemberOwnerReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		MemberOwner memberOwner = new MemberOwner();
		copyProperties(memberOwner, req);
		
		// 设置创建时间
		if (memberOwner.getCreatetime() == null) {
			memberOwner.setCreatetime(new Date().getTime());
		}
		
		// 数据插入操作
		long count = memberOwnerMapper.insert(memberOwner);
		
		long a = 1;
		if(count!=a){
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		return rs;
	}
	
	/**
	 * 父类方法重写，我的车主信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberOwnerService#update(com.tianrui.api.req.admin.MemberOwnerReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(MemberOwnerReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		MemberOwner memberOwner = new MemberOwner();
		copyProperties(memberOwner, req);
		
		// 数据更新操作
		long count = memberOwnerMapper.updateByPrimaryKeySelective(memberOwner);
		long a = 1;
		if(count!=a){
			rs.setCode("1");
			rs.setError("操作失败");
			return rs;
		}
		
		return rs;
	}
	
	/**
	 * 父类方法重写，我的车主信息删除功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IMemberOwnerService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		// 数据删除操作
		long count = memberOwnerMapper.deleteByPrimaryKey(id);
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
	 * @param argMemberOwnerList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 下午6:23:00
	 */
	private List<MemberOwnerResp> convert2MemberOwnerList(List<MemberOwner> argMemberOwnerList) {
		List<MemberOwnerResp> memberOwnerRespList = null;
		if (argMemberOwnerList != null) {
			memberOwnerRespList = new ArrayList<MemberOwnerResp>();
			for (MemberOwner memberOwner : argMemberOwnerList) {
				MemberOwnerResp memberOwnerResp =  new MemberOwnerResp();
				// 主键
				memberOwnerResp.setId(memberOwner.getId());
				// 会员主键
				memberOwnerResp.setMemberId(memberOwner.getMemberid());;
				// 车主主键
				memberOwnerResp.setOwnerId(memberOwner.getOwnerid());
				
				MemberVo vender =memberVoService.get(memberOwner.getOwnerid()) ;
				if( vender!=null ){
					// 车主名字
					memberOwnerResp.setOwnerName(vender.getRealName());
					// 车主电话
					memberOwnerResp.setOwnerTel(vender.getCellphone());
					
				}
				// 是否开启
				memberOwnerResp.setIsEnabled(memberOwner.getIsenabled());
				// 状态
				memberOwnerResp.setStatus(memberOwner.getStatus());
				// 创建时间
				memberOwnerResp.setCreateTime(convertLongTODate(memberOwner.getCreatetime()));
				// 个人图像地址
				memberOwnerResp.setAvatarsPath(memberOwner.getAvatarsPath());
				memberOwnerRespList.add(memberOwnerResp);
			}
		}
		return memberOwnerRespList;
	}
	
	/**
	 * MemberOwner与MemberOwnerReq属性的交换
	 * <p>
	 * @param argMemberOwner
	 * @param argMemberOwnerReq
	 * <p>
	 * @author guyuke
	 * @time 2016年6月06日 下午2:12:10
	 */
	public void copyProperties(MemberOwner argMemberOwner, MemberOwnerReq argMemberOwnerReq) {
		// 主键
		argMemberOwner.setId(argMemberOwnerReq.getId());
		// 会员主键
		argMemberOwner.setMemberid(argMemberOwnerReq.getMemberId());;
		// 车主主键
		argMemberOwner.setOwnerid(argMemberOwnerReq.getOwnerId());
		// 车主名字
		argMemberOwner.setOwnername(argMemberOwnerReq.getOwnerName());
		// 车主电话
		argMemberOwner.setOwnertelno(argMemberOwnerReq.getOwnerTel());
		// 是否开启
		argMemberOwner.setIsenabled(argMemberOwnerReq.getIsEnabled());
		// 状态
		argMemberOwner.setStatus(argMemberOwnerReq.getStatus());
		//app搜索
		argMemberOwner.setSearch(argMemberOwnerReq.getSearch());
		// 创建时间
		if (argMemberOwnerReq.getCreateTime() != null) {
			argMemberOwner.setCreatetime(Long.parseLong(argMemberOwnerReq.getCreateTime()));
		} else {
			argMemberOwner.setCreatetime(null);
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
	
}
