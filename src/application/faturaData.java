package application;

public class faturaData {

	 private String liid;
	    private String eisim;
	    private String liisim;
	    private String fiyat;
	    public faturaData(String liid, String eisim, String liisim, String fiyat) {
	        this.liid = liid;
	        this.liisim = liisim;
	        this.eisim = eisim;
	        this.fiyat = fiyat;
	    }
	    public String getLiid() {
	        return liid;
	    }
	    public String getLiisim() {
	        return liisim;
	    }

	    public String getEisim() {
	        return eisim;
	    }

	    public String getFiyat() {
	        return fiyat;
	    }
	}

