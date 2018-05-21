package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ForecastPanel extends JPanel {
    
    private static final JButton           calculator  = new JButton("Calculator");
    private static final int               loc         = 0;
    private static       ArrayList<Double> data        = null;
    private static       String[][]        dataTable   = null;
    private static final String[]          columnNames = {"Date", "GDDs"};
    private static final String[]          days        = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
    
    public ForecastPanel(Runnable showCalc) {
        
        this.setLayout(new BorderLayout());
        JPanel t = new JPanel();
        t.setLayout(new BoxLayout(t, BoxLayout.Y_AXIS));
        t.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        if (data == null) {
            getForecast();
        }
        
        JLabel forecast = new JLabel("Forecast");
        forecast.setFont(new Font(forecast.getFont().toString(), Font.BOLD, 16));
        forecast.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTable table = new JTable(dataTable, columnNames);
        table.setSize(300, 300);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setFont(new Font(table.getFont().toString(), Font.PLAIN, 15));
        
        JPanel buttons = createButtonsPanel();
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        addOnClick(calculator, showCalc);
        
        t.add(forecast);
        t.add(table);
        this.add(t);
        this.add(buttons, BorderLayout.SOUTH);
        buttons.setMaximumSize(new Dimension(700, 100));
    }
    
    private void getForecast() {
        Calendar calendar = Calendar.getInstance();
        int      day      = calendar.get(Calendar.DAY_OF_WEEK);
        
        data = bizeeAPI.gddForecast(3840, 10);
        
        dataTable = new String[data.size()][2];
        int i = 0;
        for (Double d : data) {
            String[] it = new String[2];
            it[0] = days[(day - 1 % 7)];
            
            BigDecimal bd = new BigDecimal(d);
            bd = bd.round(new MathContext(3));
            it[1] = bd.toString();
            dataTable[i] = it;
            i++;
            day++;
        }
    }
    
    
    private JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 1));
        
        bottomButtons.add(calculator);
        
        return bottomButtons;
    }
    
    private static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e -> {
            btnAction.run();
        });
    }
}
