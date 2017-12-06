/**
 * Project Name:tianrui-wuliu-service
 * File Name:FileCargoService.java
 * Package Name:com.tianrui.service.admin.impl
 * Date:2016年5月22日下午4:44:36
 *
*/

package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFileCargoService;
import com.tianrui.api.req.admin.FileCargoReq;
import com.tianrui.api.resp.admin.FileCargoResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.mapper.FileCargoMapper;

/**
 * 货物档案接口实现类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 下午4:45:12
 */
@Service
public class FileCargoService implements IFileCargoService {
	
	@Autowired
	FileCargoMapper fileCargoMapper;
	
	/**
	 * 父类方法重写，根据条件进行货物档案信息查询
	 * 
	 * @see com.tianrui.api.admin.intf.IFileCargoService#queryCargoInfoByCondition(com.tianrui.api.req.admin.FileCargoReq)
	 */
	@Override
	public PaginationVO<FileCargoResp> queryCargoInfoByCondition(FileCargoReq req)
			throws Exception {
		// 分页VO
		PaginationVO<FileCargoResp> pageVo =new PaginationVO<FileCargoResp>();
		FileCargo reqCondition = new FileCargo();
		// 物料编码
		reqCondition.setCargono(req.getMaterCode());
		// 物料名称
		reqCondition.setCargoname(req.getMaterName());
		// 物料类别
		reqCondition.setCargotype(req.getMaterClass());
		// 货物规格
		reqCondition.setSpecifications(req.getSpec());
		// 货物型号
		reqCondition.setModel(req.getModel());
		// 物料助记码
		reqCondition.setZjm(req.getMaterMNCode());
		// 主计量单位
		reqCondition.setMeasure(req.getMeasUnit());
		// 主键
		reqCondition.setId(req.getId());
		// 状态
		reqCondition.setStatus(req.getState());
		
		long total = fileCargoMapper.selectCountByCondition(reqCondition);
		
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
			List<FileCargo> fileCargoList = fileCargoMapper.selectCargoByCondition(reqCondition);
			
			// 数据转换
			List<FileCargoResp> fileCargoRespList = convert2FileCargoList(fileCargoList);
			pageVo.setList(fileCargoRespList);
			pageVo.setPageNo(req.getPageNo());
			pageVo.setTotal(total);
			
			return pageVo;
		}
	}
	
	/**
	 * 父类方法重写，货物档案信息新增保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IFileCargoService#insert(com.tianrui.api.req.admin.FileCargoReq)
	 */
	@Override
	public Result insert(FileCargoReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		if(req != null ){
			Boolean flag = getSaveFlag(req.getMaterCode(),req.getMaterName(), rs);
			if(flag){
				FileCargo fileCargo = new FileCargo();
				copyProperties(fileCargo, req);
				// 设置创建时间
				if (fileCargo.getCreatetime() == null) {
					fileCargo.setCreatetime(new Date().getTime());
				}
				// 数据插入操作
				long count = fileCargoMapper.insert(fileCargo);
				rs.setData(count);
			}
		}else {
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	/**
	 * 
	 * @描述:判断输入数据合法性
	 * @param materCode
	 * @param materName
	 * @param rs
	 * @return
	 * @返回类型 Boolean
	 * @创建人 tank
	 * @创建时间 2016年6月22日下午4:28:31
	 */
	private Boolean getSaveFlag(String materCode, String materName, Result rs) {
		Boolean flag = true;
		if(StringUtils.isNotBlank(materCode)){
			FileCargo fc = new FileCargo();
			fc.setCargono(materCode);
			if(fileCargoMapper.selectCountByCondition(fc) > 0){
				rs.setCode("01");
				rs.setError("物料编码已经存在，请重新输入");
				flag = false;
			}else{
				if ( StringUtils.isNotBlank(materName)) {
					fc = new FileCargo();
					fc.setCargoname(materName);
					if(fileCargoMapper.selectCountByCondition(fc) > 0){
						rs.setCode("11");
						rs.setError("物料名称已经存在，请重新输入...");
						flag = false;
					}
				}else {
					rs.setCode("1");
					rs.setError("物料名称不能为空，请输入...");
					flag = false;
				}
				
			}
		}else {
			rs.setCode("0");
			rs.setError("物料编码不能为空，请输入...");
			flag = false;
		}
		return flag;
	}

	/**
	 * 父类方法重写，货物档案信息修改后保存功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IFileCargoService#update(com.tianrui.api.req.admin.FileCargoReq)
	 */
	@Override
	public Result updateByPrimaryKeySelective(FileCargoReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		Boolean flag = true;
		if(req.getState() != ""){
			
		}else {
			flag = getUpdateFlag(req, rs);
		}
		if(flag){
			FileCargo fileCargo = new FileCargo();
			copyProperties(fileCargo, req);
			// 设置更新时间
			if (fileCargo.getModifytime() == null) {
				fileCargo.setModifytime(new Date().getTime());
			}
			// 数据更新操作
			long count = fileCargoMapper.updateByPrimaryKeySelective(fileCargo);
			if(count != 1){
				rs.setCode("1");
				rs.setError("修改失败");
			}
		}
		return rs;
	}
	/**
	 * 
	 * @描述:修改输入合法性验证
	 * @param req
	 * @param rs
	 * @return
	 * @返回类型 Boolean
	 * @创建人 tank
	 * @创建时间 2016年6月22日下午5:23:16
	 */
	private Boolean getUpdateFlag(FileCargoReq req, Result rs) {
		Boolean flag = true;
		if(StringUtils.isNotBlank(req.getMaterCode())){
			FileCargo fc = new FileCargo();
			fc.setCargono(req.getMaterCode());
			if(fileCargoMapper.selectCargoByCondition(fc).size() > 0){
				if(!req.getId().equals(fileCargoMapper.selectCargoByCondition(fc).get(0).getId())){
					rs.setCode("01");
					rs.setError("物料编码已经存在，请重新输入");
					flag = false;
				}
			}
		}else {
			rs.setCode("0");
			rs.setError("物料编码不能为空，请输入...");
			flag = false;
		}
		if ( StringUtils.isNotBlank(req.getMaterName())) {
			FileCargo fc = new FileCargo();
			fc.setCargoname(req.getMaterName());
			if(fileCargoMapper.selectCargoByCondition(fc).size() > 0){
				if(!req.getId().equals(fileCargoMapper.selectCargoByCondition(fc).get(0).getId())){
					rs.setCode("11");
					rs.setError("物料名称已经存在，请重新输入...");
					flag = false;
				}
			}
		}else {
			rs.setCode("1");
			rs.setError("物料名称不能为空，请输入...");
			flag = false;
		}
		return flag;
	}

	/**
	 * 父类方法重写，货物档案信息删除功能操作
	 * 
	 * @see com.tianrui.api.admin.intf.IFileCargoService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public Result deleteByPrimaryKey(String id) throws Exception {
		
		Result rs = Result.getSuccessResult();
		
		// 数据删除操作
		long count = fileCargoMapper.deleteByPrimaryKey(id);
		
		rs.setData(count);
		
		return rs;
	}
	
	/**
	 * 不同类型的List间转换操作
	 * <p>
	 * @param argFileCargoList
	 * @return
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:23:00
	 */
	private List<FileCargoResp> convert2FileCargoList(List<FileCargo> argFileCargoList) {
		List<FileCargoResp> fileCargoRespList = null;
		if (argFileCargoList != null) {
			fileCargoRespList = new ArrayList<FileCargoResp>();
			for (FileCargo fileCargo : argFileCargoList) {
				FileCargoResp fileCargoResp =  new FileCargoResp();
				// 主键
				fileCargoResp.setId(fileCargo.getId());
				//被调用次数
				fileCargoResp.setCount(fileCargo.getCount());
				// 组织编码
				fileCargoResp.setOrgCode(fileCargo.getOrganizationid());;
				// 组织名称
				fileCargoResp.setOrgName(fileCargo.getOrganizationname());;
				// 组织类型
				fileCargoResp.setOrgType(fileCargo.getDesc1());
				// 物料编码
				fileCargoResp.setMaterCode(fileCargo.getCargono());
				// 物料名称
				fileCargoResp.setMaterName(fileCargo.getCargoname());
				// 物料类别
				fileCargoResp.setMaterClass(fileCargo.getCargotype());
				// 状态
				fileCargoResp.setState(fileCargo.getStatus());
				//支付类型
				fileCargoResp.setPayType(fileCargo.getPayType());
				// 货物规格
				fileCargoResp.setSpec(fileCargo.getSpecifications());
				// 货物型号
				fileCargoResp.setModel(fileCargo.getModel());
				// 物料助记码
				fileCargoResp.setMaterMNCode(fileCargo.getZjm());
				// 主计量单位
				fileCargoResp.setMeasUnit(fileCargo.getMeasure());
				// 图片路径
				fileCargoResp.setImgPath(fileCargo.getImagepath());
				fileCargoResp.setDesc1(fileCargo.getDesc1());
				fileCargoResp.setDesc2(fileCargo.getDesc2());
				if(null == fileCargo.getDesc3() || "".equals(fileCargo.getDesc3())){
					fileCargoResp.setDesc3("");
				}else {
					fileCargoResp.setDesc3(fileCargo.getDesc3());
				}
				
				fileCargoResp.setDesc4(fileCargo.getDesc4());
				fileCargoRespList.add(fileCargoResp);
			}
		}
		return fileCargoRespList;
	}
	
	/**
	 * FileCargo与FileCargoReq属性的交换
	 * <p>
	 * @param argFileCargo
	 * @param argFileCargoReq
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午2:12:10
	 */
	public void copyProperties(FileCargo argFileCargo, FileCargoReq argFileCargoReq) {
		// 主键
		argFileCargo.setId(argFileCargoReq.getId());
		// 组织编码
		argFileCargo.setOrganizationid(argFileCargoReq.getOrgCode());;
		// 组织名称
		argFileCargo.setOrganizationname(argFileCargoReq.getOrgName());;
//		// 组织类型
//		argFileCargo.setDesc1(argFileCargoReq.getOrgType());
		// 物料编码
		argFileCargo.setCargono(argFileCargoReq.getMaterCode());
		// 物料名称
		argFileCargo.setCargoname(argFileCargoReq.getMaterName());
		// 物料类别
		argFileCargo.setCargotype(argFileCargoReq.getMaterClass());
		// 状态
		argFileCargo.setStatus(argFileCargoReq.getState());
		// 支付类型
		argFileCargo.setPayType(argFileCargoReq.getPayType());
		// 货物规格
		argFileCargo.setSpecifications(argFileCargoReq.getSpec());
		// 货物型号
		argFileCargo.setModel(argFileCargoReq.getModel());
		// 物料助记码
		argFileCargo.setZjm(argFileCargoReq.getMaterMNCode());
		// 主计量单位
		argFileCargo.setMeasure(argFileCargoReq.getMeasUnit());
		// 图片路径
		argFileCargo.setImagepath(argFileCargoReq.getImgPath());
		// 创建人
		argFileCargo.setCreator(argFileCargoReq.getCreator());
		// 创建时间
		if (argFileCargoReq.getCreateTime() != null) {
			argFileCargo.setCreatetime(Long.parseLong(argFileCargoReq.getCreateTime()));
		} else {
			argFileCargo.setCreatetime(null);
		}
		// 修改人
		argFileCargo.setModifier(argFileCargoReq.getModifier());
		// 修改时间
		if (argFileCargoReq.getModifyTime() != null) {
			argFileCargo.setModifytime(Long.parseLong(argFileCargoReq.getModifyTime()));
		} else {
			argFileCargo.setModifytime(null);
		}
		// 自定义项1
		argFileCargo.setDesc1(argFileCargoReq.getDesc1());
		// 自定义项2
		argFileCargo.setDesc2(argFileCargoReq.getDesc2());
		// 自定义项3
		argFileCargo.setDesc3(argFileCargoReq.getDesc3());
		// 自定义项4
		argFileCargo.setDesc4(argFileCargoReq.getDesc4());
	}

	@Override
	public FileCargoResp findByid(String id) throws Exception {
		FileCargo fileCargo = fileCargoMapper.selectByPrimaryKey(id);
		FileCargoResp fileCargoResp =  new FileCargoResp();
		// 主键
		fileCargoResp.setId(fileCargo.getId());
		//被调用次数
		fileCargoResp.setCount(fileCargo.getCount());
		// 组织编码
		fileCargoResp.setOrgCode(fileCargo.getOrganizationid());;
		// 组织名称
		fileCargoResp.setOrgName(fileCargo.getOrganizationname());;
		// 组织类型
		fileCargoResp.setOrgType(fileCargo.getDesc1());
		// 物料编码
		fileCargoResp.setMaterCode(fileCargo.getCargono());
		// 物料名称
		fileCargoResp.setMaterName(fileCargo.getCargoname());
		// 物料类别
		fileCargoResp.setMaterClass(fileCargo.getCargotype());
		// 状态
		fileCargoResp.setState(fileCargo.getStatus());
		// 货物规格
		fileCargoResp.setSpec(fileCargo.getSpecifications());
		// 货物型号
		fileCargoResp.setModel(fileCargo.getModel());
		// 物料助记码
		fileCargoResp.setMaterMNCode(fileCargo.getZjm());
		// 主计量单位
		fileCargoResp.setMeasUnit(fileCargo.getMeasure());
		// 图片路径
		fileCargoResp.setImgPath(fileCargo.getImagepath());
		return fileCargoResp;
	}

	@Override
	public boolean batchUpdateByPrimaryKeySelective(FileCargoReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		Boolean flag = true;
		if(req.getState() != ""){
			
		}else {
			flag = getUpdateFlag(req, rs);
		}
		if(flag){
			FileCargo fileCargo = new FileCargo();
			copyProperties(fileCargo, req);
			// 设置更新时间
			if (fileCargo.getModifytime() == null) {
				fileCargo.setModifytime(new Date().getTime());
			}
			// 数据更新操作
			long count = fileCargoMapper.batchUpdateByPrimaryKeySelective(fileCargo);
			if(count != 0){
				return true;
			}
		}
		return false;
	}
}

