package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Spreadsheet;

public interface SpreadsheetView {

  /**
   * Renders the view of the given spreadsheet.
   * @param model the spreadsheet to be rendered
   */
  void render(Spreadsheet model);




}
