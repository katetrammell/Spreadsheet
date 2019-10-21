import java.util.List;

public interface Cell<T> {

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
   * sets the value of this field to the given value
   * @param value the value to be set
   */
  void setValue(T value);

  /**
   * Sets the formula of the sell to the given formula
   * @param form the formula to be set
   * @throws IllegalArgumentException if the given formula contains a circular reference
   */
  void setFormula(Formula<T> form);

  /**
   * gets the formula of this cell. Returns null if this cell is a value cell.
   * @return formula of the cell or null if a value cell
   */
  Formula getFormula();


}
