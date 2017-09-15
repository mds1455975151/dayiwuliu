/**
 * Project Name:tianrui-wuliu-service
 * File Name:FileCargoService.java
 * Package Name:com.tianrui.service.admin.impl
 * Date:2016年5月22日下午4:44:36
 *
*/

package com.tianrui.service.admin.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.admin.intf.IFilePositionService;
import com.tianrui.api.req.admin.FilePositionReq;
import com.tianrui.api.resp.admin.FilePositionResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FilePositoin;
import com.tianrui.service.admin.mapper.FilePositoinMapper;


/**
  * @ClassName: FilePositionService 
  * @Description: 位置档案
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年5月25日 上午9:41:01 
  *
 */
@Service
public class FilePositionService implements IFilePositionService {
	
	private static Logger logger =LoggerFactory.getLogger(FilePositionService.class);

	@Autowired
	FilePositoinMapper filePositoinMapper;
	
	@Override
	public PaginationVO<FilePositionResp> findAllPage(FilePositionReq req) throws Exception {
		long begin=System.currentTimeMillis();
		PaginationVO<FilePositionResp> page =null;
		if( req!=null && req.getPageNo() >0 ){
			page =new PaginationVO<FilePositionResp>();
			
			FilePositoin query =new FilePositoin();
			query.setName(req.getName());
			if( StringUtils.isNotBlank(req.getStatus()) ){
				query.setStatus(Short.valueOf(req.getStatus()));
			}
//			query.setOrgid(req.getCurrOrgId());
			
			long total =filePositoinMapper.countByCondition(query);
			if( total >0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<FilePositoin> list= filePositoinMapper.selectByCondition(query);
				page.setList(conver2FilePositionList(list));
			}
			//拼装数据
			page.setPageNo(req.getPageNo());
			page.setTotal(total);
		}	
		logger.info("findAllPage end,耗时:{}ms,参数:{},返回结果:{}",new Object[]{(System.currentTimeMillis()-begin),JSON.toJSON(req),JSON.toJSON(page)});
		return page;
	}

	@Override
	public Result saveFilePosition(FilePositionReq req) throws Exception {
		long begin=System.currentTimeMillis();
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser()) && StringUtils.isNotBlank(req.getCurrOrgId())  ){
			FilePositoin bean =new FilePositoin();
			PropertyUtils.copyProperties(bean, req);
			bean.setCreator(req.getCurrUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setOrgid(req.getCurrOrgId());
			bean.setId(UUIDUtil.getId());
			bean.setStatus(Short.valueOf("1"));
			filePositoinMapper.insert(bean);
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		logger.info("saveFilePosition end,耗时:{}ms,参数:{},返回结果:{}",new Object[]{(System.currentTimeMillis()-begin),JSON.toJSON(req),JSON.toJSON(rs)});
		return rs;
	}

	@Override
	public Result updateFilePosition(FilePositionReq req) throws Exception {
		long begin=System.currentTimeMillis();
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser())){
			if( StringUtils.isNotBlank(req.getId()) ){
				FilePositoin dbBean=filePositoinMapper.selectByPrimaryKey(req.getId());
				if( dbBean !=null  && StringUtils.equals(dbBean.getOrgid(), req.getCurrOrgId()) ){
					FilePositoin updateBean = new FilePositoin();
					PropertyUtils.copyProperties(updateBean, req);
					filePositoinMapper.updateByPrimaryKeySelective(updateBean);
				}else{
					rs.setErrorCode(ErrorCode.FILEROUTE_REFUSE_NOT_ORG);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		logger.info("updateFilePosition end,耗时:{}ms,参数:{},返回结果:{}",new Object[]{(System.currentTimeMillis()-begin),JSON.toJSON(req),JSON.toJSON(rs)});
		return rs;
	}

	@Override
	public List<FilePositionResp> findWithCondition(FilePositionReq req) throws Exception {
		long begin=System.currentTimeMillis();
		FilePositoin query =new FilePositoin();
		List<FilePositionResp> rs =null;
		if( req !=null ){
			query.setOrgid(req.getCurrOrgId());
			query.setName(req.getName());
			query.setStatus(Short.valueOf("1"));
			List<FilePositoin> list =filePositoinMapper.selectByCondition(query);
			rs =conver2FilePositionList(list);
		}
		logger.info("findWithCondition end,耗时:{}ms,参数:{},返回结果:{}",new Object[]{(System.currentTimeMillis()-begin),JSON.toJSON(req),JSON.toJSON(rs)});
		return rs;
	}
	
	
	@Override
	public Result delFilePosition(FilePositionReq req) throws Exception {
		long begin=System.currentTimeMillis();
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser())){
			if( StringUtils.isNotBlank(req.getId()) ){
				FilePositoin dbBean=filePositoinMapper.selectByPrimaryKey(req.getId());
				if( dbBean !=null  && StringUtils.equals(dbBean.getOrgid(), req.getCurrOrgId()) ){
					FilePositoin updateBean = new FilePositoin();
					updateBean.setId(req.getId());
					updateBean.setStatus(Short.valueOf(req.getStatus()));
					filePositoinMapper.updateByPrimaryKeySelective(updateBean);
				}else{
					rs.setErrorCode(ErrorCode.FILEROUTE_REFUSE_NOT_ORG);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		logger.info("delFilePosition end,耗时:{}ms,参数:{},返回结果:{}",new Object[]{(System.currentTimeMillis()-begin),JSON.toJSON(req),JSON.toJSON(rs)});
		return rs;
	}
	

	@Override
	public Result detailFilePosition(FilePositionReq req) throws Exception {
		long begin=System.currentTimeMillis();
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser())){
			if( StringUtils.isNotBlank(req.getId()) ){
				FilePositoin dbBean=filePositoinMapper.selectByPrimaryKey(req.getId());
				if( dbBean !=null  && StringUtils.equals(dbBean.getOrgid(), req.getCurrOrgId()) ){
					rs.setData(conver2FilePosition(dbBean));
				}else{
					rs.setErrorCode(ErrorCode.FILEROUTE_REFUSE_NOT_ORG);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		logger.info("detailFilePosition end,耗时:{}ms,参数:{},返回结果:{}",new Object[]{(System.currentTimeMillis()-begin),JSON.toJSON(req),JSON.toJSON(rs)});
		return rs;
	}

	private List<FilePositionResp> conver2FilePositionList(List<FilePositoin> list)throws Exception{
		List<FilePositionResp> rs = null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs = new ArrayList<FilePositionResp>();
			for( FilePositoin item:list ){
				rs.add(conver2FilePosition(item));
			}
		}
		return rs;
	}

	
	private FilePositionResp conver2FilePosition(FilePositoin filePositoin) throws Exception{
		FilePositionResp resp =null;
		if( filePositoin !=null ){
			resp =new FilePositionResp();
			PropertyUtils.copyProperties(resp, filePositoin);
		}
		return resp;
	}

	@Override
	public boolean deletePosition(String id) throws Exception {
		// TODO Auto-generated method stub
		long a = filePositoinMapper.deleteByPrimaryKey(id);
		if(a==1){
			return true;
		}
		return false;
	}
}

