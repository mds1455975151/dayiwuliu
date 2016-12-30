package com.tianrui.service.mongo.impl;

import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.ApiPosition;
import com.tianrui.service.mongo.ApiPositionDao;
@Repository("apiPositionDao")
public class ApiPositionDaoImpl extends BaseDaoImpl<ApiPosition, String> implements ApiPositionDao{


}
