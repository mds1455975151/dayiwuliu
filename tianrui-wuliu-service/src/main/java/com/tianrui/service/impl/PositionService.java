package com.tianrui.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IPositionService;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.anlian.PositionCity;
import com.tianrui.service.bean.anlian.PositionCounty;
import com.tianrui.service.bean.anlian.PositionProvince;
import com.tianrui.service.mapper.PositionCityMapper;
import com.tianrui.service.mapper.PositionCountyMapper;
import com.tianrui.service.mapper.PositionProvinceMapper;
@Service
public class PositionService implements IPositionService{

	@Autowired
	PositionCityMapper positionCityMapper;
	@Autowired
	PositionCountyMapper positionCountyMapper;
	@Autowired
	PositionProvinceMapper positionProvinceMapper;
	
	@Override
	public Result update(List<Map<String, String>> req) {
		Result rs = Result.getSuccessResult();
		for(Map<String, String> map:req){
			//地区名称
			String name = map.get("name");
			//地区编码
			String code = map.get("code");
			System.out.println(code.substring(code.length()-4, code.length()));
			if(code.substring(code.length()-4, code.length()).equals("0000")){
				//后四位等于0 省
				PositionProvince record = new PositionProvince();
				record.setId(code);
				record.setName(name);
				positionProvinceMapper.insertSelective(record);
			}else if(code.substring(code.length()-2, code.length()).equals("00")){
				//两位等于0 市
				PositionCity record = new PositionCity();
				record.setId(code);
				record.setName(name);
				record.setFartherid(code.substring(0, code.length()-4)+"0000");
				positionCityMapper.insertSelective(record);
			}else{
				//县
				PositionCounty record = new PositionCounty();
				record.setId(code);
				record.setName(name);
				record.setFartherid(code.substring(0, code.length()-2)+"00");
				positionCountyMapper.insertSelective(record);
			}
		}
		
		return rs;
	}

}
