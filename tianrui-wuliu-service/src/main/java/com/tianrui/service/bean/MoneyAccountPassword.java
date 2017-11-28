package com.tianrui.service.bean;

import java.util.Date;

public class MoneyAccountPassword {
    private String id;

    private String cellphone;

    private String username;

    private String useryhno;

    private String password;

    private String gesturepass;

    private String gestureStatus;

    private Long creatertime;

    private Date lasttime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUseryhno() {
        return useryhno;
    }

    public void setUseryhno(String useryhno) {
        this.useryhno = useryhno == null ? null : useryhno.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGesturepass() {
        return gesturepass;
    }

    public void setGesturepass(String gesturepass) {
        this.gesturepass = gesturepass == null ? null : gesturepass.trim();
    }

    public String getGestureStatus() {
        return gestureStatus;
    }

    public void setGestureStatus(String gestureStatus) {
        this.gestureStatus = gestureStatus == null ? null : gestureStatus.trim();
    }

    public Long getCreatertime() {
        return creatertime;
    }

    public void setCreatertime(Long creatertime) {
        this.creatertime = creatertime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
}