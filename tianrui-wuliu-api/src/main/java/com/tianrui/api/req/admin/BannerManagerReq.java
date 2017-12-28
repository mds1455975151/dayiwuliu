package com.tianrui.api.req.admin;

public class BannerManagerReq {

	private String id;
    
    //发布图片多张图片的id   多个id  ;  分割
  	private String pushBannerIds;
  	//名称
    private String picName;
    //链接地址
    private String httpUrl;
    //启用禁用状态 0：未启用 1：启用
    private Byte picStatus;
    //发布状态 0：未发布 1 已发布
    private Byte pushStatus;
    //创建人
    private String creator;
    //创建时间
    private Long createDate;
    //修改人
    private String modifier;
    //数据状态 0：无效 1：有效
    private String status;
    
    protected int pageNo;
	
	protected int pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public String getPushBannerIds() {
		return pushBannerIds;
	}

	public void setPushBannerIds(String pushBannerIds) {
		this.pushBannerIds = pushBannerIds;
	}

	public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl == null ? null : httpUrl.trim();
    }

    public Byte getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(Byte picStatus) {
        this.picStatus = picStatus;
    }

    public Byte getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Byte pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
}