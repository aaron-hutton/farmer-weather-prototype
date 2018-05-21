package uk.ac.cam.cl.interaction_design.group19.app.settings;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import uk.ac.cam.cl.interaction_design.group19.app.util.Action;
import uk.ac.cam.cl.interaction_design.group19.app.util.Property;
import uk.ac.cam.cl.interaction_design.group19.app.util.Updatable;

public class SettingsView extends JPanel implements Updatable {
    private static final Pattern postcodeRegex = Pattern.compile(
            "^(([gG][iI][rR] {0,}0[aA]{2})|" +
            "((([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y]?[0-9][0-9]?)|" +
            "(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|" +
            "([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y]))) {0,}" +
            "[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$");
    
    private static final Border                    settingsMargin = new EmptyBorder(10, 10, 10, 10);
    private static final Border                    labelMargin    = new EmptyBorder(5, 5, 5, 5);
    private static final Font                      headingFont    =
            new JLabel().getFont().deriveFont(Font.BOLD).deriveFont(18f);
    private static final Map<ExtremeEvent, String> eventNames     = Map.of(
            ExtremeEvent.FLOOD, "Flood",
            ExtremeEvent.DROUGHT, "Drought",
            ExtremeEvent.FROST, "Frost",
            ExtremeEvent.SNOW, "Snow",
            ExtremeEvent.STRONG_WIND, "Strong wind"
    );
    
    private final Map<ExtremeEvent, JCheckBox> alertCheckboxes;
    private final JTextField                   locationField = new JTextField();
    
    public SettingsView(Property<String> locationProperty,
                        Property<Boolean> highContrastProperty,
                        Map<ExtremeEvent, Property<Boolean>> alertProperties) {
        alertCheckboxes = eventNames.keySet().stream().collect(Collectors.toMap(e -> e, e -> new JCheckBox()));
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(settingsMargin);
        
        addLabel("Settings", headingFont);
        addTextSettingPanel(new JLabel("Location: "), locationField);
        locationField.setText(locationProperty.get());
        locationField.addActionListener(e -> {
                var     text = locationField.getText();
                Matcher m    = postcodeRegex.matcher(text);
                if (m.matches()) {
                    locationProperty.set(text);
                } else {
                    throw new RuntimeException("'" + text + "' is not a valid postcode");
                }
        });
        addHighContrastPanel(highContrastProperty);
        addNotificationPanel(alertProperties);
    }
    
    private void addLabel(String text, Font font) {
        var outer    = new JPanel(new BorderLayout());
        var settings = new JLabel(text);
        settings.setFont(font);
        settings.setHorizontalAlignment(SwingConstants.CENTER);
        outer.add(settings, BorderLayout.CENTER);
        this.add(outer);
        this.add(new JSeparator());
    }
    
    private void addTextSettingPanel(JComponent descriptor, JTextField inputField) {
        var outer = new JPanel(new BorderLayout());
        var sp    = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0);
        sp.setEnabled(false);
        sp.setDividerSize(0);
        descriptor.setBorder(labelMargin);
        sp.add(descriptor);
        sp.add(inputField);
        outer.add(sp, BorderLayout.CENTER);
        this.add(outer);
        this.add(new JSeparator());
    }
    
    private void addHighContrastPanel(Property<Boolean> highContrast) {
        var checkBox = new JCheckBox();
        checkBox.setSelected(highContrast.get());
        addCheckboxObserver(checkBox, () -> highContrast.set(true), () -> highContrast.set(false));
        var highContrastPanel = createCheckboxSettingPanel(new JLabel("High contrast: "), checkBox);
        this.add(highContrastPanel);
        this.add(new JSeparator());
    }
    
    private void addNotificationPanel(Map<ExtremeEvent, Property<Boolean>> alerts) {
        var allAlertsCheckBox = new JCheckBox();
        allAlertsCheckBox.setSelected(alerts.values().stream().allMatch(Property::get));
        allAlertsCheckBox.addActionListener(e -> {
            var selected = allAlertsCheckBox.getModel().isSelected();
            alertCheckboxes.values().forEach(box -> box.setSelected(selected));
        });
        var notificationPanel = createCheckboxSettingPanel(new JLabel("Notifications: "), allAlertsCheckBox);
        this.add(notificationPanel);
        
        alertCheckboxes.keySet()
                       .forEach(event -> {
                           var alert    = alerts.get(event);
                           var checkBox = alertCheckboxes.get(event);
                           addCheckboxObserver(checkBox, () -> alert.set(true), () -> alert.set(false));
                           this.add(createCheckboxSettingPanel(new JLabel(eventNames.get(event)), checkBox));
                       });
    }
    
    private void addCheckboxObserver(JCheckBox checkBox, Action onSelected, Action onDeselected) {
        checkBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                onSelected.call();
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                onDeselected.call();
            }
        });
    }
    
    private JComponent createCheckboxSettingPanel(JComponent descriptor, JCheckBox checkBox) {
        var sp = new JPanel(new BorderLayout());
        descriptor.setBorder(labelMargin);
        sp.add(descriptor, BorderLayout.WEST);
        sp.add(checkBox, BorderLayout.EAST);
        return sp;
    }
    
    @Override
    public void update() {
    }
}
