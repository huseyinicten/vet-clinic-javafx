package application;

public class ikayıtData {
    private String lid;
    private String adet;
    private String lisim;
    private String litar;
    private String fiyat;

    public ikayıtData(String lid, String adet, String lisim, String litar, String fiyat) {
        this.lid = lid;
        this.adet = adet;
        this.lisim = lisim;
        this.litar = litar;
        this.fiyat = fiyat;
    }

    public String getLid() {
        return lid;
    }

    public String getAdet() {
        return adet;
    }

    public String getLisim() {
        return lisim;
    }

    public String getLitar() {
        return litar;
    }

    public String getFiyat() {
        return fiyat;
    }
}