package com.tianrui.service.bean;
/**
 * @Description 银行类别Bean
 * @author zhanggaohao
 * @version 2017年6月24日 下午1:56:05
 */
public class BankType {
	
    private String id;
    //银行类别编码
    private String code;
    //银行简称
    private String abbrName;
    //银行名称
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName == null ? null : abbrName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public BankType() {
		super();
	}

	public BankType(String id, String code, String abbrName, String name) {
		super();
		this.id = id;
		this.code = code;
		this.abbrName = abbrName;
		this.name = name;
	}

	@Override
	public String toString() {
		return "BankType [id=" + id + ", code=" + code + ", abbrName=" + abbrName + ", name=" + name + "]";
	}
}