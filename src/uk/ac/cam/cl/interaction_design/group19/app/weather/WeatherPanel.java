package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * Base class for panels in weather view
 * <p>
 * <p>
 * Makes sure the space is divided correctly
 * between buttons and other information
 * <p>
 * Also includes utility methods for subclasses
 */
public abstract class WeatherPanel extends JPanel
{
    protected Supplier<LocalDateTime> dateSupplier;

    /**
     * Main panel       - 0.85 of vertical space
     * Buttons panel    - 0.15 of vertical space
     */
    public WeatherPanel(Supplier<LocalDateTime> dateSupplier)
    {
        super(new BorderLayout());
        this.dateSupplier = dateSupplier;
    }

    protected void populate()
    {
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.85);
        sp.setEnabled(false);
        sp.setDividerSize(0);

        var mainPanel = createMainPanel();
        mainPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, 85));
        sp.add(mainPanel);

        var buttonPanel = createButtonsPanel();
        buttonPanel.setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width, 15));
        sp.add(buttonPanel);

        this.add(sp, BorderLayout.CENTER);

        update();
    }

    protected abstract JPanel createMainPanel();

    protected abstract JPanel createButtonsPanel();

    public abstract void update();

    protected JPanel createPanel(JComponent... components)
    {
        var outer = new JPanel(new GridBagLayout());
        var inner = new JPanel(new GridLayout(1, components.length));
        Stream.of(components).forEach(c -> c.setBorder(BorderFactory.createLineBorder(Color.BLACK)));
        Stream.of(components).forEachOrdered(inner::add);
        outer.add(inner);
        return outer;
    }

    protected void addOnClick(JButton btn, Runnable btnAction)
    {
        btn.addActionListener(e ->
        {
            if (e.getActionCommand().equals(btn.getText()))
            {
                btnAction.run();
            }
        });
    }
}
