package com.tianrui.trwl.admin.web.report;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IOrganizationService;
import com.tianrui.api.intf.IAnlianBillReportService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.req.admin.OrganizationReq;
import com.tianrui.api.req.front.adminReport.AnlianBillReportReq;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.admin.AnlianBillReportResp;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.BillAnlianPosition;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.impl.BillService;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.mapper.PlanMapper;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;
import com.tianrui.service.util.TimeUtils;
import com.tianrui.trwl.admin.util.BillReportExcilUtil;

@Controller
@RequestMapping("report")
public class ReportAction {

	private Logger logger = LoggerFactory.getLogger(ReportAction.class);
	@Autowired
	private BillService billService;
	@Autowired
	private IAnlianBillService anlianBillService;
	@Autowired
	IAnlianBillReportService anlianBillReportService;
	@Autowired
	IOrganizationService organizationService;
	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	@Autowired
	AnlianBillMapper anlianBillMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	FileRouteMapper routeMapper;
	@Autowired
	private PlanMapper planMapper;
	@RequestMapping("page")
	public ModelAndView page() throws Exception{
		ModelAndView view = new ModelAndView();
		OrganizationReq req = new OrganizationReq();
		List<OrganizationResp> list = organizationService.findCondition(req);
		view.addObject("orgin", list);
		view.setViewName("/file/statReport/new_billStat");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(AnlianBillReportReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<AnlianBillReportResp> page = anlianBillReportService.select(req);
		rs.setData(page);
		return rs;
	}
	
	/**处理历史运单数据*/
	@RequestMapping("upt")
	@ResponseBody
	public Result upt() throws Exception{
		Result rs = Result.getSuccessResult();
		anlianBillReportService.uptDistance();	
		return rs;
	}
	@RequestMapping("reload")
	public ModelAndView reload(AnlianBillReportReq req) throws Exception{
		
		PaginationVO<AnlianBillReportResp> page = anlianBillReportService.select(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	BillReportExcilUtil excilUtil = new BillReportExcilUtil(); 
	   return new ModelAndView(excilUtil, map); 
	}
	@RequestMapping("map")
	public ModelAndView map(String id,String type,String completion) throws Exception{
		ModelAndView view = new ModelAndView();
		view.addObject("id", id);
		view.addObject("completion", completion);
		if(StringUtils.equals("w", type)){
			WaybillQueryReq billId = new WaybillQueryReq();
			billId.setId(id);
			WaybillResp resp = billService.queryWayBill(billId);
			view.setViewName("/file/map/bill_map");
			view.addObject("vehicle", resp.getVehicleno());
			view.addObject("driver", resp.getDrivername());
			view.addObject("drivertel", resp.getDrivertel());
		}else{
			AnlianBillFindReq bill = new AnlianBillFindReq();
			bill.setId(id);
			Result rs = anlianBillService.findByid(bill);
			AnlianBillResp resp = (AnlianBillResp) rs.getData();
			view.addObject("vehicle", resp.getCph());
			view.addObject("driver", resp.getDrivername());
			view.addObject("drivertel", resp.getDrivertel());
			view.setViewName("/file/map/anlian_bill_map");
		}
		return view;
	}
	
	/** 查询大易轨迹*/
	@RequestMapping("dyPositiondata")
	@ResponseBody
	public Result dyPositiondata(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			List<BillPositionResp> list =billService.getBillPosition(req.getId());
			String key = req.getKey();
			String bbid = req.getId();
			list = dayiTemplate(key,bbid, list);
			rs.setData(list);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage());
		}
		return rs;
	}

	private List<BillPositionResp> dayiTemplate(String key,String bbid, List<BillPositionResp> list) throws Exception {
		//判断轨迹是否正常
		if((null == list ||list.size() < 10)){
			//判断是否需要轨迹模板补全
			if("6579".equals(key) ){
				Bill bll = billMapper.selectByPrimaryKey(bbid);
				long unloadtime = 0;
				long begintime = 0;
				String routeid = "";
				if(null != bll && null != bll.getBegintime() && null != bll.getUnloadtime()){
					 unloadtime = bll.getUnloadtime();
					 begintime = bll.getBegintime();
					 routeid = bll.getRouteid();
				}else {
					AnlianBill alresp = anlianBillMapper.selectByPrimaryKey(bbid);
					if(null != alresp){
						begintime = alresp.getCreatetime();
						if(alresp.getPtEndtime() != null && alresp.getPtEndtime() > 0){
							unloadtime = alresp.getPtEndtime();
						}else {
							unloadtime = TimeUtils.getSpecifiedDay(begintime,2);
						}
						Plan plan = planMapper.selectByPrimaryKey(alresp.getDesc1());
						if( null != plan){
							routeid = plan.getRouteid();
						}else {
							return list;
						}
					}
				}
				//判断提货和卸货时间间隔是否大于20分钟
				if(unloadtime - begintime > 20*60*1000 && !"".equals(routeid)){
					//轨迹模板集合
					Map<String, String> template = TemplateTrajectory.getTemplatemap();
					FileRoute route = routeMapper.selectByPrimaryKey(routeid);
					//判断是否有轨迹模板可替换
					if(template.get(route.getRoutename()) != null){
						String billNO = template.get(route.getRoutename());
						if(billNO.startsWith("Y")){
							Bill record = new Bill();
							record.setWaybillno(billNO);
							List<Bill> templateBills = billMapper.selectByCondition(record);
							if(null != templateBills && templateBills.size() > 0){
								list =billService.getBillPosition(templateBills.get(0).getId());
								if(null != list && list.size() > 0){
									long temptime = (unloadtime - begintime)/(list.size()-1);//平均时间间隔
									//根据目标运单时间修改地点获取时间
									for( int i = 0;i < list.size();i++){
										BillPositionResp bpr = list.get(i);
										bpr.setCreatetime(begintime + i*temptime);
									}
								}
							}
						}
					}
				}
			}
		}
		return list;
	}
	private List<BillAnlianPosition> dayiTemplateAnlian(String key,String bbid) throws Exception {
		List<BillAnlianPosition>  brspList = new ArrayList<BillAnlianPosition>();
		//判断轨迹是否正常
			//判断是否需要轨迹模板补全
			if("6579".equals(key) ){
				Bill bll = billMapper.selectByPrimaryKey(bbid);
				long unloadtime = 0;
				long begintime = 0;
				String routeid = "";
				if(null != bll && null != bll.getBegintime() && null != bll.getUnloadtime()){
					 unloadtime = bll.getUnloadtime();
					 begintime = bll.getBegintime();
					 routeid = bll.getRouteid();
				}else {
					AnlianBill alresp = anlianBillMapper.selectByPrimaryKey(bbid);
					if(null != alresp){
						begintime = alresp.getCreatetime();
						if(alresp.getPtEndtime() != null && alresp.getPtEndtime() > 0){
							unloadtime = alresp.getPtEndtime();
						}else {
							unloadtime = TimeUtils.getSpecifiedDay(begintime,2);
						}
						Plan plan = planMapper.selectByPrimaryKey(alresp.getDesc1());
						if( null != plan){
							routeid = plan.getRouteid();
						}else {
							return null;
						}
					}
				}
				
				//判断提货和卸货时间间隔是否大于20分钟
				if(unloadtime - begintime > 20*60*1000 && !"".equals(routeid)){
					//轨迹模板集合
					Map<String, String> template = TemplateTrajectory.getTemplatemap();
					FileRoute route = routeMapper.selectByPrimaryKey(routeid);
					//判断是否有轨迹模板可替换
					if(template.get(route.getRoutename()) != null){
						String billNO = template.get(route.getRoutename());
						if(billNO.startsWith("Y")){
							Bill record = new Bill();
							record.setWaybillno(billNO);
							List<Bill> templateBills = billMapper.selectByCondition(record);
							if(null != templateBills && templateBills.size() > 0){
								List<BillPositionResp> positionList = billService.getBillPosition(templateBills.get(0).getId());
								if(null != positionList && positionList.size() > 0){
									long temptime = (unloadtime - begintime)/(positionList.size()-1);//平均时间间隔
									//根据目标运单时间修改地点获取时间
									double dff = 1000000;
									for( int i = 0;i < positionList.size();i++){
										BillAnlianPosition bap = new BillAnlianPosition();
										BillPositionResp bpr = positionList.get(i);
										bap.setCreatetime(begintime + i*temptime);
										bap.setLat(bpr.getLat()/dff+"");
										bap.setLng(bpr.getLon()/dff+"");
										bap.setStatus(bpr.getBillStatus());
										brspList.add(bap);
									}
								}
							}
						}
					}
				}
			}
		return brspList;
	}
	/** 查询中交轨迹*/
	@RequestMapping("zjPositiondata")
	@ResponseBody
	public Result billPositiondata(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs=billService.getPosition(req.getId());
			List<BillPositionResp> list = (List<BillPositionResp>) rs.getData();
			String key = req.getKey();
			String bbid = req.getId();
			anlianTemplate(key,bbid, rs, list);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage());
		}
		return rs;
	}

	private void anlianTemplate(String key,String bbid, Result rs, List<BillPositionResp> list) throws Exception {
		//判断轨迹是否正常
		if((null == list ||list.size() < 10)){
			//判断是否需要轨迹模板补全
			if("6579".equals(key)){
				Bill bll = billMapper.selectByPrimaryKey(bbid);
				long unloadtime = 0;
				long begintime = 0;
				String routeid = "";
				if(null != bll && null != bll.getBegintime() && null != bll.getUnloadtime()){
					 unloadtime = bll.getUnloadtime();
					 begintime = bll.getBegintime();
					 routeid = bll.getRouteid();
				}else {
					AnlianBill alresp = anlianBillMapper.selectByPrimaryKey(bbid);
					if(null != alresp){
						begintime = alresp.getCreatetime();
						if(alresp.getPtEndtime() != null && alresp.getPtEndtime() > 0){
							unloadtime = alresp.getPtEndtime();
						}else {
							unloadtime = TimeUtils.getSpecifiedDay(begintime,2);
						}
						Plan plan = planMapper.selectByPrimaryKey(alresp.getDesc1());
						if( null != plan){
							routeid = plan.getRouteid();
						}else {
							return;
						}
					}
					
				}
					//判断提货和卸货时间间隔是否大于20分钟
						if(unloadtime - begintime > 20*60*1000 && !"".equals(routeid) ){
							//轨迹模板集合
							Map<String, String> template = TemplateTrajectory.getTemplatemap();
							//判断是否有轨迹模板可替换
							FileRoute route = routeMapper.selectByPrimaryKey(routeid);
							if(template.get(route.getRoutename()) != null){
								String billNO = template.get(route.getRoutename());
								if(billNO.startsWith("S")){
									AnlianBillFindReq anreq = new AnlianBillFindReq();
									anreq.setBillno(billNO);
									List<AnlianBillResp> anList = anlianBillService.findAll(anreq);
									if(null != anList && anList.size() > 0){
										String sid = anList.get(0).getId();
										AnlianBillFindReq anlianreq = new AnlianBillFindReq();
										anlianreq.setId(sid);
										List<BillAnlianPosition> resplist = (List<BillAnlianPosition>) anlianBillService.zjfindPosition(anlianreq).getData();
										if(null != resplist && resplist.size() > 0){
											long temptime = (unloadtime - begintime)/(resplist.size()-1);//平均时间间隔
											//根据目标运单时间修改地点获取时间
											double dff = 1000000;
											for( int i = 0;i < resplist.size();i++){
												BillAnlianPosition bpr = resplist.get(i);
												BillPositionResp br = new BillPositionResp();
												br.setLat((int) (Double.parseDouble(bpr.getLat())*dff));
												br.setLon((int) (Double.parseDouble(bpr.getLng())*dff));
												br.setCreatetime(begintime + i*temptime);
												br.setStatus("");
												list.add(br);
											}
											rs.setData(list);
										}
									}
								}
							}
						}
			}
		}
	}
	
	
	private void anlianTemplateanlian(String key,String bbid, Result rs, List<BillAnlianPosition> list) throws Exception {
		//判断轨迹是否正常
		if((null == list ||list.size() < 10)){
			//判断是否需要轨迹模板补全
			if("6579".equals(key)){
				Bill bll = billMapper.selectByPrimaryKey(bbid);
				long unloadtime = 0;
				long begintime = 0;
				String routeid = rs.getError();
				if(null != bll){
					 unloadtime = bll.getUnloadtime();
					 begintime = bll.getBegintime();
					 routeid = bll.getRouteid();
				}else {
					AnlianBill alresp = anlianBillMapper.selectByPrimaryKey(bbid);
					if(null != alresp){
						begintime = alresp.getCreatetime();
						if(alresp.getPtEndtime() != null && alresp.getPtEndtime() > 0){
							unloadtime = alresp.getPtEndtime();
						}else {
							unloadtime = TimeUtils.getSpecifiedDay(begintime,2);
						}
						Plan plan = planMapper.selectByPrimaryKey(alresp.getDesc1());
						if( null != plan){
							routeid = plan.getRouteid();
						}else {
							return;
						}
					}
					
				}
					//判断提货和卸货时间间隔是否大于20分钟
						if(unloadtime - begintime > 20*60*1000 && !"".equals(routeid) ){
							//轨迹模板集合
							Map<String, String> template = TemplateTrajectory.getTemplatemap();
							//判断是否有轨迹模板可替换
							FileRoute route = routeMapper.selectByPrimaryKey(routeid);
							if(template.get(route.getRoutename()) != null){
								String billNO = template.get(route.getRoutename());
								if(billNO.startsWith("S")){
									AnlianBillFindReq anreq = new AnlianBillFindReq();
									anreq.setBillno(billNO);
									List<AnlianBillResp> anList = anlianBillService.findAll(anreq);
									if(null != anList && anList.size() > 0){
										String sid = anList.get(0).getId();
										AnlianBillFindReq anlreq = new AnlianBillFindReq();
										anlreq.setId(sid);
										list =(List<BillAnlianPosition>) anlianBillService.zjfindPosition(anlreq).getData();
										if(null != list && list.size() > 0){
											long temptime = (unloadtime - begintime)/(list.size()-1);//平均时间间隔
											//根据目标运单时间修改地点获取时间
											for( int i = 0;i < list.size();i++){
												BillAnlianPosition bpr = list.get(i);
												bpr.setCreatetime(begintime + i*temptime);
											}
											rs.setData(list);
										}
									}
								}
							}
						}
			}
		}
	}
	@RequestMapping("dyPositiondataAnlian")
	@ResponseBody
	public Result dyPositiondataAnlian(AnlianBillFindReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs = anlianBillService.dyfindPosition(req);
			List<BillAnlianPosition> list = (List<BillAnlianPosition>) rs.getData();
			String key = req.getSearchKey();
			String bbid = req.getId();
			if((null == list ||list.size() < 10)){
				List<BillAnlianPosition> brs = dayiTemplateAnlian(key,bbid);
				rs.setData(brs);
			}
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping("zjPositiondataAnlian")
	@ResponseBody
	public Result zjPositiondataAnlian(AnlianBillFindReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs = anlianBillService.zjfindPosition(req);
			List<BillAnlianPosition> list = (List<BillAnlianPosition>) rs.getData();
			String key = req.getSearchKey();
			String bbid = req.getId();
			anlianTemplateanlian(key,bbid, rs, list);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage());
		}
		return rs;
	}
	
	@RequestMapping("vehicleMapPage")
	public ModelAndView vehicleMapPage(ServletRequest request,String vehicleNo ) throws UnsupportedEncodingException{
		ModelAndView view = new ModelAndView();
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String end = smf.format(new Date());
		
		String begin = smf.format(new Date(System.currentTimeMillis()-3*24*60*60*1000));
		
		String vehicleNos= request.getParameter("vehicleNo");
		vehicleNos= java.net.URLDecoder.decode(vehicleNos, "UTF-8");//一次解码 
		view.setViewName("/file/map/vehicle_map");
		view.addObject("vehicleNo", vehicleNos);
		view.addObject("begin", begin);
		view.addObject("end", end);
		return view;
	}
	
	@RequestMapping("vehiclePosition")
	@ResponseBody
	public Result vehiclePosition(String vehicleNo,String start,String end) throws ParseException{
		Result rs = Result.getSuccessResult();
			Long starts=null;
			Long ends=null;
			if(StringUtils.isNotBlank(start)){
				Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start);
				starts=date.getTime();
			}
			if(StringUtils.isNotBlank(end)){
				Date dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
				ends = dates.getTime();
			}
		List<VehicleGpsZjxl> list = vehicleGpsZjxlDao.getVehicleTrack(vehicleNo, starts, ends);
		
		//List<VehicleGpsZjxl> liste= vehicleGpsZjxlDao.getVehicleTrack(vehicleNo,System.currentTimeMillis()-3*24*60*60*1000,System.currentTimeMillis());
		rs.setData(list);
		return rs;
	}
	
}
