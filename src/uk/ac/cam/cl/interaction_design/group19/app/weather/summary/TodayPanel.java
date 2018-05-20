package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.time.LocalDateTime;

public class TodayPanel extends DayPanel {
    public TodayPanel() {
        super(() -> LocalDateTime.now());
    }
}
