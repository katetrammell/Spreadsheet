package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicSpreadSheetGraphicalView implements SpreadsheetView {

  @Override
  public void render(Spreadsheet model) {
    JFrame window = new JFrame();
    JTable spread;
    HashMap<Coord, Cell> spreadData = model.getAllCells();
    int spreadHeight = model.getHeight();
    int spreadWidth = model.getWidth();
    String[][] spreadStrings = new String[spreadHeight][spreadWidth];
    // gets all the data from model
    for (int i = 0; i < spreadHeight; i++) {
      for (int j = 0; j < spreadWidth; j++) {
        String cell;
        if (spreadData.containsKey(new Coord(j + 1, i + 1))) {
          Cell currentCell = model.getCellAt(i + 1, j + 1);
          if (currentCell.getFormula() == null) {
            cell = currentCell.toString();
          }
          else {
            cell = currentCell.getFormula().evaluate().toString();
          }
        }
        else {
          cell = "";
        }
        spreadStrings[i][j] = cell;
      }
    }
    // to get column names from model
    String[] columnNames = new String[spreadWidth];
    for (int i = 1; i <= spreadWidth; i++) {
      columnNames[i - 1] = Coord.colIndexToName(i);
    }
    spread = new JTable(spreadStrings, columnNames);
    spread.setBounds(30, 40, 200, 300);
    JScrollPane scroll = new JScrollPane(spread);
    window.setTitle("test");
    window.add(scroll);
    window.setSize(500, 200);
    window.setVisible(true);

  }
}
