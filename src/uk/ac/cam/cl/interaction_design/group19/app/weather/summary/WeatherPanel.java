package uk.ac.cam.cl.interaction_design.group19.app.weather.summary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * Base class for panels in weather view
 * <p>
 * <p>
 * Makes sure the space is divided correctly
 * between buttons and other information
 * <p>
 * Also includes utility methods for subclasses
 */
public abstract class WeatherPanel extends JPanel {
    private static final float                   DEFAULT_FONT_SIZE = 18;
    private static final double                  MAIN_PANEL_RATIO  = 0.85;
    private static final int                     REFERENCE_HEIGHT  = 100;
    protected final      Supplier<LocalDateTime> dateSupplier;
    private final        ComponentListener       resizeListener    = new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
            update();
        }
    };
    
    
    /**
     * Main panel       - 0.85 of vertical space
     * Buttons panel    - 0.15 of vertical space
     */
    public WeatherPanel(Supplier<LocalDateTime> dateSupplier) {
        super(new BorderLayout());
        this.dateSupplier = dateSupplier;
        this.addComponentListener(resizeListener);
    }
    
    protected static JPanel createPanel(JComponent... components) {
        var outer = new JPanel(new GridBagLayout());
        var inner = new JPanel(new GridLayout(1, components.length));
        Stream.of(components).forEachOrdered(inner::add);//c ->
//        {
//            c.setBorder(BorderFactory.createDashedBorder(Color.GREEN));
//            inner.add(c);
//        });
        outer.add(inner);
        return outer;
    }
    
    protected static JLabel createLabel(float fontSize) {
        return createLabel("", fontSize);
    }
    
    protected static JLabel createLabel(String text, float fontSize) {
        var label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(fontSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
//        label.setBorder(BorderFactory.createLineBorder(Color.RED));
        return label;
    }
    
    protected static JLabel createLabel() {
        return createLabel("");
    }
    
    protected static JLabel createLabel(String text) {
        return createLabel(text, DEFAULT_FONT_SIZE);
    }
    
    protected static void addOnClick(JButton btn, Runnable btnAction) {
        btn.addActionListener(e ->
                              {
                                  if (e.getActionCommand().equals(btn.getText())) {
                                      btnAction.run();
                                  }
                              });
    }
    
    protected void populate() {
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(MAIN_PANEL_RATIO);
        sp.setEnabled(false);
        sp.setDividerSize(0);
        
        var height = this.getHeight() != 0 ? this.getHeight() : REFERENCE_HEIGHT;
        
        var mainPanel = createMainPanel();
        mainPanel.setPreferredSize(new Dimension(
                mainPanel.getPreferredSize().width,
                (int) (height * MAIN_PANEL_RATIO)));
        sp.add(mainPanel);
        
        var buttonPanel = createButtonsPanel();
        buttonPanel.setPreferredSize(new Dimension(
                buttonPanel.getPreferredSize().width,
                height - (int) (height * MAIN_PANEL_RATIO)));
        sp.add(buttonPanel);
        
        this.add(sp, BorderLayout.CENTER);
        
        update();
    }
    
    protected abstract JPanel createMainPanel();
    
    protected abstract JPanel createButtonsPanel();
    
    public abstract void update();
}
