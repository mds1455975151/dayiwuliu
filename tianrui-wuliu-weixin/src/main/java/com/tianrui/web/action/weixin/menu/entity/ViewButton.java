package com.tianrui.web.action.weixin.menu.entity;
/**
 * 
 * @类描述：view类型的按钮
 * @创建人：lsj
 * @创建时间：2016年7月19日下午3:41:35
 *
 * @修改人：lsj
 * @修改时间：2016年7月19日下午3:41:35
 * @修改备注：
 *
 */
public class ViewButton extends Button {
	private String type;
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
