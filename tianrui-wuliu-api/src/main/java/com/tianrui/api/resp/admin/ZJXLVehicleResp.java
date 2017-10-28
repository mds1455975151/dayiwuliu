package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;

public class ZJXLVehicleResp extends BaseResp{
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
	    
	    private String createtimes;

	    private String modifier;

	    private Long modifytime;

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

		 public String getCreatetimes() {
				if(createtime != null){
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					createtimes = fmt.format(new Date(createtime));
				}
		    	return createtimes;
			}

			public void setCreatetimes(String createtimes) {
				this.createtimes = createtimes;
			}
	    
	    
}
