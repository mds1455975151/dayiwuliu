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

import com.tianrui.api.admin.intf.IFileRouteService;
import com.tianrui.api.req.admin.FileRouteReq;
import com.tianrui.api.req.admin.FileRouteSaveReq;
import com.tianrui.api.req.admin.FileRouteUpdateReq;
import com.tianrui.api.resp.admin.FileRouteResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.mapper.FileRouteMapper;

/**
 * 
  * @ClassName: FileRouteService 
  * @Description: 路径档案
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年5月25日 上午9:43:29 
  *
 */
@Service
public class FileRouteService implements IFileRouteService {
	
	private static Logger logger =LoggerFactory.getLogger(FileRouteService.class);
	
	@Autowired
	FileRouteMapper fileRouteMapper;

	@Override
	public PaginationVO<FileRouteResp> findAllPage(FileRouteReq req) throws Exception {
		PaginationVO<FileRouteResp> page =null;
		if( req!=null && req.getPageNo() >0 ){
			page =new PaginationVO<FileRouteResp>();
			
			FileRoute query =new FileRoute();
			query.setRoutename(req.getRoutename());
			query.setStatus(req.getStatus());
			query.setOrganizationid(req.getCurrOrgId());
			
			long total =fileRouteMapper.countByCondition(query);
			if( total >0 ){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				List<FileRoute> list= fileRouteMapper.selectByCondition(query);
				page.setList(conver2FileRouteList(list));
			}
			//拼装数据
			page.setPageNo(req.getPageNo());
			page.setTotal(total);
		}	
		return page;
	}

	@Override
	public Result saveFileRoute(FileRouteSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrOrgId()) && StringUtils.isNotBlank(req.getCurrUser())  ){
			FileRoute fileRoute =new FileRoute();
			PropertyUtils.copyProperties(fileRoute, req);
			
			if( StringUtils.isNotBlank(req.getDistanceStr()) ){
				fileRoute.setDistance(Double.valueOf(req.getDistanceStr()));
			}
			
			fileRoute.setId(UUIDUtil.getId());
			fileRoute.setStatus("1");
			
			fileRoute.setCreatetime(System.currentTimeMillis());
			fileRoute.setCreator(req.getCurrUser());
			fileRoute.setModifier(req.getCurrUser());
			fileRoute.setModifytime(System.currentTimeMillis());
			
			fileRoute.setOrganizationid(req.getCurrOrgId());
			fileRoute.setOrganizationname(req.getCurrOrgName());
			fileRouteMapper.insert(fileRoute);
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		
		return rs;
	}

	@Override
	public Result updateFileRoute(FileRouteUpdateReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser())){
			if( StringUtils.isNotBlank(req.getId()) ){
				FileRoute dbRoute=fileRouteMapper.selectByPrimaryKey(req.getId());
				if( dbRoute !=null  && StringUtils.equals(dbRoute.getOrganizationid(), req.getCurrOrgId()) ){
					FileRoute update = new FileRoute();
					PropertyUtils.copyProperties(update, req);
					update.setModifier(req.getCurrUser());
					update.setModifytime(System.currentTimeMillis());
					if( StringUtils.isNotBlank(req.getDistanceStr()) ){
						update.setDistance(Double.valueOf(req.getDistanceStr()));
					}
					fileRouteMapper.updateByPrimaryKeySelective(update);
				}else{
					rs.setErrorCode(ErrorCode.FILEROUTE_REFUSE_NOT_ORG);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		return rs;
	}

	@Override
	public Result delFileRoute(FileRouteReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser())  && StringUtils.isNotBlank(req.getCurrUser())  && StringUtils.isNotBlank(req.getStatus()) ){
			if( StringUtils.isNotBlank(req.getId()) ){
				FileRoute dbRoute=fileRouteMapper.selectByPrimaryKey(req.getId());
				if( dbRoute !=null  && StringUtils.equals(dbRoute.getOrganizationid(), dbRoute.getOrganizationid()) ){
					FileRoute update = new FileRoute();
					update.setId(req.getId());
					update.setStatus(req.getStatus());
					update.setModifier(req.getCurrUser());
					update.setModifytime(System.currentTimeMillis());
					fileRouteMapper.updateByPrimaryKeySelective(update);
				}else{
					rs.setErrorCode(ErrorCode.FILEROUTE_REFUSE_NOT_ORG);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		return rs;
	}
	@Override
	public Result detailFileRoute(FileRouteReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req!=null && StringUtils.isNotBlank(req.getCurrUser())  && StringUtils.isNotBlank(req.getCurrUser())  ){
			if( StringUtils.isNotBlank(req.getId()) ){
				FileRoute dbRoute=fileRouteMapper.selectByPrimaryKey(req.getId());
				if( dbRoute !=null  && StringUtils.equals(dbRoute.getOrganizationid(), dbRoute.getOrganizationid()) ){
					rs.setData(dbRoute);
				}else{
					rs.setErrorCode(ErrorCode.FILEROUTE_REFUSE_NOT_ORG);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.FILEROUTE_USER_IS_NULL);
		}
		return rs;
	}
	
	private List<FileRouteResp> conver2FileRouteList(List<FileRoute> list){
		List<FileRouteResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs= new ArrayList<FileRouteResp>(list.size());
			for(FileRoute item:list){
				rs.add(conver2FileRoute(item));
			}
		}
		return rs;
	}
	
	private FileRouteResp conver2FileRoute(FileRoute fileRoute){
		FileRouteResp fileRouteResp =null;
		if( fileRoute !=null ){
			fileRouteResp =new 	FileRouteResp();
			try {
				PropertyUtils.copyProperties(fileRouteResp, fileRoute);
			}catch (Exception e) {
				logger.warn("转化为路径返回对象异常:{}",e.getMessage(),e);
			}
		}
		return fileRouteResp;
	}

	@Override
	public boolean delete(String id) throws Exception {
		if( StringUtils.isNotBlank(id) ){
			long a = fileRouteMapper.deleteByPrimaryKey(id);
			if(a == 1){
				return true;
			}
		}
		return false;
	}
}

