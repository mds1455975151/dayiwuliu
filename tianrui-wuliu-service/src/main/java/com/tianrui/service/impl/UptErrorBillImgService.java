package com.tianrui.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IFileService;
import com.tianrui.api.intf.IUptErrorBillImgService;
import com.tianrui.api.req.front.system.FileUploadReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.mapper.BillMapper;
import com.tianrui.service.util.TimeUtils;
import com.tianrui.service.util.UptImgUtil;

@Service
public class UptErrorBillImgService implements IUptErrorBillImgService{

	@Autowired
	BillMapper billMapper;
	@Autowired
	IFileService fileService;
	
	@Override
	public Result uptBillImg() throws Exception {
		Result rs = Result.getSuccessResult();
		Bill bill = new Bill();
		bill.setCreateTimeBegin(TimeUtils.StringZoLong("2017-11-01 00:00:00"));
		bill.setCreateTimeEnd(TimeUtils.StringZoLong("2017-11-03 00:00:00"));
		List<Bill> list = billMapper.selectByUptBillImg(bill);
		System.out.println("-----------本次处理数据总数--"+list.size());
		for(Bill b : list){
			Bill upt = new Bill();
			upt.setId(b.getId());
			System.out.println("------------"+b.getId());
			if(StringUtils.isNotBlank(b.getPickupimgurl())){
				//提货榜单不为空
				String base64 = "";
				try {
					base64 = UptImgUtil.downLoadFromUrl(b.getPickupimgurl());
					if(base64.startsWith("data/image/")){
						String imgUrl = uploadImg(base64.substring(22));
						if(StringUtils.isNotBlank(imgUrl)){
							upt.setPickupimgurl(imgUrl);
							System.out.println("----------修改提货榜单-");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					upt.setPickupimgurl("");
				}
				
				if(base64.startsWith("data/image/")){
					String imgUrl = uploadImg(base64.substring(22));
					if(StringUtils.isNotBlank(imgUrl)){
						upt.setPickupimgurl(imgUrl);
						System.out.println("----------修改提货榜单-");
					}
				}
			}
			if(StringUtils.isNotBlank(b.getSignimgurl())){
				//签收榜单不为空
				String base64 = "";
				try {
					base64 = UptImgUtil.downLoadFromUrl(b.getSignimgurl());
					if(base64.startsWith("data/image/")){
						String imgUrl = uploadImg(base64.substring(22));
						if(StringUtils.isNotBlank(imgUrl)){
							upt.setSignimgurl(imgUrl);
							System.out.println("----------修改卸货榜单-");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					upt.setSignimgurl("");
				}
			}
			if(StringUtils.isNotBlank(upt.getSignimgurl())||StringUtils.isNotBlank(upt.getPickupimgurl())){
				billMapper.updateByPrimaryKeySelective(upt);
				System.out.println("修改数据");
			}
		}
		return rs;
	}
	/**上传图片*/
	public String uploadImg(String img) throws Exception{
		String imgPath = "";
		FileUploadReq req = new FileUploadReq();
		req.setImgStr(img);
		Result rs = fileService.uploadBillImg(req);
		if(rs.getCode().equals("000000")){
			imgPath = (String) rs.getData();
		}
		return imgPath;
	}

}
