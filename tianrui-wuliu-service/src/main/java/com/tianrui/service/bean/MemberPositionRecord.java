package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
  * @ClassName: MemberPositionRecord 
  * @Description: 上传位置记录表 mongo数据表
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午9:15:58 
  *
 */
@Document(collection = "memberPositionRecord")
public class MemberPositionRecord implements Serializable{
	private static final long serialVersionUID = -7103622484528639237L;

	private String id;

    private String memberid;

    private Integer lat;

    private Integer lon;

    private String creator;

    private Long createtime;//服务器保存点时间
    private String proxygps;
    
    private String createTimeStr;//服务器保存点时间
    
    private Long timeStap;//移动端取点时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public String getProxygps() {
		return proxygps;
	}

	public void setProxygps(String proxygps) {
		this.proxygps = proxygps;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Long getTimeStap() {
		return timeStap;
	}

	public void setTimeStap(Long timeStap) {
		this.timeStap = timeStap;
	}

}