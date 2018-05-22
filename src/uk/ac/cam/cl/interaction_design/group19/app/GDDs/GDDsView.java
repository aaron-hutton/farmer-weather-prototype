package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.function.Supplier;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;

public class GDDsView extends JPanel implements Updatable {

    public static final String CALC_CARD = "Calculator";
    public static final String FORECAST_CARD = "Forecast";
    Supplier<ArrayList<Double>> forecastSupplier;
    
    //Create the overall layout for the GDD tab
    public GDDsView(Supplier<ArrayList<Double>> supplier) {
        forecastSupplier = supplier;
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel cards = new JPanel();

        var layout = new CardLayout();
        cards.setLayout(layout);
        
        //Allow switching between the calculator and forecast panel while in the GDD view
        cards.add(new CalcPanel(
                        () -> layout.show(cards, FORECAST_CARD)),
                CALC_CARD);
        cards.add(new ForecastPanel(
                        () -> layout.show(cards, CALC_CARD), forecastSupplier.get()),
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
    
    //Update forecast data (when supplier has new data)
    public void update() {
        ForecastPanel.update(forecastSupplier.get());
    }
}
