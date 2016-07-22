package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;

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
public class OrgMemberResp extends BaseResp{

	private String id;
	/**
	 * 组织机构id
	 */
	private String organizationid;
	/**
	 * 会员账号
	 */
	private String membertel;
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
	
	private String createtimeStr;
	
	public String getCreatetimeStr() {
		if(createtime !=null){
			createtimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createtime));
		}
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
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
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
