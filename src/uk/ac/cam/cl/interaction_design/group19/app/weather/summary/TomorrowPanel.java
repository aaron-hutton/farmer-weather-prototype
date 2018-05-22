package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import uk.ac.cam.cl.interaction_design.group19.app.util.WeatherData;

/**
 * Panel responsible for Tomorrow view,
 * a specialisation to DayPanel that uses one day after today as the time to query for weather data
 */
public class TomorrowPanel extends DayPanel {
    public TomorrowPanel(Function<LocalDateTime, WeatherData> getDayDataFor,
                         Function<LocalDateTime, List<List<WeatherData>>> getHourlyDataFor) {
        super(() -> getDayDataFor.apply(LocalDateTime.now().plusDays(1)),
              () -> getHourlyDataFor.apply(LocalDateTime.now().plusDays(1)));
    }
}
