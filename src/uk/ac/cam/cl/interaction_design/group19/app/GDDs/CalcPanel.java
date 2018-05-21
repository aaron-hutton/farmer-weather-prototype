package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalcPanel extends JPanel {
    
    private static final String  description = "Calculate total growing degree days between start date and today";
    private static final JButton forecast    = new JButton("Forecast");
    private static final int     loc         = 0;
    
    
    public CalcPanel(Runnable showForecast) {
        
        this.setLayout(new BorderLayout());
        
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        
        DateFormat          dateFormat = new SimpleDateFormat("dd/MM/yy");
        JFormattedTextField jdate      = new JFormattedTextField(dateFormat);
        jdate.setMaximumSize(new Dimension(Integer.MAX_VALUE, jdate.getPreferredSize().height));
        JLabel  start = new JLabel("Start");
        JButton enter = new JButton("Enter");
        
        JLabel calc = new JLabel("Calculator");
        calc.setFont(new Font(calc.getFont().toString(), Font.BOLD, 20));
        calc.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel desc = new JLabel("<html>" + description + "</html>");
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel output = new JLabel("Total GDDs:");
        output.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel dataOut = new JLabel("Please enter a date in the box above, in the format dd/mm/yy");
        
        JPanel title      = new JPanel();
        JPanel descriptor = new JPanel();
        descriptor.setLayout(new BoxLayout(descriptor, BoxLayout.PAGE_AXIS));
        descriptor.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel inputInner = new JPanel();
        inputInner.setLayout(new GridLayout(1, 2));
        inputInner.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel inputOuter = new JPanel();
        inputOuter.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel out = new JPanel();
        out.setAlignmentX(Component.LEFT_ALIGNMENT);
        out.setLayout(new BoxLayout(out, BoxLayout.PAGE_AXIS));
        JPanel buttons = createButtonsPanel();
        
        title.add(calc);
        descriptor.add(desc);
        inputInner.add(jdate);
        inputInner.add(enter);
        inputOuter.add(start);
        inputOuter.add(inputInner);
        out.add(output);
        out.add(dataOut);
        
        addOnClick(forecast, showForecast);
        addOnClick(enter, () -> {
            dataOut.setFont(new Font(dataOut.getFont().toString(), Font.PLAIN, 12));
            dataOut.setForeground(Color.BLACK);
            Date date = (Date) jdate.getValue();
            if (date != null) {
                dataOut.setText("Please wait, contacting server");
                try {
                    dataOut.setText(Integer.toString((int) bizeeAPI.gddSince(loc, 10, date)));
                    dataOut.setFont(new Font(dataOut.getFont().toString(), Font.BOLD, 18));
                } catch (IllegalArgumentException e) {
                    dataOut.setText("The date you have entered is invalid, please use the format dd/mm/yy");
                    dataOut.setForeground(Color.red);
                }
            } else {
                dataOut.setText("The date you have entered is invalid, please use the format dd/mm/yy");
                dataOut.setForeground(Color.red);
            }
        });
        
        main.add(title);
        main.add(descriptor);
        main.add(inputOuter);
        main.add(out);
        this.add(main);
        this.add(buttons, BorderLayout.SOUTH);
        buttons.setMaximumSize(new Dimension(700, 100));
    }
    
    private static JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 1));
        
        bottomButtons.add(forecast);
        
        return bottomButtons;
    }
    
    private static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e -> {
            btnAction.run();
        });
    }
    
}
