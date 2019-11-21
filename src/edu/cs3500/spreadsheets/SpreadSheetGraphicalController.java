package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.sexp.CellMaker;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicalView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller for graphical spreadsheet.
 * TODO: ADD TO CONTROLLER DIRECTORY LATER
 */
public class SpreadSheetGraphicalController implements ActionListener, MouseListener {


  SpreadSheetGraphicalView view;
  Spreadsheet spread;
  Coord lastSelectedCell;

  public SpreadSheetGraphicalController(Spreadsheet spread, SpreadSheetGraphicalView view) {
    this.spread = spread;
    this.view = view;
    view.render(spread);
    view.setListener(this);
    System.out.print("Test");
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
        try {
          new Parser();
          Sexp sexp;
          if (contents.substring(0,1).equals("=")) {
            sexp = Parser.parse(contents.substring(1));
          } else {
            sexp = Parser.parse(contents);
          }
          Cell cell = sexp.accept(new CellMaker(spread));
          spread.setCell(cell, coord.getY(), coord.getX());
          System.out.println("Coord set: " + cell.toString());
          System.out.println("coord: " + coord.toString());
          view.updateCell(coord, cell);

        } catch (Exception ee) {
          ee.printStackTrace();
          view.setTextBox("Error. Invalid input");
        }
        break;
      case "Add Row":
        int newHeight = spread.getHeight() + 1;
        spread.setHeight(newHeight);
        view.updateCell(new Coord(1, newHeight + 1), null);
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
    }
    else {
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
}
