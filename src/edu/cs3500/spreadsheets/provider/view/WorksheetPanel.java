package edu.cs3500.spreadsheets.provider.view;


import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.WorksheetReadOnly;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

/**
 * The grid layout of our spreadsheet program showing row / column names and cell values.
 */
final class WorksheetPanel extends JPanel {

  private static final int CELL_WIDTH = 75;
  private static final int CELL_HEIGHT = 30;
  private static final int FONT_SIZE = 15;
  private static final int LEFT_PAD = 5;
  Coord selected;
  Coord topLeft;
  private WorksheetReadOnly ws;

  /**
   * Constructor for WorksheetPanel.
   */
  WorksheetPanel(WorksheetReadOnly ws) {
    this.ws = ws;
    topLeft = new Coord(1, 1);
    selected = null;
  }

  // set the handler for click callback
  void setFeatureHandler(FeatureHandler handler) {
    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        Coord c = getCoordClicked(mouseEvent);

        if (c != null) {
          handler.cellSelected(c);
        }
      }

      @Override
      public void mousePressed(MouseEvent mouseEvent) {
        // only care about clicks
      }

      @Override
      public void mouseReleased(MouseEvent mouseEvent) {
        // only care about clicks
      }

      @Override
      public void mouseEntered(MouseEvent mouseEvent) {
        // only care about clicks
      }

      @Override
      public void mouseExited(MouseEvent mouseEvent) {
        // only care about clicks
      }
    });
  }

  // returns the Coord clicked at MouseEvent
  private Coord getCoordClicked(MouseEvent evt) {
    int x = (evt.getX() / CELL_WIDTH) * CELL_WIDTH;
    int y = (evt.getY() / CELL_HEIGHT) * CELL_HEIGHT;

    //  col and row names are not valid cells to send input to, so just return null
    if (x < CELL_WIDTH || y < CELL_HEIGHT) {
      return null;
    }

    int colOffset = x / CELL_WIDTH - 1;
    int rowOffset = y / CELL_HEIGHT - 1;

    return new Coord(topLeft.col + colOffset, topLeft.row + rowOffset);
  }

  // select what cell get shown differently
  void selectCell(Coord c) {
    this.selected = c;
  }

  // don't show any cell differently
  void deselectCell() {
    this.selected = null;
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    AffineTransform save = g2d.getTransform();

    g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE));
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2d.setColor(Color.black);

    drawHeaderBackground(g2d);
    drawLines(g2d);
    drawColNames(g2d);
    drawRowNames(g2d);
    drawCells(g2d);

    if (this.selected != null) {
      drawSelected(g2d);
    }

    g2d.setTransform(save);
  }

  private void drawSelected(Graphics2D g2d) {
    if (selected.col < topLeft.col || selected.row < topLeft.row) {
      return;
    }

    int startX = CELL_WIDTH * (selected.col - topLeft.col + 1);
    int startY = CELL_HEIGHT * (selected.row - topLeft.row + 1);
    int endX = startX + CELL_WIDTH;
    int endY = startY + CELL_HEIGHT;

    Color save = g2d.getColor();
    g2d.setColor(Color.RED);

    g2d.drawLine(startX, startY, endX, startY);
    g2d.drawLine(startX, startY, startX, endY);
    g2d.drawLine(endX, startY, endX, endY);
    g2d.drawLine(startX, endY, endX, endY);

    g2d.setColor(save);
  }

  private void drawHeaderBackground(Graphics2D g2d) {
    Color save = g2d.getColor();

    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fillRect(0, 0, this.getWidth(), CELL_HEIGHT);
    g2d.fillRect(0, 0, CELL_WIDTH, this.getHeight());

    g2d.setColor(save);
  }

  private void drawLines(Graphics2D g2d) {
    for (int x = 0; x < this.getWidth(); x += CELL_WIDTH) {
      g2d.draw(new Line2D.Double(x, 0, x, this.getHeight()));
    }

    for (int y = 0; y < this.getHeight(); y += CELL_HEIGHT) {
      g2d.draw(new Line2D.Double(0, y, this.getWidth(), y));
    }
  }

  private void drawColNames(Graphics2D g2d) {
    for (int x = CELL_WIDTH, i = topLeft.col; x < this.getWidth(); x += CELL_WIDTH, i += 1) {
      g2d.drawString(Coord.colIndexToName(i), x + LEFT_PAD, CELL_HEIGHT / 2 + FONT_SIZE / 2);
    }
  }

  private void drawRowNames(Graphics2D g2d) {
    for (int y = CELL_HEIGHT + CELL_HEIGHT / 2, i = topLeft.row; y < this.getHeight();
        y += CELL_HEIGHT, i += 1) {
      g2d.drawString(Integer.toString(i), LEFT_PAD, y + FONT_SIZE / 2);
    }
  }

  private void drawCells(Graphics2D g2d) {
    Shape clipSave = g2d.getClip();

    for (int x = CELL_WIDTH, col = topLeft.col; x < this.getWidth(); x += CELL_WIDTH, col += 1) {
      for (int y = CELL_HEIGHT, row = topLeft.row; y < this.getHeight();
          y += CELL_HEIGHT, row += 1) {
        g2d.clip(new Rectangle(x, y, CELL_WIDTH, CELL_HEIGHT));
        String toDraw = ws.getValue(new Coord(col, row)).accept(new VisualCellVisitor());
        g2d.drawString(toDraw, x + 5, y + CELL_HEIGHT / 2 + FONT_SIZE / 2);
        g2d.setClip(clipSave);
      }
    }
  }

  private static class VisualCellVisitor implements CellValueVisitor<String> {

    @Override
    public String visitBoolean(boolean b) {
      return Boolean.toString(b).toUpperCase();
    }

    @Override
    public String visitString(String s) {
      return s;
    }

    @Override
    public String visitNumber(double d) {
      return Double.toString(d);
    }

    @Override
    public String visitEmpty() {
      return "";
    }

    @Override
    public String visitError(String err) {
      return "#ERROR!: " + err;
    }
  }
}
