package com.tianrui.trwl.admin.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileCargoService;
import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.intf.IMeasureService;
import com.tianrui.api.req.admin.FileCargoReq;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.admin.MeasureReq;
import com.tianrui.api.resp.admin.FileCargoResp;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.admin.MeasureResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 我的货物处理类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 上午11:44:38
 */
@Controller
@RequestMapping("/myCargo")
public class MyCargoAction {
	public static Logger logger =LoggerFactory.getLogger(MyCargoAction.class);
	@Autowired
	private IFileOrgCargoService fileOrgCargoService;
	@Autowired
	private IFileCargoService fileCargoService;
	@Autowired
	protected IMeasureService measureService;
	/**
	 * 货物跳转页面
	 * <p>
	 * @return 货物页面
	 * @author guyuke
	 * @throws Exception 
	 * @time 2016年5月22日 上午11:51:42
	 */
	@RequestMapping("/myCargoPage")
	public ModelAndView indexPage() throws Exception{
		MeasureReq req = new MeasureReq();
		req.setMeasureBase("0");
		//查询所有量纲
		List<MeasureResp> resp = measureService.findByEntityList(req);
		ModelAndView view = new ModelAndView();
		view.addObject("measureBase",resp);
		view.setViewName("/file/myCargo/myCargoPage");
		return view;
	}
	
	/**
	 * 根据条件查询我的货物信息
	 * <p>
	 * @param id           信息ID
	 * @param orgId        组织ID
	 * @param orgName      组织名称
	 * @param materId      物料ID
	 * @param materCode    物料编码
	 * @param materName    物料名称
	 * @param materState   物料状态
	 * @param mainMeasUnit 主计量单位
	 * @param state        状态
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/getMyCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMyCargoInfoByCondition(String id,
			                                   String orgId,
			                                    String orgName,
			                                     String materId, 
											      String materCode, 
			                                       String materName, 
			                                        String materState,
			                                         String mainMeasUnit,
			                                          String state,
			          @RequestParam(defaultValue = "0")Integer pageNo,
			         @RequestParam(defaultValue = "10")Integer pageSize) throws Exception{
		Result rs = Result.getSuccessResult();
		FileOrgCargoReq orgCargoReq = new FileOrgCargoReq();
		// 主键
		orgCargoReq.setId(id);
		// 组织主键
		orgCargoReq.setOrgId(orgId);
		// 组织名称
		orgCargoReq.setOrgName(orgName);
		// 物料主键
		orgCargoReq.setMaterId(materId);
		// 物料编码
		orgCargoReq.setMaterCode(materCode);
		// 物料名称
		orgCargoReq.setMaterName(materName);
		// 物料状态
		orgCargoReq.setMaterState(materState);
		// 状态
		orgCargoReq.setState(state);
		// 主计量单位
		orgCargoReq.setMeasUnit(mainMeasUnit);
		// 页码
		orgCargoReq.setPageNo(pageNo);
		orgCargoReq.setPageSize(pageSize);
		PaginationVO<FileOrgCargoResp> pageVo = fileOrgCargoService.queryMyCargoInfoByCondition(orgCargoReq);
		rs.setData(pageVo);
		return rs;
	}
	
	/**
	 * 新增我的货物信息
	 * <p>
	 * @param orgId          组织ID
	 * @param orgname        组织名称
	 * @param materName      物料名称
	 * @param userName       用户名称
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/saveMyCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result saveMyCargoInfo(String orgId, String orgName, 
			                       String materid, String userName) throws Exception{
		
		Result rs = Result.getSuccessResult();
		if (materid != null && !"".equals(materid)) {
			FileCargoReq fileCargoReq = new FileCargoReq();
			fileCargoReq.setId(materid);
			PaginationVO<FileCargoResp> pageVo = fileCargoService.queryCargoInfoByCondition(fileCargoReq);
			
			if (pageVo == null) {
				rs.setCode("1");
				rs.setError("数据有误");
				return rs;
			}
			if(pageVo.getList().size()!=1){
				rs.setCode("1");
				rs.setError("数据有误");
				return rs;
			}
			FileCargoResp cargoResp = pageVo.getList().get(0);
			FileOrgCargoReq orgCargoReq = new FileOrgCargoReq();
			// 主键
			orgCargoReq.setId(getUUId());
			// 组织主键
			orgCargoReq.setOrgId(orgId);
			// 组织名称
			orgCargoReq.setOrgName(orgName);
			// 物料主键
			orgCargoReq.setMaterId(cargoResp.getId());
			// 物料编码
			orgCargoReq.setMaterCode(cargoResp.getMaterCode());
			// 物料名称
			orgCargoReq.setMaterName(cargoResp.getMaterName());
			// 物料状态
			orgCargoReq.setMaterState(cargoResp.getState());
			// 状态：默认启用
			orgCargoReq.setState("1");
			// 主计量单位
			orgCargoReq.setMeasUnit(cargoResp.getMeasUnit());
			// 创建人
			orgCargoReq.setCreator(userName);
			// 创建时间
			orgCargoReq.setCreateTime(Long.toString(new Date().getTime()));
			
			// 插入操作
			rs = fileOrgCargoService.insert(orgCargoReq);
		}
		return rs;
	}
	
	/**
	 * 更新我的货物信息
	 * <p>
	 * @param id           信息ID
	 * @param orgId        组织ID
	 * @param orgName      组织名称
	 * @param materId      物料ID
	 * @param materCode    物料编码
	 * @param materName    物料名称
	 * @param materState   物料状态
	 * @param mainMeasUnit 主计量单位
	 * @param state        状态
	 * @param state        用户名称
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/updateMyCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMyCargoInfo(String id,
						             String orgId,
						              String orgName,
						               String materId, 
								        String materCode, 
						                 String materName, 
						                  String materState,
						                   String mainMeasUnit,
						                    String state, 
						                     String userName) throws Exception{
		Result rs = Result.getSuccessResult();
			
		FileOrgCargoReq orgCargoReq = new FileOrgCargoReq();
		// 主键
		orgCargoReq.setId(id);
		// 组织主键
		orgCargoReq.setOrgId(orgId);
		// 组织名称
		orgCargoReq.setOrgName(orgName);
		// 物料主键
		orgCargoReq.setMaterId(materId);
		// 物料编码
		orgCargoReq.setMaterCode(materCode);
		// 物料名称
		orgCargoReq.setMaterName(materName);
		// 物料状态
		orgCargoReq.setMaterState(materState);
		// 状态
		orgCargoReq.setState(state);
		// 主计量单位
		orgCargoReq.setMeasUnit(mainMeasUnit);
		// 修改人
		orgCargoReq.setModifier(userName);
		// 修改时间
		orgCargoReq.setModifyTime(Long.toString(new Date().getTime()));
		//判断货物档案是否启用
		if("1".equals(state)){
			rs = fileOrgCargoService.findOne(id);
			if("000000".equals(rs.getCode())){
				FileOrgCargoResp fc = (FileOrgCargoResp) rs.getData();
				FileCargoResp dd = fileCargoService.findByid(fc.getMaterId());
				if("0".equals(dd.getState())){
					rs.setCode("1");
					rs.setError("组织货物已被禁用");
					return rs;
				}
			}
		}
		// 更新操作
		rs = fileOrgCargoService.updateByPrimaryKeySelective(orgCargoReq);
		
		return rs;
	}
	/**
	 * 
	 * @描述:批量停用
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年7月5日下午5:31:00
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public Result updateStatus(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		FileOrgCargoReq req = new FileOrgCargoReq();
		req.setId(id);
		req.setState("0");
		fileOrgCargoService.batchUpdateByPrimaryKeySelective(req);
		return rs;
	}
	
	/**
	 * 删除我的货物信息
	 * <p>
	 * @param id    货物信息主键
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/deleteMyCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteMyCargoInfo(String id) throws Exception{

		Result rs = fileOrgCargoService.deleteByPrimaryKey(id);
		return rs;
	}
	
	/**
	 * 获取UUID作为主键
	 * <p>
	 * @return id
	 * <p>
	 * @author guyuke
	 * @time 2016年5月23日 下午6:46:14
	 */
	private String getUUId() {
		// 获取UUID
		String uuid = UUID.randomUUID().toString();
		// 去除字符串中的"-"
		String[] uuid_array = uuid.split("-"); 
		StringBuffer idBuffer = new StringBuffer();
		for (String id : uuid_array) {
			idBuffer.append(id);
		}
		
		return idBuffer.toString();
	}
}
