package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class TodayPanel extends DayPanel {
    public TodayPanel(Function<LocalDateTime, WeatherData> getDayDataFor,
                      Function<LocalDateTime, List<List<WeatherData>>> getHourlyDataFor) {
        super(() -> getDayDataFor.apply(LocalDateTime.now()),
              () -> getHourlyDataFor.apply(LocalDateTime.now()));
    }
}
