package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
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
		//查询出状态==1（已选的物料）
		List<Materiel> list = materielMapper.queryall();
		if(list!=null){
			//把状态为已选的改变成==0（待选的物料）
			for (Materiel materiel : list) {
				materiel.setMaterieStatus(Constant.ZERO_STR);	
				materielMapper.updateByPrimaryKey(materiel);
			}
		}
		String[] ids = req.getMaterialIds().split(";");
		for (String id : ids) {
			Materiel m = materielMapper.selectByPrimaryKey(id);
			if(m!=null){
				m.setMaterieStatus(Constant.ONE_STR);
				m.setModifier(req.getCreator());
				m.setModifierTime(System.currentTimeMillis());
			}
			int count = materielMapper.updateByPrimaryKey(m);
			if(count==0){
				result.setErrorCode(ErrorCode.SYSTEM_ERROR);
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

	//查询档案路线数据同步到新表
	@Override
	public Result queryRoute(RouteReq req) {
		Result result = Result.getSuccessResult();
		List<FileRoute> list = fileRouteMapper.queryRoute(req);
		for (FileRoute fileRoute : list) {
			MaterielRoute route = materielRouteMapper.selectByPrimaryKey(fileRoute.getId());
			if(route==null){
				MaterielRoute mr = new MaterielRoute();
				mr.setId(fileRoute.getId());
				mr.setRouteName(fileRoute.getRoutename());
				mr.setCreator(req.getCreator());
				mr.setMaterieStatus(Constant.ZERO_STR);
				int count = materielRouteMapper.insert(mr);
				if(count==0){
					result.setErrorCode(ErrorCode.SYSTEM_ERROR);
				}
			}
		}
		return result;
	}
	
	@Override
	public Result setRoute(MaterielRouteReq req) {
		Result result = Result.getSuccessResult();
		String[] ids = req.getRouteIds().split(";");
		for (String id : ids) {
			MaterielRoute route = materielRouteMapper.selectByPrimaryKey(id);
			if(route.getMaterieStatus().equals(Constant.ZERO_STR)){
				route.setMaterieStatus(Constant.ONE_STR);
				route.setMaterieId(req.getMaterieId());
				route.setMaterieName(req.getMaterieName());
			}else{
				route.setMaterieStatus(Constant.ZERO_STR);
			}
			int count = materielRouteMapper.updateByPrimaryKey(route);
			if(count==0){
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

	//查询待选物料
	@Override
	public Result queryWaitMate(MaterialReq req) {
		Result result = Result.getSuccessResult();
		List<Materiel> list = materielMapper.queryWaitMate(req);
		result.setData(list);
		return result;
	}

	@Override
	public Result queryWaitRoute(MaterielRouteReq req) {
		Result result = Result.getSuccessResult();
		List<MaterielRoute> list = materielRouteMapper.queryWaitRoute(req);
		result.setData(list);
		return result;
	}

	@Override
	public Result selectMaterial(RouteReq req) {
		Result result = Result.getSuccessResult();
		//新建一个返回数据的集合
		List<FileRoute> data = new ArrayList<FileRoute>();
		//查询总的档案路线数据==待选路线
		List<FileRoute> list = fileRouteMapper.queryRoute(req);
		//查询已选路线数据
		List<MaterielRoute> listRout = materielRouteMapper.selecedRoute();
		for (FileRoute fileRoute : list) {
			if(listRout!=null){
				for (MaterielRoute materielRoute : listRout) {
					//根据id判断档案数据和已选数据是否有相等的  
					if(!fileRoute.getId().equals(materielRoute.getId())){
						//如果没有相等的就添加在新的集合里
						data.add(fileRoute);
					}
				}
			}else{
				result.setData(list);
				return result;
			}
		}
		//最后返回的是去除已选路线的数据
		result.setData(data);
		return result;
	}
	
}
