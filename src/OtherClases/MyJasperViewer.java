package OtherClases;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public final class MyJasperViewer extends JasperViewer {

    private MyJasperViewer(JasperPrint jasperPrint, boolean isExitOnClose) {
        super(jasperPrint, isExitOnClose);
        this.setExtendedState(MAXIMIZED_BOTH);
    }
   
    public static void viewReport(JasperPrint jasperPrint, String title){
        MyJasperViewer mjv = new MyJasperViewer(jasperPrint,false);
        mjv.setTitle(title);
        mjv.setVisible(true);
    }
    
    

}
