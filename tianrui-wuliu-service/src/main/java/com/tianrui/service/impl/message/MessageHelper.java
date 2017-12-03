package com.tianrui.service.impl.message;

/**
 * 消息内容封装小助手
 * @author LENOVO123
 *
 */
public class MessageHelper {

	/**
	 * 封装计划推送内容
	 * @param cyr 承运人
	 * @param tyr 托运人
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param shuliang 数量
	 * @return
	 */
	public static String getPlanPushMesage(String cyr,String tyr,String qyd,String mdd,String hwmc,String shuliang){
		StringBuffer sb = new StringBuffer();
		sb.append("承运人【");
		sb.append(cyr);
		sb.append("】承接了托运人【");
		sb.append(tyr);
		sb.append("】的货运业务，");
		sb.append(qyd);
		sb.append("至");
		sb.append(mdd);
		sb.append("，");
		sb.append(hwmc);
		sb.append("-");
		sb.append(shuliang);
		sb.append("。");
		return sb.toString();
	};
	/**
	 * 封装货运需求推送内容
	 * @param tyr 托运人
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param shuliang 数量
	 * @return
	 */
	public static String getDemandPushMesage(String tyr,String qyd,String mdd,String hwmc,String shuliang){
		StringBuffer sb = new StringBuffer();
		sb.append("托运人【");
		sb.append(tyr);
		sb.append("】发布了新的货运需求，");
		sb.append(qyd);
		sb.append("至");
		sb.append(mdd);
		sb.append("，");
		sb.append(hwmc);
		sb.append("-");
		sb.append(shuliang);
		sb.append("。");
		return sb.toString();
	};
	/**
	 * 
	* 封装需求短信发送内容
	 * @param tyr 托运人
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param shuliang 数量
	 * @return
	 */
	public static String getDemandSMSMesage(String tyr,String qyd,String mdd,String hwmc,String shuliang){
		StringBuffer sb = new StringBuffer();
		sb.append("货运速递：");
		sb.append("托运人【");
		sb.append(tyr);
		sb.append("】通过大易物流平台发布了新的货运需求，");
		sb.append(qyd);
		sb.append("至");
		sb.append(mdd);
		sb.append("，");
		sb.append(hwmc);
		sb.append("-");
		sb.append(shuliang);
		sb.append("，欢迎致电垂询：400-056-1156，了解详情：http://www.da156.cn");
		return sb.toString();
		
	};
	/**
	 * 封装托运消息首页滚动消息
	 * @param tyr 托运人
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param shuliang 数量
	 * @return
	 */
	public static String getDemandRollingMesage(String tyr,String qyd,String mdd,String hwmc,String shuliang){
		StringBuffer sb = new StringBuffer();
		sb.append(hwmc);
		sb.append("(");
		sb.append(shuliang);
		sb.append(")-");
		sb.append(tyr);
		return sb.toString();
		
	};
	/**
	 * 封装计划委派首页滚动消息
	 * @param cyr 承运人
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param shuliang 数量
	 * @return
	 */
	public static String getPlanRollingMesage(String cyr,String qyd,String mdd,String hwmc,String shuliang){
		StringBuffer sb = new StringBuffer();
		sb.append(cyr+"-");
		sb.append(hwmc);
		sb.append("(");
		sb.append(shuliang);
		sb.append(")");
		return sb.toString();
		
	};
	/**
	 * 封装司机提货首页滚动消息
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param cph 车牌号
	 * @return
	 */
	public static String getPickUpRollingMesage(String qyd,String mdd,String hwmc,String cph){
		StringBuffer sb = new StringBuffer();
		sb.append(qyd);
		sb.append("至");
		sb.append(mdd);
		sb.append("，");
		sb.append(hwmc+"【");
		sb.append(cph);
		sb.append("】已经装货完成！");
		return sb.toString();
		
	};
	/**
	 * 
	 * 封装司机卸货首页滚动消息
	 * @param qyd 起运地
	 * @param mdd 目的地
	 * @param hwmc 货物名称
	 * @param cph 车牌号
	 * @return
	 */
	public static String getDischargeRollingMesage(String qyd,String mdd,String hwmc,String cph){
		StringBuffer sb = new StringBuffer();
		sb.append(qyd);
		sb.append("至");
		sb.append(mdd);
		sb.append("，");
		sb.append(hwmc+"【");
		sb.append(cph);
		sb.append("】已经完成卸货！");
		return sb.toString();
		
	};
	public static void main(String[] args) {
		String cyr="中原大易科技有限公司";
		String tyr="天韵丝霞网络科技有限公司";
		String qyd="河南省汝州市";
		String mdd="河南省信阳市";
		String hwmc="混凝土轨枕";
		String shuliang="50000吨";
		String cph="豫DEF999";
		System.out.println(getPlanPushMesage(cyr, tyr, qyd, mdd, hwmc, shuliang));
		System.out.println(getDemandPushMesage( tyr, qyd, mdd, hwmc, shuliang));
		System.out.println(getDemandSMSMesage( tyr, qyd, mdd, hwmc, shuliang));
		System.out.println(getDemandRollingMesage(tyr, qyd, mdd, hwmc,shuliang));
		System.out.println(getPlanRollingMesage(cyr, qyd, mdd, hwmc, shuliang));
		System.out.println(getPickUpRollingMesage( qyd, mdd, hwmc, cph));
		System.out.println(getDischargeRollingMesage( qyd, mdd, hwmc, cph));
	}
}
