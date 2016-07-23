package com.tianrui.web.action.weixin.menu.entity;
/**
 * 
 * @类描述：复合型按钮
 * @创建人：lsj
 * @创建时间：2016年7月19日下午3:40:55
 *
 * @修改人：lsj
 * @修改时间：2016年7月19日下午3:40:55
 * @修改备注：
 *
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
