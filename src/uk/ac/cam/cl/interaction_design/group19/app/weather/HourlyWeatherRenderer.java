package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Component;
import javax.swing.JTable;

public class HourlyWeatherRenderer extends WeatherCustomRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if(column == 0) {

            this.setText("");
            return this;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
