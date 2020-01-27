package OtherClases;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class UserInputChecker {

    public static boolean isANumber(JTextField text) {
        if (text.isEnabled()) {            
            try {
                Float.parseFloat(text.getText());
                text.setBackground(Color.white);
                return true;
            } catch (NumberFormatException e) {
                text.setBackground(Color.pink);
                return false;
            }
        } else {
            return true;
        }

    }

    public static boolean check(JTextField text) {
        if (text.isEnabled()) {
            if (text.getText().isEmpty()) {
                text.setBackground(Color.pink);
                return false;
            } else {
                text.setBackground(Color.WHITE);
                return true;
            }
        } else {
            return true;
        }
    }
    
   
   
    
    public static boolean check(JTextArea text) {
        if (text.isEnabled()) {
            if (text.getText().isEmpty()) {
                text.setBackground(Color.pink);
                return false;
            } else {
                text.setBackground(Color.WHITE);
                return true;
            }
        } else {
            return true;
        }

    }

    public static boolean check(JPanel panel, JTable tbl) {
        TitledBorder tb = (TitledBorder) panel.getBorder();
        if (panel.isEnabled()) {
            if (tbl.getRowCount() == 0) {
                panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 2),
                        tb.getTitle(),
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("FMMalithi", 0, 14)));
                return false;
            } else {
                panel.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("FMMalithi", 0, 14)));
                return true;
            }
        } else {
            return true;
        }

    }

    public static void reset(JTextField text) {
        text.setBackground(Color.WHITE);

    }

    public static void reset(JTextArea text) {
        text.setBackground(Color.WHITE);

    }

    public static void reset(JPanel panel) {
        TitledBorder tb = (TitledBorder) panel.getBorder();
        panel.setBorder(BorderFactory.createTitledBorder(null, tb.getTitle(),
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("FMMalithi", 0, 14)));

    }

}
