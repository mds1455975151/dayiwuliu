package com.tianrui.service.anlian;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.req.admin.anlian.AnlianDriverReq;
import com.tianrui.api.req.admin.anlian.AnlianShipmentReq;
import com.tianrui.api.req.admin.anlian.AnlianTruckReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.bean.VehicleTicket;
import com.tianrui.service.bean.anlian.AnlianDriver;
import com.tianrui.service.bean.anlian.AnlianShipment;
import com.tianrui.service.bean.anlian.AnlianTruck;
import com.tianrui.service.mapper.MemberVehicleMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
import com.tianrui.service.mapper.VehicleTicketMapper;
@Service
public class AnlianService implements IAnlianService{

	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;
	@Autowired
	VehicleTicketMapper vehicleTicketMapper;
	
	
	//注册车辆
	public static String TRUCK = "http://223.255.14.186:149/api/Truck";
	//注册司机
	public static String DRIVER = "http://223.255.14.186:149/api/Driver";
	//注册挂车
//	public static String TRAILER  = "http://223.255.14.186:149/api/Trailer";
	
	public static String SHIPMENT = "http://223.255.14.186:149/api/Shipment";
	
	@Override
	public Result driver(AnlianDriverReq req) {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getDriverid());
		SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(req.getDriverid());
		String data = anlianUrl(DRIVER,drivrtString(member,info));
		rs.setData(data);
		return rs;
	}

	@Override
	public Result truck(AnlianTruckReq req) {
		Result rs = Result.getSuccessResult();
		MemberVehicle vehicle = memberVehicleMapper.selectByPrimaryKey(req.getVehicleid());
		VehicleTicket ticket = vehicleTicketMapper.selectByPrimaryKey(req.getVehicleid());
		String data = anlianUrl(TRUCK,truckString(vehicle,ticket));
		rs.setData(data);
		return rs;
	}

	@Override
	public Result shipment(AnlianShipmentReq req) {
		Result rs = Result.getSuccessResult();
		//TODO
		String data = anlianUrl(SHIPMENT,shipmentString());
		rs.setData(data);
		return rs;
	}
	
	/** 请求安联接口*/
	public String anlianUrl(String urlStr ,String dataString){
		
		try {
			URL url = new URL(urlStr);
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", " application/json");//设定 请求格式 json，也可以设定xml格式
			connection.setRequestProperty("Accept-Charset", "utf-8");  //设置编码语言
			
			byte[] bypes = dataString.getBytes("utf-8");
			
			connection.getOutputStream().write(bypes);// 输入参数
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response = in.readLine();
			System.out.println("请求返回数据："+response);
		} catch (Exception e) {
			JSONObject obj = new JSONObject();
			obj.put("code", "1");
			obj.put("error", "网络异常");
		}
		
		return null;
	}
	/**运单数据转换*/
	public String shipmentString(){
		AnlianShipment shipment = new AnlianShipment();
		
		
		System.out.println(JSON.toJSONString(shipment));
		return JSON.toJSONString(shipment);
	}
	
	/** 车辆数据转换*/
	public String truckString(MemberVehicle vehicle,VehicleTicket ticket){
		AnlianTruck truck = new AnlianTruck();
		//车牌号码
		truck.setCphm(vehicle.getVehicleprefix()+vehicle.getVehicleno());
		//检验有效日期
		truck.setJyyxqz(ticket.getExpirydata());
		//核定在质量
		truck.setHdzzl(vehicle.getVehiweight().toString());
		//总质量
		truck.setZzl(ticket.getQuality());
		//登记证书编号
		truck.setDjzsbh(ticket.getCertificateno());
		//所有人
		truck.setSyr(ticket.getOwner());
		//身份证明
		truck.setSfzm(ticket.getIdcard());
		//标准车辆类型
		truck.setBzcllx(vehicle.getVehicletype());
		//使用性质
		truck.setSyxz(ticket.getNature());
		//车辆识别代码
		truck.setClsbdm(ticket.getIdentification());
		//发动机号
		truck.setFdjh(ticket.getMotor());
		//发动机型号
		truck.setFdjxh(ticket.getMotorno());
		System.out.println(JSON.toJSONString(truck));
		return JSON.toJSONString(truck);
		
	}
	/** 司机数据转换*/
	public String drivrtString(SystemMember member,SystemMemberInfo info){
		AnlianDriver driver = new AnlianDriver();
		/**司机姓名*/
		driver.setSjxm(info.getUsername());
		/**性别*/
		driver.setXb(info.getSex());
		/** 身份证号码*/
		driver.setSfzhm(info.getIdcard());
		/** 出生日期*/
		driver.setCsrq(info.getBirthday());
		/**身份证地址*/
		driver.setSfzdz(info.getIdcardaddress());
		/**驾驶证*/
		driver.setJsz(info.getIdcard());
		/** 初次领证日期*/
		driver.setCclzrq(info.getFirstlicens());
		/** 发证机关*/
		driver.setFzjg(info.getLicenceorg());
		/** 准驾车型*/
		driver.setZjcx(info.getLicenseType());
		/** 起始日期*/
		driver.setQsrq(info.getStarttime());
		/** 有效年限*/
		driver.setYxqx(info.getUsefullife());
		/** 手机号码*/
		driver.setSjhm(info.getTelphone());
		/***/
		driver.setEmail("");
		/***/
		driver.setQq("");
		System.out.println(JSON.toJSONString(driver));
		return JSON.toJSONString(driver);
	}
}
