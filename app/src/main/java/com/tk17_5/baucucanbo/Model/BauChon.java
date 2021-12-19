package com.tk17_5.baucucanbo.Model;

public class BauChon {
    private String idSV;
    private boolean tvt, tth, ttm, lth;

    public String getIdSV() {
        return idSV;
    }

    public void setIdSV(String idSV) {
        this.idSV = idSV;
    }

    public boolean isTvt() {
        return tvt;
    }

    public void setTvt(boolean tvt) {
        this.tvt = tvt;
    }

    public boolean isTth() {
        return tth;
    }

    public void setTth(boolean tth) {
        this.tth = tth;
    }

    public boolean isTtm() {
        return ttm;
    }

    public void setTtm(boolean ttm) {
        this.ttm = ttm;
    }

    public boolean isLth() {
        return lth;
    }

    public void setLth(boolean lth) {
        this.lth = lth;
    }

    public BauChon(String idSV, boolean tvt, boolean tth, boolean ttm, boolean lth) {

        this.idSV = idSV;
        this.tvt = tvt;
        this.tth = tth;
        this.ttm = ttm;
        this.lth = lth;
    }

    public BauChon() {

    }

    @Override
    public String toString() {

        return idSV + " đã bầu";
    }
}
