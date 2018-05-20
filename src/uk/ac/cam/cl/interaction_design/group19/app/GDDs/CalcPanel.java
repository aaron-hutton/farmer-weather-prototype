package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalcPanel extends JPanel {
    private static final String  description = "Calculate total growing degree days between start date and today";
    private static final JButton forecast    = new JButton("Forecast");
    private static final int     loc         = 0;
    
    public CalcPanel(Runnable showForecast) {
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        DateFormat          dateFormat = new SimpleDateFormat("dd-MM-yy");
        JFormattedTextField jdate      = new JFormattedTextField(dateFormat);
        JLabel              start      = new JLabel("Start");
        JButton             enter      = new JButton("Enter");
        
        JLabel calc = new JLabel("Calculator");
        JLabel desc = new JLabel("<html>" + description + "</html>");
        
        JLabel incorrectEntry = new JLabel("The date you've entered is invalid");
        incorrectEntry.setForeground(Color.red);
        
        JLabel output  = new JLabel("Output:");
        JLabel dataOut = new JLabel();
        
        JPanel descriptor = new JPanel();
        descriptor.setLayout(new BoxLayout(descriptor, BoxLayout.PAGE_AXIS));
        JPanel inputInner = new JPanel();
        inputInner.setLayout(new BoxLayout(inputInner, BoxLayout.LINE_AXIS));
        JPanel inputOuter = new JPanel();
        inputOuter.setLayout(new BoxLayout(inputOuter, BoxLayout.PAGE_AXIS));
        JPanel out = new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.PAGE_AXIS));
        JPanel buttons = createButtonsPanel();
        
        descriptor.add(calc);
        descriptor.add(desc);
        inputInner.add(start);
        inputInner.add(jdate);
        inputInner.add(enter);
        inputOuter.add(inputInner);
        inputOuter.add(incorrectEntry);
        out.add(output);
        out.add(dataOut);
        
        incorrectEntry.setVisible(false);
        
        addOnClick(forecast, showForecast);
        addOnClick(enter, () -> {
            Date date = (Date) jdate.getValue();
            if (date != null) {
                dataOut.setText(Integer.toString((int) bizeeAPI.gddSince(loc, 10, date)));
                dataOut.setVisible(true);
                incorrectEntry.setVisible(false);
            } else {
                incorrectEntry.setVisible(true);
            }
        });
        
        this.add(descriptor);
        this.add(inputOuter);
        this.add(out);
        this.add(buttons);
    }
    
    private static JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));
        
        forecast.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(forecast);
        
        return bottomButtons;
    }
    
    private static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e -> {
            btnAction.run();
        });
    }
    
}
