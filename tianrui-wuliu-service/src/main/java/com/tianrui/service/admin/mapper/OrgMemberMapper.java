package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.service.admin.bean.OrgMember;

public interface OrgMemberMapper {

	/**
	 * 
	 * @描述:查询列表
	 * @param member
	 * @return
	 * @返回类型 List<OrgMember>
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午4:27:31
	 */
	List<OrgMember> findByEntity(OrgMember member);
	/**
	 * 
	 * @描述:查询列表条数
	 * @param member
	 * @return
	 * @返回类型 List<OrgMember>
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午4:27:31
	 */
	long findByEntityCount(OrgMember member);
	/**
	 * 
	 * @描述:id查询
	 * @param id
	 * @return
	 * @返回类型 OrgMember
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午4:28:21
	 */
	OrgMember findById(String id);
	/**
	 * 
	 * @描述:删除
	 * @param id
	 * @return
	 * @返回类型 int
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午4:29:15
	 */
	int delectById(String id);
	/**
	 * 
	 * @描述:修改
	 * @param member
	 * @return
	 * @返回类型 int
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午4:29:56
	 */
	int update(OrgMember member);
	/**
	 * 
	 * @描述:添加
	 * @param member
	 * @return
	 * @返回类型 int
	 * @创建人 lsj
	 * @创建时间 2016年6月6日下午5:00:55
	 */
	int insertEntity(OrgMember member);
	
}
