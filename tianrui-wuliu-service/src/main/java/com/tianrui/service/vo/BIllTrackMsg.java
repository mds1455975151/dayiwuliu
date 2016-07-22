package com.tianrui.service.vo;

public interface BIllTrackMsg {

	
	 String INIT="运单创建成功,等待司机接单.";
	 String STEP1="运单信息被修改.";
	 String STEP2="车主删除运单.";
	 String STEP3="车主取消运单.";
	 String STEP4="货主签收完成.";
	 String STEP5="货主删除运单.";
	 String STEP6="司机确认接受运单.";
	 String STEP7="司机拒绝接受运单.";
	 String STEP8="司机已到达始发地,等待装货.";
	 String STEP9="司机提货发车确认,运输中.";
	 String STEP10="司机到达确认,等待卸货.";
	 String STEP11="司机卸货完成确认,等待签收.";
	 String STEP12="司机删除运单.";
}
