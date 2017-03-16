package com.tianrui.service.bean.anlian;

public class PositionCounty {
    private String id;

    private String name;

    private String fartherid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFartherid() {
        return fartherid;
    }

    public void setFartherid(String fartherid) {
        this.fartherid = fartherid == null ? null : fartherid.trim();
    }
}