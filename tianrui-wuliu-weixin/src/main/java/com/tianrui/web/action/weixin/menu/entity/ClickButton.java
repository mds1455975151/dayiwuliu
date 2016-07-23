package com.tianrui.web.action.weixin.menu.entity;
/**
 * 
 * @类描述：ClickButton
 * @创建人：lsj
 * @创建时间：2016年7月19日下午3:40:20
 *
 * @修改人：lsj
 * @修改时间：2016年7月19日下午3:40:20
 * @修改备注：
 *
 */
public class ClickButton extends Button {
	private String type;
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}