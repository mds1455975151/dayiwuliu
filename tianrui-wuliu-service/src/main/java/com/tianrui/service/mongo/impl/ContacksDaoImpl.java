package com.tianrui.service.mongo.impl;

import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.Contacts;
import com.tianrui.service.mongo.ContactDao;

@Repository("contacksDao")
public class ContacksDaoImpl extends BaseDaoImpl<Contacts,String> implements ContactDao{

}
