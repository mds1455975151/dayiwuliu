package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.message.intf.IMessageGroupService;
import com.tianrui.api.req.groupMsg.MemberGroupReq;
import com.tianrui.common.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class MessageGroupTest {

	@Autowired
	IMessageGroupService messageGroupService;
	
	@Test
	public void uptGroup() throws Exception{
		MemberGroupReq req = new MemberGroupReq();
		req.setGroupType((byte)1);
		Result rs = messageGroupService.uptMemberGroup(req);
		
		System.out.println(rs.getCode()+rs.getError());
	}
}
