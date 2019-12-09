package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.BasicStringCell;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.sexp.CellMaker;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.BasicSpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller for graphical spreadsheet.
 */
public class SpreadSheetGraphicalController implements SpreadsheetController,
    ActionListener, MouseListener, KeyListener {

  private SpreadSheetGraphicalView view;
  private Spreadsheet spread;
  private Coord lastSelectedCell;
  // needed to track when cursor goes off screen scrolling left
  private static int tempX = 1;
  private static int DEFAULT_ROW_SIZE = 30;

  /**
   * A constructor for the class.
   *
   * @param spread the spreadsheet to be controlled.
   * @param view the view to be controlled.
   */
  public SpreadSheetGraphicalController(Spreadsheet spread, SpreadSheetGraphicalView view) {
    this.spread = spread;
    this.view = view;
    view.render(spread);
    view.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Coord coord;
    Cell c;
    switch (e.getActionCommand()) {
      case "X Button":
        coord = view.getSelectedCell();
        c = spread.getCellAt(coord);
        if (c != null) {
          view.setTextBox(c.toString());
        }
        break;
      case "Check Button":
        coord = lastSelectedCell;
        String contents = view.getTextBox();
        this.changeCell(coord, contents);
        break;
      case "Add Row":
        this.addRow();
        break;
      case "Add column":
        this.addCol();
        break;
      case "Save Test":
        BasicSpreadsheetTextualView newView = new BasicSpreadsheetTextualView();
        try {
          PrintWriter writer = new PrintWriter(view.getSaveBox());
          newView.render(spread, writer);
          writer.close();
        } catch (IOException eee) {
          view.setSaveBox("Make sure to not use quotation marks "
              + "or any characters invalid for file names");
        }
        break;
      case "Increase Row Height":
        view.setRowHeight(lastSelectedCell.getY() - 1,
            view.getRowHeight(lastSelectedCell.getY() - 1) + 5);
        spread.setRowHeight(lastSelectedCell.getY() - 1,
            view.getRowHeight(lastSelectedCell.getY() - 1) + 5);
        break;
      case "Decrease Row Height" :
        view.setRowHeight(lastSelectedCell.getY() - 1,
            view.getRowHeight(lastSelectedCell.getY() - 1) - 5);
        spread.setRowHeight(lastSelectedCell.getY() - 1,
            view.getRowHeight(lastSelectedCell.getY() - 1) - 5);
        break;
      default:
    }
    setAllRowHeights();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Coord c;
    try {
      c = view.getSelectedCell();
    } catch (IllegalArgumentException eee) {
      return;
    }
    Cell testCell = spread.getCellAt(c);
    if (testCell != null) {
      view.setTextBox(testCell.toString());
    } else {
      view.setTextBox("");
    }
    this.lastSelectedCell = c;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // ignore /suppress this method
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // supress
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // ignore /suppress this method
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // ignore /suppress this method
  }


  @Override
  public void addRow() {
    int newHeight = spread.getHeight() + 1;
    spread.setHeight(newHeight);
    view.updateCell(new Coord(1, newHeight + 1), null);
  }

  @Override
  public void addCol() {
    int newWidth = spread.getWidth() + 1;
    spread.setWidth(newWidth);
    view.addCol(newWidth);
  }

  @Override
  public void changeCell(Coord coord, String contents) {
    try {
      new Parser();
      Sexp sexp;
      if (contents.substring(0, 1).equals("=")) {
        sexp = Parser.parse(contents.substring(1));
      } else {
        sexp = Parser.parse(contents);
      }
      Cell cell = sexp.accept(new CellMaker(spread));
      spread.setCell(cell, coord.getY(), coord.getX());
      view.updateCell(coord, cell);

    } catch (Exception ee) {
      view.setTextBox("Error. Invalid input");
    }

  }

  @Override
  public void keyTyped(KeyEvent e) {
    // ignore /suppress this method
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_DOWN:
        if (lastSelectedCell.getY() < spread.getHeight()) {
          lastSelectedCell = new Coord(
              lastSelectedCell.getX(), lastSelectedCell.getY() + 1);
        }
        break;
      case KeyEvent.VK_UP:
        if (lastSelectedCell.getY() > 1) {
          lastSelectedCell = new Coord(
              lastSelectedCell.getX(), lastSelectedCell.getY() - 1);
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (lastSelectedCell.getX() < spread.getWidth()) {
          if (tempX == 0) {
            tempX += 1;
          } else {
            lastSelectedCell = new Coord(
                lastSelectedCell.getX() + 1, lastSelectedCell.getY());
          }
        }
        break;

      case KeyEvent.VK_LEFT:
        if (lastSelectedCell.getX() == 1 && tempX == 1) {
          tempX = 0;
        }
        if (lastSelectedCell.getX() > 1) {
          lastSelectedCell = new Coord(
              lastSelectedCell.getX() - 1, lastSelectedCell.getY());
        }
        break;
      //case KeyEvent.VK_BACK_SPACE:
      case KeyEvent.VK_DELETE:
        spread.removeCell(lastSelectedCell);
        view.updateCell(lastSelectedCell, new BasicStringCell(""));
        break;
      default:
    }
    if (spread.getCellAt(lastSelectedCell) == null) {
      view.setTextBox("");
    } else {
      view.setTextBox(spread.getCellAt(lastSelectedCell).toString());
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // ignore /suppress this method
  }

  /**
   * Sets all row heights to a desired level using data from
   * the model.
   */
  private void setAllRowHeights() {
    for (int i = 0; i < spread.getHeight(); i++) {
      view.setRowHeight(i, spread.getRowHeight(i));
    }
  }
}