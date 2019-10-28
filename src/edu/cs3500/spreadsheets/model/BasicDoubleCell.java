package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Class to represent cells in a spreadsheet which contain doubles.
 */
public class BasicDoubleCell extends AbstractBasicCell<Double> {

  public BasicDoubleCell(Double val) {
    super(val);
  }

  public BasicDoubleCell(Formula<Double> form) {
    super(form);
  }

  public BasicDoubleCell(Double val, List<Cell> deps) {
    super(val, deps);
  }

  public BasicDoubleCell(Formula<Double> form, List<Cell> deps) {
    super(form, deps);
  }

  @Override
  public boolean isNumericValue() {
    return true;
  }

  public Double getNumericValue(double base) {
    return this.getValue();
  }


  @Override
  public boolean equals(Object other) {
    if (!(other instanceof BasicDoubleCell)) {
      return false;
    }
    else {
      BasicDoubleCell otherBC = (BasicDoubleCell) other;
      return (this.getValue() == otherBC.getValue()
          && this.getFormula() == otherBC.getFormula());
    }
  }


}
