package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for a textual view of a spreadsheet model.
 */
public class BasicSpreadsheetTextualView implements SpreadsheetTextualView {


  /**
   * Version of render that also takes in an appendable, and writes the textual view
   * of the model to the given appendable.
   * @param model the model to be rendered
   * @param a the appendable to be written to
   * @throws IllegalArgumentException if either arguments are null
   */
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
    HashMap<Integer, Integer> rowHeights = makeRowHash(model);
    for (Integer i: rowHeights.keySet()) {
      try {
        a.append("ROW ");
        a.append(Integer.toString(i));
        a.append(" ");
        a.append(rowHeights.get(i).toString());
        a.append("\n");
      } catch (IOException e) {
        System.out.println("Cannot append");
      }
    }
  }

  /**
   * This version of render takes in only a model and creates a new StringWriter,
   * renders the model to that StringWriter, then prints it out.
   * @param model the spreadsheet to be rendered
   * @throws IllegalArgumentException if given model is null
   */
  @Override
  public void render(Spreadsheet model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    StringWriter writer = new StringWriter();
    render(model, writer);
    System.out.print(writer.getBuffer());
  }

  @Override
  public int getColWidth(int index) {
    return 0;
  }

  /**
   * Makes a hashMap with all the nonstandard row and rowHeights combos.
   * @param spread the spreadsheet model.
   * @return row - rowHeights map.
   */
  protected HashMap<Integer, Integer> makeRowHash(Spreadsheet spread) {
    HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
    for (int i = 0; i < spread.getHeight(); i++) {
      if (spread.getRowHeight(i) != BasicSpreadsheet.DEFAULT_ROW_HEIGHT) {
        result.put(i, spread.getRowHeight(i));
      }
    }
    return result;
  }
}