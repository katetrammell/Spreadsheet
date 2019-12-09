package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * Model for a spreadsheet that can sum, multiply, evaluate greater than, and X.
 */
public interface Spreadsheet {


  /**
   * Gets the cell at the specified location.
   * @param column the column of the cell
   * @param row the row number of the cell
   * @return the cell at the specified location
   * @throw IllegalArgumentException if location is invalid
   */
  Cell getCellAt(int column, int row) throws IllegalArgumentException;

  /**
   * Gets the cell at the specified location.
   * @param c coordiante of the desired cell.
   * @return cell at location c;
   */
  Cell getCellAt(Coord c);

  /**
   * Returns a HashMap of all cells and their corresponding Coords.
   * @return HashMap of cells and Coords
   */
  HashMap<Coord, Cell> getAllCells();

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
   * Sets the cell at the given location to the given cell.
   * If the cell position is beyond the scope of the board,
   * the board will expand and fill with nulls for the new cells.
   * @param c the cell to put in
   * @param coord the location of the cell
   * @throws IllegalArgumentException if the rows or col are negative.
   */
  void setCell(Cell c, Coord coord) throws IllegalArgumentException;

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

  /**
   * Changes the width of spreadsheet.
   * @param width width to be changed to
   */
  void setWidth(int width);

  /**
   * Changes the height of the spreadsheet.
   * @param height height to be changed to.
   */
  void setHeight(int height);

  /**
   * Removes cell from the grid of cells. Will do nothing if
   * the cell isn't already instantiated.
   * @param c coordinate to be removed.
   */
  void removeCell(Coord c);

  /**
   * Set the height of a row of a cell in the spreadsheet.
   * Allows for saving of row heights. If a row isn't set specifically,
   * the row will be set to a default size.
   * @param row number to bet set.
   * @param height of the row.
   */
  void setRowHeight(int row, int height);

  /**
   * Gets the height of a row.
   * @param row desired row.
   * @return height of the desired row. If the row is not
   * in the hashmap, but is in the spreadsheet it will return 0.
   * If the row is not in the hashmap, but also not in the spreadsheet,
   * it will return -1.
   */
  int getRowHeight(int row);

}
