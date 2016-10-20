package com.tianrui.service.admin.bean;
/**
 * 
 * @类描述：组织机构—用户关系
 * @创建人：lsj
 * @创建时间：2016年6月6日下午4:14:25
 *
 * @修改人：lsj
 * @修改时间：2016年6月6日下午4:14:25
 * @修改备注：
 *
 */
public class OrgMember {

	private String id;
	/**
	 * 组织机构id
	 */
	private String organizationid;
	/**
	 * 会员账号
	 */
	private String membertel;
	
	private String membername;
	/**
	 * 备注
	 */
	private String memberdesc;
	/**
	 * 添加者id
	 */
	private String creator;
	/**
	 * 添加时间
	 */
	private Long createtime;
	/**
	 * 查询起始位置
	 */
	private Integer limit;
	/**
	 * 查询条数
	 */
	private Integer pageSize;
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getMembertel() {
		return membertel;
	}
	public void setMembertel(String membertel) {
		this.membertel = membertel;
	}
	public String getMemberdesc() {
		return memberdesc;
	}
	public void setMemberdesc(String memberdesc) {
		this.memberdesc = memberdesc;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
