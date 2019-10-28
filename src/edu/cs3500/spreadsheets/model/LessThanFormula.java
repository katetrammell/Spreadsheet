package edu.cs3500.spreadsheets.model;

/**
 * Class that represent lessThanFormula.
 */
public class LessThanFormula implements Formula<Boolean> {

  private Cell cell1;
  private Cell cell2;

  /**
   * Constructor for LessThanFormula. Takes in the cells to be compared.
   * @param cell1 first cell.
   * @param cell2 second cell.
   */
  public LessThanFormula(Cell cell1, Cell cell2) {
    if (!cell1.isNumericValue() | !cell2.isNumericValue()) {
      throw new IllegalArgumentException();
    }
    this.cell1 = cell1;
    this.cell2 = cell2;
  }

  public LessThanFormula() {
    this.cell1 = null;
    this.cell2 = null;
  }

  @Override
  public Boolean evaluate() {
    // casted because at this point we know it is a double
    return (double) cell1.getValue() < (double) cell2.getValue();
  }

  @Override
  public void addFormula(Formula form) {
    throw new IllegalCallerException("LessThanFormula doesn't contain formula");
  }

  @Override
  public void addCell(Cell cell) {
    throw new IllegalCallerException("LessThanFormula has to be intialized with cells");
  }

}
