package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.service.admin.bean.MyDriver;

public interface MyDriverMapper {

	List<MyDriver> findByEntity(MyDriver driver);
}
