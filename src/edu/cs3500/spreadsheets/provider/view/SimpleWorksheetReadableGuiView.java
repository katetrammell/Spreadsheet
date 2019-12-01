package edu.cs3500.spreadsheets.provider.view;

import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.WorksheetReadOnly;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import javax.swing.JFrame;

/**
 * Class to represent a GUI version of the spreadsheet view. Allows user to view cells in a grid
 * format, and enables the user to scroll.
 */
public class SimpleWorksheetReadableGuiView extends JFrame implements WorksheetView {

  WorksheetReadOnly ws;
  private WorksheetPanel wp;

  /**
   * Constructor for the visual view.
   *
   * @param ws the read-only worksheet to display.
   */
  public SimpleWorksheetReadableGuiView(WorksheetReadOnly ws) {
    this.ws = Objects.requireNonNull(ws);

    initFrame();
  }

  private void initFrame() {
    setTitle("SimpleWorksheet");

    setSize(500, 300);
    setMinimumSize(new Dimension(500, 300));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setFocusable(true);
    wp = new WorksheetPanel(ws);
    ScrollableWorksheet scrollablePanel = new ScrollableWorksheet(wp);
    this.add(scrollablePanel, BorderLayout.CENTER);

    addKeyListener(scrollablePanel.getKeyListener());
  }

  @Override
  public void render() {
    this.requestFocusInWindow();
    this.setVisible(true);
    this.repaint();
    this.requestFocusInWindow();
  }

  @Override
  public void selectCell(Coord cellCoord) {
    this.wp.selectCell(cellCoord);
  }

  @Override
  public void deselectCell() {
    this.wp.deselectCell();
  }

  @Override
  public void setFeatureHandler(FeatureHandler handler) {
    wp.setFeatureHandler(handler);

    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent keyEvent) {
        // sought after keycodes can only be found in presses
      }

      @Override
      public void keyPressed(KeyEvent keyEvent) {

        if (wp.selected == null) {
          return;
        }

        switch (keyEvent.getKeyCode()) {
          case KeyEvent.VK_LEFT:
            if (wp.selected.col != 1) {
              wp.selected = new Coord(wp.selected.col - 1, wp.selected.row);
            }
            break;

          case KeyEvent.VK_RIGHT:
            wp.selected = new Coord(wp.selected.col + 1, wp.selected.row);
            break;

          case KeyEvent.VK_UP:
            if (wp.selected.row != 1) {
              wp.selected = new Coord(wp.selected.col, wp.selected.row - 1);
            }
            break;

          case KeyEvent.VK_DOWN:
            wp.selected = new Coord(wp.selected.col, wp.selected.row + 1);
            break;

          default:
            break;
        }

        handler.cellSelected(wp.selected);
      }

      @Override
      public void keyReleased(KeyEvent keyEvent) {
        // only care about presses
      }

    });
  }
}
