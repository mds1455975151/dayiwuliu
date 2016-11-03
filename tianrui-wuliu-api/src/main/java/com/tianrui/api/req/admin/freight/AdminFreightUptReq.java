package com.tianrui.api.req.admin.freight;

import com.tianrui.api.req.BaseReq;
/**
 * 
 * @类描述：运价策略审核
 * @创建人：lsj
 * @创建时间：2016年8月24日下午4:37:13
 *
 * @修改人：lsj
 * @修改时间：2016年8月24日下午4:37:13
 * @修改备注：
 *
 */
public class AdminFreightUptReq extends BaseReq{

	private String id;
	
	private String infoid;
	
	private String audit;
	
	private String auditresson;
	
	private String updater;
	
	private Long modifytime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getAudit() {
		return audit;
	}

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getAuditresson() {
		return auditresson;
	}

	public void setAuditresson(String auditresson) {
		this.auditresson = auditresson;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
}
