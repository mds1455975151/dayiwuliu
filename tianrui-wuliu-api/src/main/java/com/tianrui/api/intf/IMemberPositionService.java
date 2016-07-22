package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.position.PositionQueryReq;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.vo.Result;

/**
 * 
  * @ClassName: IMemberPositionService 
  * @Description: 用户位置信息
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午9:24:58 
  *
 */
public interface IMemberPositionService {
	
	//上传位置
	Result savePosition(PositionSaveReq req)throws Exception;
	//获取最后位置
	PositionResp queryLastPosition(PositionQueryReq req)throws Exception;
	//获取位置记录
	List<PositionResp> queryPosition(PositionQueryReq req)throws Exception;
}
