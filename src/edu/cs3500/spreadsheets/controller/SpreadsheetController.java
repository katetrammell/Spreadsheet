package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * An interface for the controller of a spreadsheet.
 */
public interface SpreadsheetController  {

  /**
   * Adds a single row to the model. This will affect the height
   * of the model.
   */
  void addRow();

  /**
   * Adds a single column to the model. This will affect the width
   * of the model.
   */
  void addCol();

  /**
   * Change the contents of a cell at the specified coordinate.
   * The cells contents will be replaced by the string.
   * The view will be updated.
   * @param c Coordinate of the desired cell.
   * @param s new contents of the cell.
   */
  void changeCell(Coord c, String s);

}
