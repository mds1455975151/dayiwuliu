package com.tianrui.api.resp.front.position;

/**
 *  位置信息返回
  * @ClassName: PositionResp 
  * @Description: TODO
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午9:34:40 
  *
 */
public class PositionResp {

	private String id;

	private String memberid;

	private Integer lat;

	private Integer lon;

	private String creator;

	private Long createtime;

	private String modifier;

	private Long modifytime;
	private String proxyGps;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
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
		this.creator = creator;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public String getProxyGps() {
		return proxyGps;
	}
	public void setProxyGps(String proxyGps) {
		this.proxyGps = proxyGps;
	}
	
}
