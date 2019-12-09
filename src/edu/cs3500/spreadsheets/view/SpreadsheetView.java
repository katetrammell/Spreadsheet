package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Spreadsheet;

/**
 * Represents a view of a spreadsheet model.
 */
public interface SpreadsheetView {

  /**
   * Renders the view of the given spreadsheet.
   * @param model the spreadsheet to be rendered
   * @throws IllegalArgumentException if given model is null
   */
  void render(Spreadsheet model) throws IllegalArgumentException;

  int getColWidth(int index);

}
