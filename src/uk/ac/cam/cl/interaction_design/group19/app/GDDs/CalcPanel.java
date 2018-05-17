package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

public class CalcPanel extends JPanel {

    public CalcPanel() {

        DateFormat date = new SimpleDateFormat("dd-MM-yy");
        JFormattedTextField jdate = new JFormattedTextField(date);


    }

}
