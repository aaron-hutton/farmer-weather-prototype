package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;

public class GDDsView extends JPanel implements Updatable {
    
    public static final String CALC_CARD     = "Calculator";
    public static final String FORECAST_CARD = "Forecast";
    
    public GDDsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel cards = new JPanel();

        var layout = new CardLayout();
        cards.setLayout(layout);

        cards.add(new CalcPanel(
                        () -> layout.show(cards, FORECAST_CARD)),
                CALC_CARD);
        cards.add(new ForecastPanel(
                        () -> layout.show(cards, CALC_CARD)),
                FORECAST_CARD);
        layout.show(cards, CALC_CARD);



        JLabel g = new JLabel();
        g.setFont(new Font(g.getFont().toString(), Font.BOLD, 18));
        g.setAlignmentX(Component.LEFT_ALIGNMENT);
        g.setText("Growing Degree Days");
        cards.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(g);
        this.add(cards);
    }
    
    @Override
    public void update() {
        System.err.println("update of the gdds view not implemented");
    }
}
