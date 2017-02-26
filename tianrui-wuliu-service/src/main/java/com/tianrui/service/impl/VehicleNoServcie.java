package com.tianrui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IVehicleNOService;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.mapper.MemberVehicleMapper;
@Service
public class VehicleNoServcie implements IVehicleNOService{

	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	
	private static String randNumber = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
	
	private static String randStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static int stringNum = 5;//随机产生字符数量
	
	@Override
	public String getVehicleNo() {
		long f = 0;
		long x = 0;
		String vehicleNo = null;
		do {
			vehicleNo = vehicleNo();
			//判断车辆唯一
			MemberVehicle vehic = new MemberVehicle();
			vehic.setVehicleprefix(vehicleNo.substring(0,2));
			vehic.setVehicleno(vehicleNo.substring(2,7));
			f = memberVehicleMapper.selectCountByCondition(vehic);
		} while (f!=x);
		return vehicleNo;
	}

	public String vehicleNo(){
		int f = (int)(Math.random()*26);
		String no = "";
		for (int i = 0; i < stringNum; i++) {
			int n=(int)(Math.random()*36);
			if(i%2==0){
				n=(int)(Math.random()*10);
			}
			no += String.valueOf(randNumber.charAt(n));
		}
		String fix = String.valueOf(randStr.charAt(f));
		String vehicleNo = "临"+fix+no;
		return vehicleNo;

	}
	
}
