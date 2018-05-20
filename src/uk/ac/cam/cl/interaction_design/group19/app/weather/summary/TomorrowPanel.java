package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.time.LocalDateTime;

public class TomorrowPanel extends DayPanel {
    public TomorrowPanel() {
        super(() -> LocalDateTime.now().plusDays(1));
    }
}
