package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GDDsView extends JPanel {
    
    public static final String CALC_CARD     = "Calculator";
    public static final String FORECAST_CARD = "Forecast";
    
    public GDDsView() {
        JLabel gdds = new JLabel("Growing Degree Days");
        this.add(gdds);
        
        var layout = new CardLayout();
        this.setLayout(layout);
        
        this.add(new CalcPanel(
                         () -> layout.show(this, FORECAST_CARD)),
                 CALC_CARD);
        this.add(new ForecastPanel(
                         () -> layout.show(this, CALC_CARD)),
                 FORECAST_CARD);
        layout.show(this, CALC_CARD);
    }
}
