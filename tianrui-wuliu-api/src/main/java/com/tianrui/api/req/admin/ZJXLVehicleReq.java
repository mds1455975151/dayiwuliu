package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class ZJXLVehicleReq extends BaseReq{
	 	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

		private String id;

	    private String vehicleid;

	    private String vehicleno;

	    private String crossloge;

	    private String vehiclelogo;

	    private String creator;

	    private Long createtime;

	    private String modifier;

	    private Long modifytime;

	    private Integer limit;
		
		
		
		public Integer getLimit() {
			limit = (pageNo-1)*pageSize;
			return limit;
		}
		
		public void setLimit(Integer limit) {
			this.limit = limit;
		}
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getVehicleid() {
			return vehicleid;
		}

		public void setVehicleid(String vehicleid) {
			this.vehicleid = vehicleid;
		}

		public String getVehicleno() {
			return vehicleno;
		}

		public void setVehicleno(String vehicleno) {
			this.vehicleno = vehicleno;
		}

		public String getCrossloge() {
			return crossloge;
		}

		public void setCrossloge(String crossloge) {
			this.crossloge = crossloge;
		}

		public String getVehiclelogo() {
			return vehiclelogo;
		}

		public void setVehiclelogo(String vehiclelogo) {
			this.vehiclelogo = vehiclelogo;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public Long getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Long createtime) {
			this.createtime = createtime;
		}

		public String getModifier() {
			return modifier;
		}

		public void setModifier(String modifier) {
			this.modifier = modifier;
		}

		public Long getModifytime() {
			return modifytime;
		}

		public void setModifytime(Long modifytime) {
			this.modifytime = modifytime;
		}
	    

}
