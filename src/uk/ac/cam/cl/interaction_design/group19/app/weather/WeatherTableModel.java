package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import uk.ac.cam.cl.interaction_design.group19.app.api.HourlyData;
import uk.ac.cam.cl.interaction_design.group19.app.api.WeatherData;

public class WeatherTableModel extends AbstractTableModel {

    private List<HourlyData> data;

    public WeatherTableModel(List<HourlyData> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex);
    }

    @Override
    public Class getColumnClass(int col) {
        return WeatherData.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
