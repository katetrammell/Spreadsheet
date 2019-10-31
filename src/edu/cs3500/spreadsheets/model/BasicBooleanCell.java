package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Class to represent Boolean cells in spreadsheet.
 */
public class BasicBooleanCell extends AbstractBasicCell<Boolean> {

  //test
  public BasicBooleanCell(Boolean val) {
    super(val);
  }

  public BasicBooleanCell(Formula<Boolean> form) {
    super(form);
  }

  public BasicBooleanCell(Boolean val, List<Coord> deps) {
    super(val, deps);
  }

  public BasicBooleanCell(Formula<Boolean> form, List<Coord> deps) {
    super(form, deps);
  }

  @Override
  public boolean isNumericValue() {
    return false;
  }

  @Override
  public Double getNumericValue(double base) {
    return base;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof BasicBooleanCell)) {
      return false;
    } else {
      BasicBooleanCell otherBC = (BasicBooleanCell) other;
      return (this.getValue() == otherBC.getValue()
          && this.getFormula() == otherBC.getFormula());
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
