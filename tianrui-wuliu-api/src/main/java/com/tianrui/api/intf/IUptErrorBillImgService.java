package com.tianrui.api.intf;

import java.io.IOException;

import com.tianrui.common.vo.Result;

/**
 * 修改运单错误图片
 * @author jh
 *
 */
public interface IUptErrorBillImgService {

	/** 修改运单图片*/
	Result uptBillImg()throws Exception;
}
