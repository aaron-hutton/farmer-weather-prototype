package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.CardLayout;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import javax.swing.JPanel;

public class DayPanel extends JPanel {
    public static final String SUMMARY_CARD   = "Summary";
    public static final String FULL_INFO_CARD = "FullInfo";
    public static final String HOURLY_CARD    = "Hourly";
    
    public DayPanel(Supplier<LocalDateTime> dateSupplier) {
        var layout = new CardLayout();
        this.setLayout(layout);
        
        this.add(new SummaryPanel(
                         dateSupplier,
                         () -> layout.show(this, FULL_INFO_CARD),
                         () -> layout.show(this, HOURLY_CARD)),
                 SUMMARY_CARD);
        this.add(new FullInfoPanel(
                         dateSupplier,
                         () -> layout.show(this, SUMMARY_CARD)),
                 FULL_INFO_CARD);
        this.add(new HourlyPanel(
                         dateSupplier,
                         () -> layout.show(this, SUMMARY_CARD)),
                 HOURLY_CARD);
        layout.show(this, SUMMARY_CARD);
    }
}
