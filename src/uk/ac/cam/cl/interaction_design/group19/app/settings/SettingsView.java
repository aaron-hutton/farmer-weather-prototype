package uk.ac.cam.cl.interaction_design.group19.app.settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Map;
import java.util.stream.Stream;
import javafx.scene.control.SplitPane;
import javafx.util.Pair;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SettingsView extends JPanel
{
    private static final Map<ExtremeEvent, String> eventNames = Map.of(
            ExtremeEvent.FLOOD, "Flood",
            ExtremeEvent.DROUGHT, "Drought",
            ExtremeEvent.FROST, "Frost",
            ExtremeEvent.SNOW, "Snow",
            ExtremeEvent.STRONG_WIND, "Strong wind"
    );

    private Pair<String, String> location = new Pair<>("CB2", "1RH");
    private boolean highContrast = false;
    private Map<ExtremeEvent, Boolean> alerts = Map.of(
            ExtremeEvent.FROST, false,
            ExtremeEvent.SNOW, false,
            ExtremeEvent.FLOOD, false,
            ExtremeEvent.DROUGHT, false,
            ExtremeEvent.STRONG_WIND, false
    );

    public SettingsView()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        var settings = new JLabel("SETTINGS");
        this.add(settings);

        var locationPanel = createTextSettingPanel(new JLabel("Location: "), new JFormattedTextField());
        this.add(locationPanel);

        var highContrastPanel = createCheckboxSettingPanel(new JLabel("High contrast: "), new JCheckBox());
        this.add(highContrastPanel);

        var notificationPanel = createCheckboxSettingPanel(new JLabel("Notifications: "), new JCheckBox());
        this.add(notificationPanel);

        eventNames.values().stream().forEachOrdered(name ->
                this.add(createCheckboxSettingPanel(new JLabel(name), new JCheckBox()))
        );
    }

    private JComponent createCheckboxSettingPanel(JComponent descriptor, JCheckBox checkBox)
    {
        var sp = new JPanel(new BorderLayout());

        sp.add(descriptor, BorderLayout.WEST);
        sp.add(checkBox, BorderLayout.EAST);
        return sp;
    }

    private JComponent createTextSettingPanel(JComponent descriptor, JTextField inputField)
    {
        var outer = new JPanel(new BorderLayout());

        var sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0);
        sp.setEnabled(false);
        sp.setDividerSize(0);
        sp.add(descriptor);
        sp.add(inputField);

        outer.add(sp, BorderLayout.CENTER);
        return outer;
    }
}
