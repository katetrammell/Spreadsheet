package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Cell class in a spreadsheet that contian booleans.
 */
public class BasicBooleanCell extends AbstractBasicCell<Boolean> {

  public BasicBooleanCell(Boolean val) {
    super(val);
  }

  public BasicBooleanCell(Formula<Boolean> form) {
    super(form);
  }

  public BasicBooleanCell(Boolean val, List<Cell> deps) {
    super(val, deps);
  }

  public BasicBooleanCell(Formula<Boolean> form, List<Cell> deps) {
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
    }
    else {
      BasicBooleanCell otherBC = (BasicBooleanCell) other;
      return (this.getValue() == otherBC.getValue()
      && this.getFormula() == otherBC.getFormula());
    }
  }

  //TODO: Double check this is ok
  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
