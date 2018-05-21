package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GDDsView extends JPanel {

    public static final String CALC_CARD = "Calculator";
    public static final String FORECAST_CARD = "Forecast";
    
    //Create the overall layout for the GDD tab
    public GDDsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel cards = new JPanel();

        var layout = new CardLayout();
        cards.setLayout(layout);
        
        //Allow switching between the calculator and forecast panel while in the GDD view
        cards.add(new CalcPanel(
                        () -> layout.show(cards, FORECAST_CARD)),
                CALC_CARD);
        cards.add(new ForecastPanel(
                        () -> layout.show(cards, CALC_CARD)),
                FORECAST_CARD);
        layout.show(cards, CALC_CARD);
        
        //Create a title for the tab so users know what they are on
        JLabel g = new JLabel();
        g.setFont(new Font(g.getFont().toString(), Font.BOLD, 20));
        g.setText("Growing Degree Days");
        g.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(g);
        this.add(cards);
    }
}
