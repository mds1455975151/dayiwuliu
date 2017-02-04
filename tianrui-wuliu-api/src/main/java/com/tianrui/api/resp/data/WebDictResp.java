package com.tianrui.api.resp.data;

public class WebDictResp {
    private String id;

    private String type;

    private String wlcode;

    private String wlname;

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
}