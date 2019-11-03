package edu.cs3500.spreadsheets.view;


import edu.cs3500.spreadsheets.model.Spreadsheet;

public interface SpreadsheetTextualView extends SpreadsheetView {

  /**
   * Render that takes in a spreadsheet and an appendable.
   * @param spread spreadsheet to be rendered
   * @param append the appendable to be rendered to
   * @throws IllegalArgumentException if either argument are null
   */
  void render(Spreadsheet spread, Appendable append) throws IllegalArgumentException;

}
