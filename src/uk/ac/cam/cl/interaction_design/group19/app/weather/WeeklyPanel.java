package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import java.util.function.Supplier;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;

public class WeeklyPanel extends JPanel implements Updatable {
    private final Supplier<List<List<WeatherData>>> dataSupplier;
    
    public WeeklyPanel(Supplier<List<List<WeatherData>>> dataSupplier) {
        this.dataSupplier = dataSupplier;
        
        List<List<WeatherData>> data = dataSupplier.get();
        if (data == null || data.size() == 0) {
            JLabel failLabel = new JLabel("There is no data to display.");
            this.add(failLabel);
        } else {
            WeeklyTable table = new WeeklyTable(data.get(0));
            this.add(table);
        }
    }
    
    @Override
    public void update() {
        System.err.println("update of the weekly panel not implemented");
    }
}
