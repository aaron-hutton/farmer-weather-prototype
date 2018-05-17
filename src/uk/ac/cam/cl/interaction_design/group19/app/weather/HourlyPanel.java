package uk.ac.cam.cl.interaction_design.group19.app.weather;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HourlyPanel extends WeatherPanel
{
    private final JButton summary = new JButton("< summary");

    public HourlyPanel(Supplier<LocalDateTime> dateSupplier, Runnable showSummary)
    {
        super(dateSupplier);
        addOnClick(summary, showSummary);
        populate();

    }

    @Override
    protected JPanel createMainPanel()
    {
        return new JPanel();
    }

    @Override
    protected JPanel createButtonsPanel()
    {
        var bottomButtons = new JPanel();
        bottomButtons.setLayout(new GridLayout(1, 2));

        summary.setHorizontalAlignment(SwingConstants.LEFT);
        bottomButtons.add(summary);

        bottomButtons.add(new JPanel());

        return bottomButtons;
    }

    @Override
    public void update()
    {

    }
}
