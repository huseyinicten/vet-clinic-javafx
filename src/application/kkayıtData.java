package application;
public class kkayıtData {
	private String eid;
	private String ad;
	private String tel;
	private String isim;
	private String tür;
	private String cins;
	private String cinsiyet;
	private String tar;
	public kkayıtData (String eid, String ad, String tel, String isim, String tür , String cins, String cinsiyet,String tar){
	this.eid = eid;
	this.ad = ad;
	this.tel = tel;
	this.isim = isim;
	this.tür = tür;
	this.cins = cins;
	this.cinsiyet = cinsiyet;
	this.tar = tar;
	}
	public String getEid() {
	    return eid;
	}
	public String getAd() {
	    return ad;
	}
	public String getTel() {
	    return tel;
	}
	public String getIsim() {
	    return isim;
	}
	public String getTür() {
	    return tür;
	}
	public String getCins() {
	    return cins;
	}
	public String getCinsiyet() {
	    return cinsiyet;
	}
	public String getTar() {
	    return tar;
	}

}