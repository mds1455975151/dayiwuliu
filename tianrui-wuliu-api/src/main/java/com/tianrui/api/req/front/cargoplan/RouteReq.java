package com.tianrui.api.req.front.cargoplan;
/**
 * 
 * @类描述：路线查询传参
 * @创建人：lsj
 * @创建时间：2016年5月23日下午3:30:52
 *
 * @修改人：lsj
 * @修改时间：2016年5月23日下午3:30:52
 * @修改备注：
 *
 */
public class RouteReq {

	 /**
     * ID
     * @mbggenerated
     */
    private String id;

    /**
     *  路线所属组织名称 
     * @mbggenerated
     */
    private String organizationname;

    /**
     * 所属组织id
     * @mbggenerated
     */
    private String organizationid;
    
    private String status;

    //路线名称
    private String routename;

    private Integer pageNo;
    private Integer pageSize;
    //创建人
    private String creator;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
