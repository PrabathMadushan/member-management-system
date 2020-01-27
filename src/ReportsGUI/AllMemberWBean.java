package ReportsGUI;

public class AllMemberWBean {

    private float MW;
    private float DW;
    private float WW;
    private float DWW;
    private String SA;
    private String SN;

    public AllMemberWBean(float MW, float DW, float WW, float DWW, String SA, String SN) {
        this.MW = MW;
        this.DW = DW;
        this.WW = WW;
        this.DWW = DWW;
        this.SA = SA;
        this.SN = SN;
    }

  

    public float getDW() {
        return DW;
    }

    public void setDW(float DW) {
        this.DW = DW;
    }

    public float getMW() {
        return MW;
    }

    public void setMW(float MW) {
        this.MW = MW;
    }

    public float getWW() {
        return WW;
    }

    public void setWW(float WW) {
        this.WW = WW;
    }

    public float getDWW() {
        return DWW;
    }

    public void setDWW(float DWW) {
        this.DWW = DWW;
    }

    public String getSA() {
        return SA;
    }

    public void setSA(String SA) {
        this.SA = SA;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

}
