package com.tianrui.api.req.cargo;

public class CargoReq {

    private String id;
    //被调用次数
    private String count;

    /**
     * 所属组织名称 
     */
    private String organizationname;

    /**
     * 所属组织id
     */
    private String organizationid;

    /**
     * 货物分类
     */
    private String cargotype;

    /**
     * 货物名称
     */
    private String cargoname;

    /**
     * 货物编码
     * @mbggenerated
     */
    private String cargono;

    /**
     * 货物助记码
     */
    private String zjm;

    /**
     *  货物状态（0-停用；1-启用；2-已删除）
     */
    private String status;
    
    /**
     *  支付类型（0：在线支付，1：发票单支付）
     */
    private String payType;

    /**
     * 主计量单位
     * @mbggenerated
     */
    private String measure;

    /**
     * 货物规格
     */
    private String specifications;

    /**
     * 货物型号
     */
    private String model;

    /**
     * 货物图片路径
     */
    private String imagepath;

    /**
     * 创建者 
     */
    private String creator;

    /**
     * 创建时间
     */
    private Long createtime;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Long modifytime;

    /** 分页开始位置 */
    private Integer pageNo;
    
    /** 分页数据行数 */
    private Integer pageSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}

	public String getCargotype() {
		return cargotype;
	}

	public void setCargotype(String cargotype) {
		this.cargotype = cargotype;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getCargono() {
		return cargono;
	}

	public void setCargono(String cargono) {
		this.cargono = cargono;
	}

	public String getZjm() {
		return zjm;
	}

	public void setZjm(String zjm) {
		this.zjm = zjm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
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

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
}
