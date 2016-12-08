package com.tianrui.trwl.admin.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
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
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.IMeasureService;
import com.tianrui.api.req.admin.FileCargoReq;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.admin.MeasureReq;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.api.resp.admin.FileCargoResp;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.admin.MeasureResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 货物档案处理类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 上午11:44:38
 */
@Controller
@RequestMapping("/fileCargo")
public class FileCargoAction {
	public static Logger logger =LoggerFactory.getLogger(FileCargoAction.class);
	@Autowired
	private IFileCargoService fileCargoService;
	@Autowired
	private IFileService iFileService;
	@Autowired
	protected IMeasureService measureService;
	@Autowired
	private IFileOrgCargoService fileOrgCargoService;
	/**
	 * 货物跳转页面
	 * <p>
	 * @return 货物页面
	 * @author guyuke
	 * @throws Exception 
	 * @time 2016年5月22日 上午11:51:42
	 */
	@RequestMapping("/cargoArchPage")
	public ModelAndView indexPage() throws Exception{
		MeasureReq req = new MeasureReq();
		req.setMeasureBase("0");
		//查询所有量纲
		List<MeasureResp> resp = measureService.findByEntityList(req);
		ModelAndView view = new ModelAndView();
		view.addObject("measureBase",resp);
		view.setViewName("/file/fileCargo/cargoArchPage");
		return view;
	}
	
	/**
	 * 根据条件查询货物档案信息
	 * <p>
	 * @param materCode    物料编码
	 * @param materName    物料名称
	 * @param materClass   物料类别
	 * @param spec         货物规格
	 * @param model        货物型号
	 * @param materMNCode  物料助记码
	 * @param mainMeasUnit 主计量单位
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/getCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result queryCargoInfoByCondition(String materCode, 
			                                 String materName, 
			                                  String materClass, 
			                                   String spec, 
			                                    String model, 
			                                     String materMNCode, 
			                                      String mainMeasUnit,
			                                       String id,
			                                        String state,
			   @RequestParam(defaultValue = "0")String pageNo,
			   @RequestParam(defaultValue = "10")String pageSize) throws Exception{
		Result rs = Result.getSuccessResult();
		FileCargoReq cargoReq = new FileCargoReq();
		// 物料编码
		cargoReq.setMaterCode(materCode);
		// 物料名称
		cargoReq.setMaterName(materName);
		// 物料类别
		cargoReq.setMaterClass(materClass);
		// 货物类别
		cargoReq.setSpec(spec);
		// 货物型号
		cargoReq.setModel(model);
		// 物料助记码
		cargoReq.setMaterMNCode(materMNCode);
		// 主计量单位
		cargoReq.setMeasUnit(mainMeasUnit);
		// 主键
		cargoReq.setId(id);
		// 状态
		cargoReq.setState(state);
		// 页码
		cargoReq.setPageNo(Integer.parseInt(pageNo));
		cargoReq.setPageSize(Integer.parseInt(pageSize));
		PaginationVO<FileCargoResp> pageVo = fileCargoService.queryCargoInfoByCondition(cargoReq);
		rs.setData(pageVo);
		return rs;
	}
	
	/**
	 * 新增货物档案信息
	 * <p>
	 * @param materCode    物料编码
	 * @param materName    物料名称
	 * @param materClass   物料类别
	 * @param spec         货物规格
	 * @param model        货物型号
	 * @param materMNCode  物料助记码
	 * @param mainMeasUnit 主计量单位
	 * @param imgPath      图片地址
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/saveCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result saveCargoInfo(String materCode, 
                                 String materName, 
                                  String materClass, 
                                   String spec, 
                                    String model, 
                                     String materMNCode, 
                                      String mainMeasUnit, 
                                       String payType, 
                                        String imgPath, 
                                         String userName,
                                          String desc1,
                                           String desc2) throws Exception{
		
		Result rs = Result.getSuccessResult();
		if (imgPath != null) {
			// 转换并上传图片地址
			FileUploadReq fileUploadReq = new FileUploadReq();
			fileUploadReq.setImgStr(imgPath);
			rs = iFileService.uploadImg(fileUploadReq);
		}
		
		if ("000000".equals(rs.getCode())) {
			FileCargoReq cargoReq = new FileCargoReq();
			// 主键
			cargoReq.setId(getUUId());
			// 物料编码
			cargoReq.setMaterCode(materCode);
			// 物料名称
			cargoReq.setMaterName(materName);
			// 物料类别
			cargoReq.setMaterClass(materClass);
			// 状态：默认启用
			cargoReq.setState("1");
			// 货物类别
			cargoReq.setSpec(spec);
			// 货物型号
			cargoReq.setModel(model);
			// 物料助记码
			cargoReq.setMaterMNCode(materMNCode);
			// 主计量单位
			cargoReq.setMeasUnit(mainMeasUnit);
			//支付类型
			cargoReq.setPayType(payType);
			cargoReq.setDesc1(desc1);
			cargoReq.setDesc2(desc2);
			// 货物图片地址
			if (rs.getData() == null) {
				cargoReq.setImgPath(null);
			} else {
				cargoReq.setImgPath(rs.getData().toString());
			}
			// 创建人
			cargoReq.setCreator(userName);
			// 创建时间
			cargoReq.setCreateTime(Long.toString(new Date().getTime()));
			
			// 插入操作
			rs = fileCargoService.insert(cargoReq);
		}
		
		return rs;
	}
	
	/**
	 * 更新货物档案信息
	 * <p>
	 * @param materCode    物料编码
	 * @param materName    物料名称
	 * @param materClass   物料类别
	 * @param state        状态
	 * @param spec         货物规格
	 * @param model        货物型号
	 * @param materMNCode  物料助记码
	 * @param mainMeasUnit 主计量单位
	 * @param imgPath      图片地址
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/updateCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result updateCargoInfo(String id,
								   String materCode, 
                                    String materName, 
                                     String materClass, 
                                      String state,
                                       String spec, 
                                        String model, 
                                         String materMNCode, 
                                          String mainMeasUnit, 
                                           String payType, 
                                            String imgPath,
                                             String desc1,
                                              String desc2,
                                               String userName) throws Exception{
		Result rs = Result.getSuccessResult();
		if (imgPath != null) {
			// 转换并上传图片地址
			FileUploadReq fileUploadReq = new FileUploadReq();
			fileUploadReq.setImgStr(imgPath);
			rs = iFileService.uploadImg(fileUploadReq);
		}
		
		if ("000000".equals(rs.getCode())) {
			FileCargoReq cargoReq = new FileCargoReq();
			// 主键
			cargoReq.setId(id);
			// 物料编码
			cargoReq.setMaterCode(materCode);
			// 物料名称
			cargoReq.setMaterName(materName);
			// 物料类别
			cargoReq.setMaterClass(materClass);
			// 状态
			cargoReq.setState(state);
			// 货物类别
			cargoReq.setSpec(spec);
			// 货物型号
			cargoReq.setModel(model);
			// 物料助记码
			cargoReq.setMaterMNCode(materMNCode);
			// 主计量单位
			cargoReq.setMeasUnit(mainMeasUnit);
			// 支付类型
			cargoReq.setPayType(payType);
			cargoReq.setDesc1(desc1);
			cargoReq.setDesc2(desc2);
			// 货物图片地址
			if (rs.getData() == null) {
				cargoReq.setImgPath(null);
			} else {
				cargoReq.setImgPath(rs.getData().toString());
			}
			// 修改人
			cargoReq.setModifier(userName);
			// 修改时间
			cargoReq.setModifyTime(Long.toString(new Date().getTime()));
			
			// 更新操作
			rs = fileCargoService.updateByPrimaryKeySelective(cargoReq);
		}
		if("000000".equals(rs.getCode())){
			//停用状态修改我的货物状态
			if("0".equals(state)){
				FileOrgCargoReq req = new FileOrgCargoReq();
				req.setMaterId(id);
				List<FileOrgCargoResp> list = fileOrgCargoService.queryMyCargoInfo(req);
				for(FileOrgCargoResp resp : list){
					FileOrgCargoReq uptreq = new FileOrgCargoReq();
					uptreq.setId(resp.getId());
					uptreq.setState("0");
					fileOrgCargoService.updateByPrimaryKeySelective(uptreq);
				}
			}
		}
		return rs;
	}
	/**
	 * 
	 * @描述:批量禁用
	 * @param id
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年7月5日下午4:47:34
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public Result updateStatus(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		FileCargoReq cargoReq = new FileCargoReq();
		cargoReq.setId(id);
		cargoReq.setState("0");
		String[] ids = id.split(",");
		List<FileOrgCargoResp> list = null;
		fileCargoService.batchUpdateByPrimaryKeySelective(cargoReq);
		for(String stid : ids){
			FileOrgCargoReq req = new FileOrgCargoReq();
			req.setMaterId(stid.substring(1,stid.length()-1));
			list = fileOrgCargoService.queryMyCargoInfo(req);
			StringBuffer str = new StringBuffer();
			for(FileOrgCargoResp resp : list){
				if(StringUtils.isBlank(str.toString())){
					str.append("'"+resp.getId()+"'");
				}else{
					str.append(",'"+resp.getId()+"'");
				}
			}
			if(StringUtils.isNotBlank(str.toString())){
				FileOrgCargoReq uptreq = new FileOrgCargoReq();
				uptreq.setId(str.toString());
				uptreq.setState("0");
				fileOrgCargoService.batchUpdateByPrimaryKeySelective(uptreq);
			}
		}
		return rs;
	}
	
	/**
	 * 删除货物档案信息
	 * <p>
	 * @param id    档案信息主键
	 * @return
	 * @throws Exception
	 * <p>
	 * @author guyuke
	 * @time 2016年5月22日 下午6:43:21
	 */
	@RequestMapping(value = "/deleteCargoInfo", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteCargoInfo(String id) throws Exception{

		Result rs = fileCargoService.deleteByPrimaryKey(id);
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
