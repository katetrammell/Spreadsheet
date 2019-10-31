package edu.cs3500.spreadsheets.model;

/**
 * Model for a spreadsheet that can sum, multiply, evaluate greater than, and X.
 */
public interface Spreadsheet {


  /**
   * TODO: Decide what to do if there is no cell
   * Gets the cell at the specified location.
   * @param column the column of the cell
   * @param row the row number of the cell
   * @return the cell at the specified location
   * @throw IllegalArgumentException if location is invalid
   */
  Cell getCellAt(int column, int row) throws IllegalArgumentException;

  /**
   * Sets the cell at the given location to the given cell.
   * If the cell position is beyond the scope of the board,
   * the board will expand and fill with nulls for the new cells.
   * @param c the cell to put in
   * @param column the row of the cell
   * @param row the row of the cell
   * @throws IllegalArgumentException if the rows or col are negative.
   */
  void setCell(Cell c, int column, int row) throws IllegalArgumentException;

  /**
   * Gets the current width of the spreadsheet.
   * @return the width of the spreadsheet
   */
  int getWidth();

  /**
   * Gets the current height of the spreadsheet.
   * @return the height of the spreadsheet
   */
  int getHeight();

}
