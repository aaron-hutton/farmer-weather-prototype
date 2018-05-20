package uk.ac.cam.cl.interaction_design.group19.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBarImplementation extends BasicScrollBarUI {

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(MainWindow.BACKGROUND_COLOR);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(thumbBounds.x,
                thumbBounds.y+thumbBounds.width/2,
                thumbBounds.width,
                thumbBounds.height-thumbBounds.width);
        g.fillOval(thumbBounds.x,
                thumbBounds.y,
                thumbBounds.width,
                thumbBounds.width);
        g.fillOval(thumbBounds.x,
                thumbBounds.y+thumbBounds.height-thumbBounds.width,
                thumbBounds.width,
                thumbBounds.width);
    }
}
