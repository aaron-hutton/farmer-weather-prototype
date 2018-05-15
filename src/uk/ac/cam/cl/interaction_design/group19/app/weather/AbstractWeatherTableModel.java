package uk.ac.cam.cl.interaction_design.group19.app.weather;

import javax.swing.table.AbstractTableModel;

public abstract class AbstractWeatherTableModel extends AbstractTableModel {

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
