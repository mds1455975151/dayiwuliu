package com.tianrui.api.intf;

import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;

/**
  * @ClassName: IMemberVoService 
  * @Description: 获取用户资料
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月23日 下午3:17:06 
  *
 */
public interface IMemberVoService {

	public MemberVo get(String id);
	
	public Result flush(String id);
	public MemberVo get(String id,boolean isFlush) ;
}
