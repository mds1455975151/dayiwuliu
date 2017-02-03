package com.tianrui.api.req.front.member;

public class AdminMenberInfoReq {

	private String id ;
	
	private String sex;
    private String birthday;
    private String firstlicens;
    private String licenceorg;
    private String starttime;
    private String usefullife;
    private String idcardaddress;
    //准驾车型
   	private String licenseType;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getFirstlicens() {
		return firstlicens;
	}
	public void setFirstlicens(String firstlicens) {
		this.firstlicens = firstlicens;
	}
	public String getLicenceorg() {
		return licenceorg;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public void setLicenceorg(String licenceorg) {
		this.licenceorg = licenceorg;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getUsefullife() {
		return usefullife;
	}
	public void setUsefullife(String usefullife) {
		this.usefullife = usefullife;
	}
	public String getIdcardaddress() {
		return idcardaddress;
	}
	public void setIdcardaddress(String idcardaddress) {
		this.idcardaddress = idcardaddress;
	}
}
