package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import java.util.List;

/**
 * Interface for cells on spreadsheet.
 * @param <T> The data type of the cell.
 */
public interface Cell<T> {

  /**
   * Tells whether the value of the cell is numerical or not.
   * @return true if it is numerical, false if not.
   */
  boolean isNumericValue();

  /**
   * Gets a list of cells that reference this cell.
   * @return a list of cells that reference this cell
   */
  List<Coord> getDependencies();

  /**
   * Sets this cell as a dependent of the given cell.
   * @param c the cell that depends on this cell
   */
  void setDependent(Coord c);

  /**
   * Gets the value of the cell.
   * @return value of the cell.
   */
  T getValue();


  /**
   * gets the formula of this cell. Returns null if this cell is a value cell.
   * @return formula of the cell or null if a value cell
   */
  Formula<T> getFormula();

  /**
   * Calculates the numeric value of the cell.
   * @return the numeric value of the cell (defaults to given value)
   */
  Double getNumericValue(double base);

  CellValue toProviderCell();

}
