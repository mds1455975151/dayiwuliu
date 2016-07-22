package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMeasureService;
import com.tianrui.api.req.admin.MeasureCountReq;
import com.tianrui.api.req.admin.MeasureReq;
import com.tianrui.api.resp.admin.MeasureResp;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Measure;
import com.tianrui.service.mapper.MeasureMapper;
@Service
public class MeasureService implements IMeasureService{
	
	@Autowired
	private MeasureMapper measureMapper;
	
	public Result update(MeasureReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		Measure ms = new Measure();
		ms.setMeasureCode(req.getMeasureCode());
		if(measureMapper.selectByEntity(ms) != null && measureMapper.selectByEntity(ms).size()>0){
			if(!req.getId().equals(measureMapper.selectByEntity(ms).get(0).getId())){
				rs.setCode("1");
				rs.setError("计量单位编码已经存在");
				return rs;
			}
		}
		ms = new Measure();
		ms.setMeasureName(req.getMeasureName());
		if(measureMapper.selectByEntity(ms) != null && measureMapper.selectByEntity(ms).size()>0){
			if(!req.getId().equals(measureMapper.selectByEntity(ms).get(0).getId())){
				rs.setCode("1");
				rs.setError("计量单位名称已经存在");
				return rs;
			}
		}
		measureMapper.selectByEntity(ms);
		List<Measure> mlist = measureMapper.selectByPrimaryKey(req.getId());
		if(mlist.size()!=1){
			rs.setCode("1");
			rs.setError("请求数据有误");
			return rs;
		}
		//修改原基本单位时measureType measureBase
		if("0".equals(mlist.get(0).getMeasureBase())){
			if(!mlist.get(0).getMeasureType().equals(req.getMeasureType())){
				rs.setCode("1");
				rs.setError("基本单位,量纲不可修改");
				return rs;
			}
			if(!mlist.get(0).getMeasureBase().equals(req.getMeasureBase())){
				rs.setCode("1");
				rs.setError("基本单位,是否基本单位不可修改");
				return rs;
			}
		}
		
		//如果measureBase==0 修改基本量纲， 否则基本数据修改
		if("0".equals(req.getMeasureBase())){
			Measure mm = mlist.get(0);
			Measure measure = new Measure();
			measure.setMeasureType(mm.getMeasureType());
			//list量纲集合
			List<Measure> list = measureMapper.selectByEntity(measure);
			//list2新基本量纲
			measure.setMeasureCode(mm.getMeasureCode());
			List<Measure> list2 = measureMapper.selectByEntity(measure);
			if(list2.size()!=1){
				rs.setCode("1");
				rs.setError("请求数据有误");
				return rs;
			}
			Measure old = new Measure();
			old.setMeasureType(mm.getMeasureType());
			old.setMeasureBase("0");
			//list1旧基本量纲
			List<Measure> list1 = measureMapper.selectByEntity(old);
			
			if(list1.size()!=1){
				rs.setCode("1");
				rs.setError("请求数据有误");
				return rs;
			}
			//计算换算率
			double c = 0.00;
			c = list2.get(0).getConversion()/list1.get(0).getConversion();
			//逐条修改换算率
			for (int i = 0; i < list.size(); i++) {
				Measure upt = list.get(i);
				double newc = upt.getConversion();
				newc = newc/c;
				upt.setMeasureBase("1");
				upt.setConversion(newc);
				measureMapper.updateByPrimaryKeySelective(upt);
			}
			//修改量纲间的换算率
			Measure mea = new Measure();
			mea.setMeasureCode(mm.getMeasureType());
			mea.setMeasureMain(mm.getMeasureType());
			List<Measure> meaMain = measureMapper.selectByorClm(mea);
			for (int i = 0; i < meaMain.size(); i++) {
				Measure m = meaMain.get(i);
				double nwc = m.getConversion();
				if(mm.getMeasureType().equals(m.getMeasureMain())){
					nwc = nwc/c;
				}else{
					nwc = nwc*c;
				}
				m.setConversion(nwc);
				measureMapper.updateByPrimaryKeySelective(m);
			}
		}
		Measure newupt = new Measure();
		PropertyUtils.copyProperties(newupt, req);
		if("0".equals(req.getMeasureBase())){
			newupt.setConversion((double)1);
		}
		measureMapper.updateByPrimaryKeySelective(newupt);
		return rs;
	}

	public List<MeasureResp> findByEntityList(MeasureReq req) throws Exception, InvocationTargetException, NoSuchMethodException {
		Measure measure = new Measure();
		PropertyUtils.copyProperties(measure, req);
		List<Measure> list = measureMapper.selectByEntity(measure);
		List<MeasureResp> resplist = new ArrayList<MeasureResp>();
		for(Measure mea : list){
			MeasureResp resp = new MeasureResp();
			PropertyUtils.copyProperties(resp, mea);
			resplist.add(resp);
		}
		return resplist;
	}

	public Result insert(MeasureReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		Measure measure = new Measure();
		measure.setMeasureCode(req.getMeasureCode());
		List<Measure> list = measureMapper.selectByEntity(measure);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("编号已存在");
			return rs;
		}else {
			measure = new Measure();
			measure.setMeasureName(req.getMeasureName());
			list = measureMapper.selectByEntity(measure);
			if(list.size()!=0){
				rs.setCode("11");
				rs.setError("计量单位名称已存在");
				return rs;
			}
		}
		
		//是基本单位
		if("0".equals(req.getMeasureBase())){
			Measure ent = new Measure();
			//量纲集合
			ent.setMeasureType(req.getMeasureType());
			List<Measure> list1 = measureMapper.selectByEntity(ent);
			//原主量纲
			ent.setMeasureBase("0");
			List<Measure> list2 = measureMapper.selectByEntity(ent);
			double c = 1 ;
			//计算换算率
			if(list2.size()==1){
				Measure oldBase = list2.get(0);
				double oc = oldBase.getConversion();
				double nc = req.getConversion();
				c = nc / oc ;
			}
			//逐条修改换算率
			for (int i = 0; i < list1.size(); i++) {
				Measure upt = list1.get(i);
				double newc = upt.getConversion();
				newc = newc/c;
				upt.setMeasureBase("1");
				upt.setConversion(newc);
				measureMapper.updateByPrimaryKeySelective(upt);
			}
			//修改量纲间的换算率
			Measure mea = new Measure();
			mea.setMeasureCode(req.getMeasureType());
			mea.setMeasureMain(req.getMeasureType());
			List<Measure> meaMain = measureMapper.selectByorClm(mea);
			for (int i = 0; i < meaMain.size(); i++) {
				Measure m = meaMain.get(i);
				double nwc = m.getConversion();
				if(req.getMeasureType().equals(m.getMeasureMain())){
					nwc = nwc/c;
				}else{
					nwc = nwc*c;
				}
				m.setConversion(nwc);
				measureMapper.updateByPrimaryKeySelective(m);
			}
		}
		
		PropertyUtils.copyProperties(measure, req);
		if("0".equals(measure.getMeasureBase())){
			measure.setConversion((double)1);
		}
		String dataString = DateUtil.getDateString();
		measure.setCreateTime(dataString);
		measureMapper.insertSelective(measure);
		return rs;
	}

	public Result saveMain(MeasureReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Measure mea = new Measure();
		mea.setMeasureCode(req.getMeasureCode());
		mea.setMeasureMain(req.getMeasureMain());
		//查询量纲间换算是否存在
		List<Measure> list = measureMapper.selectByEntity(mea);
		if(list.size()!=0){
			rs.setCode("1");
			rs.setError("该记录已存在");
			return rs;
		}
		//验证数据准确性
		Measure m = new Measure();
		m.setMeasureType(req.getMeasureCode());
		List lm = measureMapper.selectByEntity(m);
		Measure n = new Measure();
		n.setMeasureType(req.getMeasureMain());
		List ln = measureMapper.selectByEntity(n);
		if(lm.size()==0||ln.size()==0){
			rs.setCode("1");
			rs.setError("请求数据有误");
			return rs;
		}
		String dataString = DateUtil.getDateString();
		Measure measure = new Measure();
		measure.setId(UUIDUtil.getId());
		measure.setMeasureCode(req.getMeasureCode());
		measure.setMeasureMain(req.getMeasureMain());
		measure.setConversion(req.getConversion());
		measure.setCreateTime(dataString);
		measureMapper.insertSelective(measure);
		return rs;
	}

	public Result similarCalculation(MeasureCountReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		double value = Double.parseDouble(req.getValue()) ;
		String measureCode = req.getMeasureCode();
		String measureMain = req.getMeasureMain();
		
		Measure measure = new Measure();
		measure.setMeasureCode(measureCode);
		//验证数据准确性
		List<Measure> mCodeList = measureMapper.selectByEntity(measure);
		if(mCodeList.size()!=1){
			rs.setCode("1");
			rs.setError("数据设置有误，请检查数据结构...");
			return rs;
		}
		//验证数据准确性
		Measure mType = new Measure();
		mType.setMeasureType(mCodeList.get(0).getMeasureType());
		mType.setMeasureBase("0");
		List<Measure> mTypeList = measureMapper.selectByEntity(mType);
		if(mTypeList.size()!=1){
			rs.setCode("1");
			rs.setError("数据设置有误，请检查数据结构...");
			return rs;
		}
		double result = mCodeList.get(0).getConversion()*value;
		//measureMain为空，同量纲运算
		if(StringUtils.isBlank(measureMain)||measureMain.equals(mCodeList.get(0).getMeasureType())){
			rs.setData(result);
			return rs;
		}else{//不同量纲计算
			Measure msm = new Measure();
			msm.setMeasureCode(mCodeList.get(0).getMeasureType());//换算单位所属量纲
			msm.setMeasureMain(measureMain);
			List<Measure> msmlist = measureMapper.selectByEntity(msm);
			msm.setMeasureCode(measureMain);
			msm.setMeasureMain(mCodeList.get(0).getMeasureType());
			List<Measure> listmsm = measureMapper.selectByEntity(msm);
			if(msmlist.size()==1){
				result = result*msmlist.get(0).getConversion();
				rs.setData(result);
				return rs;
			}else if(listmsm.size()==1){
				result = result/listmsm.get(0).getConversion();
				rs.setData(result);
				return rs;
			}else{
				rs.setCode("1");
				rs.setError("暂无此换算方法，请添加换算率");
				return rs;
			}
		}
	}

	@Override
	public List<MeasureResp> findMeasureBlur(MeasureReq req) throws Exception {
		Measure measure = new Measure();
		PropertyUtils.copyProperties(measure, req);
		List<Measure> list = measureMapper.findMeasureBlur(measure);
		List<MeasureResp> resp = copyProperties(list);
		return resp;
	}
	
	public List<MeasureResp> copyProperties(List<Measure> req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MeasureResp> list = new ArrayList<MeasureResp>();
		for(Measure mea : req){
			MeasureResp resp = new MeasureResp();
			PropertyUtils.copyProperties(resp, mea);
			list.add(resp);
		}
		return list;
	}

	@Override
	public Result delectMeasureById(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		Measure measure = measureMapper.selectByPrimaryKey(id).get(0);
//		if("0".equals(measure.getMeasureBase())){
//			rs.setCode("1");
//			rs.setError("不能删除基本单位");
//			return rs;
//		}
		int a = measureMapper.deleteByPrimaryKey(id);
		if(a!=1){
			rs.setCode("1");
			rs.setError("删除失败");
			return rs;
		}
		return rs;
	}

	@Override
	public List<MeasureResp> findMeasonMainNotNull() throws Exception {
		List<Measure> list = measureMapper.findMeasureMainNotNull();
		List<MeasureResp> resp = copyProperties(list);
		return resp;
	}

	@Override
	public MeasureResp findMeasureById(String id) throws Exception {
		List<Measure> mea = measureMapper.selectByPrimaryKey(id);
		if(mea.size()!=1){
			return null;
		}
		List<MeasureResp> list = copyProperties(mea);
		return list.get(0);
	}
}
