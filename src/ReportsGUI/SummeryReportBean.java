package ReportsGUI;

public class SummeryReportBean {

    private String TNTR;
    private double MW;
    private double WW;
    private double DWW;
    private double TOTAL;

    public SummeryReportBean(String TNTR, double MW, double WW, double DWW, double TOTAL) {
        this.TNTR = TNTR;
        this.MW = MW;
        this.WW = WW;
        this.DWW = DWW;
        this.TOTAL = TOTAL;
    }

    public String getTNTR() {
        return TNTR;
    }

    public void setTNTR(String TNTR) {
        this.TNTR = TNTR;
    }

    public double getMW() {
        return MW;
    }

    public void setMW(double MW) {
        this.MW = MW;
    }

    public double getWW() {
        return WW;
    }

    public void setWW(double WW) {
        this.WW = WW;
    }

    public double getDWW() {
        return DWW;
    }

    public void setDWW(double DWW) {
        this.DWW = DWW;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    
  

}
