package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import java.util.List;

/**
 * Class to represent double cells in a spreadsheet.
 */
public class BasicDoubleCell extends AbstractBasicCell<Double> {

  public BasicDoubleCell(Double val) {
    super(val);
  }

  public BasicDoubleCell(Formula<Double> form) {
    super(form);
  }

  public BasicDoubleCell(Double val, List<Coord> deps) {
    super(val, deps);
  }

  public BasicDoubleCell(Formula<Double> form, List<Coord> deps) {
    super(form, deps);
  }

  @Override
  public boolean isNumericValue() {
    return true;
  }

  @Override
  public Double getNumericValue(double base) {
    if (this.getFormula() == null && this.getValue() == null) {
      return base;
    } else if (this.getValue() == null) {
      return this.getFormula().evaluate();
    } else {
      return this.getValue();
    }
  }

  @Override
  public CellValue toProviderCell() {
    if (this.getFormula() == null) {
      return new ProviderNumberCell(this.getValue());
    } else if (this.getFormula().evaluate() != null) {
      return new ProviderNumberCell(this.getFormula().evaluate());
    } else {
      return new ProviderEmptyCell();
    }
  }


  @Override
  public boolean equals(Object other) {
    if (!(other instanceof BasicDoubleCell)) {
      return false;
    } else {
      BasicDoubleCell otherBC = (BasicDoubleCell) other;
      if (this.getFormula() != null) {
        return this.getFormula().equals(((BasicDoubleCell) other).getFormula());
      } else if (this.getValue() != null) {
        return this.getValue().equals(((BasicDoubleCell) other).getValue());
      } else {
        return true;
      }
    }
  }

  @Override
  public int hashCode()  {
    return super.hashCode();
  }

  @Override
  public String toString() {
    String ans = "";
    if (this.getValue() != null) {
      ans += String.format("%f", this.getValue());
    }
    if (this.getFormula() != null) {
      ans += this.getFormula().toString();
    }
    return ans;
  }
}
