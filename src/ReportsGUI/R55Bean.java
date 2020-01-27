package ReportsGUI;

public class R55Bean {

    private String SA;
    private String SN;
    private String JHA;
    private String CDate;

    public R55Bean(String SA, String SN, String JHA, String D55) {
        this.SA = SA;
        this.SN = SN;
        this.JHA = JHA;
        this.CDate = D55;
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

    public String getJHA() {
        return JHA;
    }

    public void setJHA(String JHA) {
        this.JHA = JHA;
    }

    public String getCDate() {
        return CDate;
    }

    public void setCDate(String CDate) {
        this.CDate = CDate;
    }

}
