package com.tianrui.service.admin.impl.weixin;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IWXUserService;
import com.tianrui.api.req.admin.weixin.WeixinUserReq;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.service.admin.mapper.UsersMapper;
@Service
public class WXUserService implements IWXUserService{

	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public Result login(WeixinUserReq req) {
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(req.getOpenid())){
			rs.setCode("1");
			rs.setError("请在微信上登录");
			return rs;
		}
		Users user = new Users();
		user.setAccount(req.getUsername());
		List<Users> list = usersMapper.selectByCondition(user);
		//账号验证通过
		if(list.size()==1){
			if(!StringUtils.equals(list.get(0).getPassword(), Md5Utils.MD5(req.getPassword()))){
				rs.setCode("1");
				rs.setError("密码错误");
				return rs;
			}else{
				Users upt = new Users();
				upt.setId(list.get(0).getId());
				upt.setDesc4(req.getOpenid());
				usersMapper.updateByPrimaryKeySelective(upt);
				rs.setData(list.get(0));
				return rs;
			}
		}else{
			rs.setCode("1");
			rs.setError("账号错误");
			return rs;
		}
	}

	@Override
	public Result wxLogin(WeixinUserReq req) {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(req.getOpenid())){
			rs.setCode("1");
			rs.setError("请在微信上登录");
			return rs;
		}
		Users user = new Users();
		user.setDesc4(req.getOpenid());
		List<Users> list = usersMapper.selectByCondition(user);
		if(list.size()==1){
			rs.setData(list.get(0));
		}else{
			rs.setCode("1");
			rs.setError("微信登录失败，请账号登录");
			for(Users s : list){
				s.setDesc4("");
				usersMapper.updateByPrimaryKeySelective(s);
			}
		}
		return rs;
	}

}
