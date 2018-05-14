package uk.ac.cam.cl.interaction_design.group19.app;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class MapView extends JPanel {
    public MapView() {
        super(new GridLayout(1, 1));
        this.setSize(new Dimension(300, 300));
    }
}
