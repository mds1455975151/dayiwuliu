package com.tianrui.service.mongo.impl;

import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.FileReg;
import com.tianrui.service.mongo.FileRegDao;

@Repository("fileRegDao")
public class FileRegDaoImpl extends BaseDaoImpl<FileReg,String> implements FileRegDao{

}
