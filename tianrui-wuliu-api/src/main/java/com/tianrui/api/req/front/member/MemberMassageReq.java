package com.tianrui.api.req.front.member;

import com.tianrui.api.req.BaseReq;
/**
 * 
 * @类描述：会员信息查询类
 * @创建人：lsj
 * @创建时间：2016年6月22日下午4:57:21
 *
 * @修改人：lsj
 * @修改时间：2016年6月22日下午4:57:21
 * @修改备注：
 *
 */
public class MemberMassageReq extends BaseReq{

	/**
	 * 登陆账号
	 */
	private String cellphone;

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
}
