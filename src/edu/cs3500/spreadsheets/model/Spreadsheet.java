package edu.cs3500.spreadsheets.model;

/**
 * Model for a spreadsheet that can sum, multiply, evaluate greater than, and X
 */
public interface Spreadsheet {

  //TODO: Uncomment this... possibly why we were getting errors
  /*
  *//**
   * Sums the values of the given cells.
   * Ignores non-numeric values and defaults to zero.
   * @param range Any number of cell's to sum
   * @return sum of the cells' values
   *//*
  public double sum(Cell... range);

  *//**
   * Multiplies the values of the given cells.
   * Ignores non-numeric values and defaults to 0.
   * @param range Any number of cells to multiply
   * @return product of the cells' values
   *//*
  public double product(Cell... range);

  *//**
   * Compares the two cells numerical value.
   * @param cell1 the first cell.
   * @param cell2 the second cell
   * @return weather or not the first cell is greater than the second
   * @throws IllegalArgumentException if any of the cells are missing or not a number
   *
   *//*
  public boolean greaterThan(Cell cell1, Cell cell2);

  *//**
   * Concatenates the given cells.
   * Ignores non string cells. Defaults to "".
   * @param range cells to concatenates
   * @return the concatenation of the cells
   *//*
  public String concat(Cell... range);
*/
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
