package com.tianrui.trwl.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IOrgMemberService;
import com.tianrui.api.admin.intf.IOrganizationService;
import com.tianrui.api.admin.intf.IUserService;
import com.tianrui.api.req.admin.OrgMemberReq;
import com.tianrui.api.req.admin.OrganizationReq;
import com.tianrui.api.req.admin.OrganizationSaveReq;
import com.tianrui.api.req.admin.OrganizationUpdateReq;
import com.tianrui.api.req.admin.UserQueryReq;
import com.tianrui.api.resp.admin.OrgMemberResp;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.admin.UserResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;
/**
 * 
* @ClassName: OrganizationAction
* @Description: 组织档案后台action
* @author wuchl
* @date 2016年5月20日
*
 */
@Controller
@RequestMapping("/Organization")
public class OrganizationAction {
	
	@Autowired
	public IOrganizationService organizationService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgMemberService orgMemberService;
	/**
	 * 
	* @Description: 组织档案页面跳转
	* @param @return
	* @param @throws IOException    
	* @return ModelAndView    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	@RequestMapping("/Organization")
	public ModelAndView Organization() throws IOException{
		return new ModelAndView("file/organization/organization");
	}
	/**
	 * 
	* @Description: 查询所有组织档案
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Result query() throws Exception{
		
		Result rs = Result.getSuccessResult();
		OrganizationReq orgReq = new OrganizationReq();
		orgReq.setStatus("1");
		List<OrganizationResp> list = organizationService.findCondition(orgReq);
		rs.setData(list);
		return rs;
	}
	/**
	 * 
	* @Description: 組織檔案分頁
	* @param @param organizationname
	* @param @param organizationtype
	* @param @param organizationno
	* @param @param pageNo
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	@RequestMapping("/queryOrgByPage")
	@ResponseBody
	public Result queryOrgByPage(
			@RequestParam(defaultValue = "") String organizationname,
			@RequestParam(defaultValue = "") String organizationtype,
			@RequestParam(defaultValue = "") String organizationno,
			@RequestParam(defaultValue = "1") String pageNo,
			@RequestParam(defaultValue = "10") String pageSize
			) throws Exception{
		Result rs = Result.getSuccessResult();
		OrganizationReq req = new OrganizationReq();
		req.setOrganizationname(organizationname);
		req.setOrganizationtype(organizationtype);
		req.setOrganizationno(organizationno);
		req.setPageNo(Integer.valueOf(pageNo));
		req.setPageSize(Integer.valueOf(pageSize));
		PaginationVO<OrganizationResp> paginationVO = organizationService.findOrgByPage(req);
		rs.setData(paginationVO);
		return rs;
		
	}
	/**
	 * 
	* @Description: 組織檔案新增
	* @param @param organizationname
	* @param @param organizationtype
	* @param @param organizationno
	* @param @param status
	* @param @param request
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(
			@RequestParam(defaultValue = "") String organizationname,
			@RequestParam(defaultValue = "") String organizationtype,
			@RequestParam(defaultValue = "") String organizationno,
			@RequestParam(defaultValue = "") String status,
			HttpServletRequest request) throws Exception {
		Users currUser =SessionManager.getSessionMember(request);
		OrganizationSaveReq saveReq = new OrganizationSaveReq();
		Result rs =Result.getSuccessResult();
		if(organizationname == null||"".equals(organizationname)
				||organizationtype == null ||"".equals(organizationtype)
				||organizationno == null|| "".equals(organizationno)
				||status == null||"".equals(status)){
			rs.setCode("1");
			rs.setError("数据不能为空");
		}
		saveReq.setOrganizationname(organizationname);
		saveReq.setOrganizationtype(organizationtype);
		saveReq.setOrganizationno(organizationno);
		saveReq.setStatus(status);
		saveReq.setCreator(currUser.getAccount());
		rs = organizationService.saveOrganization(saveReq);
		return rs;
	}
	/**
	 * 
	* @Description: 修改組織資料
	* @param @param organizationname
	* @param @param organizationtype
	* @param @param organizationno
	* @param @param status
	* @param @param request
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(
			@RequestParam(defaultValue = "") String id,
			@RequestParam(defaultValue = "") String organizationname,
			@RequestParam(defaultValue = "") String organizationtype,
			@RequestParam(defaultValue = "") String organizationno,
			@RequestParam(defaultValue = "") String status,
			HttpServletRequest request) throws Exception {
		Users currUser =SessionManager.getSessionMember(request);
		OrganizationUpdateReq req = new OrganizationUpdateReq();
		Result rs = Result.getSuccessResult();
		if(organizationname == null||"".equals(organizationname)
				||organizationtype == null ||"".equals(organizationtype)
				||organizationno == null|| "".equals(organizationno)
				||status == null||"".equals(status)){
			rs.setCode("1");
			rs.setError("数据不能为空");
		}
		req.setId(id);
		req.setOrganizationname(organizationname);
		req.setOrganizationtype(organizationtype);
		req.setOrganizationno(organizationno);
		req.setStatus(status);
		req.setModifier(currUser.getAccount());
		rs = organizationService.updateOrganization(req);
		return rs;
	}
	/**
	 * 
	* @Description: 更改組織狀態
	* @param @param id
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月21日
	 */
	@RequestMapping("/orgprohibition")
	@ResponseBody
	public Result orgprohibition(@RequestParam(defaultValue = "") String id) throws Exception{
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(id)){
			rs.setCode("1");
			rs.setError("数据不能为空");
			return rs;
		}
		OrganizationResp resp  = organizationService.findOne(id);
		if(resp==null){
			rs.setCode("1");
			rs.setError("无效的id");
			return rs;
		}
		String ss ="";
		if("1".equals(resp.getStatus())){
			ss="2";
		}
		if("2".equals(resp.getStatus())){
			ss="1";
		}
		resp.setStatus(ss);
		organizationService.update(resp);
		return rs;
	}
	/**
	 * 
	* @Description: 根据ID查询
	* @param @param id
	* @param @return
	* @param @throws Exception    
	* @return Result    
	* @throws
	* @author wuchl
	* @date 2016年5月22日
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public Result findById(@RequestParam(defaultValue = "") String id) throws Exception{
		Result rs = Result.getSuccessResult();
		OrganizationResp resp  = organizationService.findOne(id);
		rs.setData(resp);
		return rs;
	}
	/**
	 * 
	 * @描述:删除组织
	 * @param id
	 * @return
	 * @throws Exception
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年7月5日上午10:25:13
	 */
	@RequestMapping("/deleteOrgById")
	@ResponseBody
	public Result deleteOrg(@RequestParam(defaultValue = "") String id) throws Exception{
		Result rs = Result.getSuccessResult();
		UserQueryReq req = new UserQueryReq();
		req.setOrgid(id);
		PaginationVO<UserResp> resp = userService.findUserByPage(req);
		if(resp != null && resp.getList() != null && resp.getList().size()!=0){
			rs.setCode("1");
			rs.setError("该组织下已有用户");
			return rs;
		}
		//删除该组织下会员
		OrgMemberReq or = new OrgMemberReq();
		or.setOrganizationid(id);
		List<OrgMemberResp> list = orgMemberService.findlist(or);
		for(OrgMemberResp res : list){
			orgMemberService.deleteEntity(res.getId());
		}
		if(!organizationService.deleteOrg(id)){
			rs.setCode("1");
			rs.setError("删除失败");
		}
		return rs;
	}
}
