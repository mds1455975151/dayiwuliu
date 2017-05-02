package com.tianrui.service.bean;

public class AnlianDict {
    private String id;

    private String type;

    private String alcode;

    private String alname;
    
    private String alvcode;

    private String alvname;


    private String wlcode;

    private String wlname;
    
    private String jtbName;
    
    private String jtbCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAlcode() {
        return alcode;
    }

    public void setAlcode(String alcode) {
        this.alcode = alcode == null ? null : alcode.trim();
    }

    public String getAlname() {
        return alname;
    }

    public void setAlname(String alname) {
        this.alname = alname == null ? null : alname.trim();
    }

    public String getWlcode() {
        return wlcode;
    }

    public void setWlcode(String wlcode) {
        this.wlcode = wlcode == null ? null : wlcode.trim();
    }

    public String getWlname() {
        return wlname;
    }

    public void setWlname(String wlname) {
        this.wlname = wlname == null ? null : wlname.trim();
    }

	public String getJtbName() {
		return jtbName;
	}

	public void setJtbName(String jtbName) {
		this.jtbName = jtbName == null ? null : jtbName.trim();
	}

	public String getJtbCode() {
		return jtbCode;
	}

	public void setJtbCode(String jtbCode) {
		this.jtbCode = jtbCode == null ? null : jtbCode.trim();
	}

	public String getAlvcode() {
		return alvcode;
	}

	public void setAlvcode(String alvcode) {
		this.alvcode = alvcode;
	}

	public String getAlvname() {
		return alvname;
	}

	public void setAlvname(String alvname) {
		this.alvname = alvname;
	}
}