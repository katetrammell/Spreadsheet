package edu.cs3500.spreadsheets.provider.view;


import edu.cs3500.spreadsheets.provider.model.Coord;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 * A worksheet panel with the ability to scroll infinitely in both directions.
 */
final class ScrollableWorksheet extends JPanel {

  private static final int STARTING_MAX = 100;
  private boolean sidescroll = false;

  /**
   * Scrollable panels must contain a non-scroll panel within them.
   *
   * @param wp the non-scrolling worksheet panel to scroll over.
   */
  ScrollableWorksheet(WorksheetPanel wp) {
    JScrollBar horizontalScroll = new JScrollBar(JScrollBar.HORIZONTAL);
    JScrollBar verticalScroll = new JScrollBar(JScrollBar.VERTICAL);

    this.setLayout(new BorderLayout());
    this.add(wp, BorderLayout.CENTER);
    this.add(horizontalScroll, BorderLayout.SOUTH);
    this.add(verticalScroll, BorderLayout.EAST);

    verticalScroll.addAdjustmentListener(l -> {
      wp.topLeft = new edu.cs3500.spreadsheets.provider.model.Coord(wp.topLeft.col, l.getValue() + 1);
      l.getAdjustable().setMaximum(wp.topLeft.row + STARTING_MAX);
      this.repaint();
    });

    horizontalScroll.addAdjustmentListener(l -> {
      wp.topLeft = new edu.cs3500.spreadsheets.provider.model.Coord(l.getValue() + 1, wp.topLeft.row);
      l.getAdjustable().setMaximum(wp.topLeft.col + STARTING_MAX);
      this.repaint();
    });

    addMouseWheelListener(evt -> {
      if (sidescroll) {
        int resultVal = Math
            .max(0, horizontalScroll.getValue() + evt.getUnitsToScroll() / evt.getScrollAmount());
        horizontalScroll.setValue(resultVal);
      } else {
        int resultVal = Math
            .max(0, verticalScroll.getValue() + evt.getUnitsToScroll() / evt.getScrollAmount());
        verticalScroll.setValue(resultVal);
      }
    });
  }

  KeyListener getKeyListener() {
    return new KeyListener() {
      @Override
      public void keyTyped(KeyEvent keyEvent) {
        // only care about presses and releases
      }

      @Override
      public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_SHIFT) {
          sidescroll = true;
        }
      }

      @Override
      public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_SHIFT) {
          sidescroll = false;
        }
      }
    };
  }
}
