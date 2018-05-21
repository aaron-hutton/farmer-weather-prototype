package uk.ac.cam.cl.interaction_design.group19.app.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import uk.ac.cam.cl.interaction_design.group19.app.weather.WeatherType;

public class Icons {
    //TODO: move these mappings to a file
    private static final Map<IconType, String> iconNames = Map.ofEntries(
            new SimpleEntry<>(IconType.ARROW_DOWN, "arrow_down.png"),
            new SimpleEntry<>(IconType.ARROW_UP, "arrow_up.png"),
            new SimpleEntry<>(IconType.ARROW_RIGHT, "arrow_right.png"),
            new SimpleEntry<>(IconType.ARROW_LEFT, "arrow_left.png"),
            new SimpleEntry<>(IconType.ARROW_UP_RIGHT, "arrow_up_right.png"),
            new SimpleEntry<>(IconType.ARROW_UP_LEFT, "arrow_up_left.png"),
            new SimpleEntry<>(IconType.ARROW_DOWN_RIGHT, "arrow_down_right.png"),
            new SimpleEntry<>(IconType.ARROW_DOWN_LEFT, "arrow_down_left.png"),
            new SimpleEntry<>(IconType.SNOWFLAKE, "frost.png"),
            new SimpleEntry<>(IconType.RAINDROP, "slice_9_9.png"),
            new SimpleEntry<>(IconType.CLEAR_NIGHT, "slice_1_5.png"),
            new SimpleEntry<>(IconType.SUNNY_DAY, "slice_1_1.png"),
            new SimpleEntry<>(IconType.PARTLY_CLOUDY_NIGHT, "slice_7_1.png"),
            new SimpleEntry<>(IconType.PARTLY_CLOUDY_DAY, "slice_1_3.png"),
            new SimpleEntry<>(IconType.HORIZONTAL_BARS, "slice_9_1.png"),
            new SimpleEntry<>(IconType.CLOUDY, "cloudy.png"),
            new SimpleEntry<>(IconType.OVERCAST, "overcast.png"),
            new SimpleEntry<>(IconType.LIGHT_RAIN_SHOWER_NIGHT, "light_rain_night.png"),
            new SimpleEntry<>(IconType.LIGHT_RAIN_SHOWER_DAY, "slice_1_7.png"),
            new SimpleEntry<>(IconType.DRIZZLE, "slice_3_7.png"),
            new SimpleEntry<>(IconType.LIGHT_RAIN, "slice_3_9.png"),
            new SimpleEntry<>(IconType.HEAVY_RAIN, "slice_5_9.png"),
            new SimpleEntry<>(IconType.SLEET, "slice_3_3.png"),
            new SimpleEntry<>(IconType.HAIL, "slice_3_1.png"),
            new SimpleEntry<>(IconType.LIGHT_SNOW_SHOWER_NIGHT, "light_snow_night.png"),
            new SimpleEntry<>(IconType.LIGHT_SNOW_SHOWER_DAY, "slice_5_3.png"),
            new SimpleEntry<>(IconType.LIGHT_SNOW, "slice_7_5.png"),
            new SimpleEntry<>(IconType.HEAVY_SNOW, "slice_7_9.png"),
            new SimpleEntry<>(IconType.THUNDER, "slice_9_7.png"),
            new SimpleEntry<>(IconType.QUESTION_MARK, "question.png")
    );
    
    private static final Map<WeatherType, IconType> weatherToIconMap = Map.ofEntries(
            new SimpleEntry<>(WeatherType.CLEAR_NIGHT, IconType.CLEAR_NIGHT),
            new SimpleEntry<>(WeatherType.SUNNY_DAY, IconType.SUNNY_DAY),
            new SimpleEntry<>(WeatherType.PARTLY_CLOUDY_NIGHT, IconType.PARTLY_CLOUDY_NIGHT),
            new SimpleEntry<>(WeatherType.PARTLY_CLOUDY_DAY, IconType.PARTLY_CLOUDY_DAY),
            new SimpleEntry<>(WeatherType.MIST, IconType.HORIZONTAL_BARS),
            new SimpleEntry<>(WeatherType.FOG, IconType.HORIZONTAL_BARS),
            new SimpleEntry<>(WeatherType.CLOUDY, IconType.CLOUDY),
            new SimpleEntry<>(WeatherType.OVERCAST, IconType.OVERCAST),
            new SimpleEntry<>(WeatherType.LIGHT_RAIN_SHOWER_NIGHT, IconType.LIGHT_RAIN_SHOWER_NIGHT),
            new SimpleEntry<>(WeatherType.LIGHT_RAIN_SHOWER_DAY, IconType.LIGHT_RAIN_SHOWER_DAY),
            new SimpleEntry<>(WeatherType.DRIZZLE, IconType.DRIZZLE),
            new SimpleEntry<>(WeatherType.LIGHT_RAIN, IconType.LIGHT_RAIN),
            new SimpleEntry<>(WeatherType.HEAVY_RAIN_SHOWER_NIGHT, IconType.HEAVY_RAIN),
            new SimpleEntry<>(WeatherType.HEAVY_RAIN_SHOWER_DAY, IconType.HEAVY_RAIN),
            new SimpleEntry<>(WeatherType.HEAVY_RAIN, IconType.HEAVY_RAIN),
            new SimpleEntry<>(WeatherType.SLEET_SHOWER_NIGHT, IconType.SLEET),
            new SimpleEntry<>(WeatherType.SLEET_SHOWER_DAY, IconType.SLEET),
            new SimpleEntry<>(WeatherType.SLEET, IconType.SLEET),
            new SimpleEntry<>(WeatherType.HAIL_SHOWER_NIGHT, IconType.HAIL),
            new SimpleEntry<>(WeatherType.HAIL_SHOWER_DAY, IconType.HAIL),
            new SimpleEntry<>(WeatherType.HAIL, IconType.HAIL),
            new SimpleEntry<>(WeatherType.LIGHT_SNOW_SHOWER_NIGHT, IconType.LIGHT_SNOW_SHOWER_NIGHT),
            new SimpleEntry<>(WeatherType.LIGHT_SNOW_SHOWER_DAY, IconType.LIGHT_SNOW_SHOWER_DAY),
            new SimpleEntry<>(WeatherType.LIGHT_SNOW, IconType.LIGHT_SNOW),
            new SimpleEntry<>(WeatherType.HEAVY_SNOW_SHOWER_NIGHT, IconType.HEAVY_SNOW),
            new SimpleEntry<>(WeatherType.HEAVY_SNOW_SHOWER_DAY, IconType.HEAVY_SNOW),
            new SimpleEntry<>(WeatherType.HEAVY_SNOW, IconType.HEAVY_SNOW),
            new SimpleEntry<>(WeatherType.THUNDER_SHOWER_NIGHT, IconType.THUNDER),
            new SimpleEntry<>(WeatherType.THUNDER_SHOWER_DAY, IconType.THUNDER),
            new SimpleEntry<>(WeatherType.THUNDER, IconType.THUNDER)
    );
    
    private static final Map<WindDir, IconType> dirToIconMap = Map.of(
            WindDir.N, IconType.ARROW_UP,
            WindDir.NE, IconType.ARROW_UP_RIGHT,
            WindDir.E, IconType.ARROW_RIGHT,
            WindDir.SE, IconType.ARROW_DOWN_RIGHT,
            WindDir.S, IconType.ARROW_DOWN,
            WindDir.SW, IconType.ARROW_DOWN_LEFT,
            WindDir.W, IconType.ARROW_LEFT,
            WindDir.NW, IconType.ARROW_UP_LEFT
    );
    
    public static final  String   ICONS_PATH        = "resources/icons/";
    private static final IconType DEFAULT_ICON_TYPE = IconType.QUESTION_MARK;
    
    // keep a buffer of icons which we check before loading from the hard drive
    static Map<IconType, BufferedImage> loadedIcons = new HashMap<>();
    
    public static BufferedImage getSizedWidthIcon(WindDir dir, int width) {
        if (dir == null) {
            return getSizedWidthIcon((IconType) null, width);
        } else {
            return getSizedWidthIcon(dirToIconMap.getOrDefault(dir, null), width);
        }
    }
    
    public static BufferedImage getSizedWidthIcon(WeatherType weather, int width) {
        if (weather == null) {
            return getSizedWidthIcon((IconType) null, width);
        } else {
            return getSizedWidthIcon(weatherToIconMap.getOrDefault(weather, null), width);
        }
    }
    
    public static BufferedImage getSizedWidthIcon(IconType icon, int width) {
        BufferedImage temp = getIcon(icon);
        Image toRet =
                temp.getScaledInstance(width, width * temp.getHeight() / temp.getWidth(), Image.SCALE_SMOOTH);
        return toBufferedImage(toRet);
    }
    
    public static BufferedImage getSizedHeightIcon(WindDir dir, int height) {
        if (dir == null) {
            return getSizedHeightIcon((IconType) null, height);
        } else {
            return getSizedHeightIcon(dirToIconMap.getOrDefault(dir, null), height);
        }
    }
    
    public static BufferedImage getSizedHeightIcon(WeatherType weather, int height) {
        if (weather == null) {
            return getSizedHeightIcon((IconType) null, height);
        } else {
            return getSizedHeightIcon(weatherToIconMap.getOrDefault(weather, null), height);
        }
    }
    
    public static BufferedImage getSizedHeightIcon(IconType icon, int height) {
        BufferedImage temp = getIcon(icon);
        Image toRet =
                temp.getScaledInstance(height * temp.getWidth() / temp.getHeight(), height, Image.SCALE_SMOOTH);
        return toBufferedImage(toRet);
    }
    
    public static BufferedImage getIcon(IconType icon) {
        if (loadedIcons.containsKey(icon)) {
            return loadedIcons.get(icon);
        } else {
            var    defaultIconName = iconNames.get(DEFAULT_ICON_TYPE);
            String iconName;
            if (icon != null) {
                iconName = iconNames.getOrDefault(icon, defaultIconName);
            } else {
                iconName = defaultIconName;
            }
            try {
                BufferedImage iconImage = ImageIO.read(new File(ICONS_PATH + iconName));
                loadedIcons.put(icon, iconImage);
                return iconImage;
            } catch (IOException e) {
                System.err.println("Couldn't open icon file");
                e.printStackTrace();
                return null;
            }
        }
    }
    
    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        
        // Return the buffered image
        return bimage;
    }
}
