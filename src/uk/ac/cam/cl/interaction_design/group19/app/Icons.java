package uk.ac.cam.cl.interaction_design.group19.app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherType;

public class Icons {

    public static BufferedImage getSizedIcon(WeatherType theWeather, int width) {
        BufferedImage temp = getIcon(theWeather);
        Image toRet = temp.getScaledInstance(width, width * temp.getHeight() / temp.getWidth(), Image.SCALE_SMOOTH);
        return toBufferedImage(toRet);
    }

    public static BufferedImage getIcon(WeatherType theWeather) {
        String icon = "question.png";
        switch (theWeather) {
            case RAINDROP:
                icon = "slice_9_9.png";
                break;
            case NOT_AVAILABLE:
                break;
            case NOT_USED:
                break;
            case CLEAR_NIGHT:
                icon = "slice_1_5.png";
                break;
            case SUNNY_DAY:
                icon = "slice_1_1.png";
                break;
            case PARTLY_CLOUDY_NIGHT:
                icon = "slice_7_1.png";
                break;
            case PARTLY_CLOUDY_DAY:
                icon = "slice_1_3.png";
                break;
            case MIST:
                icon = "slice_9_1.png";
                break;
            case FOG:
                icon = "slice_9_1.png";
                break;
            case CLOUDY:
                icon = "cloudy.png";
                break;
            case OVERCAST:
                icon = "overcast.png";
                break;
            case LIGHT_RAIN_SHOWER_NIGHT:
                icon = "light_rain_night.png";
                break;
            case LIGHT_RAIN_SHOWER_DAY:
                icon = "slice_1_7.png";
                break;
            case DRIZZLE:
                icon = "slice_3_7.png";
                break;
            case LIGHT_RAIN:
                icon = "slice_3_9.png";
                break;
            case HEAVY_RAIN_SHOWER_NIGHT:
                icon = "slice_5_9.png";
                break;
            case HEAVY_RAIN_SHOWER_DAY:
                icon = "slice_5_9.png";
                break;
            case HEAVY_RAIN:
                icon = "slice_5_9.png";
                break;
            case SLEET_SHOWER_NIGHT:
                icon = "slice_3_3.png";
                break;
            case SLEET_SHOWER_DAY:
                icon = "slice_3_3.png";
                break;
            case SLEET:
                icon = "slice_3_3.png";
                break;
            case HAIL_SHOWER_NIGHT:
                icon = "slice_3_1.png";
                break;
            case HAIL_SHOWER_DAY:
                icon = "slice_3_1.png";
                break;
            case HAIL:
                icon = "slice_3_1.png";
                break;
            case LIGHT_SNOW_SHOWER_NIGHT:
                icon = "light_snow_night.png";
                break;
            case LIGHT_SNOW_SHOWER_DAY:
                icon = "slice_5_3.png";
                break;
            case LIGHT_SNOW:
                icon = "slice_7_5.png";
                break;
            case HEAVY_SNOW_SHOWER_NIGHT:
                icon = "slice_7_9.png";
                break;
            case HEAVY_SNOW_SHOWER_DAY:
                icon = "slice_7_9.png";
                break;
            case HEAVY_SNOW:
                icon = "slice_7_9.png";
                break;
            case THUNDER_SHOWER_NIGHT:
                icon = "slice_9_7.png";
                break;
            case THUNDER_SHOWER_DAY:
                icon = "slice_9_7.png";
                break;
            case THUNDER:
                icon = "slice_9_7.png";
                break;
        }
        try {
            return ImageIO.read(new File("resources/icons/" + icon));
        } catch (IOException e) {
            System.err.println("Couldn't open icon file");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
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
