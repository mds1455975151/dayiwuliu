package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.Plan;

public interface PlanTemplateDao extends BaseDao<Plan,String> {

	//获取用户的模版
	List<Plan>  findWithuId(String uId);
}
