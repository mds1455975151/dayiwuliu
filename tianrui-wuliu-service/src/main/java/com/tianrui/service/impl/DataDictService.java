
/**
 * @标题: DataDictService.java
 * @功能描述：TODO
 * @作者： lsj
 * @创建时间： 2016年5月4日 下午3:37:36
 */

package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianrui.api.intf.IDataDictService;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.resp.common.DataDictResp;
import com.tianrui.service.bean.DataDict;
import com.tianrui.service.mapper.DataDictMapper;


@Service
public class DataDictService implements IDataDictService{
	
	public static Logger logger =LoggerFactory.getLogger(DataDictService.class);

	@Autowired
	private DataDictMapper dataDictMapper;  
	
	public List<DataDictResp> findDictList(DataDictReq req) {
		DataDict dataDict =new DataDict();
		dataDict.setSubcode(req.getSubCode());
		dataDict.setItemcode(req.getItemcode());
		List<DataDict> list=dataDictMapper.selectByCondition(dataDict);
		List<DataDictResp> rs = convert2Resp(list);
		return rs;
	}

	public DataDictResp findDict(DataDictReq req) {
		DataDict dataDict =new DataDict();
		dataDict.setSubcode(req.getSubCode());
		dataDict.setItemcode(req.getItemcode());
		List<DataDict> list=dataDictMapper.selectByCondition(dataDict);
		List<DataDictResp> rs = convert2Resp(list);
		return rs ==null?null:rs.get(0);
	}


	private List<DataDictResp> convert2Resp(List<DataDict> list){
		List<DataDictResp> rs = null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs = new ArrayList<DataDictResp>();
			for(DataDict dest :list  ){
				DataDictResp resp = new DataDictResp();
				try {
					BeanUtils.copyProperties(resp, dest);
					rs.add(resp);
				} catch (Exception e) {
					logger.warn("convert2Resp exception :{}",e.getMessage(),e);
				}
			}
		}
		return rs;
	}



}
