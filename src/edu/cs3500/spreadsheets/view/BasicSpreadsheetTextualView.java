package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BasicSpreadsheetTextualView implements SpreadsheetTextualView {

  @Override
  public void render(Spreadsheet model, Appendable a) throws IllegalArgumentException {
    if (model == null || a == null) {
      throw new IllegalArgumentException("spreadsheet and appendable must be non null");
    }
    HashMap<Coord, Cell> cells = model.getAllCells();
    for (Map.Entry<Coord, Cell> entry : cells.entrySet()) {
      try {
        a.append(entry.getKey().toString());
        a.append(" ");
        a.append(entry.getValue().toString());
        a.append("\n");
      } catch (IOException e) {
        System.out.print("Cannont append");
        return;
      }
    }
  }

  @Override
  public void render(Spreadsheet model) {

  }
}
