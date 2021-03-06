package com.tianrui.service.admin.bean;

import com.tianrui.service.bean.IModel;
/**
 * 
 * @类描述：系统角色
 * @创建人：tank
 * @创建时间：2016年4月12日下午1:29:13
 *
 * @修改人：tank
 * @修改时间：2016年4月12日下午1:29:13
 * @修改备注：
 *
 */
public class Role implements IModel{
    /**
     * id
     * @mbggenerated
     */
    private Integer id;

    /**
     * 名称
     * @mbggenerated
     */
    private String name;

    /**
     * 描述
     * @mbggenerated
     */
    private String description;

    /**
     * 创建时间
     * @mbggenerated
     */
    private String createtime;

    /**
     * 编号
     * @mbggenerated
     */
    private String number;

    /**
     * 创建者
     * @mbggenerated
     */
    private String creator;

    /**
     *
     * @mbggenerated
     */
    private String desc1;

    /**
     *
     * @mbggenerated
     */
    private String desc2;

    /**
     *
     * @mbggenerated
     */
    private String desc3;

    /**
     *
     * @mbggenerated
     */
    private String desc4;
    /**
     * 分页
     */
    private Integer start;
   /**
    * 分页
    */
    private Integer limit;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.ID
     *
     * @return the value of trwuliu_role.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.ID
     *
     * @param id the value for trwuliu_role.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.name
     *
     * @return the value of trwuliu_role.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.name
     *
     * @param name the value for trwuliu_role.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.description
     *
     * @return the value of trwuliu_role.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.description
     *
     * @param description the value for trwuliu_role.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.CREATETIME
     *
     * @return the value of trwuliu_role.CREATETIME
     *
     * @mbggenerated
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.CREATETIME
     *
     * @param createtime the value for trwuliu_role.CREATETIME
     *
     * @mbggenerated
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.number
     *
     * @return the value of trwuliu_role.number
     *
     * @mbggenerated
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.number
     *
     * @param number the value for trwuliu_role.number
     *
     * @mbggenerated
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.creator
     *
     * @return the value of trwuliu_role.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.creator
     *
     * @param creator the value for trwuliu_role.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.DESC1
     *
     * @return the value of trwuliu_role.DESC1
     *
     * @mbggenerated
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.DESC1
     *
     * @param desc1 the value for trwuliu_role.DESC1
     *
     * @mbggenerated
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.DESC2
     *
     * @return the value of trwuliu_role.DESC2
     *
     * @mbggenerated
     */
    public String getDesc2() {
        return desc2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.DESC2
     *
     * @param desc2 the value for trwuliu_role.DESC2
     *
     * @mbggenerated
     */
    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.DESC3
     *
     * @return the value of trwuliu_role.DESC3
     *
     * @mbggenerated
     */
    public String getDesc3() {
        return desc3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.DESC3
     *
     * @param desc3 the value for trwuliu_role.DESC3
     *
     * @mbggenerated
     */
    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trwuliu_role.DESC4
     *
     * @return the value of trwuliu_role.DESC4
     *
     * @mbggenerated
     */
    public String getDesc4() {
        return desc4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trwuliu_role.DESC4
     *
     * @param desc4 the value for trwuliu_role.DESC4
     *
     * @mbggenerated
     */
    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
    
}