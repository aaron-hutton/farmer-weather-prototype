package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import java.util.function.Supplier;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;

/**
 * The JPanel instance displays the weekly view of the weather
 */
public class WeeklyPanel extends JPanel implements Updatable {
    
    public static final int NUM_DAYS_TO_SHOW = 5;
    
    private final Supplier<List<WeatherData>> dataSupplier;
    private WeeklyTable table;
    
    public WeeklyPanel(Supplier<List<WeatherData>> dataSupplier) {
        this.dataSupplier = dataSupplier;
        
        update();
    }
    
    @Override
    public void update() {
        this.removeAll();
        List<WeatherData> data = dataSupplier.get();
    
        // Display an error if there is no data to display
        if (data == null || data.size() == 0) {
            JLabel failLabel = new JLabel("There is no data to display.");
            this.add(failLabel);
        } else {
            if(table == null) {
                table = new WeeklyTable(data);
            } else {
                table.updateTable(data);
            }
            this.add(table);
        }
    }
}
