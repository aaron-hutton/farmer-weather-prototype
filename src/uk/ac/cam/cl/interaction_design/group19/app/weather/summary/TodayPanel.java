package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

/**
 * Panel responsible for Today view,
 * a specialisation to DayPanel that uses today as the time to query for weather data
 */
public class TodayPanel extends DayPanel {
    public TodayPanel(Function<LocalDateTime, WeatherData> getDayDataFor,
                      Function<LocalDateTime, List<List<WeatherData>>> getHourlyDataFor) {
        super(() -> getDayDataFor.apply(LocalDateTime.now()),
              () -> getHourlyDataFor.apply(LocalDateTime.now()));
    }
}
