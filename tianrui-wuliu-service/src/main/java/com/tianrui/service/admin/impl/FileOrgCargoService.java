/**
 * Project Name:tianrui-wuliu-service
 * File Name:FileOrgCargoService.java
 * Package Name:com.tianrui.service.admin.impl
 * Date:2016年5月22日下午4:44:36
 *
*/

package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileOrgCargo;
import com.tianrui.service.admin.mapper.FileOrgCargoMapper;

/**
 * 我的货物接口实现类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 下午4:45:12
 */
@Service
public class FileOrgCargoService implements IFileOrgCargoService {
	
	@Autowired
	FileOrgCargoMapper fileOrgCargoMapper;
	
	/**
	 * 父类方法重写，根据条件进行我的货物信息查询
	 * 
	 * @see com.tianrui.api.admin.intf.IFileOrgCargoService#queryCargoInfoByCondition(com.tianrui.api.req.admin.FileOrgCargoReq)
	 */
	@Override
	public PaginationVO<FileOrgCargoResp> queryMyCargoInfoByCondition(FileOrgCargoReq req)
			throws Exception {
		
		// 分页VO
		PaginationVO<FileOrgCargoResp> pageVo =new PaginationVO<FileOrgCargoResp>();
		FileOrgCargo reqCondition = new FileOrgCargo();
		// 主键
		reqCondition.setId(req.getId());
		// 组织ID
		reqCondition.setOrganizationid(req.getOrgId());;
		// 组织名称
		reqCondition.setOrganizationname(req.getOrgName());
		// 物料ID
		reqCondition.setCargoid(req.getMaterId());
		// 物料编码
		reqCondition.setCargono(req.getMaterCode());
		// 物料名称
		reqCondition.setCargoname(req.getMaterName());
		// 物料状态
		reqCondition.setCargostatus(req.getMaterState());
		// 状态
		reqCondition.setStatus(req.getState());
		// 主计量单位
		reqCondition.setMeasure(req.getMeasUnit());
		
		long total = fileOrgCargoMapper.selectCountByCondition(reqCondition);
		
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
			List<FileOrgCargo> orgCargoList = fileOrgCargoMapper.selectMyCargoByCondition(reqCondition);
			
			// 数据转换
			List<FileOrgCargoResp> orgCargoRespList = convert2FileOrgCargoList(orgCargoList);
			pageVo.setList(orgCargoRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	@Override
	public List<FileOrgCargoResp> queryMyCargoInfo(FileOrgCargoReq req)
			throws Exception {
		
		FileOrgCargo reqCondition = new FileOrgCargo();
		// 主键
		reqCondition.setId(req.getId());
		// 组织ID
		reqCondition.setOrganizationid(req.getOrgId());;
		// 组织名称
		reqCondition.setOrganizationname(req.getOrgName());
		// 物料ID
		reqCondition.setCargoid(req.getMaterId());
		// 物料编码
		reqCondition.setCargono(req.getMaterCode());
		// 物料名称
		reqCondition.setCargoname(req.getMaterName());
		// 物料状态
		reqCondition.setCargostatus(req.getMaterState());
		// 状态
		reqCondition.setStatus(req.getState());
		// 主计量单位
		reqCondition.setMeasure(req.getMeasUnit());
		// 查询操作
		List<FileOrgCargo> orgCargoList = fileOrgCargoMapper.selectMyCargoByCondition(reqCondition);
		
		// 数据转换
		List<FileOrgCargoResp> orgCargoRespList = convert2FileOrgCargoList(orgCargoList);
		
		return orgCargoRespList;
	}
	
	/**
	 * 父类方法重写，我的货物信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IFileOrgCargoService#insert(com.tianrui.api.req.admin.FileOrgCargoReq)
	 */
	@Override
	public Result insert(FileOrgCargoReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		FileOrgCargo fileOrgCargo = new FileOrgCargo();
		copyProperties(fileOrgCargo, req);
		
		// 设置创建时间
		if (fileOrgCargo.getCreatetime() == null) {
			fileOrgCargo.setCreatetime(new Date().getTime());
		}
		
		// 数据插入操作
		long count = fileOrgCargoMapper.insert(fileOrgCargo);
		if(count != 1){
			rs.setCode("1");
			rs.setError("添加失败");
		}
		return rs;
	}
	
	/**
	 * 父类方法重写，我的货物信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IFileOrgCargoService#update(com.tianrui.api.req.admin.FileOrgCargoReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(FileOrgCargoReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		FileOrgCargo fileOrgCargo = new FileOrgCargo();
		copyProperties(fileOrgCargo, req);
		
		// 设置更新时间
		if (fileOrgCargo.getModifytime() == null) {
			fileOrgCargo.setModifytime(new Date().getTime());
		}
		
		// 数据更新操作
		long count = fileOrgCargoMapper.updateByPrimaryKeySelective(fileOrgCargo);
		
		if(count != 1){
			rs.setCode("1");
			rs.setError("修改失败");
			return rs;
		}
		
		return rs;
	}
	
	/**
	 * 父类方法重写，我的货物信息删除功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IFileOrgCargoService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		// 数据删除操作
		long count = fileOrgCargoMapper.deleteByPrimaryKey(id);
		
		rs.setData(count);
		
		return rs;
	}
	
	/**
	 * 不同类型的List间转换操作
	 * <p>
	 * @param argFileOrgCargoList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:23:00
	 */
	private List<FileOrgCargoResp> convert2FileOrgCargoList(List<FileOrgCargo> argFileOrgCargoList) {
		List<FileOrgCargoResp> fileOrgCargoRespList = null;
		if (argFileOrgCargoList != null) {
			fileOrgCargoRespList = new ArrayList<FileOrgCargoResp>();
			for (FileOrgCargo fileOrgCargo : argFileOrgCargoList) {
				FileOrgCargoResp fileOrgCargoResp =  new FileOrgCargoResp();
				// 主键
				fileOrgCargoResp.setId(fileOrgCargo.getId());
				//被调用次数
				fileOrgCargoResp.setCount(fileOrgCargo.getCount());
				// 组织主键
				fileOrgCargoResp.setOrgId(fileOrgCargo.getOrganizationid());;
				// 组织名称
				fileOrgCargoResp.setOrgName(fileOrgCargo.getOrganizationname());
				// 物料主键
				fileOrgCargoResp.setMaterId(fileOrgCargo.getCargoid());
				// 物料编码
				fileOrgCargoResp.setMaterCode(fileOrgCargo.getCargono());
				// 物料名称
				fileOrgCargoResp.setMaterName(fileOrgCargo.getCargoname());
				// 物料状态
				fileOrgCargoResp.setMaterState(fileOrgCargo.getCargostatus());
				// 状态
				fileOrgCargoResp.setState(fileOrgCargo.getStatus());
				// 主计量单位
				fileOrgCargoResp.setMeasUnit(fileOrgCargo.getMeasure());
				fileOrgCargoRespList.add(fileOrgCargoResp);
			}
		}
		return fileOrgCargoRespList;
	}
	
	/**
	 * FileOrgCargo与FileOrgCargoReq属性的交换
	 * <p>
	 * @param argFileOrgCargo
	 * @param argFileOrgCargoReq
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午2:12:10
	 */
	public void copyProperties(FileOrgCargo argFileOrgCargo, FileOrgCargoReq argFileOrgCargoReq) {
		// 主键
		argFileOrgCargo.setId(argFileOrgCargoReq.getId());
		// 组织ID
		argFileOrgCargo.setOrganizationid(argFileOrgCargoReq.getOrgId());;
		// 组织名称
		argFileOrgCargo.setOrganizationname(argFileOrgCargoReq.getOrgName());
		// 物料ID
		argFileOrgCargo.setCargoid(argFileOrgCargoReq.getMaterId());
		// 物料编码
		argFileOrgCargo.setCargono(argFileOrgCargoReq.getMaterCode());
		// 物料名称
		argFileOrgCargo.setCargoname(argFileOrgCargoReq.getMaterName());
		// 物料状态
		argFileOrgCargo.setCargostatus(argFileOrgCargoReq.getMaterState());
		// 状态
		argFileOrgCargo.setStatus(argFileOrgCargoReq.getState());
		// 主计量单位
		argFileOrgCargo.setMeasure(argFileOrgCargoReq.getMeasUnit());
		// 创建人
		argFileOrgCargo.setCreator(argFileOrgCargoReq.getCreator());
		// 创建时间
		if (argFileOrgCargoReq.getCreateTime() != null) {
			argFileOrgCargo.setCreatetime(Long.parseLong(argFileOrgCargoReq.getCreateTime()));
		} else {
			argFileOrgCargo.setCreatetime(null);
		}
		// 修改人
		argFileOrgCargo.setModifier(argFileOrgCargoReq.getModifier());
		// 修改时间
		if (argFileOrgCargoReq.getModifyTime() != null) {
			argFileOrgCargo.setModifytime(Long.parseLong(argFileOrgCargoReq.getModifyTime()));
		} else {
			argFileOrgCargo.setModifytime(null);
		}
		// 自定义项1
		argFileOrgCargo.setDesc1(argFileOrgCargoReq.getDesc1());
		// 自定义项2
		argFileOrgCargo.setDesc2(argFileOrgCargoReq.getDesc2());
		// 自定义项3
		argFileOrgCargo.setDesc3(argFileOrgCargoReq.getDesc3());
		// 自定义项4
		argFileOrgCargo.setDesc4(argFileOrgCargoReq.getDesc4());
	}

	@Override
	public Result findOne(String id) throws Exception {
		Result rs =Result.getSuccessResult();
		FileOrgCargo fileOrgCargo=fileOrgCargoMapper.selectByPrimaryKey(id);
		if(fileOrgCargo !=null  ){
			FileOrgCargoResp fileOrgCargoResp =  new FileOrgCargoResp();
			// 主键
			fileOrgCargoResp.setId(fileOrgCargo.getId());
			// 组织主键
			fileOrgCargoResp.setOrgId(fileOrgCargo.getOrganizationid());;
			// 组织名称
			fileOrgCargoResp.setOrgName(fileOrgCargo.getOrganizationname());
			// 物料主键
			fileOrgCargoResp.setMaterId(fileOrgCargo.getCargoid());
			// 物料编码
			fileOrgCargoResp.setMaterCode(fileOrgCargo.getCargono());
			// 物料名称
			fileOrgCargoResp.setMaterName(fileOrgCargo.getCargoname());
			// 物料状态
			fileOrgCargoResp.setMaterState(fileOrgCargo.getCargostatus());
			// 状态
			fileOrgCargoResp.setState(fileOrgCargo.getStatus());
			// 主计量单位
			fileOrgCargoResp.setMeasUnit(fileOrgCargo.getMeasure());
			rs.setData(fileOrgCargoResp);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}

	@Override
	public boolean batchUpdateByPrimaryKeySelective(FileOrgCargoReq req) throws Exception {
		// TODO Auto-generated method stub
		FileOrgCargo fileOrgCargo = new FileOrgCargo();
		copyProperties(fileOrgCargo, req);
		// 设置更新时间
		if (fileOrgCargo.getModifytime() == null) {
			fileOrgCargo.setModifytime(new Date().getTime());
		}
		// 数据更新操作
		int count = fileOrgCargoMapper.batchUpdateByPrimaryKeySelective(fileOrgCargo);
		
		if(count != 0){
			return true;
		}
		return false;
	}
	
	
	
}

