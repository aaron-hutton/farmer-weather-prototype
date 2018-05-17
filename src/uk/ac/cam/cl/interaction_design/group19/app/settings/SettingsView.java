package uk.ac.cam.cl.interaction_design.group19.app.settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Map;
import java.util.stream.Stream;
import javafx.util.Pair;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SettingsView extends JPanel
{
    private static final Map<ExtremeEvent, String> eventNames = Map.of(

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

        var locationPanel = createSettingPanel(new JLabel("Location: "), new JFormattedTextField());
        this.add(locationPanel);

        var notificationPanel = createSettingPanel(new JLabel("High contrast: "), new JCheckBox());
        this.add(notificationPanel);
    }

    private JPanel createSettingPanel(JComponent descriptor, JComponent inputField)
    {
        var panel = new JPanel(new BorderLayout());
        panel.add(descriptor, BorderLayout.WEST);
        panel.add(inputField, BorderLayout.EAST);
        return panel;
    }
}
