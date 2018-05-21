package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.Location;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeAPI;
import uk.ac.cam.cl.interaction_design.group19.app.api.MetOfficeLocation;

public class WeeklyPanel extends JPanel {
    private static final int FALLBACK_LOCATION_ID = 0;
    
    public WeeklyPanel() {
        List<MetOfficeLocation> available_locations = MetOfficeAPI.hourlyLocationList();
        Location                homerton            = Location.fromAddress("Homerton College, Cambridge");
        MetOfficeLocation       closest             = homerton.closest(available_locations);
        int                     nearest_id          = closest != null ? closest.id : FALLBACK_LOCATION_ID;
        List<List<HourlyData>>  data                = MetOfficeAPI.fiveDayForecast(nearest_id);
        if (data == null || data.size() == 0) {
            JLabel failLabel = new JLabel("There is no data to display.");
            this.add(failLabel);
        } else {
            WeeklyTable table = new WeeklyTable(data.get(0));
            this.add(table);
        }
    }
}
