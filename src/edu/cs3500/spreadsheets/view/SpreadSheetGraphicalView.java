package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import java.awt.event.ActionListener;

/**
 * An interface for a graphical view of a spreadsheet.
 */
public interface SpreadSheetGraphicalView extends SpreadsheetView {

  /**
   * Adds a column to the view.
   * @param colNum column index where the column is to be added.
   */
  void addCol(int colNum);

  /**
   * Updates a cell at a coordinate with the new provided cell.
   * @param c coordinate of the cell to be updated.
   * @param cell cell to replace the previous cell.
   */
  void updateCell(Coord c, Cell cell);

  /**
   * Gets the text from the textbox at the top of the spreadsheet.
   * @return the text from the box as a String.
   */
  String getTextBox();

  /**
   * Sets the value of the textBox at the top of the spreadsheet.
   * @param s string to be set.
   */
  void setTextBox(String s);

  /**
   * Adds an action listener to an object.
   * @param listener listener to be added.
   */
  void setListener(ActionListener listener);

  /**
   * Retrives the selected cell on spreadsheet.
   * @return coordinate of the selected cell.
   */
  Coord getSelectedCell();

  /**
   * Gets the text from the saveBox in the gui.
   * @return the text from the saveBox.
   */
  String getSaveBox();

  /**
   * Set the text of save text field.
   * @param s String to be changed to.
   */
  void setSaveBox(String s);

  /**
   * Changes the height of a row of cell in the spreadsheet.
   * @param row the number of the desired row to be edited.
   * @param height the new height to be set.
   */
  void setRowHeight(int row, int height);

  /**
   * Get the height of a desired row.
   * @param row row to get the height of.
   * @return height of the desired row.
   */
  int getRowHeight(int row);

  /**
   * Sets the width a specific column. Columns will be a default size
   * if not explicitly set.
   * @param col desired column index number.
   * @param width desired width for the column.
   */
  void setColWidth(int col, int width);

  /**
   * Get the width of a specific column.
   * @param col desired column.
   * @return width of desired column.
   */
  int getColWidth(int col);

}