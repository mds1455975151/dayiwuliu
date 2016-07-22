package com.tianrui.api.req.front.position;

import com.tianrui.api.req.BaseReq;
import com.tianrui.common.vo.MemberVo;
/**
 * 
  * @ClassName: PositionQueryReq 
  * @Description: 最后位置查询
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月5日 上午9:30:10 
  *
 */
public class PositionQueryReq extends BaseReq{

	private Long startTime;
	private Long endTime;
	private String currId;
	
	
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}

	
	
}
