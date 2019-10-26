/**
 * Class that represent lessThanFormula.
 */
public class LessThanFormula implements Formula<Boolean> {

  Cell cell1;
  Cell cell2;

  LessThanFormula(Cell cell1, Cell cell2) {
    if (!cell1.isNumericValue() | !cell2.isNumericValue()) {
      throw new IllegalArgumentException();
    }
    this.cell1 = cell1;
    this.cell2 = cell2;
  }

  @Override
  public Boolean evaluate() {
    // casted because at this point we know it is a double
    return (double) cell1.getValue() < (double) cell2.getValue();
  }

}
