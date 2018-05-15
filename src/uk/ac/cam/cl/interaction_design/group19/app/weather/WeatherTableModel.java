package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.table.AbstractTableModel;
import uk.ac.cam.cl.interaction_design.group19.app.WeatherData;

public class WeatherTableModel extends AbstractTableModel {

    private WeatherData[] data;
    public WeatherTableModel(WeatherData[] data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
