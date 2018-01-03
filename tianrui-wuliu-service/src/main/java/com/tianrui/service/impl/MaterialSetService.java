package com.tianrui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMaterialSetService;
import com.tianrui.api.req.admin.MaterialReq;
import com.tianrui.api.req.admin.MaterielRouteReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.mapper.FileCargoMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.bean.Materiel;
import com.tianrui.service.bean.MaterielRoute;
import com.tianrui.service.mapper.MaterielMapper;
import com.tianrui.service.mapper.MaterielRouteMapper;

@Service
public class MaterialSetService implements IMaterialSetService {

	@Autowired
	private FileCargoMapper fileCargoMapper;
	@Autowired
	private MaterielMapper materielMapper;
	@Autowired
	private FileRouteMapper fileRouteMapper;
	@Autowired
	private MaterielRouteMapper materielRouteMapper;

	@Override
	public Result queryMaterial(MaterialReq req) {
		Result result = Result.getSuccessResult();
		//查询档案数据
		List<FileCargo> list = fileCargoMapper.queryMaterial();
		for (FileCargo fileCargo : list) {
			Materiel mNew = materielMapper.selectByPrimaryKey(fileCargo.getId());
			//如果根据id去新表里查不到数据就把当前一条数据插入到新表里
			if(mNew==null){
				Materiel materiel = new Materiel();
				materiel.setId(fileCargo.getId());
				materiel.setMaterieName(fileCargo.getCargoname());
				materiel.setMaterieStatus(Constant.ZERO_STR);
				materiel.setCreator(fileCargo.getCreator());
				materiel.setCreateDate(System.currentTimeMillis());
				materiel.setModifier(req.getCreator());
				materiel.setModifierTime(System.currentTimeMillis());
				int count = materielMapper.insert(materiel);
				if(count>0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			}
		}
		return result;
	}

	@Override
	public Result setSelectedMaterial(MaterialReq req) {
		Result result = Result.getSuccessResult();
		String[] ids = req.getMaterialIds().split(";");
		for (String id : ids) {
			FileCargo fileCargo = fileCargoMapper.selectByPrimaryKey(id);
			if(fileCargo!=null){
				Materiel materiel = new Materiel();
				materiel.setId(id);
				materiel.setMaterieName(fileCargo.getCargoname());
				materiel.setMaterieStatus(Constant.ONE_STR);
				materiel.setCreator(req.getCreator());
				materiel.setCreateDate(System.currentTimeMillis());
				materiel.setModifier(req.getCreator());
				materiel.setModifierTime(System.currentTimeMillis());
				int count = materielMapper.insert(materiel);
				if(count>0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.SYSTEM_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result querySelecedMaterial(MaterialReq req) {
		Result result = Result.getSuccessResult();
		List<Materiel> list = materielMapper.querySelecedMaterial(req);
		result.setData(list);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public Result queryRoute(RouteReq req) {
		Result result = Result.getSuccessResult();
		List<FileRoute> list = fileRouteMapper.queryRoute(req);
		result.setData(list);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}
	
	@Override
	public Result setRoute(MaterielRouteReq req) {
		Result result = Result.getSuccessResult();
		String[] ids = req.getRouteIds().split(";");
		for (String id : ids) {
			MaterielRoute mr = new MaterielRoute();
			mr.setId(id);
			mr.setMaterieId(req.getMaterieId());
			mr.setRouteName(req.getRouteName());
			mr.setCreator(req.getCreator());
			mr.setCreateDate(System.currentTimeMillis());
			mr.setMaterieName(req.getMaterieName());
			mr.setMaterieStatus(Constant.ONE_STR);
			mr.setModifier(req.getCreator());
			mr.setModifierTime(System.currentTimeMillis());
			int count = materielRouteMapper.insert(mr);
			if(count>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result querySelecedRoute(MaterielRouteReq req) {
		Result result = Result.getSuccessResult();
		List<MaterielRoute> list = materielRouteMapper.querySelecedRoute(req);
		result.setData(list);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public Result delMaterial(String id) {
		Result result = Result.getSuccessResult();
		if(id.isEmpty()){
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			return result;
		}
		int count = materielMapper.deleteByPrimaryKey(id);
		if(count>0){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}else{
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public Result delRoute(String id) {
		Result result = Result.getSuccessResult();
		if(id.isEmpty()){
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			return result;
		}
		int count = materielRouteMapper.deleteByPrimaryKey(id);
		if(count>0){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}else{
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
