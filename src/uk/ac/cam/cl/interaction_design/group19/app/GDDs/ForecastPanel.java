package uk.ac.cam.cl.interaction_design.group19.app.GDDs;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import uk.ac.cam.cl.interaction_design.group19.app.MetOfficeAPI;

public class ForecastPanel extends JPanel {

    private static final JButton calculator = new JButton("Forecast");
    private static final int loc = 0;
    private static ArrayList<Pair<String, Double>> data = null;
    private static String[][] dataTable = null;
    private static final String[] columnNames = {"Date", "GDDs"};

    public ForecastPanel(Runnable showCalc) {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        if(data == null) getForecast();

        JLabel forecast = new JLabel("Forecast");

        JTable table = new JTable(dataTable, columnNames);

        JPanel buttons = createButtonsPanel();

        addOnClick(calculator, showCalc);

        this.add(forecast);
        this.add(table);
        this.add(buttons);
    }

    private void getForecast() {
        MetOfficeAPI api = new MetOfficeAPI();
        data = bizeeAPI.gddForecast(3840, 10);

        dataTable = new String[data.size()][2];
        int i =0;
        for(Pair p : data) {
            String[] it = new String[2];
            it[0] = (String) p.fst;
            double d = (Double) p.snd;
            it[1] = Integer.toString((int) d);
            dataTable[i] = it;
            i++;
        }
    }

    private static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e -> {
            btnAction.run();
        });
    }


    private static JPanel createButtonsPanel() {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));

        calculator.setHorizontalAlignment(SwingConstants.RIGHT);
        bottomButtons.add(calculator);

        return bottomButtons;
    }

}
