package com.tianrui.api.intf;

import com.tianrui.api.req.front.member.MemberPushSaveReq;
import com.tianrui.common.vo.Result;

/**
 * 
  * @ClassName: IMemberPushService 
  * @Description: 用户推送信息
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午9:37:00 
  *
 */
public interface IMemberPushService {
	
	//保存push消息
	Result savePush(MemberPushSaveReq req)throws Exception;
	//保存push消息 货主版本
	Result savePushOwner(MemberPushSaveReq req)throws Exception;
	
	//发送推送消息
	Result sendPsuhMesage(String memberId, String msg,String code) throws Exception;
	//发送推送消息
	Result sendPsuhOwnerMesage(String memberId, String msg,String code) throws Exception;
	
	Result deletePushWithMId(String memberId);
	
}
