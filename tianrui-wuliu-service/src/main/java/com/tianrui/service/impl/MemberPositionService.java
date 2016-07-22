package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberPositionService;
import com.tianrui.api.req.front.position.PositionQueryReq;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberPosition;
import com.tianrui.service.bean.MemberPositionRecord;
import com.tianrui.service.mapper.MemberPositionMapper;
import com.tianrui.service.mongo.MemberPositionRecordDao;

@Service
public class MemberPositionService implements IMemberPositionService{
	
	@Autowired
	MemberPositionMapper memberPositionMapper;
	@Autowired
	MemberPositionRecordDao memberPositionRecordDao; 
	
	
	@Override
	public Result savePosition(PositionSaveReq req) throws Exception {
		Result rs = new Result();
		if( req !=null && StringUtils.isNotBlank(req.getCurrId()) ){
			//查找是否有次记录
			MemberPosition  posiotionDB =memberPositionMapper.findWithMid(req.getCurrId());
			//存在就修改
			if( posiotionDB !=null ){
				updatePosition(req, posiotionDB);
			//否则新增	
			}else{
				insertPosition(req);
			}
			//保存记录到mongo
			insertPositionRecord(req);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_PARAM_ERROR);
		}
		return rs;
	}

	private void insertPositionRecord(PositionSaveReq req) {
		MemberPositionRecord record =new MemberPositionRecord();
		record.setLat(req.getLat());
		record.setLon(req.getLon());
		record.setProxygps(req.getProxyGps());
		record.setId(UUIDUtil.getId());
		record.setMemberid(req.getCurrId());
		record.setCreatetime(System.currentTimeMillis());
		record.setCreator(req.getCurrId());
		memberPositionRecordDao.save(record);
	}

	private void insertPosition(PositionSaveReq req) {
		MemberPosition save = new MemberPosition();
		save.setLat(req.getLat());
		save.setLon(req.getLon());
		save.setModifier(req.getCurrId());
		save.setModifytime(System.currentTimeMillis());
		save.setProxygps(req.getProxyGps());
		save.setId(UUIDUtil.getId());
		save.setMemberid(req.getCurrId());
		save.setCreatetime(System.currentTimeMillis());
		save.setCreator(req.getCurrId());
		memberPositionMapper.insert(save);
	}

	private void updatePosition(PositionSaveReq req, MemberPosition posiotionDB) {
		MemberPosition update = new MemberPosition();
		update.setLat(req.getLat());
		update.setLon(req.getLon());
		update.setModifier(req.getCurrId());
		update.setModifytime(System.currentTimeMillis());
		update.setProxygps(req.getProxyGps());
		update.setId(posiotionDB.getId());
		memberPositionMapper.updateByPrimaryKeySelective(update);
	}

	@Override
	public PositionResp queryLastPosition(PositionQueryReq req) throws Exception {
		PositionResp resp =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurrId()) ){
			resp=new PositionResp();
			//查找是否有次记录
			MemberPosition  posiotionDB =memberPositionMapper.findWithMid(req.getCurrId());
			resp =conver2resp(posiotionDB);
		}
		return resp;
	}

	private PositionResp conver2resp(MemberPosition posiotionDB)throws Exception {
		PositionResp resp=null;
		if( posiotionDB !=null ){
			resp =new PositionResp();
			PropertyUtils.copyProperties(resp, posiotionDB);
		}
		return resp;
	}
	private List<PositionResp> conver2resps(List<MemberPositionRecord> list)throws Exception {
		List<PositionResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<PositionResp>();
			for(MemberPositionRecord item :list ){
				PositionResp resp =new PositionResp();
				PropertyUtils.copyProperties(resp, item);
				rs.add(resp);
			}
		}
		return rs;
	}

	@Override
	public List<PositionResp> queryPosition(PositionQueryReq req) throws Exception {
		List<PositionResp> list =null;
		if( req !=null && StringUtils.isNotBlank(req.getCurrId()) ){
			List<MemberPositionRecord>  itemList =memberPositionRecordDao.findWithBid(req.getCurrId(), req.getStartTime(), req.getEndTime());
			list =conver2resps(itemList);
		}
		return list;
	}
	
	
	
	
}
