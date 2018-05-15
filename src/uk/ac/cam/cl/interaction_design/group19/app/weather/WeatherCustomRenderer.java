package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public abstract class WeatherCustomRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        switch(column) {
            case 0:
                System.err.println("The 0th column of the renderer should be overridden.");
                System.exit(1);
            case 2:
            case 4:

        }
        return this;
    }
}
