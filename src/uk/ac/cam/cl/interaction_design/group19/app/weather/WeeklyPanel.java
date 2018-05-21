package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.MainWindow;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class WeeklyPanel extends JPanel {
    public WeeklyPanel() {
        List<MetOfficeLocation> available_locations = new MetOfficeAPI().hourlyLocationList();
        Location                homerton            = Location.fromAddress("Homerton College, Cambridge");
        int                     nearest_id          = homerton.closest(available_locations).id;
        
        List<List<HourlyData>> data = (new MetOfficeAPI()).fiveDayForecast(nearest_id);
        if(data == null || data.size() == 0) {
            JLabel failLabel = new JLabel("There is no data to display.");
            this.add(failLabel);
        } else {
            WeeklyTable table = new WeeklyTable(data.get(0));
            this.add(table);
        }
    }
}
