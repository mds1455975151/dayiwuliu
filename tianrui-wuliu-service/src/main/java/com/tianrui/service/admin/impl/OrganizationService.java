package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IOrganizationService;
import com.tianrui.api.req.admin.OrganizationReq;
import com.tianrui.api.req.admin.OrganizationSaveReq;
import com.tianrui.api.req.admin.OrganizationUpdateReq;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileOrg;
import com.tianrui.service.admin.mapper.FileOrgMapper;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
@Service
public class OrganizationService implements IOrganizationService {
	@Autowired
	FileOrgMapper fileOrgMapper;
	@Autowired
	CacheClient cache;
	@Override
	public Result saveOrganization(OrganizationSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(req != null ){
			Boolean flag = getSaveFlag(req.getOrganizationname(),req.getOrganizationno(), rs);
			if(flag){
				FileOrg org = new FileOrg();
				String id =UUIDUtil.getId();
				org.setId(id);
				org.setOrganizationname(req.getOrganizationname());
				org.setOrganizationtype(req.getOrganizationtype());
				org.setOrganizationno(req.getOrganizationno());
				org.setStatus(req.getStatus());
				org.setCreator(req.getCreator());
				org.setCreatetime(System.currentTimeMillis());
				fileOrgMapper.insert(org);

				String key =CacheHelper.buildKey(CacheModule.ORG,id);
				cache.saveObject(key, org);
			}
		}else {
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	/**
	 * 
	 * @描述:判断输入是否合法
	 * @param organizationname
	 * @param organizationno
	 * @param rs
	 * @return
	 * @返回类型 Boolean
	 * @创建人 tank
	 * @创建时间 2016年6月23日下午6:45:56
	 */
	private Boolean getSaveFlag(String organizationname, String organizationno, Result rs) {
		Boolean flag = true;
		if(StringUtils.isNotBlank(organizationname)){
			FileOrg org = new FileOrg();
			org.setOrganizationname(organizationname);
			if(fileOrgMapper.countWithCondition(org) > 0){
				rs.setCode("11");
				rs.setError("组织名称不能重复，请重新输入。");
				flag = false;
			}else if (StringUtils.isNotBlank(organizationno)) {
				org = new FileOrg();
				org.setOrganizationno(organizationno);
				if(fileOrgMapper.countWithCondition(org) > 0){
					rs.setCode("11");
					rs.setError("组织编码不能重复，请重新输入。");
					flag = false;
				}
			}else {
				rs.setCode("02");
				rs.setError("组织编号不能为空");
				flag = false;
			}
		}else {
			rs.setCode("01");
			rs.setError("组织名称不能为空");
			flag = false;
		}
		return flag;
	}

	@Override
	public Result updateOrganization(OrganizationUpdateReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Boolean flag = getUpdateFlag(req.getOrganizationname(),req.getOrganizationno(), req.getId(), rs);
		if(flag){
			FileOrg org = new FileOrg();
			org.setId(req.getId());
			org.setOrganizationname(req.getOrganizationname());
			org.setOrganizationtype(req.getOrganizationtype());
			org.setOrganizationno(req.getOrganizationno());
			org.setStatus(req.getStatus());
			org.setModifier(req.getModifier());
			org.setModifytime(System.currentTimeMillis());
			fileOrgMapper.updateByPrimaryKeySelective(org);
			
			flush(req.getId());
		}
		return rs;
	}
	/**
	 * 
	 * @描述:判断输入是否合法
	 * @param organizationname
	 * @param organizationno
	 * @param rs
	 * @return
	 * @返回类型 Boolean
	 * @创建人 tank
	 * @创建时间 2016年6月23日下午6:57:57
	 */
	private Boolean getUpdateFlag(String organizationname, String organizationno,String id , Result rs) {
		Boolean flag = true;
		if(StringUtils.isNotBlank(organizationname)){
			FileOrg org = new FileOrg();
			org.setOrganizationname(organizationname);
			if(fileOrgMapper.findWithCondition(org).size() > 0){
				if(!id.equals(fileOrgMapper.findWithCondition(org).get(0).getId())){
					rs.setCode("11");
					rs.setError("组织名称不能重复，请重新输入。");
					flag = false;
				}else if (StringUtils.isNotBlank(organizationno)) {
					org = new FileOrg();
					org.setOrganizationno(organizationno);
					if(fileOrgMapper.findWithCondition(org).size() > 0){
						if(!id.equals(fileOrgMapper.findWithCondition(org).get(0).getId())){
							rs.setCode("11");
							rs.setError("组织编码不能重复，请重新输入。");
							flag = false;
						}
					}
				}else {
					rs.setCode("02");
					rs.setError("组织编号不能为空");
					flag = false;
				}
			}
		}else {
			rs.setCode("01");
			rs.setError("组织名称不能为空");
			flag = false;
		}
		return flag;
	}
	@Override
	public List<OrganizationResp> findCondition(OrganizationReq req) throws Exception {
		List<OrganizationResp> rs = null;
		FileOrg fileOrg = new FileOrg();
		fileOrg.setOrganizationname(req.getOrganizationname());
		fileOrg.setOrganizationtype(req.getOrganizationtype());
		fileOrg.setOrganizationno(req.getOrganizationno());
		fileOrg.setStatus(req.getStatus());
		List<FileOrg> list = fileOrgMapper.findWithCondition(fileOrg);
		rs = conver2OrgResp(list);
		return rs;
	}

	private List<OrganizationResp> conver2OrgResp(List<FileOrg> fileOrgList){
		List<OrganizationResp> rs = null;
		if( CollectionUtils.isNotEmpty(fileOrgList) ){
			rs =new ArrayList<OrganizationResp>();
			for( FileOrg fileOrg:fileOrgList  ){
				rs.add(conver2OrgResp(fileOrg));
			}
		}
		return rs;
	}

	private OrganizationResp conver2OrgResp(FileOrg fileOrg){
		OrganizationResp resp =null;
		if( fileOrg!=null ){
			resp =new OrganizationResp();
			resp.setId(fileOrg.getId());
			resp.setOrganizationname(fileOrg.getOrganizationname());
			resp.setOrganizationtype(fileOrg.getOrganizationtype());
			resp.setOrganizationno(fileOrg.getOrganizationno());
			resp.setCreator(fileOrg.getCreator());
			resp.setCreatetime(fileOrg.getCreatetime());
			resp.setStatus(fileOrg.getStatus());
			resp.setCount(fileOrg.getCount());
		}
		return  resp;
	}

	@Override
	public PaginationVO<OrganizationResp> findOrgByPage(OrganizationReq req) throws Exception {
		
		PaginationVO<OrganizationResp> page = null;
		if( req!=null && req.getPageNo() >0 ){
			page =new PaginationVO<OrganizationResp>();
			
			
			FileOrg fileOrg =new FileOrg();
			fileOrg.setOrganizationname(req.getOrganizationname());
			fileOrg.setOrganizationtype(req.getOrganizationtype());
			fileOrg.setOrganizationno(req.getOrganizationno());
			
			long total =fileOrgMapper.countWithCondition(fileOrg);
			
			if(total>0){
				fileOrg.setStart((req.getPageNo()-1)*req.getPageSize());
				fileOrg.setLimit(req.getPageSize());
				List<FileOrg> fileOrgs=fileOrgMapper.findWithCondition(fileOrg);
				
				page.setList(conver2OrgResp(fileOrgs));
			}
			page.setPageNo(req.getPageNo());
			page.setTotal(total);
			
		}
		return page;
	}

	@Override
	public OrganizationResp findOne(String id) throws Exception {
		OrganizationResp resp = null;
		if(id != null){
			String key =CacheHelper.buildKey(CacheModule.ORG,id);
			FileOrg vo = cache.getObj(key, FileOrg.class);
			if( vo ==null ){
				vo =selectByPrimaryKey(id);
				cache.saveObject(key, vo);
			}
			
			resp = conver2OrgResp(vo);
		}
		return resp;
	}

	@Override
	public void update(OrganizationResp resp) throws Exception {
		FileOrg org = new FileOrg();
		org.setId(resp.getId());
		org.setStatus(resp.getStatus());
		fileOrgMapper.updateByPrimaryKeySelective(org);
		
		flush(resp.getId());
	}
	
	private void flush(String id){
		if( StringUtils.isNotBlank(id) ){
			String key =CacheHelper.buildKey(CacheModule.ORG,id);
			FileOrg vo =selectByPrimaryKey(id);
			cache.saveObject(key, vo);
		}
	}
	
	private FileOrg selectByPrimaryKey(String id){
		return fileOrgMapper.selectByPrimaryKey(id);
	}
	@Override
	public boolean deleteOrg(String id) throws Exception {
		// TODO Auto-generated method stub
		int a = fileOrgMapper.deleteByPrimaryKey(id);
		if(a==1){
			return true;
		}
		return false;
	}
}
