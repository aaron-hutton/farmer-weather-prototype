package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import uk.ac.cam.cl.interaction_design.group19.app.api.DayData;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;

public class TodayPanel extends DayPanel {
    public TodayPanel(Function<LocalDateTime, DayData> getDayDataFor,
                      Function<LocalDateTime, List<List<HourlyData>>> getHourlyDataFor) {
        super(() -> getDayDataFor.apply(LocalDateTime.now()),
              () -> getHourlyDataFor.apply(LocalDateTime.now()));
    }
}
