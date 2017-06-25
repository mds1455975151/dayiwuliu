package com.tianrui.api.resp.front.bill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.common.constants.Constant;

public class AnlianBillResp {
    private String id;
    
    private String systemShipper = Constant.SYSTEM_SHIPPER;
    
    private String status;
    
    private String drivertel;

    private String billno;
    
    private Double pickupweight; 
    private String pickupimgurl;
    
    private Double signweight; 
    private String signimgurl;
    private Long signtime;
    
    private Double trueweight;
    
    private String 	confirmPriceA;
    private String	confirmPriceB;
    
    //支付对象 1-司机 2-车主
    private String payment;
    //收货人用户id
    private String receive_memberid;

    private String ownerid;

    private String driverid;

    private String venderid;

    private String ddh;

    private String dwzl;

    private String sj;

    private String cph;

    private String zzl;

    private String ztj;

    private String zsl;

    private String yf;

    private String jffs;

    private String qycs;

    private String mdcs;

    private String lc;

    private String yqthrq;

    private String yqdhrq;

    private String khdm;

    private String thdz;

    private String shdz;

    private String shr;

    private String lxsj;

    private String jj;

    private String hpmc;

    private String sl;

    private String dw;

    private String hpsx;

    private String creater;

    private Long createtime;
    
    private String createtimeStr;
    
    private String aldriverid;

    private Integer start;
    
    private String routeName;
    
    private Integer limit;
    private String desc1;
    
    private String desc4;

    public String getId() {
        return id;
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

	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public String getOwnerid() {
        return ownerid;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDrivertel() {
		return drivertel;
	}

	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}

	public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getVenderid() {
        return venderid;
    }

    public void setVenderid(String venderid) {
        this.venderid = venderid == null ? null : venderid.trim();
    }

    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh == null ? null : ddh.trim();
    }

    public String getDwzl() {
        return dwzl;
    }

    public void setDwzl(String dwzl) {
        this.dwzl = dwzl == null ? null : dwzl.trim();
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj == null ? null : sj.trim();
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph == null ? null : cph.trim();
    }

    public String getZzl() {
        return zzl;
    }

    public void setZzl(String zzl) {
        this.zzl = zzl == null ? null : zzl.trim();
    }

    public String getZtj() {
        return ztj;
    }

    public void setZtj(String ztj) {
        this.ztj = ztj == null ? null : ztj.trim();
    }

    public String getZsl() {
        return zsl;
    }

    public void setZsl(String zsl) {
        this.zsl = zsl == null ? null : zsl.trim();
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf == null ? null : yf.trim();
    }

    public String getJffs() {
        return jffs;
    }

    public void setJffs(String jffs) {
        this.jffs = jffs == null ? null : jffs.trim();
    }

    public String getQycs() {
        return qycs;
    }

    public void setQycs(String qycs) {
        this.qycs = qycs == null ? null : qycs.trim();
    }

    public String getMdcs() {
        return mdcs;
    }

    public void setMdcs(String mdcs) {
        this.mdcs = mdcs == null ? null : mdcs.trim();
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc == null ? null : lc.trim();
    }

    public String getYqthrq() {
        return yqthrq;
    }

    public void setYqthrq(String yqthrq) {
        this.yqthrq = yqthrq == null ? null : yqthrq.trim();
    }

    public String getYqdhrq() {
        return yqdhrq;
    }

    public void setYqdhrq(String yqdhrq) {
        this.yqdhrq = yqdhrq == null ? null : yqdhrq.trim();
    }

    public String getKhdm() {
        return khdm;
    }

    public void setKhdm(String khdm) {
        this.khdm = khdm == null ? null : khdm.trim();
    }

    public String getThdz() {
        return thdz;
    }

    public void setThdz(String thdz) {
        this.thdz = thdz == null ? null : thdz.trim();
    }

    public String getShdz() {
        return shdz;
    }

    public void setShdz(String shdz) {
        this.shdz = shdz == null ? null : shdz.trim();
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr == null ? null : shr.trim();
    }

    public String getLxsj() {
        return lxsj;
    }

    public void setLxsj(String lxsj) {
        this.lxsj = lxsj == null ? null : lxsj.trim();
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj == null ? null : jj.trim();
    }

    public String getHpmc() {
        return hpmc;
    }

    public void setHpmc(String hpmc) {
        this.hpmc = hpmc == null ? null : hpmc.trim();
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl == null ? null : sl.trim();
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw == null ? null : dw.trim();
    }

    public String getHpsx() {
        return hpsx;
    }

    public void setHpsx(String hpsx) {
        this.hpsx = hpsx == null ? null : hpsx.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public String getCreatetimeStr() {
		if(createtime != null){
			createtimeStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(createtime));
		}
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getSystemShipper() {
		return systemShipper;
	}

	public void setSystemShipper(String systemShipper) {
		this.systemShipper = systemShipper;
	}

	public String getAldriverid() {
		return aldriverid;
	}

	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getReceive_memberid() {
		return receive_memberid;
	}

	public void setReceive_memberid(String receive_memberid) {
		this.receive_memberid = receive_memberid;
	}

	public Double getPickupweight() {
		return pickupweight;
	}

	public void setPickupweight(Double pickupweight) {
		this.pickupweight = pickupweight;
	}

	public String getPickupimgurl() {
		return pickupimgurl;
	}

	public void setPickupimgurl(String pickupimgurl) {
		this.pickupimgurl = pickupimgurl;
	}

	public Double getSignweight() {
		return signweight;
	}

	public void setSignweight(Double signweight) {
		this.signweight = signweight;
	}

	public String getSignimgurl() {
		return signimgurl;
	}

	public void setSignimgurl(String signimgurl) {
		this.signimgurl = signimgurl;
	}

	public Long getSigntime() {
		return signtime;
	}

	public void setSigntime(Long signtime) {
		this.signtime = signtime;
	}

	public Double getTrueweight() {
		return trueweight;
	}

	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}

	public String getConfirmPriceA() {
		return confirmPriceA;
	}

	public void setConfirmPriceA(String confirmPriceA) {
		this.confirmPriceA = confirmPriceA;
	}

	public String getConfirmPriceB() {
		return confirmPriceB;
	}

	public void setConfirmPriceB(String confirmPriceB) {
		this.confirmPriceB = confirmPriceB;
	}
}