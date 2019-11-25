package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.BasicStringCell;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.sexp.CellMaker;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicalView;

import java.awt.event.*;

/**
 * Controller for graphical spreadsheet. TODO: ADD TO CONTROLLER DIRECTORY LATER
 */
public class SpreadSheetGraphicalController implements SpreadsheetController,
    ActionListener, MouseListener, KeyListener {

  private SpreadSheetGraphicalView view;
  private Spreadsheet spread;
  private Coord lastSelectedCell;

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
        //c = spread.getCellAt(coord);
        String contents = view.getTextBox();
        this.changeCell(coord, contents);
        break;
      case "Add Row":
        this.addRow();
        break;
      case "Add column":
        this.addCol();
        break;
    }
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
    System.out.println("Last selected cell: " + c.toString());
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

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

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
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
          lastSelectedCell = new Coord(
              lastSelectedCell.getX() + 1, lastSelectedCell.getY());
        }
        break;
      case KeyEvent.VK_LEFT:
        if (lastSelectedCell.getX() > 1) {
          lastSelectedCell = new Coord(
              lastSelectedCell.getX() - 1, lastSelectedCell.getY());
        }
        break;
      case KeyEvent.VK_DELETE:
        spread.removeCell(lastSelectedCell);
        view.updateCell(lastSelectedCell, new BasicStringCell(""));
        System.out.println("Delete");
    }
    if (spread.getCellAt(lastSelectedCell) == null) {
      view.setTextBox("");
    }
    else {
      view.setTextBox(spread.getCellAt(lastSelectedCell).toString());
    }
    System.out.println("Last selected Cell: " + lastSelectedCell.toString());
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}