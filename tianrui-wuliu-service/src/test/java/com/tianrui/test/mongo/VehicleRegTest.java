package com.tianrui.test.mongo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.VehicleReg;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.VehicleRegDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class VehicleRegTest {
	
	public static Logger logger =LoggerFactory.getLogger(VehicleRegTest.class);
	@Autowired
	private  VehicleRegDao vehicleRegDao;
	
	

	public VehicleRegTest() {
		super();
	}

	@Test
	@Ignore
	public void saveTest(){
		VehicleReg bean =new VehicleReg();
		bean.setId(UUIDUtil.getId());
		bean.setVehicleNo("京D12980");
		bean.setVehicleMobile("13809090909");
		bean.setVehicleLen("14.1");
		bean.setVehicleLoad("55.5");
		bean.setVehicleOwner("王武");
		bean.setVehicleOwnerIdCard("410482199902120909");
		bean.setVehicleOwnerTel("13900000000");
		
		bean.setRegStepStatus((short)1);
		bean.setCheckStatus((short)0);
		bean.setCreateTime(System.currentTimeMillis());
		bean.setLastUpdateTime(System.currentTimeMillis());
		
		vehicleRegDao.save(bean);

	}
	
	@Test
	@Ignore
	public void regStep2(){
		VehicleReg bean =vehicleRegDao.findOne("b715faabee8f4cedbce3b75a30acca7c");
		//2临时认证 1完全认证 
		bean.setAuthType((short)2);
		bean.setVehicleImg("http://www.appb2b.com/trwlWebStatic/tianrui/images/carinfo.jpg");
		//营运证
		bean.setRoadTransportNo("1wdsdWDDSD");
		bean.setTaxiLicenseNo("12345677");
		bean.setTaxiLicenseImg("http://www.appb2b.com/trwlWebStatic/tianrui/images/carinfo.jpg");
		//行驶证
		bean.setDrivingLicenseNo("12346774");
		bean.setDrivingLicenseImg("http://www.appb2b.com/trwlWebStatic/tianrui/images/carinfo.jpg");
		//登记证
		bean.setVehicleGradeImg("12346774");
		bean.setVehicleGradeNo("http://www.appb2b.com/trwlWebStatic/tianrui/images/carinfo.jpg");
		
		bean.setRegStepStatus((short)2);
		bean.setLastUpdateTime(System.currentTimeMillis());
		vehicleRegDao.remove("b715faabee8f4cedbce3b75a30acca7c");
		vehicleRegDao.save(bean);
	}
	@Test
	public void regStep3(){
		VehicleReg bean =vehicleRegDao.findOne("b715faabee8f4cedbce3b75a30acca7c");
		bean.setDriverName("王六");
		//性别
		bean.setDriverSex("1");
		//身份证号
		bean.setDriverIdCard("410482198808120909");
		//出生日期
		bean.setDriverBirthDate("1988-08-12");
		//联系电话
		bean.setDriverLinkTel("17709090909");
		//身份证地址
		bean.setDriverIdCardAddr("河南省汝州市临汝镇");
		//初次领证日期
		bean.setDriverCardFirstlicens("1988-08-12");
		//发证机关
		bean.setDriverCardLicenceorg("1988-08-12");
		//驾驶证注册时间
		bean.setDriverCardRegDate("1988-08-12");
		//有效年限 
		bean.setDriverCardUsefullife("6");
		//准架车型
		bean.setDriverCardType("A1");
		//驾驶证图片
		bean.setDriverCardImg("http://www.appb2b.com/trwlWebStatic/tianrui/images/carinfo.jpg");
		
		bean.setRegStepStatus((short)3);
		bean.setLastUpdateTime(System.currentTimeMillis());
		vehicleRegDao.remove("b715faabee8f4cedbce3b75a30acca7c");
		vehicleRegDao.save(bean);
	}
	
	
}
