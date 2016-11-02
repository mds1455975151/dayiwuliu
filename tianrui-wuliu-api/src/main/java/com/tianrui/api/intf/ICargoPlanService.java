package com.tianrui.api.intf;

import java.util.List;
import java.util.Map;

import com.tianrui.api.req.admin.AdminPlanReq;
import com.tianrui.api.req.front.cargoplan.PlanAppointReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanEditReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.front.cargoplan.PlanStatResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：货运计划接口
 * @创建人：lsj
 * @创建时间：2016年5月24日下午2:56:57
 *
 * @修改人：lsj
 * @修改时间：2016年5月24日下午2:56:57
 * @修改备注：
 *
 */
public interface ICargoPlanService {
	/**
	 * 
	* @Description: 分页查询
	* @param @param req
	* @param @return
	* @param @throws Exception    
	* @return PaginationVO<PlanResp>    
	* @throws
	* @author wuchl
	* @date 2016年6月14日
	 */
	public PaginationVO<PlanResp> pageForAdmin(AdminPlanReq req)throws Exception;
	
	//
	public PaginationVO<PlanResp> pageForApp(PlanQueryReq req)throws Exception;
	
	public PlanResp detail(PlanQueryReq req)throws Exception;
	
	/**
	 * 车主操作
	 */
	//接受确认
	public Result acceptConfirm(PlanConfirmReq req)throws Exception;
	//拒绝确认
	public Result refuseConfirm(PlanConfirmReq req)throws Exception;
	//删除确认
	public Result venderDelConfirm(PlanConfirmReq req)throws Exception;
	//委派计划
	public Result addAppointPlan(PlanAppointReq req)throws Exception;
	//修改委派计划
	public Result editAppointPlan(PlanAppointReq req)throws Exception;
	
	/**
	 * 货主操作
	 */
	//收回
	public Result cancleConfirm(PlanConfirmReq req)throws Exception;
	//删除
	public Result ownerDeleteConfirm(PlanConfirmReq req)throws Exception;
	//修改重新发布
	public Result editPlan(PlanEditReq req)throws Exception;
	//新建
	public Result savePlan(PlanSaveReq req)throws Exception;
	//完成
	public Result completePlan(PlanConfirmReq req)throws Exception;
	
	//计划完成情况
	public PlanStatResp planstat(PlanQueryReq req)throws Exception;

	public List<PlanResp> findPlanByEndTime(Long st) throws Exception;

	/**
	 * 查询实际运输量（货主签收量）
	 * @param planId
	 * @param memberid
	 * @return
	 */
	Map<String, String> planComplete(String planId, String memberid, int type);

}
