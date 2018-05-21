package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;

public class ForecastPanel extends JPanel {

    private static final JButton calculator = new JButton("Calculator");
    private static ArrayList<Double> data = null;
    private static String[][] dataTable = null;
    private static final String[] columnNames = {"Date", "GDDs"};
    private static final String[] days = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
    public static int MINIMUM_ROW_HEIGHT = 70;

    public ForecastPanel(Runnable showCalc) {
        
        //Set entire panel layout
        this.setLayout(new BorderLayout());
        JPanel t = new JPanel();
        t.setLayout(new BoxLayout(t, BoxLayout.Y_AXIS));
        t.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Create api object
        bizeeAPI api = new bizeeAPI();
        
        //Get new data if the location has been updated or the app has just been opened
        if(data == null) {
            //TODO: real location
            getForecast(api, 3840);
        }
        
        //Create a forecast title
        JLabel forecast = new JLabel("Forecast");
        forecast.setFont(new Font(forecast.getFont().toString(), Font.BOLD, 20));
        forecast.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Create a button to take you to the calculator screen
        JPanel buttons = createButtonsPanel();
        
        //Make the button switch pane on click
        addOnClick(calculator, showCalc);
        
        t.add(forecast);
        t.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //If the server is down
        if(data == null) {
            
            //Create an error message
            JTextPane server = new JTextPane();
            server.setText("We are currently having server problems, please try again later");
            //Set text colour to red
            server.setForeground(Color.red);
            server.setFont(new Font(server.getFont().toString(), Font.PLAIN, 18));
            server.setEditable(false);
            server.setOpaque(true);
            server.setBackground(MainWindow.BACKGROUND_COLOR);
            
            //Centre text
            StyledDocument doc = server.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            
            //Add error message to view
            t.add(server);
        } else {
            
            //Create a table with the GDDs for the next five days
            JTable table = new JTable(dataTable, columnNames);
            table.setSize(300, 300);
            table.setAlignmentX(Component.CENTER_ALIGNMENT);
            table.setFont(new Font(table.getFont().toString(), Font.PLAIN, 18));
            
            //Table stylistic decisions
            table.setOpaque(false);
            table.setBorder(BorderFactory.createEmptyBorder());
            table.setTableHeader(null);
    
            table.setRowHeight(Math.min((MainWindow.SCREEN_HEIGHT - 80) / 5, MINIMUM_ROW_HEIGHT));
            table.setMaximumSize(new Dimension(MainWindow.SCREEN_WIDTH-60, table.getPreferredSize().height));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setBackground(MainWindow.BACKGROUND_COLOR);
    
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
            table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
            
            //Add table to view
            t.add(table);
        }
        
        //Add table pane and title to view
        this.add(t);
        //Add calculator button to bottom of screen
        this.add(buttons, BorderLayout.SOUTH);
        buttons.setMaximumSize(new Dimension(700, 100));
    }
    
    //Get the GDDs forecast for the next five days
    private void getForecast(bizeeAPI api, int location) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        
        data = api.gddForecast(location, 10);
        
        //Put the data into a table writable format and add day
        dataTable = new String[data.size()][2];
        int i =0;
        for(Double d : data) {
            String[] it = new String[2];
            it[0] = days[(day-1 % 7)];
            
            //Round to 3sf
            BigDecimal bd = new BigDecimal(d);
            bd = bd.round(new MathContext(3));
            it[1] = bd.toString();
            dataTable[i] = it;
            i++;
            day++;
        }
    }
    
    //Add action on button click
    private static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e -> {
            btnAction.run();
        });
    }

    //Create calculator button and layout
    private JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 1));

        bottomButtons.add(calculator);

        return bottomButtons;
    }

}
