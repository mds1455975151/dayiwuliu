package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.req.front.bill.WaybillConfirmReq;
import com.tianrui.api.req.front.bill.WaybillEditReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.bill.WaybillSaveReq;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.api.resp.front.bill.BillGpsResp;
import com.tianrui.api.resp.front.bill.BillPlanResp;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.api.resp.front.bill.BillVehicleResp;
import com.tianrui.api.resp.front.bill.JTBBillResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
  * @ClassName: IBillService 
  * @Description: 运单服务
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月1日 下午3:09:56 
  *
  */
public interface IBillService {
	/**
	 * 车主操作
	 */
	/**发布运单*/
	public Result saveWayBill(WaybillSaveReq req)throws Exception;
	/**修改运单*/
	public Result editWayBill(WaybillEditReq req)throws Exception;
	/**车主删除运单*/
	public Result verderdelete(WaybillConfirmReq req)throws Exception;
	/**车主取消运单*/
	public Result cancleConfirm(WaybillConfirmReq req)throws Exception;
	
	/**
	 * 货主操作
	 */
	/**签收确认*/
	public Result signConfirm(WaybillConfirmReq req)throws Exception;
	/**货主删除确认*/
	public Result ownerdelete(WaybillConfirmReq req)throws Exception;
	
	/**
	 * 司机操作
	 */
	/**司机接受确认*/
	public Result acceptConfirm(WaybillConfirmReq req)throws Exception;
	/**司机拒绝确认*/
	public Result refuseConfirm(WaybillConfirmReq req)throws Exception;
	/**司机待装货*/
	public Result pickupConfirm(WaybillConfirmReq req) throws Exception;
	/**司机发车确认*/
	public Result departureConfirm(WaybillConfirmReq req)throws Exception;
	/**司机到达确认*/
	public Result arrivedConfirm(WaybillConfirmReq req)throws Exception;
	/**司机卸货完成确认*/
	public Result dischargeConfirm(WaybillConfirmReq req)throws Exception;
	/**司机删除*/
	public Result driverdelete(WaybillConfirmReq req)throws Exception;
	
	
	
	/**查询详情*/
	public WaybillResp queryWayBill(WaybillQueryReq req)throws Exception;
	/**查询详情包含状态*/
	public WaybillResp queryWayBillWithTrack(WaybillQueryReq req)throws Exception;
	/**分页查询*/
	public PaginationVO<WaybillResp> page(WaybillQueryReq req)throws Exception;
	/**分页查询 后台*/
	public PaginationVO<WaybillResp> pageForBack(WaybillQueryReq req)throws Exception;
	/**获取经纬度*/
	public BillGpsResp gps(WaybillQueryReq req)throws Exception;
	/**查询大易可推送交通部运单*/
	public PaginationVO<JTBBillResp> findJtbBill(WaybillQueryReq req)throws Exception;
	
	/**查询安联可推送交通部运单*/
	public PaginationVO<JTBBillResp> findALJtbBill(WaybillQueryReq req)throws Exception;
	
	/**查询*/
	public BillPlanResp queryWithPlanId(String pid)throws Exception;
	
	/**查询*/
	public List<BillVehicleResp> queryVehicle(String pid)throws Exception;
	
	/**不包含始发点  目的点*/
	public List<PositionResp> getBIllTrack(WaybillQueryReq req) throws Exception;
	/**包含运单始发点目的点  且运单途经点只获取三个*/
	public List<PositionResp> getBIllTrackAll(WaybillQueryReq req) throws Exception;
	/**
	 * 查询运单轨迹
	 * @param bid 运单id
	 * @return
	 * @throws Exception
	 */
	public List<BillPositionResp> getBillPosition(String bid) throws Exception;
	/**查询委派运单*/
	public PaginationVO<WaybillResp> queryAppointBillPage(WaybillQueryReq req) throws Exception;

	/**修改运单磅单*/
	public Result updateBillImage(WaybillConfirmReq req) throws Exception;
	
	/**查询后台运单报表*/
	PaginationVO<StatReportOfBillResp> queryAdminStatReport(StatReportReq req);
	/**后台管理导出运单数据*/
	public List<StatReportOfBillResp> queryAdminAllStatReport(StatReportReq req);
	/**后台管理导出运单数据条数*/
	public int queryAdminStatReportCount(StatReportReq req);
	/** 大易运单推送交通部*/
	public Result putJtbBill(String id)throws Exception;
	
	/** 安联运单推送交通部*/
	public Result putAnlianJtbBill(String id)throws Exception;
	
	public Result findJtbBillDetail(WaybillQueryReq req) throws Exception;
	
}
