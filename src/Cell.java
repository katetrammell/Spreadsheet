import java.util.List;

public interface Cell<T> {

  boolean isNumericValue();

  /**
   * Gets a list of cells that reference this cell.
   * @return a list of cells that reference this cell
   */
  List<Cell> getDependencies();

  /**
   * Sets this cell as a dependent of the given cell.
   * @param c the cell that depends on this cell
   */
  void setDependent(Cell c);

  /**
   * Gets the value of the cell.
   * @return value of the cell.
   */
  T getValue();


  /**
   * gets the formula of this cell. Returns null if this cell is a value cell.
   * @return formula of the cell or null if a value cell
   */
  Formula getFormula();

  /**
   * Calculates the numeric value of the cell.
   * @return the numeric value of the cell (defaults to given value)
   */
  Double getNumericValue(double base);

}
