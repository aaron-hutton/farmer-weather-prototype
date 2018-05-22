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
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.degreedays.api.RequestFailureException;

public class CalcPanel extends JPanel {

    private static final String description = "Calculate total growing degree days between start date (input format dd/mm/yy) and today";
    private static final JButton forecast = new JButton("Forecast");
    private static final int loc = 0;


    public CalcPanel(Runnable showForecast) {

        this.setLayout(new BorderLayout());
        
        //Create new panel for all items (except buttons)
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        
        //Take date input in the form dd/mm/yy
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        JFormattedTextField jdate = new JFormattedTextField(dateFormat);
        //Make the text box one line in height
        jdate.setFont(new Font(jdate.getFont().toString(), Font.PLAIN, 16));
        jdate.setMaximumSize(new Dimension(Integer.MAX_VALUE, jdate.getPreferredSize().height));
        JLabel start = new JLabel("Start");
        start.setFont(new Font(start.getFont().toString(), Font.PLAIN, 16));
        //Create new button for text entry
        JButton enter = new JButton("Enter");
        enter.setFont(new Font(enter.getFont().toString(), Font.PLAIN, 16));
        
        //Make calculator title
        JLabel calc = new JLabel("Calculator");
        calc.setFont(new Font(calc.getFont().toString(), Font.BOLD, 20));
        calc.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Make field for description
        JTextPane desc = new JTextPane();
        desc.setText(description);
        desc.setEditable(false);
        desc.setFont(new Font(desc.getFont().toString(), Font.PLAIN, 16));
        desc.setOpaque(false);
        
        //Centre description text
        StyledDocument dataDesc = desc.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        dataDesc.setParagraphAttributes(0, dataDesc.getLength(), center, false);
        
        //Label text
        JLabel output = new JLabel("Total GDDs:");
        output.setFont(new Font(output.getFont().toString(), Font.PLAIN, 16));
        output.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        //Create output field and put preliminary message in
        JTextPane dataOut = new JTextPane();
        dataOut.setText("Please enter a date in the box above");
        dataOut.setEditable(false);
        dataOut.setOpaque(false);
        dataOut.setFont(new Font(dataOut.getFont().toString(), Font.PLAIN, 16));
        
        //Centre output text
        StyledDocument dataDoc = dataOut.getStyledDocument();
        dataDoc.setParagraphAttributes(0, dataDoc.getLength(), center, false);
        
        //Create panels for all elements
        JPanel title = new JPanel();
        JPanel descriptor = new JPanel();
        descriptor.setLayout(new BoxLayout(descriptor, BoxLayout.PAGE_AXIS));
        //Make sure description doesn't take up more space than needed
        descriptor.setMaximumSize(new Dimension(1000, 120));
        JPanel inputInner = new JPanel();
        //Make it so that the button and text field are next to each other
        inputInner.setLayout(new GridLayout(1, 2));
        JPanel inputOuter = new JPanel();
        JPanel gddLabel = new JPanel();
        JPanel out = new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.PAGE_AXIS));
        JPanel buttons = createButtonsPanel();

        title.add(calc);
        descriptor.add(desc);
        inputInner.add(jdate);
        inputInner.add(enter);
        inputOuter.add(start);
        inputOuter.add(inputInner);
        gddLabel.add(output);
        out.add(dataOut);
        
        //Create api object
        bizeeAPI api = new bizeeAPI();
        
        //Go to forecast panel on click
        addOnClick(forecast, showForecast);
        
        //Get the GDDs since the start date on click
        addOnClick(enter, () -> {
            //Reset text size and colour after error message or data returned
            dataOut.setFont(new Font(dataOut.getFont().toString(), Font.PLAIN, 16));
            dataOut.setForeground(Color.BLACK);
            //Get date from JFormattedTextField for the api call
            Date date = (Date) jdate.getValue();
            if(date != null) {
                //Displayed if the call to the api doesn't return quickly
                dataOut.setText("Please wait, contacting server");
                try {
                    //Display (rounded) GDDs since start date
                    //TODO: real location
                    dataOut.setText(Integer.toString((int) api.gddSince(loc, 10, date)));
                    dataOut.setFont(new Font(dataOut.getFont().toString(), Font.BOLD, 70));
                } catch (IllegalArgumentException | RequestFailureException e){
                    //Tell user they've entered an invalid date (e.g. in the future)
                    dataOut.setText("The date you have entered is invalid (in the future or too far in the past)");
                    //Make text red for error message
                    dataOut.setForeground(Color.red);
                }
            } else {
                //Tell user they've entered the date in the wrong format, or not a date
                dataOut.setText("The date you have entered is invalid, please use the format dd/mm/yy");
                dataOut.setForeground(Color.red);
            }
        });
        
        //Add all objects to main panel, add main panel and buttons to forecast panel
        main.add(title);
        main.add(descriptor);
        main.add(inputOuter);
        main.add(gddLabel);
        main.add(out);
        this.add(main);
        this.add(buttons, BorderLayout.SOUTH);
        
        //Make sure button doesn't take up too much space
        buttons.setMaximumSize(new Dimension(700, 100));
    }
    
    //Create forecast button
    private static JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 1));

        bottomButtons.add(forecast);

        return bottomButtons;
    }
    
    //Add action on click
    private static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e -> {
            btnAction.run();
        });
    }

}
