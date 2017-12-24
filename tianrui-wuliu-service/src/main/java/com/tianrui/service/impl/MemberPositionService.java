package com.tianrui.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberPositionService;
import com.tianrui.api.req.front.position.PositionQueryReq;
import com.tianrui.api.req.front.position.PositionSaveReq;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.DateUtil;
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
	public Result savePositionList(PositionSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurrId()) ){
			if(req.getLat()<0||req.getLon()<0){
				rs.setErrorCode(ErrorCode.PARAM_FU_ERROR);
				LoggerFactory.getLogger("external").info("用户[{}],位置信息异常：lat [{}],lon[{}]", req.getCurrId(),req.getLat(),req.getLon());
				return rs;
			}
			LoggerFactory.getLogger("external").info("用户[{}],位置信息：lat [{}],lon[{}]",req.getCurrId(), req.getLat(),req.getLon());
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
			insertPositionLs(req);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_PARAM_ERROR);
		}
		return rs;
	}
	
	@Override
	public Result savePosition(PositionSaveReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurrId()) ){
			if(req.getLat()<0||req.getLon()<0){
				rs.setErrorCode(ErrorCode.PARAM_FU_ERROR);
				LoggerFactory.getLogger("external").info("用户[{}],位置信息异常：lat [{}],lon[{}]", req.getCurrId(),req.getLat(),req.getLon());
				return rs;
			}
			LoggerFactory.getLogger("external").info("用户[{}],位置信息：lat [{}],lon[{}]",req.getCurrId(), req.getLat(),req.getLon());
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
		record.setId(UUIDUtil.getId());
		record.setLat(req.getLat());
		record.setLon(req.getLon());
		record.setProxygps(req.getProxyGps());
		record.setMemberid(req.getCurrId());
		record.setCreatetime(System.currentTimeMillis());
		record.setCreateTimeStr(DateUtil.getDateString());
		record.setCreator(req.getCurrId());
		record.setTimeStap(req.getTimeStap());
		memberPositionRecordDao.save(record);
	}
	
	private void insertPositionLs(PositionSaveReq req) {
		MemberPositionRecord record =new MemberPositionRecord();
		record.setId(UUIDUtil.getId());
		record.setLat(req.getLat());
		record.setLon(req.getLon());
		record.setProxygps(req.getProxyGps());
		record.setMemberid(req.getCurrId());
		record.setCreatetime(req.getTimeStap());
		record.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date(req.getTimeStap())));
		record.setCreator(req.getCurrId());
		record.setTimeStap(req.getTimeStap());
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
